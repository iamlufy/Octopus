package cn.chenhuanming.octopus.util;

import org.apache.poi.ss.usermodel.BorderStyle;

/**
 * @author zhuangzf
 * @date 2019/1/16 14:21
 */
public class StyleUtils {
    public static BorderStyle[] convertBorder(String border) {
        BorderStyle[] result = new BorderStyle[4];
        String[] split = border.split(",");
        for (int i = 0; i < split.length; i++) {
            short val = Short.parseShort(split[i]);
            BorderStyle style = BorderStyle.valueOf(val);
            result[i] = style;
        }
        return result;
    }
}
