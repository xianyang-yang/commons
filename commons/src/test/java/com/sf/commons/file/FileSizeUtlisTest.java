package com.sf.commons.file;

import static com.sf.commons.file.FileSizeUtlis.humanSize;
import static org.junit.Assert.assertEquals;

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
		System.out.println(humanSize(1024*(1024+500)));
		
	}

	@Test
	public void test() {
		assertEquals("100B", humanSize(100));
		assertEquals("1K", humanSize(1024));
		assertEquals("1M", humanSize(1024*1024));
		assertEquals("1.5M", humanSize(1024*(1024+512)));
		assertEquals("1.75G", humanSize(1024*1024*(1024+768)));
		assertEquals("1T", humanSize(1024L*1024*1024*1024));
		assertEquals("1P", humanSize(1024L*1024*1024*1024*1024));
		assertEquals("1024P", humanSize(1024L*1024*1024*1024*1024*1024));
	}

}
