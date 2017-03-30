package com.cxz.news.module.news.main;

import com.cxz.news.local.dao.NewsTypeDao;
import com.cxz.news.rxbus.RxBus;
import com.cxz.news.base.IRxBusPresenter;
import com.cxz.news.utils.XLog;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by chenxz on 2017/3/26.
 */
public class NewsMainPresenter implements IRxBusPresenter {

    private INewsMainView mView;
    private NewsTypeDao mDbDao;
    private RxBus mRxBus;

    @Override
    public <T> void registerRxBus(Class<T> eventType, Action1<T> action) {
        Subscription subscription = mRxBus.doSubscribe(eventType, action, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                XLog.e(throwable.toString());
            }
        });
        mRxBus.addSubscription(this, subscription);
    }

    @Override
    public void unregisterRxBus() {
        mRxBus.unSubscribe(this);
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }
}
