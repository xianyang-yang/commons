package com.sf.commons.file;

import static com.sf.commons.file.FileSizeUtlis.humanSize;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月3日 下午9:17:53	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class FileSizeUtlisTest {
	public static void main(String[] args) {
		// System.out.println(humanSize(1024*(1024+500)));
		List<String> inputs = getInputs(900000);
		testrtrimPerformance(inputs);
		testStripEndPerformace(inputs);

	}

	@Test
	public void test() {
		assertEquals("100B", humanSize(100));
		assertEquals("1K", humanSize(1024));
		assertEquals("1M", humanSize(1024 * 1024));
		assertEquals("1.5M", humanSize(1024 * (1024 + 512)));
		assertEquals("1.75G", humanSize(1024 * 1024 * (1024 + 768)));
		assertEquals("1T", humanSize(1024L * 1024 * 1024 * 1024));
		assertEquals("1P", humanSize(1024L * 1024 * 1024 * 1024 * 1024));
		assertEquals("1024P", humanSize(1024L * 1024 * 1024 * 1024 * 1024 * 1024));
	}

	public static List<String> getInputs(int size) {
		int total = size * 10;
		List<String> inputs = new ArrayList<>(total);
		int index = 0;
		for (double i = 0; i < size; i += 0.1) {
			String input = new BigDecimal(i).setScale(2, RoundingMode.HALF_DOWN).toString();
			inputs.add(input);
			if ((++index) % 100000 == 0) {
				System.out.println("aready add:" + index + "/" + total);
			}
		}
		return inputs;
	}

	public static void testStripEndPerformace(List<String> inputs) {
		long start = System.currentTimeMillis();
		for (String s : inputs) {
			StringUtils.stripEnd(StringUtils.stripEnd(s, "0"), ".");
			// System.out.println(r);
		}
		System.out.println("test1 cost:" + (System.currentTimeMillis() - start));
	}

	public static void testrtrimPerformance(List<String> inputs) {
		long start = System.currentTimeMillis();
		for (String s : inputs) {
			FileSizeUtlis.rtrim(s, '0', '.');
			// System.out.println(r);
		}
		System.out.println("test2 cost:" + (System.currentTimeMillis() - start));
	}

}
