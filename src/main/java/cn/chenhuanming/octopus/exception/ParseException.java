package cn.chenhuanming.octopus.exception;

import cn.chenhuanming.octopus.core.temp.field.impl.FullField;
import cn.chenhuanming.octopus.model.CellPosition;
import cn.chenhuanming.octopus.core.Field;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chenhuanming
 * Created at 2019-01-08
 */
@Getter
@Setter
public class ParseException extends Exception {
    private CellPosition cellPosition;
    private Field field;
    private FullField fullField;

    ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
