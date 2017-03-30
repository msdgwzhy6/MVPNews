package com.cxz.news.base;

import rx.functions.Action1;

/**
 * Created by chenxz on 2017/3/26.
 * RxBus Presenter
 */
public interface IRxBusPresenter extends IBasePresenter {

    /**
     * 注册
     * @param eventType
     * @param action
     * @param <T>
     */
    <T> void registerRxBus(Class<T> eventType, Action1<T> action);

    /**
     * 注销
     */
    void unregisterRxBus();

}
