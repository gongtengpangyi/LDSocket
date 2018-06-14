package frz.ld.socket.server;

import frz.ld.socket.RunableLifeCycle;
import frz.ld.socket.server.core.SendHelper;

/**
 * 与客户端的连接类
 * 
 * 代表的是每一个与客户端的连接，由Landy类自动分配产生
 * 
 * @author GongTengPangYi
 *
 * @param <D>
 *            对应的Landy类
 */
public abstract class TeddyBear<D extends Landy> extends RunableLifeCycle {

	/**
	 * 对应的Landy类
	 */
	protected D landy;

	/**
	 * 用于在Collection中存储标识的key，为了让其他TeddyBear找到自己
	 */
	protected Object key;

	/**
	 * 所在的Collection分组编号 从0开始 每个TeddyBear只能属于一个Landy的一个Collection。
	 */
	protected int collectionIndex;

	/**
	 * 构建器
	 * 
	 * @param landy
	 *            所属的landy
	 */
	protected TeddyBear(D landy) {
		super();
		this.landy = landy;
	}

	/**
	 * 获取当前TeddyBear的存储键名
	 * 
	 * @return 键名
	 */
	public Object getKey() {
		return key;
	}

	/**
	 * 获取当前TeddyBear所在的Collection组号
	 * 
	 * @return
	 */
	public int getCollectionIndex() {
		return collectionIndex;
	}

	/**
	 * 注册当前TeddyBear
	 * 
	 * @param collectionIndex
	 *            要求分配的组号 从0开始
	 * @param key
	 *            键名
	 * @return 是否注册成功
	 */
	public boolean register(int collectionIndex, String key) {
		if (this.key == null && this.landy != null) {
			TeddyBearCollection<?> teddyBearCollection = this.landy.getTeddyBearCollection(collectionIndex);
			if (teddyBearCollection != null) {
				teddyBearCollection.put(key, this);
				this.collectionIndex = collectionIndex;
				this.key = key;
				return true;
			}
		}
		return false;
	}

	/**
	 * 注册当前TeddyBear到编号为0的分组
	 * 
	 * @param key
	 *            键名
	 * @return 是否注册成功
	 */
	public boolean register(String key) {
		return register(0, key);
	}

	/**
	 * 向此TeddyBear连接的客户端发送一条字节流数据
	 * 
	 * @param bytes
	 *            字节流数据
	 */
	public abstract void sendData(byte[] bytes);

	/**
	 * 向客户端发送一条字节流数据
	 * 
	 * @param key
	 *            客户端对应TeddyBear的key
	 * @param bytes
	 *            字节流数据
	 */
	public void sendData(Object key, byte[] bytes) {
		if (landy != null) {
			landy.sendData(collectionIndex, key, bytes);
		}
	}

	/**
	 * 向此TeddyBear连接的客户端发送一条字符串数据
	 * 
	 * @param message
	 *            字符串数据
	 */
	public abstract void sendMessage(String message);

	/**
	 * 向客户端发送一条字符串数据
	 * 
	 * @param key
	 *            客户端对应TeddyBear的key
	 * @param message
	 *            字符串数据
	 */
	public void sendMessage(Object key, String message) {
		if (landy != null) {
			landy.sendMessage(collectionIndex, key, message);
		}
	}

	/**
	 * 向此TeddyBear连接的客户端发送一个数据包
	 * 
	 * @param pack
	 *            数据包
	 */
	public void sendPack(Object pack) {
		Object obj = SendHelper.getSend(pack);
		if (obj == null) {
			return;
		}
		if (obj instanceof String) {
			sendMessage((String) obj);
		} else {
			sendData((byte[]) obj);
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
		if (landy != null) {
			landy.sendPack(collectionIndex, key, pack);
		}
	}

}
