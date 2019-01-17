package cn.chenhuanming.octopus.core.temp;

import cn.chenhuanming.octopus.core.temp.reader.ReadExcelConfig;
import cn.chenhuanming.octopus.core.temp.writer.WriteExcelConfig;

import java.io.IOException;

/**
 * @author zhuangzf
 * @date 2019/1/16 10:16
 */
public interface CellDefinitionReader {

    /**
     *
     *
     * @return
     * @throws IOException
     */
    ReadExcelConfig loadReadExcelConfig() throws IOException;

    /**
     *
     *
     * @return
     * @throws IOException
     */
    WriteExcelConfig loadWriteExcelConfig() throws IOException;

}
