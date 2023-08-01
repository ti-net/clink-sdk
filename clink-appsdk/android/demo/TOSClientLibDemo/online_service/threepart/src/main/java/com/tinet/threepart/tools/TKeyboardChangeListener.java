package com.tinet.threepart.tools;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * @ProjectName: TIMSDK
 * @ClassName: KeyboardChangeListener
 * @Author: zhangping
 * @CreateDate: 2020/7/25 14:18
 * @Description: 描述说明
 */
public class TKeyboardChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = "ListenerHandler";
    private View mContentView;
    private int mOriginHeight;
    private int mPreHeight;
    private KeyBoardListener mKeyBoardListen;

    public interface KeyBoardListener {
        /**
         * call back
         *
         * @param isShow         true is show else hidden
         * @param keyboardHeight keyboard height
         */
        void onKeyboardChange(boolean isShow, int keyboardHeight);
    }

    public void setKeyBoardListener(KeyBoardListener keyBoardListen) {
        this.mKeyBoardListen = keyBoardListen;
    }

    public TKeyboardChangeListener(Activity contextObj) {
        if (contextObj == null) {
            return;
        }
        mContentView = findContentView(contextObj);
        if (mContentView != null) {
            addContentTreeObserver();
        }
    }

    private View findContentView(Activity contextObj) {
        return contextObj.findViewById(android.R.id.content);
    }

    private void addContentTreeObserver() {
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        int currHeight = mContentView.getHeight();
        if (currHeight == 0) {
            return;
        }
        boolean hasChange = false;
        if (mPreHeight == 0) {
            mPreHeight = currHeight;
            mOriginHeight = currHeight;
        } else {
            if (mPreHeight != currHeight) {
                hasChange = true;
                mPreHeight = currHeight;
            } else {
                hasChange = false;
            }
        }
        if (hasChange) {
            boolean isShow;
            int keyboardHeight = 0;
            if (mOriginHeight == currHeight) {
                //hidden
                isShow = false;
            } else {
                //show
                keyboardHeight = mOriginHeight - currHeight;
                isShow = true;
            }

            if (mKeyBoardListen != null) {
                mKeyBoardListen.onKeyboardChange(isShow, keyboardHeight);
            }
        }
    }
}
