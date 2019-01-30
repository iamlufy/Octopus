package cn.chenhuanming.octopus.core.temp.reader;

import cn.chenhuanming.octopus.core.temp.ExcelConfig;
import cn.chenhuanming.octopus.model.CellPosition;
import cn.chenhuanming.octopus.model.CheckedData;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author chenhuanming
 * Created at 2019-01-12
 */
public class CheckedExcelReader<T> extends DefaultExcelReader<CheckedData<T>> {
    public CheckedExcelReader(Workbook workbook) {
        super(workbook);
    }

    @Override
    public SheetReader<CheckedData<T>> get(int index, ExcelConfig excelConfig, CellPosition startPoint) {
        return new CheckedSheetReader<>(workbook.getSheetAt(index), excelConfig, startPoint);
    }
}
