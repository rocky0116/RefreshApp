package com.sxwz.refreshlib;


import com.sxwz.refreshlib.indicator.PtrIndicator;

/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/5/4 14:45
 * description:
 *****************************************************/
class UIHandlerHolder implements UIHandler {

    private UIHandler mHandler;
    private UIHandlerHolder mNext;

    private boolean contains(UIHandler handler) {
        return mHandler != null && mHandler == handler;
    }

    private UIHandlerHolder() {

    }

    public boolean hasHandler() {
        return mHandler != null;
    }

    private UIHandler getHandler() {
        return mHandler;
    }

    public static void addHandler(UIHandlerHolder head, UIHandler handler) {

        if (null == handler) {
            return;
        }
        if (head == null) {
            return;
        }
        if (null == head.mHandler) {
            head.mHandler = handler;
            return;
        }

        UIHandlerHolder current = head;
        for (; ; current = current.mNext) {

            // duplicated
            if (current.contains(handler)) {
                return;
            }
            if (current.mNext == null) {
                break;
            }
        }

        UIHandlerHolder newHolder = new UIHandlerHolder();
        newHolder.mHandler = handler;
        current.mNext = newHolder;
    }

    public static UIHandlerHolder create() {
        return new UIHandlerHolder();
    }

    public static UIHandlerHolder removeHandler(UIHandlerHolder head, UIHandler handler) {
        if (head == null || handler == null || null == head.mHandler) {
            return head;
        }

        UIHandlerHolder current = head;
        UIHandlerHolder pre = null;
        do {

            // delete current: link pre to next, unlink next from current;
            // pre will no change, current move to next element;
            if (current.contains(handler)) {

                // current is head
                if (pre == null) {

                    head = current.mNext;
                    current.mNext = null;

                    current = head;
                } else {

                    pre.mNext = current.mNext;
                    current.mNext = null;
                    current = pre.mNext;
                }
            } else {
                pre = current;
                current = current.mNext;
            }

        } while (current != null);

        if (head == null) {
            head = new UIHandlerHolder();
        }
        return head;
    }

    @Override
    public void onUIReset(BaseRefreshView frame) {
        UIHandlerHolder current = this;
        do {
            final UIHandler handler = current.getHandler();
            if (null != handler) {
                handler.onUIReset(frame);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onUIRefreshPrepare(BaseRefreshView frame) {
        if (!hasHandler()) {
            return;
        }
        UIHandlerHolder current = this;
        do {
            final UIHandler handler = current.getHandler();
            if (null != handler) {
                handler.onUIRefreshPrepare(frame);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onUIRefreshBegin(BaseRefreshView frame) {
        UIHandlerHolder current = this;
        do {
            final UIHandler handler = current.getHandler();
            if (null != handler) {
                handler.onUIRefreshBegin(frame);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onUIRefreshComplete(BaseRefreshView frame) {
        UIHandlerHolder current = this;
        do {
            final UIHandler handler = current.getHandler();
            if (null != handler) {
                handler.onUIRefreshComplete(frame);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onUIPositionChange(BaseRefreshView frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        UIHandlerHolder current = this;
        do {
            final UIHandler handler = current.getHandler();
            if (null != handler) {
                handler.onUIPositionChange(frame, isUnderTouch, status, ptrIndicator);
            }
        } while ((current = current.mNext) != null);
    }
}
