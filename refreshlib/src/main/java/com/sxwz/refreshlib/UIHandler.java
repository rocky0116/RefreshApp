package com.sxwz.refreshlib;


import com.sxwz.refreshlib.indicator.PtrIndicator;
/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/5/4 14:45
 * description:
 *****************************************************/
public interface UIHandler {

    /**
     * When the content view has reached top and refresh has been completed, view will be reset.
     *
     * @param frame
     */
    public void onUIReset(BaseRefreshView frame);

    /**
     * prepare for loading
     *
     * @param frame
     */
    public void onUIRefreshPrepare(BaseRefreshView frame);

    /**
     * perform refreshing UI
     */
    public void onUIRefreshBegin(BaseRefreshView frame);

    /**
     * perform UI after refresh
     */
    public void onUIRefreshComplete(BaseRefreshView frame);

    public void onUIPositionChange(BaseRefreshView frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator);
}
