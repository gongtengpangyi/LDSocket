package frz.ld.socket.server.netty;

import frz.ld.socket.server.LdPackParser;
import frz.ld.socket.server.core.IStringStreamLandy;
import io.netty.channel.ChannelHandlerContext;

/**
 * 字符流的nettyLandy
 * 
 * @author GongTengPangYi
 *
 */
public class NettyStringLandy extends NettyLandy implements IStringStreamLandy {

	/**
	 * 编码格式
	 */
	protected String charset;
	
	public NettyStringLandy(int port, LdPackParser packParser) {
		super(port, packParser);
	}

	public NettyStringLandy(int port, long connetTimeout, LdPackParser packParser) {
		super(port, connetTimeout, packParser);
	}

	@Override
	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public String getCharset() {
		return charset;
	}

	@Override
	protected NettyTeddyBear<?> createNewTeddyBear(ChannelHandlerContext ctx) {
		NettyStringTeddyBear teddyBear = new NettyStringTeddyBear(this, ctx);
		teddyBear.setCharset(charset);
		return teddyBear;
	}

}
