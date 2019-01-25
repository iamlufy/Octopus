package cn.chenhuanming.octopus.core.temp.field;

import cn.chenhuanming.octopus.core.read.ImportValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chenhuanming
 * Created at 2018/12/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DefaultWriteField extends AbstractField implements ImportValidation {


    private boolean blankAble;

    private List<String> options;

    protected Pattern regex;




}
