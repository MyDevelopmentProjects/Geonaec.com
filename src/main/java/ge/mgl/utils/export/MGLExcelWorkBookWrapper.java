package ge.mgl.utils.export;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by georgevashakidze on 1/8/17.
 */

public class MGLExcelWorkBookWrapper {

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFSheet[] sheets;
    private String fileName;

    private MGLExcelWorkBookWrapper(HSSFWorkbook workbook, HSSFSheet sheet, String fileName) {
        this.workbook = workbook;
        this.sheet = sheet;
        this.fileName = fileName;
    }

    private MGLExcelWorkBookWrapper(HSSFWorkbook workbook, HSSFSheet sheets[], String fileName) {
        this.workbook = workbook;
        this.sheets = sheets;
        this.fileName = fileName;
    }

    public static MGLExcelWorkBookWrapper createWorkBookWithHeader(String reportName, String[] titles) {

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet(reportName);

        HSSFRow header = sheet.createRow(0);

        for (int i = 0; i < titles.length; i++) {
            header.createCell(i).setCellValue(titles[i]);
        }

        HSSFCellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);

        header.setRowStyle(style);

        return new MGLExcelWorkBookWrapper(workbook, sheet, reportName);

    }

    public static MGLExcelWorkBookWrapper createWorkBookWithHeader(String reportName, Map<String, String[]> sheetsWithHeaders) {

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet[] sheets = new HSSFSheet[2];
        int index = 0;
        for (Map.Entry<String, String[]> entry : sheetsWithHeaders.entrySet())
        {
            String[] titles = entry.getValue();
            int sheetIndex = index+1;
            HSSFSheet sheet = workbook.createSheet("Sheet" + sheetIndex);
            HSSFRow header = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                header.createCell(i).setCellValue(titles[i]);
            }
            HSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
            header.setRowStyle(style);
            sheets[index] = sheet;
            index++;
        }

        return new MGLExcelWorkBookWrapper(workbook, sheets, reportName);

    }


    public void setCellValues(List<MGLExcelWorkBookValueWrapper> values, int rowIndex, int sheetNumber) {

        HSSFRow row = this.sheets[sheetNumber].createRow(rowIndex);

        for (int i = 0; i < values.size(); i++) {

            HSSFCell cell = row.createCell(i);

            MGLExcelWorkBookValueWrapper value = values.get(i);

            String objValue = String.valueOf(value.getValue());

            switch (value.getType()) {

                case LONG:
                    if (NumberUtils.isNumber(objValue)) {
                        Long l = Long.valueOf(objValue);
                        cell.setCellValue(l);
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    }
                    break;
                case STRING:
                    cell.setCellValue(objValue);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    break;

                case INTEGER:
                    if (NumberUtils.isNumber(objValue)) {
                        Integer integer = Integer.valueOf(objValue);
                        cell.setCellValue(integer);
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    }
                    break;

                case BOOLEAN:
                    Boolean bool = Boolean.valueOf(objValue);
                    cell.setCellValue(bool);
                    cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
                    break;

                case DOUBLE:

                    if (NumberUtils.isNumber(objValue)) {
                        Double d = Double.valueOf(objValue);
                        cell.setCellValue(d);
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    }
                    break;

            }

        }
    }

    public void setFormulaCell(String[] formulas, int rowIndex) {

        HSSFRow row = this.sheet.createRow(rowIndex);

        for (int i = 0; i < formulas.length; i++) {
            HSSFCell cell = row.createCell(i);
            if (formulas[i] != null) {
                cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
                cell.setCellFormula(formulas[i]);
            }
        }

    }

    public void downloadFile(HttpServletResponse response) {

        String fileName = String.format("%s%s%s%s", this.fileName, "-", new Date().toString(), ".xls");

        response.setContentType("application/vnd.ms-excel");

        response.setHeader("Content-disposition", String.format("attachment;filename=%s", fileName));

        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            this.workbook.write(bos);

            response.setContentLength(bos.size());

            this.workbook.write(response.getOutputStream());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}