package com.zte.msm.frame.util.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.context.ContextLoader;

import com.zte.msm.frame.util.date.DateUtil;

/**
 * 导出excel工具类 导出的excel是存在于项目的src/main/webapp/export目录下，如修改，请在84行设置
 * 
 * @author 10206747
 *
 */
public class DataExportUtil {
	/**
	 * 导出excle
	 * 
	 * @param sheetName
	 *            表名
	 * @param titleColumns
	 *            表头
	 * @param list
	 *            要导出的数据
	 * @throws IOException
	 */
	public static String exportDataToExcel(String sheetName, String[] titleColumns, List<Object[]> list)
			throws IOException {
		return writeToFile(sheetName, titleColumns, list);
	}

	/**
	 * 导出excle
	 * 
	 * @param sheetName
	 *            表名
	 * @param titleColumns
	 *            表头
	 * @param list
	 *            要导出的数据
	 */
	private static String writeToFile(String sheetName, String[] titleColumns, List<Object[]> list) throws IOException {
		String name = "";
		HSSFWorkbook workbook = new HSSFWorkbook(); // 工作薄
		List<Object[]> dataList = new ArrayList<Object[]>();
		// 数据大于10000条，分多张sheet
		if (list != null && list.size() > 10000) {
			int c = list.size() % 10000 == 0 ? list.size() / 10000 : list.size() / 10000 + 1;
			int allTotal = 0;
			for (int i = 0; i < c; i++) {
				if (i == c - 1) {
					dataList = list.subList(allTotal + 1, list.size() - 1);
				} else {
					if (allTotal == 0) {
						dataList = list.subList(1, i * 10001);
					} else {
						dataList = list.subList(allTotal + 1, i * 10000 + 1);
					}
				}
				allTotal = allTotal + 10000;
				HSSFSheet sheet = workbook.createSheet(sheetName + i); // 工作表
				setHeaderRow(workbook, sheet, sheetName + i, titleColumns); // 设置表头
				setSignData(dataList, workbook, sheet); // 插入数据
			}
		} else {
			HSSFSheet sheet = workbook.createSheet(sheetName); // 工作表
			setHeaderRow(workbook, sheet, sheetName, titleColumns); // 设置表头
			setSignData(list, workbook, sheet); // 插入数据
		}
		try {
			FileOutputStream outputStream;
			name = name + sheetName + DateUtil.getNowDateTimeStr() + ".xls"; // 导出excel的表名称
			outputStream = new FileOutputStream(
					ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") + "/export/"
							+ name); // 存储在服务器上路径
			workbook.write(outputStream);// 输出
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return name;
	}

	/***
	 * 设置表头
	 * 
	 * @param workbook
	 * @param sheet
	 * @param sheetName
	 * @param titleColumns
	 */
	private static void setHeaderRow(HSSFWorkbook workbook, HSSFSheet sheet, String sheetName, String[] titleColumns) {
		HSSFRow row = null; // 行
		HSSFCell cell = null; // 单元格
		row = sheet.createRow(1); // 表头行
		for (int i = 0; i < titleColumns.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(getHeadCellStyle(workbook));
			cell.setCellValue(titleColumns[i]);
		}
		row.setHeight((short) (30 * 40)); // 设置行高

		row = sheet.createRow(0); // 标题
		cell = row.createCell(0);
		cell.setCellStyle(getHeadCellStyle(workbook));
		cell.setCellValue(sheetName + "");
		row.setHeight((short) (30 * 40));
		setHeaderRowStyle(workbook, row, titleColumns.length);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titleColumns.length - 1)); // 合并标题栏
	}

	/***
	 * 向表格里写入数据
	 * 
	 * @param list
	 * @param workbook
	 * @param sheet
	 */
	private static void setSignData(List<Object[]> list, HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFRow row = null; // 行
		HSSFCell cell = null; // 单元格
		HSSFCellStyle style = getContentCellStyle(workbook);
		int START_ROW = 2;
		if (list.size() > 0) {
			// 多列
			if (list.get(0) instanceof Object[]) {
				for (Object[] obj : list) {
					row = sheet.createRow(START_ROW);
					for (int i = 0; i < obj.length; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(style);
						cell.setCellValue((String) obj[i]);
					}
					START_ROW++;
				}
			} else {
				for (Object obj : list) {
					row = sheet.createRow(START_ROW);
					cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue((String) obj);
					START_ROW++;
				}
			}

		}
	}

	/***
	 * 取得表头样式
	 * 
	 * @param workbook
	 *            工作薄
	 * @return
	 */
	private static HSSFCellStyle getHeadCellStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		return style;
	}

	/***
	 * 为了合并单元格，要把其他的单元格带上边框线
	 * 
	 * @param workbook
	 * @param row
	 * @param length
	 */
	private static void setHeaderRowStyle(HSSFWorkbook workbook, HSSFRow row, int length) {
		HSSFCell cell = null; // 单元格
		for (int i = 1; i < length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(null);
		}
	}

	/***
	 * 取得每行数据样式
	 * 
	 * @param workbook
	 * @return
	 */
	private static HSSFCellStyle getContentCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 9);
		style.setFont(font);
		style.setWrapText(true);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		return style;
	}
}