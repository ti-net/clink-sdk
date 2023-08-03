package com.tinet.oskit.adapter.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:2021/8/9
 * Author:liuzr
 * Description:
 */
public class HorizontalItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 间隔尺寸，像素
     */
    private int size;

    public HorizontalItemDecoration(int size) {
        this.size = size;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, size);
    }
}
