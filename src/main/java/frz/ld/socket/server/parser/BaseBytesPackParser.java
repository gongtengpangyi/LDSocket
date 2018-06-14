package frz.ld.socket.server.parser;

/**
 * 基于字节流的基本数据包解析器
 * 
 * @author GongTengPangYi
 *
 */
public abstract class BaseBytesPackParser extends BasePackParser {

	public BaseBytesPackParser(String packageName) {
		super(packageName);
	}

	@Override
	public Class<?> parseClass(String message) {
		return null;
	}

	@Override
	public Object parseObject(Class<?> clazz, String message) {
		return null;
	}

}
