package cn.chenhuanming.octopus.core;

import cn.chenhuanming.octopus.core.config.ConfigReader;
import cn.chenhuanming.octopus.core.temp.DefaultExcelConfig;
import cn.chenhuanming.octopus.core.temp.configreader.XmlCellDefinitionReader;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author chenhuanming
 * Created at 2018/12/20
 */
public class OctopusTest extends AbstractWriterTest {

    @Test
    public void export() throws IOException {
        String rootPath = this.getClass().getClassLoader().getResource("").getPath();
        FileOutputStream os = new FileOutputStream(rootPath + "/octopusExport.xlsx");

        ConfigReader configReader = Octopus.getXMLConfigReader(this.getClass().getClassLoader().getResourceAsStream("applicants.xml"));
//        final Config config1 = configReader.getConfig();9 777  6 m,
        final XmlCellDefinitionReader xmlCellDefinitionReader = new XmlCellDefinitionReader(this.getClass().getClassLoader().getResourceAsStream("applicants.xml"));
        DefaultExcelConfig config = xmlCellDefinitionReader.readConfig();
        System.out.println();
//        Octopus.writeOneSheet(os, configReader, "test", applicantsList);
    }
}