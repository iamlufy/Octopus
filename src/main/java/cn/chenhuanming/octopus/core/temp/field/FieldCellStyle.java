package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.core.read.style.HeaderCellStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.awt.*;

/**
 * @author zhuangzf
 * @date 2019/1/17 8:42
 */
public interface FieldCellStyle extends Field  {
    short getFontSize();

    Color getColor();

    boolean isBold();

    Color getForegroundColor();

    BorderStyle[] getBorder();

    Color[] getBorderColor();

    CellStyle getCellStyle(Workbook book);
}
