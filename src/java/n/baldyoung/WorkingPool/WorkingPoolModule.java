package n.baldyoung.WorkingPool;



import java.util.concurrent.*;
import java.util.List;

/***
 * ！不推荐
 * 用来处理数量未知的工作集合，这是对线程池的二次包装。
 * 注意！：工作池只能给出提交的结果，并不能返回工作的结果，这意味着如果线程池运行过程中出现异常，无法通过现有调用获知。
 */
@Deprecated
public class WorkingPoolModule{
	
	private ExecutorService service;
	private String name;

	/**
	 * 创建给定的线程池服务对象和对应名称的工作池对象
	 * @param tService 线程池实现对象
	 * @param tName 命名
	 */
	public WorkingPoolModule(ExecutorService tService, String tName){
		this.service = tService;
		this.name = tName;
	}
	public WorkingPoolModule(ExecutorService tService){
		this(tService, "");
	}
	public WorkingPoolModule(String tName){
		this(Executors.newCachedThreadPool(), tName);
	}
	public WorkingPoolModule(){
		this("");
	}
	private void shutdown(){
		if(null != this.service) 
			this.service.shutdown();
		this.service = null;
	}

	/**
	 * 提交工作
	 * @param r
	 * @return
	 */
	public boolean submit(Runnable r){
		boolean result = false;
		try{
			if(null != this.service && !this.service.isShutdown()){
				this.service.submit(r);
				result = true;
			}
		}catch(Exception e){
			//System.out.println(e.getMessage());
		}
		return result;
	}

	/**
	 * 提交工作集合
	 * @param rList
	 * @return
	 */
	public int submit(List<Runnable> rList){
		int result = 0;
		for(Runnable r : rList){
			if(this.submit(r)) result++;
		}
		return result;
	}

	
	
	
}