package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.formatter.Formatter;
import lombok.Data;

import java.util.Date;

/**
 * @author zhuangzf
 * @date 2019/1/21 19:44
 */
@Data
public class FieldProperty {
    private String name;
    private String description;
    private String defaultValue;
    private Formatter<Date> dateFormat;
    private Formatter formatter;
}
