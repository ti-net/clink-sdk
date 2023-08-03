package com.tinet.threepart.keyboard;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.tinet.threepart.tools.TDensityUtil;
import com.tinet.threepart.tools.TSPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: KeyBoardObservable
 * @Author: liuzr
 * @CreateDate: 2021-10-21 10:04
 * @Description: 监听软键盘的 可被观察者
 */
public class KeyBoardObservable {

    public static final String KeyBoardHeight = "KeyBoardHeight";

    private int lastHeight;
    private List<KeyBoardObserver> observers;

    private boolean keyBoardVisibile;

    /**
     * 注册监听
     *
     * @param listener
     */
    public void register(@NonNull KeyBoardObserver listener) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(listener);
    }

    /**
     * 注销
     *
     * @param listener
     */
    public void unRegister(@NonNull KeyBoardObserver listener) {
        if (observers != null) {
            observers.remove(listener);
        }
    }

    /**
     * 抢先测量
     *
     * @param heightMeasureSpec
     */
    public void beforeMeasure(Context context, int heightMeasureSpec) {
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        if (lastHeight == 0) {
            lastHeight = height;
            return;
        }
        if (lastHeight == height) {
            return;
        }
        final int offset = lastHeight - height;
        if (Math.abs(offset) < TDensityUtil.dp2px(80)) {
            return;
        }

        if (offset > 0) {
            keyBoardVisibile = true;
        } else {
            keyBoardVisibile = false;
        }
        int keyBoardHeight = Math.abs(offset);
        TSPUtils.getInstance(context).putInt(KeyBoardHeight, keyBoardHeight);

        update(keyBoardVisibile, keyBoardHeight);
        lastHeight = height;
    }

    public boolean isKeyBoardVisibile() {
        return keyBoardVisibile;
    }

    /**
     * 通知更新
     *
     * @param keyBoardVisibile
     */
    private void update(final boolean keyBoardVisibile, int keyBoardHeight) {
        if (observers != null) {
            for (KeyBoardObserver observable : observers) {
                observable.update(keyBoardVisibile, keyBoardHeight);
            }
        }
    }

}
