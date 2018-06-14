package frz.ld.socket.server;

import java.util.ArrayList;
import java.util.List;

import frz.ld.socket.RunableLifeCycle;

/**
 * Socket服务开启类
 * 
 * 用于开启ServerSocket的服务，并分配连接会话包的管理
 * 
 * 长连接的核心端
 * 
 * 服务器通过启动一个Landy对象来启动一个协议的server， 当客户端有新的连接达到这个landy对象时，会自动为其分配一个TeddyBear对象。
 * 然后根据通讯数据内容，由开发人员设定分配规则，将不同的数据包分配到不同的数据包对象中。
 * 
 * TeddyBear可以通过注册到一个Collection中，来达到让其他TeddyBear通过Landy向自己发送消息的功能
 * 每个TeddyBear只能注册到一个Collection中，由唯一的key标识。
 * 
 * Collection和Landy是多对多关系，即TeddyBear所属什么互通小组和其所连接的server的ip、port无关，只与注册有关
 * 但也可以由开发人员自行规定其为一对一或一对多，即自定义与互通小组与server相关
 * 
 * @author GongTengPangYi
 *
 */
public abstract class Landy extends RunableLifeCycle {

	/**
	 * 默认的连接时延
	 */
	protected static long DEFAULT_CONNECT_TIMEOUT = 50000;

	/**
	 * 重新设置默认的连接超时
	 * 
	 * @param dEFAULT__TIMEOUT
	 */
	public static final void setDEFAULT__TIMEOUT(long dEFAULT__TIMEOUT) {
		DEFAULT_CONNECT_TIMEOUT = dEFAULT__TIMEOUT;
	}

	/**
	 * 开启服务的主机IP
	 */
	protected String ip;

	/**
	 * 开启服务的端口
	 */
	protected int port;

	/**
	 * 服务等待时长
	 */
	protected long connetTimeout;

	/**
	 * 读取消息的等待时长
	 */
	protected long readTimeOut;

	/**
	 * 所拥有的互通小组的列表
	 */
	protected List<TeddyBearCollection<?>> teddyBearCollections;

	/**
	 * 构造器
	 * 
	 * @param ip
	 *            开启服务的主机IP
	 * @param port
	 *            开启服务的端口
	 * @param connetTimeout
	 *            服务等待时长
	 */
	public Landy(String ip, int port, long connetTimeout) {
		super();
		this.ip = ip;
		this.port = port;
		this.connetTimeout = connetTimeout;
		teddyBearCollections = new ArrayList<>();
	}

	/**
	 * 构造器
	 * 
	 * @param ip
	 *            开启服务的主机IP
	 * @param port
	 *            开启服务的端口
	 */
	public Landy(String ip, int port) {
		this(ip, port, DEFAULT_CONNECT_TIMEOUT);
	}

	/**
	 * 添加一个互通小组
	 * 
	 * @param teddyBearCollection
	 *            互通小组
	 */
	public void addTeddyBearCollection(TeddyBearCollection<?> teddyBearCollection) {
		if (this.teddyBearCollections != null && teddyBearCollection != null) {
			this.teddyBearCollections.add(teddyBearCollection);
		}
	}

	/**
	 * 获取互通小组列表
	 * 
	 * @return
	 */
	public List<TeddyBearCollection<?>> getTeddyBearCollections() {
		return teddyBearCollections;
	}

	/**
	 * 获取一个互通小组
	 * 
	 * @param index
	 *            互通小组在此landy对象中的序号
	 * @return 互通小组
	 */
	public TeddyBearCollection<?> getTeddyBearCollection(int index) {
		if (teddyBearCollections != null && teddyBearCollections.size() > index) {
			return teddyBearCollections.get(index);
		}
		return null;
	}

	/**
	 * 设置服务开启的ip
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 设置服务开启的端口
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 设置服务开启的连接等待时间
	 * 
	 * @param connetTimeout
	 */
	public void setConnetTimeout(long connetTimeout) {
		this.connetTimeout = connetTimeout;
	}

	/**
	 * 设置服务开启的数据读取等待时间
	 * 
	 * @param readTimeOut
	 */
	public void setReadTimeOut(long readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	/**
	 * 向客户端发送一条字节流数据
	 * 
	 * @param collectionIndex
	 *            分组标号
	 * @param key
	 *            客户端key
	 * @param bytes
	 *            字节流数据
	 */
	public void sendData(int collectionIndex, Object key, byte[] bytes) {
		TeddyBearCollection<?> teddyBearCollection = this.getTeddyBearCollection(collectionIndex);
		if (teddyBearCollection != null) {
			teddyBearCollection.sendData(key, bytes);
		}
	}

	/**
	 * 向客户端发送一条字符串数据
	 * 
	 * @param collectionIndex
	 *            分组标号
	 * @param key
	 *            客户端对应TeddyBear的key
	 * @param message
	 *            字符串数据
	 */
	public void sendMessage(int collectionIndex, Object key, String message) {
		TeddyBearCollection<?> teddyBearCollection = this.getTeddyBearCollection(collectionIndex);
		if (teddyBearCollection != null) {
			teddyBearCollection.sendMessage(key, message);
		}
	}

	/**
	 * 向客户端发送一个数据包
	 * 
	 * @param collectionIndex
	 *            分组标号
	 * @param key
	 *            客户端对应TeddyBear的key
	 * @param pack
	 *            数据包
	 */
	public void sendPack(int collectionIndex, Object key, Object pack) {
		TeddyBearCollection<?> teddyBearCollection = this.getTeddyBearCollection(collectionIndex);
		if (teddyBearCollection != null) {
			teddyBearCollection.sendPack(key, pack);
		}
	}

}
