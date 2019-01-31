package cn.chenhuanming.octopus.core.temp.reader;

import cn.chenhuanming.octopus.core.config.ConfigReader;
import cn.chenhuanming.octopus.core.temp.ExcelConfig;
import cn.chenhuanming.octopus.core.temp.configreader.XmlExcelConfigReader;
import cn.chenhuanming.octopus.core.temp.reader.ReadExcelConfig;
import cn.chenhuanming.octopus.core.temp.writer.WriteExcelConfig;
import cn.chenhuanming.octopus.model.CellPosition;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zzf
 */
public class ReadContext<T> {

    private ReadExcelConfig readExcelConfig;

    private ExcelReader excelReader;

    public ReadContext(ReadExcelConfig readExcelConfig,Workbook workbook,boolean isCheck) {
        init(readExcelConfig, isCheck, workbook);
    }

    public ReadContext(ReadExcelConfig readExcelConfig, InputStream in,boolean isCheck) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(in);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        init(readExcelConfig, isCheck, workbook);
    }

    public ReadContext( InputStream configStream,InputStream excelStream,boolean isCheck) {
        final XmlExcelConfigReader xmlCellDefinitionReader = new XmlExcelConfigReader(configStream);
        ReadExcelConfig config = xmlCellDefinitionReader.loadReadExcelConfig();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(excelStream);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        init(readExcelConfig, isCheck, workbook);
    }

    private void init(ReadExcelConfig readExcelConfig, boolean isCheck, Workbook workbook) {
        this.readExcelConfig = readExcelConfig;
        this.excelReader = isCheck ? new CheckedExcelReader(workbook) : new DefaultExcelReader(workbook);
    }

    public SheetReader<T> readSheet(int index,  CellPosition startPoint) {
        return excelReader.<T>get(index, readExcelConfig.getExcelConfig(), startPoint);
    }
    public SheetReader<T> readSheet( CellPosition startPoint) {
        return excelReader.<T>get(0, readExcelConfig.getExcelConfig(), startPoint);
    }

}
