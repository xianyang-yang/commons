package com.sf.commons.thread;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年2月6日 下午12:25:26	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class ThreadTest {
	public static void main(String[] args) throws InterruptedException {
		SuspendableTread t = new SuspendableTread();
		t.start();
		Thread.sleep(2000);
		t.setSuspend();
		Thread.sleep(2000);
		t.resumeIt();
		Thread.sleep(12000);
	}

}

class SuspendableTread extends Thread {
	boolean suspend = false;
	Object lock = new Object();

	@Override
	public void run() {
		while (true) {
			if (suspend) {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("runing....");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void setSuspend() {
		suspend = true;
	}

	public void resumeIt() {
		synchronized (lock) {
			lock.notify();
			suspend=false;
		}
	}

}