package com.cxz.news.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cxz.news.App;
import com.cxz.news.R;
import com.cxz.news.widgit.EmptyLayout;
import com.cxz.news.injector.modules.ActivityModule;
import com.cxz.news.utils.SwipeRefreshHelper;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenxz on 2017/3/25.
 * 基础 Activity
 */
public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView {

    /**
     * 把 EmptyLayout 放在基类统一处理，@Nullable 表明 View 可以为 null
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;

    /**
     * 把 Presenter 提取到基类需要配合基类的 initInjector() 进行注入，如果继承这个基类则必定要提供一个 Presenter 注入方法，
     * 该APP所有 Presenter 都是在 Module 提供注入实现，也可以选择提供另外不带 Presenter 的基类
     */
    @Inject
    protected T mPresenter;

    /**
     * 刷新控件，注意，资源ID一定要一样
     */
    @Nullable
//    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    /**
     * 绑定布局文件
     * @return  布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews(boolean isRefresh);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initInjector();
        initViews();
        initSwipeRefresh();
        updateViews(false);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    /**
     * 初始化下拉刷新
     */
    private void initSwipeRefresh() {
        if (mSwipeRefresh != null) {
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateViews(true);
                }
            });
        }
    }

    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError(final EmptyLayout.OnRetryListener onRetryListener) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(onRetryListener);
        }
    }

    @Override
    public void finishRefresh() {
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }


    /**
     * 获取 Application
     *
     * @return  Application
     */
    protected App getApp() {
        return App.getApp();
    }

    /**
     * 获取 ActivityModule
     *
     * @return ActivityModule
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // 设置tag，不然下面 findFragmentByTag(tag)找不到
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
