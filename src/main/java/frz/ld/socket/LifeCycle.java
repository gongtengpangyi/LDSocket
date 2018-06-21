package frz.ld.socket;

/**
 * 代表了整个生命周期，是大部分组件类的共同接口
 * 
 * @author GongTengPangYi
 *
 */
public interface LifeCycle {
	
	public static boolean DO_LOG = true;

	/**
	 * 关闭
	 */
	public boolean close();

	/**
	 * 开始
	 */
	public boolean start();
	
	/**
	 * 初始化
	 */
	public boolean init();
	
}
