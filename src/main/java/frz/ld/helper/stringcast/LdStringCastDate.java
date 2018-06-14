package frz.ld.helper.stringcast;

import frz.ld.helper.LdReflectHelper.StringCast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LdStringCastDate implements StringCast {
	/**
	 * 默认的时间格式
	 */
	public static final String defaultDateFormatStr = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 单例模式实现
	 */
	private static LdStringCastDate instance;

	public static LdStringCastDate newInstance(String formatStr) {
		if (instance == null) {
			synchronized (LdStringCastDate.class) {
				instance = new LdStringCastDate(formatStr);
			}
		} else {
			instance.setFormat(formatStr);
		}
		return instance;
	}

	private SimpleDateFormat format;

	protected LdStringCastDate(String formatStr) {
		super();
		format = new SimpleDateFormat(formatStr);
	}

	public SimpleDateFormat getFormat() {
		return format;
	}

	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}

	/**
	 * 重新赋值时间格式
	 * 
	 * @param formatStr
	 *            时间格式
	 */
	public void setFormat(String formatStr) {
		this.format = new SimpleDateFormat(formatStr);
	}

	@Override
	public Object cast(String value) {
		try {
			return format.parse(value);
		} catch (ParseException e) {
			System.out.println("\nStringCastDate.cast强制转换出错\n");
			e.printStackTrace();
		}
		return null;
	}

}
