package com.sxwz.refreshlib;

import android.view.View;

/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/5/4 14:45
 * description:
 *****************************************************/
public interface RefreshHandler {

    /**
     * 检查是否可以刷新，判断第一个视图是否为空
     * @param frame
     * @param content
     * @param header
     * @return
     */
    public boolean checkCanDoRefresh(final BaseRefreshView frame, final View content, final View header);

    /**
     * 刷新开始
     *
     * @param frame
     */
    public void onRefreshBegin(final BaseRefreshView frame);
}