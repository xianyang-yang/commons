package com.sf.commons.excel;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月3日 下午11:40:28	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class ExcelWriterTest {

	public static void main(String[] args) {
		ExcelWriter writer = new ExcelWriter();
		for (int i = 0; i < 10; i++) {
			String file=String.format("F:/exceltest/me%s.xlsx", i);
			writer.addExcel(file);
		}
		writer.write("F:/exceltest/total1.xlsx");
	}

}
