package com.tinet.oskit.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetRecyclerView
 * @Author: liuzr
 * @CreateDate: 2021-08-31 12:29
 * @Description:
 */
public class TinetRecyclerView extends RecyclerView {

    private boolean move = false;
    private int mIndex = 0;

    public TinetRecyclerView(Context context) {
        this(context, null);
    }

    public TinetRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TinetRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addOnScrollListener(new RecyclerViewListener());
    }

    /**
     * 滚动到指定位置
     */
    public void moveToPosition(int position) {
        if (position < 0 || position >= getAdapter().getItemCount()) {
            return;
        }
        mIndex = position;
        stopScroll();

        if (getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager glm = (LinearLayoutManager) getLayoutManager();
            int firstItem = glm.findFirstVisibleItemPosition();
            int lastItem = glm.findLastVisibleItemPosition();
            if (position <= firstItem) {
                this.scrollToPosition(position);
            } else if (position <= lastItem) {
                int top = this.getChildAt(position - firstItem).getTop();
                this.scrollBy(0, top);
            } else {
                this.scrollToPosition(position);
                move = true;
            }
        }
    }

    /**
     * RecyclerView的滚动监听, 用于平滑滚动条目置顶
     */
    class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager glm = (LinearLayoutManager) getLayoutManager();

                if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    move = false;
                    int n = mIndex - glm.findFirstVisibleItemPosition();
                    if (0 <= n && n < getChildCount()) {
                        int top = getChildAt(n).getTop();
                        smoothScrollBy(0, top);
                    }

                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager glm = (LinearLayoutManager) getLayoutManager();
                if (move) {
                    move = false;
                    int n = mIndex - glm.findFirstVisibleItemPosition();
                    if (0 <= n && n < getChildCount()) {
                        int top = getChildAt(n).getTop();
                        scrollBy(0, top);
                    }
                }
            }
        }
    }
}
