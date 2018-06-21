package frz.ld.socket.server.core;

/**
 * 基于字符流的Landy
 * 
 * @author GongTengPangYi
 *
 */
public interface IStringStreamLandy {

	/**
	 * 基本编码格式
	 */
	public static final String CHARSET_UTF8 = "utf8";
	public static final String CHARSET_GBK = "gbk";

	// TODO: 后面的以后再加

	/**
	 * 设置编码格式
	 * 
	 * @param charset
	 *            编码格式
	 */
	public void setCharset(String charset);

	/**
	 * 获取编码格式
	 * 
	 * @return 编码格式
	 */
	public String getCharset();

}
