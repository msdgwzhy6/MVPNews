package com.cxz.news.widgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cxz.news.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenxz on 2017/3/25.
 */
public class EmptyLayout extends FrameLayout {

    public static final int STATUS_HIDE = 0x1001;
    public static final int STATUS_LOADING = 0x0001;
    public static final int STATUS_NO_NET = 0x0002;
    public static final int STATUS_NO_DATA = 0x0003;

    private Context mContext;
    private int mBgColor;
    private int mEmptyStatus = STATUS_LOADING;
    private OnRetryListener mOnRetryListener;

    @BindView(R.id.empty_layout)
    FrameLayout mFrameLayout;
    @BindView(R.id.tv_net_error)
    TextView mTvEmptyMessage;
    @BindView(R.id.rl_empty_container)
    View mRlEmptyContainer;
//    @BindView(R.id.empty_loading)
//    SpinKitView mEmptyLoading;

    public EmptyLayout(Context context) {
        this(context,null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    /**
     * 初始化
     * @param attrs
     */
    private void init(AttributeSet attrs) {
        TypedArray type = mContext.obtainStyledAttributes(attrs,R.styleable.EmptyLayout);
        try {
            mBgColor = type.getColor(R.styleable.EmptyLayout_background_color, Color.WHITE);
        }finally {
            type.recycle();
        }
        View.inflate(mContext,R.layout.layout_empty_loading,this);
        ButterKnife.bind(this);
        mFrameLayout.setBackgroundColor(mBgColor);

    }

    /**
     * 隐藏视图
     */
    public void hide() {
        mEmptyStatus = STATUS_HIDE;
        switchEmptyView();
    }

    /**
     * 设置状态
     *
     * @param emptyStatus
     */
    public void setEmptyStatus(@EmptyStatus int emptyStatus) {
        mEmptyStatus = emptyStatus;
        switchEmptyView();
    }

    /**
     * 获取状态
     * @return  状态
     */
    public int getEmptyStatus() {
        return mEmptyStatus;
    }

    /**
     * 设置异常消息
     *
     * @param msg 显示消息
     */
    public void setEmptyMessage(String msg) {
        mTvEmptyMessage.setText(msg);
    }

    public void hideErrorIcon() {
        mTvEmptyMessage.setCompoundDrawables(null, null, null, null);
    }

//    public void setLoadingIcon(Sprite d) {
//        mEmptyLoading.setIndeterminateDrawable(d);
//    }

    /**
     * 切换视图
     */
    private void switchEmptyView() {
        switch (mEmptyStatus) {
            case STATUS_LOADING:
                setVisibility(VISIBLE);
                mRlEmptyContainer.setVisibility(GONE);
//                mEmptyLoading.setVisibility(VISIBLE);
                break;
            case STATUS_NO_DATA:
            case STATUS_NO_NET:
                setVisibility(VISIBLE);
//                mEmptyLoading.setVisibility(GONE);
                mRlEmptyContainer.setVisibility(VISIBLE);
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                break;
        }
    }

    /**
     * 设置重试监听器
     *
     * @param retryListener 监听器
     */
    public void setRetryListener(OnRetryListener retryListener) {
        this.mOnRetryListener = retryListener;
    }

    @OnClick(R.id.tv_net_error)
    public void onClick() {
        if (mOnRetryListener != null) {
            mOnRetryListener.onRetry();
        }
    }

    /**
     * 点击重试监听器
     */
    public interface OnRetryListener {
        void onRetry();
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_LOADING, STATUS_NO_NET, STATUS_NO_DATA})
    public @interface EmptyStatus{}
}
