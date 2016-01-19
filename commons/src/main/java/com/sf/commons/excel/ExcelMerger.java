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
 * 描述：将多个excel合成一个
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月12日 上午11:54:03	204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class ExcelMerger {
	/**
	 * 默认最多保存多少行在内存中
	 */
	private static final int DEFAULT_MAX_ROW_IN_MEMORY = 1000;
	private SXSSFWorkbook wb;
	private Sheet sheet;
	private int curRowNum = 0;

	public ExcelMerger() {
		this(DEFAULT_MAX_ROW_IN_MEMORY);
	}

	public ExcelMerger(int maxRowInMemory) {
		this(maxRowInMemory, "sheet-1",null);
	}
	public ExcelMerger(int maxRowInMemory,
			String templateFilePath,int startRow) {
		this(maxRowInMemory, "sheet-1", templateFilePath);
		curRowNum=startRow;
	}

	public ExcelMerger(int maxRowInMemory, String sheetname,
			String templateFilePath) {
		if (templateFilePath != null) {
			try {
				wb = new SXSSFWorkbook(new XSSFWorkbook(new FileInputStream(
						templateFilePath)), maxRowInMemory);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			sheet = wb.getSheetAt(0);

		} else {
			wb = new SXSSFWorkbook(maxRowInMemory);
			sheet = wb.createSheet(sheetname);
		}
	}

	public void addExcels(File... filePaths) {
		addExcels(0, 0, filePaths);
	}

	public void addExcels(int sheetIndex, int startRow, File... filePaths) {
		for (File filePath : filePaths) {
			addExcel(sheetIndex, startRow, filePath);
		}
	}

	public void addExcel(int sheetIndex, int startRow, File filePath) {
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
			addSheet(wb.getSheetAt(sheetIndex), startRow);
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

	private ExcelMerger addSheet(Sheet otherSheet, int startRow) {
		for (int row = startRow; row <= otherSheet.getLastRowNum(); row++) {
			Row currow = otherSheet.getRow(row);
			if (currow != null) {
				Row newRow = sheet.createRow(curRowNum);
				copyRow(newRow, currow);
				curRowNum++;
			}
		}
		return this;
	}

	private void copyRow(Row newRow, Row row) {
		if (row == null) {
			return;
		}
		for (int i = 0; i < row.getLastCellNum(); i++) {
			Cell newCell = newRow.createCell(i);
			newCell.setCellValue(row.getCell(i).getStringCellValue());
		}
	}
}
