package frz.ld.socket.server.core;

import java.lang.reflect.Method;

import frz.ld.helper.LdReflectException;
import frz.ld.helper.LdReflectHelper;
import frz.ld.socket.server.pack.Send;

/**
 * 数据发送工具类
 * @author GongTengPangYi
 *
 */
public class SendHelper {

	/**
	 * 从数据包中获取字符串
	 * @param pack
	 * @return
	 */
	public static String getString(Object pack) {
		Class<?> packClass = pack.getClass();
		Method[] methods = LdReflectHelper.findMethods(packClass, 0);
		try {
			for (Method method : methods) {
				if (method.getAnnotation(Send.class) != null) {
					return (String) LdReflectHelper.invokeMethod(pack, method);
				}
			}
		} catch (LdReflectException e) {
			e.printStackTrace();
		}
		return "thanks";
	}
	
	/**
	 * 从数据包中获取字节流
	 * @param pack
	 * @return
	 */
	public static byte[] getBytes(Object pack) {
		Class<?> packClass = pack.getClass();
		Method[] methods = LdReflectHelper.findMethods(packClass, 0);
		try {
			for (Method method : methods) {
				if (method.getAnnotation(Send.class) != null) {
					return (byte[]) LdReflectHelper.invokeMethod(pack, method);
				}
			}
		} catch (LdReflectException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 从数据包中获取需要发送的数据
	 * @param pack
	 * @return
	 */
	public static Object getSend(Object pack) {
		Class<?> packClass = pack.getClass();
		Method[] methods = LdReflectHelper.findMethods(packClass, 0);
		try {
			for (Method method : methods) {
				if (method.getAnnotation(Send.class) != null) {
					return LdReflectHelper.invokeMethod(pack, method);
				}
			}
		} catch (LdReflectException e) {
			e.printStackTrace();
		}
		return null;
	}
}
