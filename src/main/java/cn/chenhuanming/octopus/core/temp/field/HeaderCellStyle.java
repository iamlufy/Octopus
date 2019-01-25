package cn.chenhuanming.octopus.core.temp.field;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.awt.*;

/**
 * @author zhuangzf
 * @date 2019/1/17 8:50
 */
public interface HeaderCellStyle {
    short getHeaderFontSize();

    Color getHeaderColor();

    boolean isHeaderBold();

    Color getHeaderForegroundColor();

    BorderStyle[] getHeaderBorder();

    Color[] getHeaderBorderColor();

    CellStyle getHeaderCellStyle(Workbook book);
}
