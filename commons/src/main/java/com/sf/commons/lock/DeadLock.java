package com.sf.commons.lock;
/**
 * 描述：死锁的简单例子
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年3月11日 下午2:55:01	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class DeadLock extends Thread{
	Object lock1,lock2;
	
	public DeadLock(Object lock1, Object lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	@Override
	public void run() {
		synchronized(lock1){
			try {
				Thread.sleep(1000);
				System.out.println("tryacciqure lock:"+lock2);
				synchronized (lock2) {
					System.out.println("get lock:"+lock2);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Object lock1=new Object();
		Object lock2=new Object();
		new DeadLock(lock1, lock2).start();
		new DeadLock(lock2, lock1).start();
	}

}
