package com.sxwz.refreshlib;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.AbsListView;
/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/5/4 14:45
 * description:
 *****************************************************/
public abstract class DefaultHandler implements RefreshHandler {

    public static boolean canChildScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() <
                        absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(view, -1) || view.getScrollY() > 0;
            }
        } else {
          return view.canScrollVertically(-1);
        }
    }

    /**
     * 检查是否能下拉刷新
     *
     * @param frame
     * @param content
     * @param header
     * @return
     */
    public static boolean checkContentCanBePulledDown(BaseRefreshView frame, View content, View header) {
        return !canChildScrollUp(content);
    }

    @Override
    public boolean checkCanDoRefresh(BaseRefreshView frame, View content, View header) {
        return checkContentCanBePulledDown(frame, content, header);
    }
}