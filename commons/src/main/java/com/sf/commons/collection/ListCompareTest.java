package com.sf.commons.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年2月23日 上午10:25:11		204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class ListCompareTest {
	private static final int COUNT = 1000000;
	
	private static Vector vector = new Vector();
	private static Stack stack = new Stack();

	public static void main(String[] args) {
		
		int initSize=COUNT;
		for (int i = 0; i < 20; i++) {
			LinkedList linkedList = new LinkedList();
			ArrayList arrayList = new ArrayList(initSize);
			insertByPosition(linkedList,"linkedList");
			insertByPosition(arrayList,"arraylist");
		}
		
		
		
		
		
	}

	// 获取list的名称
	private static String getListName(List list) {
		if (list instanceof LinkedList) {
			return "LinkedList";
		} else if (list instanceof ArrayList) {
			return "ArrayList";
		} else if (list instanceof Stack) {
			return "Stack";
		} else if (list instanceof Vector) {
			return "Vector";
		} else {
			return "List";
		}
	}

	// 向list的指定位置插入COUNT个元素，并统计时间
	private static void insertByPosition(List list,String name) {
		long startTime = System.currentTimeMillis();
		// 向list的位置0插入COUNT个数
		for (int i = 0; i < COUNT; i++)
			list.add(i);
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println(name + " : insert " + COUNT + " elements into the 1st position use time："
				+ interval + " ms");
	}

	// 从list的指定位置删除COUNT个元素，并统计时间
	private static void deleteByPosition(List list) {
		long startTime = System.currentTimeMillis();
		// 删除list第一个位置元素
		for (int i = 0; i < COUNT; i++)
			list.remove(0);
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println(getListName(list) + " : delete " + COUNT + " elements from the 1st position use time："
				+ interval + " ms");
	}

	// 根据position，不断从list中读取元素，并统计时间
	private static void readByPosition(List list) {
		long startTime = System.currentTimeMillis();
		// 读取list元素
		for (int i = 0; i < COUNT; i++)
			list.get(i);
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out
				.println(getListName(list) + " : read " + COUNT + " elements by position use time：" + interval + " ms");
	}
}
