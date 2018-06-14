package frz.ld.helper;

/**
 * 反射过程异常
 * @author GongTengPangYi
 *
 */
public class LdReflectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6435031881728482256L;

	public LdReflectException() {
		super();
	}

	public LdReflectException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LdReflectException(String message, Throwable cause) {
		super(message, cause);
	}

	public LdReflectException(String message) {
		super(message);
	}

	public LdReflectException(Throwable cause) {
		super(cause);
	}

}
