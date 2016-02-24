package com.sf.commons.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月3日 下午11:09:25	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class ExcelWriter {
	/**
	 * 默认最多保存多少行在内存中
	 */
	private static final int DEFAULT_MAX_ROW_IN_MEMORY = 10000;
	private SXSSFWorkbook wb;
	private Sheet sheet;
	private int curRowNum = 0;

	public ExcelWriter() {
		this(DEFAULT_MAX_ROW_IN_MEMORY);
	}

	public ExcelWriter(int maxRowInMemory) {
		this(maxRowInMemory, "sheet-1");
	}

	public ExcelWriter(int maxRowInMemory, String sheetname) {
		wb = new SXSSFWorkbook(maxRowInMemory);
		sheet = wb.createSheet(sheetname);
	}

	public void addExcels(String... filePaths) {
		addExcels(0, filePaths);
	}

	public void addExcels(int sheetIndex, String... filePaths) {
		for (String filePath : filePaths) {
			addExcel(sheetIndex, filePath);
		}
	}

	public void addExcel(String filePath) {
		addExcel(0, filePath);
	}

	public void addExcel(int sheetIndex, String filePath) {
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
			addSheet(wb.getSheetAt(sheetIndex));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void write(String outPutPath) {
		try {
			wb.write(new FileOutputStream(new File(outPutPath)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private ExcelWriter addSheet(Sheet otherSheet) {
		for (int row = 0; row <= otherSheet.getLastRowNum(); row++) {
			Row newRow = sheet.createRow(curRowNum);
			copyRow(newRow, otherSheet.getRow(row));
			curRowNum++;
		}
		return this;
	}

	private void copyRow(Row newRow, Row row) {
		for (int i = 0; i < row.getLastCellNum(); i++) {
			Cell newCell = newRow.createCell(i);
			newCell.setCellValue(row.getCell(i).getStringCellValue());
		}
	}
}
