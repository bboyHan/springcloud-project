package com.bboyhan.cloud.util;


import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/24 18:38
 */
public class ExcelUtil {
    // 常亮，用作第一种模板类型
    private static final int HEADER_VALUE_TYPE_Z = 1;
    // 第二种模板类型
    private static final int HEADER_VALUE_TYPE_S = 2;
    /**
     * 获取一个实例
     */
    private static ExcelUtil getExcel2JSONHelper() {
        return new ExcelUtil();
    }


    /**
     * 文件过滤
     */
    private static boolean fileNameFileter(File file) {
        boolean endsWith = false;
        if (file != null) {
            String fileName = file.getName();
            endsWith = fileName.endsWith(".xls") || fileName.endsWith(".xlsx");
        }
        return endsWith;
    }


    /**
     * 获取表头行
     * @param: @param sheet
     * @param: @param index
     * @param: @return
     */
    private static Row getHeaderRow(Sheet sheet, int index) {
        Row headerRow = null;
        if (sheet != null) {
            headerRow = sheet.getRow(index);
        }
        return headerRow;
    }


    /**
     * 获取表格中单元格的value
     *
     * @throws
     * @param: @param row
     * @param: @param cellIndex
     * @param: @param formula
     */
    private static Object getCellValue(Row row, int cellIndex, FormulaEvaluator formula) {
        Cell cell = row.getCell(cellIndex);
        if (cell != null) {
            switch (cell.getCellType()) {
                //String类型
                case Cell.CELL_TYPE_STRING:
                    return cell.getRichStringCellValue().getString();


                //number类型
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().getTime();
                    } else {
                        return cell.getNumericCellValue();
                    }
                    //boolean类型
                case Cell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue();
                //公式
                case Cell.CELL_TYPE_FORMULA:
                    return formula.evaluate(cell).getNumberValue();
                default:
                    return null;
            }
        }
        return null;
    }


    /**
     * 获取表头value
     * @param: @param headerRow
     * @param: @param cellIndex 英文表头所在的行，从0开始计算哦
     * @param: @param type  表头的类型第一种 姓名（name）英文于实体类或者数据库中的变量一致
     */
    private static String getHeaderCellValue(Row headerRow, int cellIndex, int type) {
        Cell cell = headerRow.getCell(cellIndex);
        String headerValue = null;
        if (cell != null) {
            //第一种模板类型
            if (type == HEADER_VALUE_TYPE_Z) {
                headerValue = cell.getRichStringCellValue().getString();
                int l_bracket = headerValue.indexOf("（");
                int r_bracket = headerValue.indexOf("）");
                if (l_bracket == -1) {
                    l_bracket = headerValue.indexOf("(");
                }
                if (r_bracket == -1) {
                    r_bracket = headerValue.indexOf(")");
                }
                headerValue = headerValue.substring(l_bracket + 1, r_bracket);
            } else if (type == HEADER_VALUE_TYPE_S) {
                //第二种模板类型
                headerValue = cell.getRichStringCellValue().getString();
            }
        }
        return headerValue;
    }


    /**
     * 读取excel表格
     * @param: @param file
     * @param: @param headerIndex
     * @param: @param headType  表头的类型第一种 姓名（name）英文于实体类或者数据库中的变量一致
     */
    public static List<Map<String, Object>> readExcle(File file, int headerIndex, int headType) {
        List<Map<String, Object>> lists = new ArrayList<>();
        if (!fileNameFileter(file)) {
            return null;
        } else {
            try {
                //加载excel表格
                Workbook wb = WorkbookFactory.create(file);
                //读取第一个sheet页
                Sheet sheet = wb.getSheetAt(0);
                //读取表头行
                Row headerRow = getHeaderRow(sheet, headerIndex);
                //读取数据
                FormulaEvaluator formula = wb.getCreationHelper().createFormulaEvaluator();
                for (int r = headerIndex + 1; r <= sheet.getLastRowNum(); r++) {
                    Row dataRow = sheet.getRow(r);
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (int h = 0; h < dataRow.getLastCellNum(); h++) {
                        //表头为key
                        String key = getHeaderCellValue(headerRow, h, headType);
                        //数据为value
                        Object value = getCellValue(dataRow, h, formula);
                        if (!key.equals("") && !key.equals("null") && key != null) {
                            map.put(key, value);
                        }
                    }
                    lists.add(map);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        File dir = new File("C:\\Users\\admin.admin-PC\\Documents\\WeChat Files\\bboyHan\\Files\\1.xlsx");
        //dir文件，0代表是第一行为保存到数据库或者实体类的表头，一般为英文的字符串，2代表是第二种模板，
        List<Map<String, Object>> jsonArray = readExcle(dir, 0, 2);
        System.out.println(jsonArray.size());
    }


}
