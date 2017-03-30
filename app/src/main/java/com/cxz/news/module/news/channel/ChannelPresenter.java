package com.cxz.news.module.news.channel;

import com.cxz.news.local.NewsTypeInfo;
import com.cxz.news.rxbus.RxBus;
import com.cxz.news.rxbus.event.ChannelEvent;

import java.util.List;

/**
 * Created by chenxz on 2017/3/26.
 */
public class ChannelPresenter implements IChannelPresenter<NewsTypeInfo> {

    private IChannelView mView;
    //private final NewsTypeInfoDao mDbDao;
    private RxBus mRxBus;

    @Override
    public void swap(int fromPos, int toPos) {
        mRxBus.post(new ChannelEvent(ChannelEvent.SWAP_EVENT, fromPos, toPos));
    }

    @Override
    public void insert(NewsTypeInfo data) {

    }

    @Override
    public void delete(NewsTypeInfo data) {

    }

    @Override
    public void update(List<NewsTypeInfo> list) {

    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }
}
