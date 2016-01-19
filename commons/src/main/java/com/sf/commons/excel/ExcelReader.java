package com.sf.commons.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2015年12月23日 下午2:29:23	204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class ExcelReader {
	XSSFWorkbook wb;

	public ExcelReader(String path) {
		try {
			wb =  new XSSFWorkbook(new FileInputStream(new File(
					path)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	public void close(){
//		try {
//			wb.close();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
	}

	public <T> List<T> getContent(int sheetNo, int startRowNum,
			RowHandle<T> handle) {
		 XSSFSheet sheet = wb.getSheetAt(sheetNo);
		List<T> result = new ArrayList<T>();
		for (int i = startRowNum; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			T o = handle.doInRow(row);
			if(o!=null){
				result.add(o);
			}
		}
		return result;
	}
	
	public static String cellValue(XSSFRow row,int cellnum) {
		return cellValue(row.getCell(cellnum));
	}
	public static int cellValueAsInt(XSSFRow row,int cellnum) {
		String v = cellValue(row.getCell(cellnum));
		if("-".equals(v)){
			return Integer.MAX_VALUE;
		}
		return Integer.parseInt(v);
	}
	
	public static String cellValue(XSSFCell cell) {
		if (cell == null) {
            return "";
        }
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
        	int num=(int)cell.getNumericCellValue();
            strCell = String.valueOf(num);
            if(num<=100){
            	strCell='0'+strCell;
            }
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }

	public static interface RowHandle<T> {
		public T doInRow(XSSFRow row);
	}
}
