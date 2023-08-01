package com.tinet.oskit.adapter.layout;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: TIMSDK
 * @ClassName: NoScrollLinearLayoutManager
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:17
 * @Description:
 */
public class NoScrollLinearLayoutManager extends LinearLayoutManager {
    public NoScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public NoScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public NoScrollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    @Override
//    public boolean canScrollVertically() {
//        return false;
//    }


    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        if(view != null){
            view.setNestedScrollingEnabled(false);
        }
    }
}
