package frz.ld.socket.server.netty;

import java.util.HashMap;
import java.util.Map;

import frz.ld.socket.server.Landy;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * 基于Netty的Landy类基本实现
 * 
 * 必须拥有netty的jar包
 * 
 * Maven-pom.xml：
 * 
 * <dependency>
 * 
 * <groupId>io.netty</groupId>
 *
 * <artifactId>netty-all</artifactId>
 *
 * <version>4.1.25.Final</version>
 *
 * </dependency>
 * 
 * @author GongTengPangYi
 *
 */
public abstract class NettyLandy extends Landy {

	/**
	 * 默认最大连接数
	 */
	protected static int DEFAULT_MAX_CONN = 128;

	/**
	 * 默认连接用线程数
	 */
	protected static int DEFAULT_CONN_THREADS = 1;

	/**
	 * Netty的参数
	 */
	protected ServerBootstrap server;
	protected EventLoopGroup bossGroup;
	protected EventLoopGroup workerGroup;

	/**
	 * 连接用线程数
	 */
	protected int connThreads;

	/**
	 * 根据客户端地址管理的NettyTeddyBear的hash表，注意：
	 * 这里只是为了分配请求所用，与注册和客户端相互通讯所用的TeddyBearCollection不同
	 */
	protected Map<String, NettyTeddyBear<?>> nettyLandyMapByAddress;

	public NettyLandy(String ip, int port) {
		super(ip, port);
	}

	public NettyLandy(String ip, int port, long connetTimeout) {
		super(ip, port, connetTimeout);
	}

	/**
	 * 设置连接用线程数，详见netty官方对NioEventLoopGroup类的构造器参数解释，据说越少越好，默认为1
	 * 
	 * @param connThreads
	 */
	public void setConnThreads(int connThreads) {
		this.connThreads = connThreads;
	}

	@Override
	protected boolean initInternal() {

		this.nettyLandyMapByAddress = new HashMap<>();

		if (maxConnectCount <= 0) {
			maxConnectCount = DEFAULT_MAX_CONN;
		}
		if (connThreads <= 0) {
			connThreads = DEFAULT_CONN_THREADS;
		}

		bossGroup = new NioEventLoopGroup(connThreads);
		workerGroup = new NioEventLoopGroup();

		server.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					public void initChannel(SocketChannel ch) throws Exception {

						ch.pipeline().addLast(new ServerHandler());
					};
				}).option(ChannelOption.SO_BACKLOG, maxConnectCount).childOption(ChannelOption.SO_KEEPALIVE, true);

		return true;
	}

	@Override
	protected boolean startInternal() {
		ChannelFuture f;

		try {
			f = server.bind(port).sync();
			f.channel().closeFuture().sync();

			ldLog("server in port:" + port + " is start.......");

			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}

	@Override
	protected boolean runInternal() {
		return false;
	}

	@Override
	protected boolean closeInternal() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		return true;
	}

	@Override
	protected String threadId() {
		return "000000000000000000000000thread netty。。。。。。。。。。。。。";
	}

	/**
	 * 信息接收。。。。详见netty教程
	 * 
	 * @author GongTengPangYi
	 *
	 */
	protected class ServerHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			Channel channel = ctx.channel();
			String address = channel.remoteAddress().toString();
			ldLog("收到客户端 - " + address + " 的信息\n");

			ByteBuf in = (ByteBuf) msg;
			try {
				getTeddyBear(address, ctx).readByteBuf(in);
			} finally {
				ReferenceCountUtil.release(msg);
			}
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}
	}

	/**
	 * 获取该地址对应的TeddyBear
	 * 
	 * @param address
	 *            地址
	 * @param ctx
	 *            ....
	 * @return teddyBear
	 */
	protected NettyTeddyBear<?> getTeddyBear(String address, ChannelHandlerContext ctx) {
		if (this.nettyLandyMapByAddress == null) {
			this.nettyLandyMapByAddress = new HashMap<>();
		}
		NettyTeddyBear<?> teddyBear = this.nettyLandyMapByAddress.get(address);
		if (teddyBear == null) {
			teddyBear = createNewTeddyBear(ctx);
			nettyLandyMapByAddress.put(address, teddyBear);
		}
		return teddyBear;
	}

	/**
	 * 创建全新的teddyBear
	 * 
	 * @param ctx
	 *            ...
	 * @return teddyBear
	 */
	protected abstract NettyTeddyBear<?> createNewTeddyBear(ChannelHandlerContext ctx);

}
