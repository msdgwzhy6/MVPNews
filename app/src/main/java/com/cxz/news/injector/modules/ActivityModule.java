package com.cxz.news.injector.modules;

import android.app.Activity;

import com.cxz.news.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenxz on 2017/3/25.
 * Activity Module
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @PerActivity
    @Provides
    Activity getActivity() {
        return mActivity;
    }
}
