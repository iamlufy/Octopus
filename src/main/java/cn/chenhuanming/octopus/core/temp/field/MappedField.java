package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.core.Field;
import lombok.Data;

import java.util.List;

/**
 * 匹配配置文件的属性，只包含内容
 *
 * @author zhuangzf
 * @date 2019/1/21 19:26
 */
@Data
public class MappedField implements Field {

    /**
     * excel单元格信息属性
     */
    private FieldProperty fieldProperty;


    private List<? extends MappedField> children;


}
