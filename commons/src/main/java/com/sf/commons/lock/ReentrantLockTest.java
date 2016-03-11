package com.sf.commons.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年3月11日 下午1:53:26	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class ReentrantLockTest {
	Object o = new Object();

	public static void main(String[] args) {
		// test1();
		// test2();
		new ReentrantLockTest().test3();

	}

	void test3() {
		final ReentrantLock lock = new ReentrantLock();
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			final int num = i;
			service.submit(new Runnable() {
				@Override
				public void run() {
					try {
						if (lock.tryLock(5, TimeUnit.SECONDS)) {
							print(num);
							Thread.sleep(10000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					print("end:" + num);
				}
			});
		}
	}

	@SuppressWarnings("unused")
	private static void test2() {
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			service.submit(new Runnable() {
				@Override
				public void run() {
					new ReentrantLockTest().test(3);
				}
			});
		}
	}

	public void test(int i) {
		if (i > 0) {
			synchronized (o) {
				print(i);
				test(i - 1);
			}
		}
	}

	private void print(Object i) {
		System.out.println(Thread.currentThread().getName() + ",enter " + i);
	}
}
