package com.tinet.oskit.adapter.layout;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @ProjectName: TIMSDK
 * @ClassName: NoSrcollLinearLayoutManager
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:17
 * @Description:
 */
public class NoSrcollLinearLayoutManager extends LinearLayoutManager {
    public NoSrcollLinearLayoutManager(Context context) {
        super(context);
    }

    public NoSrcollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public NoSrcollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
