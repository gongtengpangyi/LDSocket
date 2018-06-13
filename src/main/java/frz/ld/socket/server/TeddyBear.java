package frz.ld.socket.server;

import frz.ld.socket.RunableLifeCycle;

/**
 * 与客户端的连接类
 * 
 * 代表的是每一个与客户端的连接
 * 
 * @author GongTengPangYi
 *
 * @param <D> 对应的Landy类
 */
public abstract class TeddyBear<D extends Landy> extends RunableLifeCycle {

	/**
	 * 对应的Landy类
	 */
	protected D landy;
	
	/**
	 * 用于标识的key
	 */
	protected Object key;

	public TeddyBear(D landy) {
		super();
		this.landy = landy;
	}

	public Object getKey() {
		return key;
	}
	
	//TODO:
	
}
