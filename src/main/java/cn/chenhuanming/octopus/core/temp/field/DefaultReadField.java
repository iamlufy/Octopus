package cn.chenhuanming.octopus.core.temp.field;

import lombok.Data;

/**
 * 默认读域
 *
 * @author zhuangzf
 * @date 2019/1/17 9:22
 */
@Data
public class DefaultReadField {
    /**
     * 匹配配置文件的属性，只包含内容
     */
    private MappedField mappedField;
}
