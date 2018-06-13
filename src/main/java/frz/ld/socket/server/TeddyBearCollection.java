package frz.ld.socket.server;

import java.util.HashMap;

/**
 * TeddyBear的一个集合
 * 
 * 与TeddyBear是一对多的关系，TeddyBear只能通过此集合来查询到其他的TeddyBear
 * 
 * 与Landy是多对多的关系， 一个集合可以用在不同的Landy对象中（用于不同协议不同服务下的会话的相互传讯）
 * 一个Landy对象也可以有多个集合（用于同协议同服务下的分群组通话）
 * 
 * @author GongTengPangYi
 *
 */
public class TeddyBearCollection extends HashMap<Object, TeddyBear<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7565452564185786659L;

	
	//TODO:
}
