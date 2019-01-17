package cn.chenhuanming.octopus.core.config;

import cn.chenhuanming.octopus.core.Field;
import cn.chenhuanming.octopus.formatter.FormatterContainer;

import java.util.List;

/**
 * @author chenhuanming
 * Created at 2018/12/17
 */
public interface Config {
    Class getClassType();

    FormatterContainer getFormatterContainer();

    List<Field> getFields();
}
