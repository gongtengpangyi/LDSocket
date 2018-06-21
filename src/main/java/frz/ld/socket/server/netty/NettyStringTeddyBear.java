package frz.ld.socket.server.netty;

import frz.ld.helper.LdReflectException;
import frz.ld.socket.server.core.IStringStreamLandy;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * 字符流的TeddyBear
 * 
 * @author GongTengPangYi
 *
 */
public class NettyStringTeddyBear extends NettyTeddyBear<NettyStringLandy> implements IStringStreamLandy {

	protected String charset;
	
	protected NettyStringTeddyBear(NettyStringLandy landy, ChannelHandlerContext ctx) {
		super(landy, ctx);
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
	protected void readByteBuf(ByteBuf in) {
		StringBuilder strBuilder = new StringBuilder();
		while(in.isReadable()){  
			strBuilder.append((char)in.readByte());  
        } 
		try {
			receiveActionExecutor.parse(this, strBuilder.toString());
		} catch (LdReflectException e) {
			e.printStackTrace();
		}
	}

}
