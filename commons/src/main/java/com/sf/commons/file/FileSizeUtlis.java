
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
		size = rtrim(size, '0', '.');
		return size + sizeUnits[level];
	}

	/**
	 * 去掉字符串右边的连续特殊字符，先匹配第一个，全部删除完成后再匹配第二个字符,以次类推
	 * 
	 * <pre>
	 * rtrim("1.700", '0', '.')=>1.7
	 * rtrim("1.00", '0', '.')=>1
	 * rtrim("1.75", '0', '.')=>1.75
	 * rtrim("10.00", '0', '.')=>10
	 * rtrim("", '0', '.')=>""
	 * rtrim(null, '0', '.')=>null
	 * </pre>
	 * 
	 * @param s
	 *            目标字符串
	 * @param skipChars
	 *            需要剔除的字符数组
	 * @return 截取后的字符串
	 */
	public static String rtrim(String s, char... skipChars) {
		if (s == null || "".equals(s)) {
			return s;
		}
		int index = s.length();
		for (char skipChar : skipChars) {
			index = forwardSkipChar(s, index, skipChar);
		}
		return index < 1 ? "" : s.substring(0, index);
	}

	/**
	 * 从字符串指定位置往前跳过连续的指定字符
	 * 
	 * @param s
	 *            字符串
	 * @param index
	 *            指定位置 （1->s.length()）
	 * @param c
	 *            指定字符
	 * @return 跳过之后的位置
	 */
	private static int forwardSkipChar(String s, int index, char c) {
		while (index > 0 && s.charAt(index - 1) == c) {
			index--;
		}
		return index;
	}
}
