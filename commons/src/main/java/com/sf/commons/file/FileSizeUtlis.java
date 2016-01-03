package com.sf.commons.file;

import static org.apache.commons.lang.StringUtils.stripEnd;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 描述：将文件大小从字节数转为可读的字符串
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2015年10月12日 上午11:00:10	204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class FileSizeUtlis {
	private static final int _1K = 1024;

	/**
	 * 支持的单位
	 */
	private static final char[] sizeUnits = new char[] { 'B', 'K', 'M', 'G', 'T', 'P' };

	/**
	 * 将文件大小从字节数转为可读的字符串
	 * 
	 * @param byteSize
	 *            字节数
	 * @return
	 */
	public static String humanSize(long byteSize) {
		int level = 0;
		double result = (double) byteSize;
		while (result >= _1K && level < sizeUnits.length - 1) {
			result /= _1K;
			level++;
		}
		String size = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).toString();
		size=stripEnd(stripEnd(size, "0"),".");
		return size+ sizeUnits[level];

	}
}
