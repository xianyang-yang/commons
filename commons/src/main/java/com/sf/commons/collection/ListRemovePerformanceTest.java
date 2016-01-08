package com.sf.commons.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述：比较Arraylist删除的两种方式的性能
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2015年11月13日 上午9:24:47		204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class ListRemovePerformanceTest {
	private static Logger logger = LoggerFactory.getLogger(ListRemovePerformanceTest.class);
	public static void main(String[] args) {
		
		int maxSize = 10000;
		int tryTimes = 50;
		for (int i = 0; i < tryTimes; i++) {
			removeByCopy(initList(maxSize));
			removeDirectly(initList(maxSize));
		}
	}

	private static void removeDirectly(List<Integer> list) {
		long start = System.currentTimeMillis();
		for(Iterator<Integer> it=list.iterator();it.hasNext();){
			if(it.next()%2==0){
				it.remove();
			}
		}
		logger.info("removeDirectly cost:{}",(System.currentTimeMillis()-start));
		logger.debug("{}",list);
		
	}
	private static List<Integer> removeByCopy(List<Integer> list) {
		long start = System.currentTimeMillis();
		List<Integer> result = new ArrayList<Integer>(list.size());
		for (Integer r : list) {
			if(r%2!=0){
				result.add(r);
			}
		}
		logger.info("removeByCopy cost:{}",(System.currentTimeMillis()-start));
		logger.debug("{}",result);
		return result;
	}

	private static List<Integer> initList(int size) {
		List<Integer> list = new ArrayList<Integer>(size);
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		return list;

	}
}
