package cn.chenhuanming.octopus.core;

import cn.chenhuanming.octopus.core.read.DefaultSheetReader;
import cn.chenhuanming.octopus.core.temp.configreader.XmlCellDefinitionReader;
import cn.chenhuanming.octopus.core.temp.reader.ReadContext;
import cn.chenhuanming.octopus.core.temp.reader.ReadExcelConfig;
import cn.chenhuanming.octopus.core.temp.reader.SheetReader;
import cn.chenhuanming.octopus.entity.Applicants;
import cn.chenhuanming.octopus.core.config.ConfigReader;
import cn.chenhuanming.octopus.model.DefaultCellPosition;
import cn.chenhuanming.octopus.core.read.XmlConfigReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenhuanming
 * Created at 2019-01-07
 */
public class DefaultSheetReaderTest {

    private Sheet sheet;

    @Before
    public void prepare() throws IOException, InvalidFormatException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("export.xlsx");
        Workbook workbook = WorkbookFactory.create(is);
        this.sheet = workbook.getSheetAt(0);
    }

    @Test
    public void test() {
        ConfigReader configReader = new XmlConfigReader(this.getClass().getClassLoader().getResourceAsStream("applicants.xml"));

        final SheetReader<Applicants> sheetReader = new DefaultSheetReader<>(sheet, configReader, new DefaultCellPosition(2, 0));

        for (Applicants applicants : sheetReader) {
            System.out.println(applicants);
        }

    }

    @Test
    public void test1() {
        final XmlCellDefinitionReader xmlCellDefinitionReader = new XmlCellDefinitionReader(this.getClass().getClassLoader().getResourceAsStream("applicants.xml"));
        ReadExcelConfig config = xmlCellDefinitionReader.loadReadExcelConfig();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("export.xlsx");
        ReadContext readContext = new ReadContext(config, is, false);


        SheetReader<Applicants> sheetReader = readContext.readSheet(new DefaultCellPosition(4, 0));
        for (Applicants applicants : sheetReader) {
            System.out.println(applicants);
        }
    }

}