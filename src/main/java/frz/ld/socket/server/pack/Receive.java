package frz.ld.socket.server.pack;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解接收到的数据包
 * 
 * 这个注解有两个值，由于当一个TeddyBear类接收到一条数据的时候，
 * 需要根据内置的PackParser去解析并分配到各自的Receive注解过的数据包类 而无论如何定义parser的规则，都需要此注解有一定的标识。例如：
 * 定义一个parser解析json数据包，然后通过定义的键名来获取键值，以达到分配效果
 * 
 * @author GongTengPangYi
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Receive {

	/**
	 * 数据中用于指代对应数据包类的键名
	 * 
	 * @return
	 */
	public String objKey() default "type";

	/**
	 * 数据中用于指代对应数据包类的值
	 * 
	 * @return
	 */
	public String objValue() default "";
}
