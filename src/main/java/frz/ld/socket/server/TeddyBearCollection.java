package frz.ld.socket.server;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * TeddyBear的一个集合，命名为互通小组
 * 
 * 与TeddyBear是一对多的关系，TeddyBear只能通过此集合来查询到其他的TeddyBear
 * 
 * 与Landy是多对多的关系， 一个集合可以用在不同的Landy对象中（用于不同协议不同服务下的会话的相互传讯）
 * 一个Landy对象也可以有多个集合（用于同协议同服务下的分群组通话）
 * 
 * 由于每个TeddyBear属于一个Collection，且Collection中每个TeddyBear需要相互通信
 * 因此每个Collection中所有的TeddyBear应当遵从同样的数据包解析模式（这个可能还不是非常灵活，但是比同一个landy采用相同解析更靠谱）
 * 
 * @author GongTengPangYi
 * 
 * @param <M>
 *            对应的Map实现类
 *
 */
public class TeddyBearCollection<M extends Map<Object, TeddyBear<?>>> implements Map<Object, TeddyBear<?>> {

	/**
	 * 实际的Map实现类（一直在思考这儿到底是内部引用还是后期去继承）
	 */
	private M realMap;

	/**
	 * 数据解析
	 */
	private LdPackParser packParser;

	/**
	 * 构造器
	 * 
	 * @param realMap
	 *            实际的map
	 * @param packParser
	 *            数据解析器
	 */
	public TeddyBearCollection(M realMap, LdPackParser packParser) {
		super();
		this.realMap = realMap;
		this.packParser = packParser;
	}

	/**
	 * 数据解析
	 * 
	 * @return
	 */
	public LdPackParser getPackParser() {
		return packParser;
	}

	/**
	 * 数据解析
	 * 
	 * @param packParser
	 */
	public void setPackParser(LdPackParser packParser) {
		this.packParser = packParser;
	}

	@Override
	public int size() {
		return realMap.size();
	}

	@Override
	public boolean isEmpty() {
		return realMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return realMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return realMap.containsKey(value);
	}

	@Override
	public TeddyBear<?> get(Object key) {
		return realMap.get(key);
	}

	@Override
	public TeddyBear<?> put(Object key, TeddyBear<?> value) {
		return realMap.put(key, value);
	}

	@Override
	public TeddyBear<?> remove(Object key) {
		return realMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends Object, ? extends TeddyBear<?>> m) {
		realMap.putAll(m);
	}

	@Override
	public void clear() {
		realMap.clear();
	}

	@Override
	public Set<Object> keySet() {
		return realMap.keySet();
	}

	@Override
	public Collection<TeddyBear<?>> values() {
		return realMap.values();
	}

	@Override
	public Set<java.util.Map.Entry<Object, TeddyBear<?>>> entrySet() {
		return realMap.entrySet();
	}

	/**
	 * 向客户端发送一条字节流数据
	 * 
	 * @param key
	 *            客户端对应TeddyBear的key
	 * @param bytes
	 *            字节流数据
	 */
	public void sendData(Object key, byte[] bytes) {
		TeddyBear<?> teddyBear = this.get(key);
		if (teddyBear != null) {
			teddyBear.sendData(bytes);
		}
	}

	/**
	 * 向客户端发送一条字符串数据
	 * 
	 * @param key
	 *            客户端对应TeddyBear的key
	 * @param message
	 *            字符串数据
	 */
	public void sendMessage(Object key, String message) {
		TeddyBear<?> teddyBear = this.get(key);
		if (teddyBear != null) {
			teddyBear.sendMessage(message);
		}
	}

	/**
	 * 向客户端发送一个数据包
	 * 
	 * @param key
	 *            客户端对应TeddyBear的key
	 * @param pack
	 *            数据包
	 */
	public void sendPack(Object key, Object pack) {
		TeddyBear<?> teddyBear = this.get(key);
		if (teddyBear != null) {
			teddyBear.sendPack(pack);
		}
	}

}
