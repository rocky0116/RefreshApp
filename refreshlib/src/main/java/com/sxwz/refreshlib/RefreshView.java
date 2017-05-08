package com.sxwz.refreshlib;

import android.content.Context;
import android.util.AttributeSet;

import com.sxwz.refreshlib.loadmore.DefaultLoadMoreViewFooter;
import com.sxwz.refreshlib.loadmore.ILoadMoreViewFactory;


/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/5/4 14:45
 * description:
 *****************************************************/
public class RefreshView extends BaseRefreshView {

    private DefaultHeader mPtrClassicHeader;

    public RefreshView(Context context) {
        super(context);
        initViews();
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        mPtrClassicHeader = new DefaultHeader(getContext());
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);

        ILoadMoreViewFactory loadMoreViewFactory = new DefaultLoadMoreViewFooter();
        setFooterView(loadMoreViewFactory);
    }

    public DefaultHeader getHeader() {
        return mPtrClassicHeader;
    }

    /**
     * Specify the last update time by this key string
     *
     * @param key
     */
    public void setLastUpdateTimeKey(String key) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeKey(key);
        }
    }

    /**
     * Using an object to specify the last update time.
     *
     * @param object
     */
    public void setLastUpdateTimeRelateObject(Object object) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeRelateObject(object);
        }
    }
}
