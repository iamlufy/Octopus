package cn.chenhuanming.octopus.core.temp.reader;


import cn.chenhuanming.octopus.core.temp.ExcelConfig;
import cn.chenhuanming.octopus.core.temp.field.impl.MappedField;
import cn.chenhuanming.octopus.exception.ParseException;
import cn.chenhuanming.octopus.formatter.Formatter;
import cn.chenhuanming.octopus.model.CellPosition;
import cn.chenhuanming.octopus.util.CellUtils;
import cn.chenhuanming.octopus.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Date;


/**
 * @author chenhuanming
 * Created at 2019-01-06
 */
@Slf4j
public class DefaultSheetReader<T> extends AbstractSheetReader<T> {

    public DefaultSheetReader(Sheet sheet, ExcelConfig excelConfig, CellPosition startPoint) {
        super(sheet, excelConfig, startPoint);
    }

    @Override
    int read(int row, int col, MappedField field, Object o) {
        if (field.isLeaf()) {

            try {
                Cell cell = sheet.getRow(row).getCell(col);
                String str;
                if (CellUtils.isDate(cell)) {
                    Formatter<Date> dateFormatter = excelConfig.getFormatterContainer().get(Date.class);
                    str = dateFormatter.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
                } else {
                    str = CellUtils.getCellValue(sheet, row, col, field.getFieldProperty().getDefaultValue());
                }

                setValue(str, field, o);
            } catch (ParseException e) {
                failWhenParse(row, col, field, e);
            }

            return col + 1;
        }

        Object instance = ReflectionUtils.newInstance(field.getPusher().getParameterTypes()[0]);
        for (MappedField child : field.getChildren()) {
            if (instance != null) {
                col = read(row, col, child, instance);
                try {
                    field.getPusher().invoke(o, instance);
                } catch (Exception e) {
                    log.error("failed to set " + instance + " into " + o, e);
                }
            }
        }
        return col;
    }

    protected void failWhenParse(int row, int col, final MappedField field, ParseException e) {
        log.error("failed to read value from " + field.getFieldProperty().getName() + " in excel(" + (row + 1) + "," + col + ")", e);
    }
}
