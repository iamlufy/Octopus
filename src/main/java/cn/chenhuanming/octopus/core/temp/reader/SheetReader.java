package cn.chenhuanming.octopus.core.temp.reader;

/**
 * Created by Administrator on 2017-06-10.
 */
public interface SheetReader<T> extends Iterable<T>{
    T get(int i);

    int size();
}
