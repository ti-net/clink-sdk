package com.tinet.oskit.adapter.layout;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @ProjectName: TIMSDK
 * @ClassName: ReverseLinearLayoutManager
 * @Author: liuzr
 * @CreateDate: 2021-08-23 16:06
 * @Description: 倒置的适配器
 */
public class ReverseLinearLayoutManager extends LinearLayoutManager {

    public ReverseLinearLayoutManager(Context context) {
        super(context);
        init();
    }

    public ReverseLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init();
    }

    public ReverseLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setReverseLayout(true);
    }
}
