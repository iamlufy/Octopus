package cn.chenhuanming.octopus.example;

import cn.chenhuanming.octopus.core.AbstractWriterTest;
import cn.chenhuanming.octopus.core.Octopus;
import cn.chenhuanming.octopus.core.config.Config;
import cn.chenhuanming.octopus.core.config.ConfigReader;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author chenhuanming
 * Created at 2019-01-12
 */
public class ApplicantExample extends AbstractWriterTest {
    @Test
    public void export() throws IOException {
        String rootPath = this.getClass().getClassLoader().getResource("").getPath();
        FileOutputStream os = new FileOutputStream(rootPath + "/applicator.xlsx");

        ConfigReader configReader = Octopus.getXMLConfigReader(this.getClass().getClassLoader().getResourceAsStream("applicants.xml"));
        Config config = configReader.getConfig();
        Octopus.writeOneSheet(os, configReader, "test", applicantsList);
    }
}
