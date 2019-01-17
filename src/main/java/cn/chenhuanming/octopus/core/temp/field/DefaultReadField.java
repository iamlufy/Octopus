package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.formatter.Formatter;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * @author zhuangzf
 * @date 2019/1/17 9:22
 */
@Data
public class DefaultReadField implements ReadField {
    protected String name;
    protected String description;
    protected String defaultValue;
    protected Formatter<Date> dateFormat;
    protected Formatter formatter;

    protected Method picker;
    protected Method pusher;

    protected List<Field> children;

    @Override
    public List<Field> getChildren() {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}
