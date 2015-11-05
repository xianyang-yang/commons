package com.sf.commons.file;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 描述：将文件大小从字节数转为可读的字符串
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2015年10月12日 上午11:00:10		204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class FileSizeUtlis {
	private static final int _1K = 1024;

	private static final char[] levelName = new char[] { 'B', 'K', 'M', 'G','T' };
	
	/**
	 * 将文件大小从字节数转为可读的字符串
	 * @param byteSize 字节数
	 * @return
	 */
	public static String humanSize(long byteSize) {
		int level = 0;
		double result = (double) byteSize;
		while (result / _1K >= 1) {
			result /= _1K;
			level++;
		}
		return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP)
				.toString() + getMemUnit(level);

	}

	private static char getMemUnit(int level) {
		if (level >= levelName.length) {
			level = levelName.length - 1;
		}
		return levelName[level];
	}
}
