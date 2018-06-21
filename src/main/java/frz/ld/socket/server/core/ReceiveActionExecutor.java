package frz.ld.socket.server.core;


import java.lang.reflect.Method;

import frz.ld.helper.LdReflectException;
import frz.ld.helper.LdReflectHelper;
import frz.ld.socket.server.LdPackParser;
import frz.ld.socket.server.TeddyBear;

/**
 * 接收到消息时候的操作
 * @author GongTengPangYi
 *
 */
public class ReceiveActionExecutor {

	/**
	 * 解析器
	 */
	private LdPackParser parser;

	public ReceiveActionExecutor(LdPackParser parser) {
		super();
		this.parser = parser;
	}
	
	public void parse(TeddyBear<?> teddyBear, String line) throws LdReflectException {
		Class<?> packClass = parser.parseClass(line);
		if (packClass == null) {
			return;
		}
		Object packObject = parser.parseObject(packClass, line);
		Method[] methods = LdReflectHelper.findMethods(packClass, 0);
		for (Method method : methods) {
			if (method.getAnnotation(frz.ld.socket.server.pack.ReceiveAction.class) != null) {
				LdReflectHelper.invokeMethod(packObject, method, teddyBear);
			}
		}
	}
	
	public void parse(TeddyBear<?> teddyBear, byte[] data) throws LdReflectException {
		Class<?> packClass = parser.parseClass(data);
		if (packClass == null) {
			return;
		}
		Object packObject = parser.parseObject(packClass, data);
		Method[] methods = LdReflectHelper.findMethods(packClass, 0);
		for (Method method : methods) {
			if (method.getAnnotation(frz.ld.socket.server.pack.ReceiveAction.class) != null) {
				LdReflectHelper.invokeMethod(packObject, method, teddyBear);
			}
		}
	}
}
