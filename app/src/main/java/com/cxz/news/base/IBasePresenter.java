package com.cxz.news.base;

/**
 * Created by chenxz on 2017/3/25.
 * 基础 Presenter
 */
public interface IBasePresenter {

    /**
     * 获取网络数据，更新界面
     * @param isRefresh 用来判断是否为下拉刷新调用，下拉刷新的时候不应该再显示加载界面和异常界面
     */
    void getData(boolean isRefresh);

    /**
     * 加载更多数据
     */
    void getMoreData();

}
