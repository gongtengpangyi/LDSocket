package frz.ld.socket.server.parser;

/**
 * 基于字符的基本数据包解析器
 * 
 * @author GongTengPangYi
 *
 */
public abstract class BaseStringPackParser extends BasePackParser {

	public BaseStringPackParser(String packageName) {
		super(packageName);
	}

	@Override
	public Class<?> parseClass(byte[] data) {
		return null;
	}

	@Override
	public Object parseObject(Class<?> clazz, byte[] data) {
		return null;
	}

}
