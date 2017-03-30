package com.cxz.news.module.news.channel;

import com.cxz.news.local.NewsTypeInfo;

import java.util.List;

/**
 * Created by chenxz on 2017/3/26.
 * 栏目管理接口
 */
public interface IChannelView {

    /**
     * 显示数据
     * @param checkList 选中栏目
     * @param uncheckList 未选中栏目
     */
    void loadData(List<NewsTypeInfo> checkList, List<NewsTypeInfo> uncheckList);

}
