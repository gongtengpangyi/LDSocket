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
 * @author GongTengPangYi
 *
 */
public abstract class Landy extends RunableLifeCycle {
	
	/**
	 * 默认的连接时延
	 */
	protected static long DEFAULT_CONNECT_TIMEOUT = 50000; 

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
	
	protected List<TeddyBearCollection> teddyBearCollections;

	/**
	 * 构造器
	 * @param ip 开启服务的主机IP
	 * @param port 开启服务的端口
	 * @param connetTimeout 服务等待时长
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
	 * @param ip 开启服务的主机IP
	 * @param port 开启服务的端口
	 */
	public Landy(String ip, int port) {
		this(ip, port, DEFAULT_CONNECT_TIMEOUT);
	}
	
	public void addTeddyBearCollection(TeddyBearCollection teddyBearCollection) {
		if (this.teddyBearCollections != null) {
			this.teddyBearCollections.add(teddyBearCollection);
		}
	}
	
	public List<TeddyBearCollection> getTeddyBearCollections() {
		return teddyBearCollections;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setConnetTimeout(long connetTimeout) {
		this.connetTimeout = connetTimeout;
	}

	public void setReadTimeOut(long readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	
	
	
}
