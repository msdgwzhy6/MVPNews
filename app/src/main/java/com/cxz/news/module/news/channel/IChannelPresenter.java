package com.cxz.news.module.news.channel;

import com.cxz.news.base.ILocalPresenter;

/**
 * Created by chenxz on 2017/3/26.
 */
public interface IChannelPresenter<T> extends ILocalPresenter<T> {

    /**
     * 交换
     * @param fromPos
     * @param toPos
     */
    void swap(int fromPos,int toPos);
}
