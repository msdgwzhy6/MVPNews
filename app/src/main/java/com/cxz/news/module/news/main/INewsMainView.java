package com.cxz.news.module.news.main;

import com.cxz.news.local.NewsTypeInfo;

import java.util.List;

/**
 * Created by chenxz on 2017/3/26.
 */
public interface INewsMainView {

    /**
     * 显示数据
     * @param checkList
     */
    void loadData(List<NewsTypeInfo> checkList);

}
