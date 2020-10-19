package util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.math.BigDecimal;

public class CellBuilder {
    private static final String DEFAULT_FONT = "宋体";
    private HSSFCellStyle alignCenterStyle;
    private HSSFCellStyle alignCenterBoldStyle;
    private HSSFCellStyle alignCenterIntStyle;
    private HSSFCellStyle alignCenterIntBoldStyle;
    private HSSFCellStyle alignCenterTitleStyle;
    private HSSFCellStyle alignCenterSmallTitleStyle;
    private HSSFCellStyle alignLeftStyle;

    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private short defaultHeight;

    public CellBuilder(HSSFWorkbook wb, HSSFSheet sheet, int defaultHeight) {
        this.wb = wb;
        this.sheet = sheet;
        this.defaultHeight = (short)defaultHeight;
    }

    private HSSFCellStyle newStyle(Short alignment, String fontName, boolean isBold, int fontHeight) {
        HSSFCellStyle style = wb.createCellStyle();
        if (alignment != null) {
            style.setAlignment(alignment);
        }
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        HSSFFont font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontHeight);
        if (isBold) {
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        }
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }

    public HSSFCellStyle getAlignCenterStyle() {
        if (alignCenterStyle == null) {
            alignCenterStyle = newStyle(HSSFCellStyle.ALIGN_CENTER, DEFAULT_FONT, false, 10);
        }
        return alignCenterStyle;
    }

    public HSSFCellStyle getAlignCenterBoldStyle() {
        if (alignCenterBoldStyle == null) {
            alignCenterBoldStyle = newStyle(HSSFCellStyle.ALIGN_CENTER, DEFAULT_FONT, true, 10);
        }
        return alignCenterBoldStyle;
    }

    public HSSFCellStyle getAlignCenterIntStyle() {
        if (alignCenterIntStyle == null) {
            alignCenterIntStyle = newStyle(HSSFCellStyle.ALIGN_CENTER, DEFAULT_FONT, false, 10);
            alignCenterIntStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
        }
        return alignCenterIntStyle;
    }

    public HSSFCellStyle getAlignCenterIntBoldStyle() {
        if (alignCenterIntBoldStyle == null) {
            alignCenterIntBoldStyle = newStyle(HSSFCellStyle.ALIGN_CENTER, DEFAULT_FONT, true, 10);
            alignCenterIntBoldStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
        }
        return alignCenterIntBoldStyle;
    }

    public HSSFCellStyle getAlignCenterTitleStyle() {
        if (alignCenterTitleStyle == null) {
            alignCenterTitleStyle = newStyle(HSSFCellStyle.ALIGN_CENTER, DEFAULT_FONT, true, 24);
        }
        return alignCenterTitleStyle;
    }

    public HSSFCellStyle getAlignCenterSmallTitleStyle() {
        if (alignCenterSmallTitleStyle == null) {
            alignCenterSmallTitleStyle = newStyle(HSSFCellStyle.ALIGN_CENTER, DEFAULT_FONT, true, 20);
        }
        return alignCenterSmallTitleStyle;
    }
    //数据左靠边
    public HSSFCellStyle getAlignLeftStyle() {
        if (alignLeftStyle == null) {
            alignLeftStyle = newStyle(HSSFCellStyle.ALIGN_LEFT, DEFAULT_FONT, false, 10);
        }
        return alignLeftStyle;
    }

    private HSSFRow getRow(int rowNum) {
        HSSFRow row = sheet.getRow(rowNum);;
        if (row == null) {
            row = sheet.createRow(rowNum);
            row.setHeight(defaultHeight);
        }
        return row;
    }
    //设置当前行的高度
    public HSSFRow setRowHeight(int rowNum, short height) {
        HSSFRow row = getRow(rowNum);
        row.setHeight(height);
        return row;
    }

    public HSSFCell buildCell(int rowNum, int col, HSSFCellStyle style, Object value) {
        HSSFRow row = getRow(rowNum);
        HSSFCell cell = row.createCell(col);
        cell.setCellStyle(style);
        if (value != null) {
            cell.setCellValue(value.toString());
            if (value.toString().contains("\n")) {
                String[] values = value.toString().split("\n");
                if (values.length > 0) {
                    row.setHeight((short) (defaultHeight * values.length * 0.6));
                }
            }
        }
        return cell;
    }

    public HSSFCell buildIntCell(int rowNum, int col, HSSFCellStyle style, Object value) {
        HSSFCell cell = getRow(rowNum).createCell(col);
        cell.setCellStyle(style);
        if (value != null) {
            Integer intVal = 0;
            if (value instanceof BigDecimal) {
                intVal = ((BigDecimal) value).intValue();
            } else {
                intVal = Integer.valueOf(value.toString());
            }
            cell.setCellValue(intVal.toString());
        }
        return cell;
    }

    public HSSFCell buildCell(int rowNum, int col, Object value) {
        return buildCell(rowNum, col, getAlignCenterStyle(), value);
    }

    public HSSFCell buildBoldCell(int rowNum, int col, Object value) {
        return buildCell(rowNum, col, getAlignCenterBoldStyle(), value);
    }

    public HSSFCell buildIntCell(int rowNum, int col, Object value) {
        return buildIntCell(rowNum, col, getAlignCenterStyle(), value);
    }

    public HSSFCell buildIntBoldCell(int rowNum, int col, Object value) {
        return buildIntCell(rowNum, col, getAlignCenterIntBoldStyle(), value);
    }

    public HSSFCell buildTitleCell(int rowNum, int col, Object value) {
        return buildCell(rowNum, col, getAlignCenterTitleStyle(), value);
    }

    public HSSFCell buildSmallTitleCell(int rowNum, int col, Object value) {
        return buildCell(rowNum, col, getAlignCenterSmallTitleStyle(), value);
    }

    public void buildRangeCell(int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);    //给定要合并的单元格范围
        sheet.addMergedRegion(region);
        setBorder(region);
    }
    //设置行的高度和宽度
    public HSSFCell buildRangeCell(int firstRow, int lastRow, int firstCol, int lastCol, Object value) {
        buildRangeCell(firstRow, lastRow, firstCol, lastCol);
        return buildSmallTitleCell(firstRow, firstCol, value);
    }

    public void setBorder(CellRangeAddress region) {
        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, wb);
        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region, sheet, wb);
        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, wb);
        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, wb);
    }
}
