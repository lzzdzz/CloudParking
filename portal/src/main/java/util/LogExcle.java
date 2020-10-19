package util;

import com.lz.pojo.log.Log;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class LogExcle {
    private static final short ROW_HEIGHT = 600;//默认行高
    private static final short ROW_TITLE_HEIGHT = 900;//标题高度
    private static final short LAST_CELL_NUM = 4;//总共有15列

    public static void buildExcel(String path, Log log) throws Exception {
        OutputStream outputStream = new FileOutputStream(path);
        //创建Excel Sheet
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("停车费日志表");

        //设置各行高度
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 3000);

        //初始化
        CellBuilder cellBuilder = new CellBuilder(wb, sheet, ROW_HEIGHT);
        //创建列
        int rowNum = 0;
        //第1行标题，高度加高
        cellBuilder.buildRangeCell(rowNum, rowNum, 0, LAST_CELL_NUM, "停车费日志表");
        cellBuilder.setRowHeight(rowNum, ROW_TITLE_HEIGHT);

        rowNum++;
        cellBuilder.buildBoldCell(rowNum, 0, "车牌号");
        cellBuilder.buildBoldCell(rowNum, 1, "区域名");
        cellBuilder.buildBoldCell(rowNum, 2, "进入时间");
        cellBuilder.buildBoldCell(rowNum, 3, "离开时间");
        cellBuilder.buildBoldCell(rowNum, 4, "费用");


                rowNum++;
                cellBuilder.buildBoldCell(rowNum, 0, log.getCarnum());
                cellBuilder.buildBoldCell(rowNum, 1, log.getAreaname());
                cellBuilder.buildBoldCell(rowNum, 2, log.getStarttime());
                cellBuilder.buildBoldCell(rowNum, 3, log.getEndtime());
                cellBuilder.buildBoldCell(rowNum, 4, log.getMoney());


        wb.write(outputStream);
        outputStream.close();
    }
}
