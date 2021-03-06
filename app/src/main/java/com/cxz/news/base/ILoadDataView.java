package com.cxz.news.base;

/**
 * Created by chenxz on 2017/3/26.
 * 加载数据的界面接口
 */
public interface ILoadDataView<T> extends IBaseView {

    /**
     * 加载数据
     * @param data 数据
     */
    void loadData(T data);

    /**
     * 加载更多
     * @param data 数据
     */
    void loadMoreData(T data);

    /**
     * 没有数据
     */
    void loadNoData();

}
