package com.tinet.oskit.adapter.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: TIMSDK
 * @ClassName: DividerGridItemDecoration
 * @Author: liuzr
 * @CreateDate: 2021-09-29 13:56
 * @Description: 下方有间距
 */
public class BottomDecoration extends RecyclerView.ItemDecoration {

    private int bottom;

    public BottomDecoration(int bottom) {
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = bottom;
    }
}
