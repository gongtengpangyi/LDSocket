package frz.ld.socket.server.netty;

import frz.ld.socket.server.TeddyBear;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 基于Netty的TeddyBear类
 * 
 * @author GongTengPangYi
 *
 * @param <D>
 *            对应Landy类
 */
public abstract class NettyTeddyBear<D extends NettyLandy> extends TeddyBear<D> {

	/**
	 * netty的参数
	 */
	protected ChannelHandlerContext ctx;
	protected Channel channel;

	/**
	 * 新的构造器
	 * 
	 * @param landy
	 *            同父类
	 * @param ctx
	 *            netty的类
	 */
	protected NettyTeddyBear(D landy, ChannelHandlerContext ctx) {
		super(landy);
		this.ctx = ctx;
		this.channel = ctx.channel();
	}

	@Override
	public void sendData(byte[] bytes) {
		ctx.writeAndFlush(Unpooled.copiedBuffer(bytes));
	}

	@Override
	public void sendMessage(String message) {
		sendData(message.getBytes());
	}

	@Override
	protected boolean initInternal() {
		return true;
	}

	@Override
	protected boolean startInternal() {
		return true;
	}

	@Override
	protected boolean runInternal() {
		return false;
	}

	@Override
	protected boolean closeInternal() {
		return true;
	}

	@Override
	protected String threadId() {
		return channel.remoteAddress().toString();
	}

	/**
	 * 读取信息，具体的实现由子类来完成
	 * 
	 * @param in
	 */
	protected abstract void readByteBuf(ByteBuf in);

}
