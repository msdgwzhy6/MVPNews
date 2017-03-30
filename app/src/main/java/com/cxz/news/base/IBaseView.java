package com.cxz.news.base;

//import com.trello.rxlifecycle.LifecycleTransformer;

import com.cxz.news.widgit.EmptyLayout;
import com.trello.rxlifecycle.LifecycleTransformer;


/**
 * Created by chenxz on 2017/3/25.
 * 基础 BaseView接口
 */
public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误
     */
    void showNetError(EmptyLayout.OnRetryListener onRetryListener);

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T>LifecycleTransformer<T> bindToLife();

    /**
     * 完成刷新
     */
    void finishRefresh();

}
