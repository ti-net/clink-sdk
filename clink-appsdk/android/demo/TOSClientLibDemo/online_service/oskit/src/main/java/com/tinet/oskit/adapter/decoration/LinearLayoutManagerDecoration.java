package com.tinet.oskit.adapter.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Time:2021/8/9
 * Author:liuzr
 * Description:
 */
public class LinearLayoutManagerDecoration extends RecyclerView.ItemDecoration {

    private Context context;

    /**
     * 间隔尺寸，像素
     */
    private int size;

    /**
     * 间隔颜色
     */
    private int color;

    /**
     * 列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
     */
    private int orientation = LinearLayoutManager.HORIZONTAL;

    private Paint mPaint = null;

    private Drawable mDivider = null;

    private boolean isSessionList = false;

    public LinearLayoutManagerDecoration(Context context, int size, int color) {
        this(context, size, color, LinearLayoutManager.HORIZONTAL);
    }

    public LinearLayoutManagerDecoration(Context context, int size, int color,boolean isSessionList) {
        this(context, size, color, LinearLayoutManager.HORIZONTAL);
        this.isSessionList = isSessionList;
    }

    public LinearLayoutManagerDecoration(Context context, int size, int color, int orientation) {
        this.context = context;
        this.size = size;
        this.color = color;
        this.orientation = orientation;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);

        TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int top = size;
        int bottom = 0;

        if(isSessionList) {
            if (parent != null && view != null) {
                int position = parent.getChildAdapterPosition(view);

                if (0 == position) {
                    bottom = size;
                }
            }
        }

        outRect.set(0, top, 0, bottom);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation != LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 绘制横向 item 分割线
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + size;

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);

            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    /**
     * 绘制纵向 item 分割线
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + size;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}
