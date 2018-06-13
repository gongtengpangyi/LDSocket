package frz.ld.socket;

/**
 * 线程类的生命周期
 * 
 * 周期基本如下：initInternal ----》 isInit = true ----》 startInternal ----》
 * 循环runInternal操作直到为负 ----》closeInternal
 * 
 * @author GongTengPangYi
 *
 */
public abstract class RunableLifeCycle implements LifeCycle, Runnable {

	/**
	 * 线程
	 */
	Thread threadSelf;

	/**
	 * 是否初始化
	 */
	boolean isInit = false;

	/**
	 * 启停标志
	 */
	protected boolean flag = false;

	@Override
	public boolean close() {
		isInit = false;
		flag = false;
		return closeInternal();
	}

	@Override
	public boolean start() {
		if (!isInit) {
			init();
		}
		flag = true;
		if (startInternal()) {
			threadSelf.start();
			return true;
		}
		return false;
	}

	@Override
	public boolean init() {
		threadSelf = new Thread(this);
		if (initInternal()) {
			isInit = true;
		}
		return isInit;
	}

	@Override
	public void run() {
		while (flag && runInternal()) {
			if (LifeCycle.DO_LOG) {
				System.out.println(threadId() + " is running");
			}
		}
	}

	/**
	 * 线程真实执行服务需要启动的内容
	 * 
	 * @return 是否正确启动需要启动的内容
	 */
	protected abstract boolean startInternal();

	/**
	 * 线程关闭时候需要关闭的内容
	 * 
	 * @return 是否正确关闭了需要关闭的内容
	 */
	protected abstract boolean closeInternal();

	/**
	 * 线程初始化时候需要执行的操作
	 * 
	 * @return 是否正确执行了初始化时候需要执行的操作
	 */
	protected abstract boolean initInternal();

	/**
	 * 线程运行过程中执行的操作
	 * 
	 * @return 是否继续运行
	 */
	protected abstract boolean runInternal();

	/**
	 * 获取线程的id
	 * 
	 * @return
	 */
	protected abstract String threadId();
}
