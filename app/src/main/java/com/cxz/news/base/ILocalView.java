package com.cxz.news.base;

import java.util.List;

/**
 * Created by chenxz on 2017/3/26.
 * 和本地数据关联的界面接口
 */
public interface ILocalView<T> {

    /**
     * 显示数据
     * @param dataList 数据
     */
    void loadData(List<T> dataList);

    /**
     * 没有数据
     */
    void noData();

}
