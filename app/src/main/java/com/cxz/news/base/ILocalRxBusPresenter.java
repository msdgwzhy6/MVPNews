package com.cxz.news.base;

import java.util.List;

/**
 * Created by chenxz on 2017/3/26.
 */
public interface ILocalRxBusPresenter<E> extends IRxBusPresenter {

    /**
     * 插入数据
     * @param data 数据
     */
    void insert(E data);

    /**
     * 删除数据
     * @param data 数据
     */
    void delete(E data);

    /**
     * 更新数据
     * @param list 所有数据
     */
    void update(List<E> list);

}
