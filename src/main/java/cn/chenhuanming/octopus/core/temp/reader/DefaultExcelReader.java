package cn.chenhuanming.octopus.core.temp.reader;

import cn.chenhuanming.octopus.core.config.ConfigReader;
import cn.chenhuanming.octopus.core.temp.ExcelConfig;
import cn.chenhuanming.octopus.exception.SheetNotFoundException;
import cn.chenhuanming.octopus.model.CellPosition;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author chenhuanming
 * Created at 2019-01-09
 */
public class DefaultExcelReader<T> implements ExcelReader<T> {
    protected Workbook workbook;

    public DefaultExcelReader(Workbook workbook) {
        if (workbook == null) {
            throw new NullPointerException("workbook can not be null");
        }
        this.workbook = workbook;
    }

    @Override
    public SheetReader<T> get(int index, ExcelConfig excelConfig, CellPosition startPoint) {
        return new DefaultSheetReader<>(workbook.getSheetAt(index), excelConfig, startPoint);
    }

    @Override
    public SheetReader<T> get(String sheetName, ExcelConfig excelConfig, CellPosition startPoint) throws SheetNotFoundException {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet.getSheetName().equals(sheetName)) {
                return get(i, excelConfig, startPoint);
            }
        }
        throw new SheetNotFoundException("not found:" + sheetName);
    }
}
