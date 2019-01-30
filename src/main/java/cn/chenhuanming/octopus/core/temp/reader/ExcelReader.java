package cn.chenhuanming.octopus.core.temp.reader;


import cn.chenhuanming.octopus.core.temp.ExcelConfig;
import cn.chenhuanming.octopus.exception.SheetNotFoundException;
import cn.chenhuanming.octopus.model.CellPosition;

/**
 * @author chenhuanming
 * Created at 2019-01-09
 */
public interface ExcelReader<T> {
    SheetReader<T> get(int index, ExcelConfig excelConfig, CellPosition startPoint) throws ArrayIndexOutOfBoundsException;

    SheetReader<T> get(String sheetName, ExcelConfig excelConfig, CellPosition startPoint) throws SheetNotFoundException;
}
