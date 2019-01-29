package cn.chenhuanming.octopus.core.read;

import cn.chenhuanming.octopus.core.temp.ExcelConfig;
import cn.chenhuanming.octopus.core.temp.reader.ReadExcelConfig;
import cn.chenhuanming.octopus.exception.ParseException;
import cn.chenhuanming.octopus.model.CellPosition;
import cn.chenhuanming.octopus.core.Field;
import cn.chenhuanming.octopus.formatter.Formatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * @author chenhuanming
 * Created at 2018/12/20
 */
public abstract class AbstractSheetReader<T> implements SheetReader<T> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractSheetReader.class);

    protected Sheet sheet;
    protected ExcelConfig excelConfig;
    protected CellPosition startPoint;

    public AbstractSheetReader(Sheet sheet, ReadExcelConfig readExcelConfig, CellPosition startPoint) {
        if (sheet == null || excelConfig == null || startPoint == null) {
            throw new NullPointerException();
        }
        this.sheet = sheet;
        this.excelConfig = readExcelConfig.getExcelConfig();

        this.startPoint = startPoint;
    }

    @Override
    public T get(int i) {
        T t = newInstance(excelConfig.getClassType());

        int col = startPoint.getCol();
        for (Field field : excelConfig.getFields()) {
            col = read(startPoint.getRow() + i, col, field, t);
        }
        return t;
    }

    /**
     * set value into object
     *
     * @param str
     * @param field
     * @param o
     */
    protected void setValue(String str, Field field, Object o) throws ParseException {
        Method pusher = field.getPusher();

        Object value = null;

        if (field.getFormatter() != null) {
            value = field.getFormatter().parse(str);
        } else {
            Formatter globalFormatter = excelConfig.getConfig().getFormatterContainer().get(pusher.getParameterTypes()[0]);
            if (globalFormatter != null) {
                value = globalFormatter.parse(str);
            } else {
                value = str;
            }
        }

        try {
            if (value != null) {
                pusher.invoke(o, value);
            }
        } catch (Exception e) {
            LOGGER.error("can not set value:" + field.getName(), e);
            throw new ParseException("invoke method failed", e);
        }
    }

    protected T newInstance(Class classType) {
        try {
            return (T) excelConfig.getConfig().getClassType().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("wrong type or no default constructor", e);
        }
    }

    @Override
    public int size() {
        return sheet.getLastRowNum() + 1;
    }

    abstract int read(int row, int col, Field field, Object o);

    @Override
    public Iterator<T> iterator() {
        return new RowIterator<T>(sheet.getLastRowNum() - startPoint.getRow(), 0);
    }

    private class RowIterator<T> implements Iterator<T> {
        private int last;
        private int cursor;

        public RowIterator(int last, int cursor) {
            this.last = last;
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return cursor <= last;
        }

        @Override
        public T next() {
            return (T) get(cursor++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
