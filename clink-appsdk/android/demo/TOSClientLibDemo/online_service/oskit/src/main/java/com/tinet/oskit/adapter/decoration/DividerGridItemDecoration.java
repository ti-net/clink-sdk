package com.tinet.oskit.adapter.decoration;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: TIMSDK
 * @ClassName: DividerGridItemDecoration
 * @Author: liuzr
 * @CreateDate: 2021-09-29 13:56
 * @Description:
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint;
    private PathEffect effects = new DashPathEffect(new float[]{8, 8, 8, 8}, 0);

    public DividerGridItemDecoration(int color) {
        paint = new Paint();
        paint.setPathEffect(effects);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();
            final int childCount = parent.getChildCount();

            for (int i = 1; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getTop() - params.topMargin;

                Path path = new Path();
                path.moveTo(left, top);
                path.lineTo(right, top);
                c.drawPath(path, paint);
            }

        } else { //horizontal
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();
            final int childCount = parent.getChildCount();

            for (int i = 1; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int left = child.getLeft() - params.leftMargin;

                Path path = new Path();
                path.moveTo(left, top);
                path.lineTo(left, bottom);
                c.drawPath(path, paint);
            }
        }
    }

    private int getOrientation(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            return layoutManager.getOrientation();
        } else
            throw new IllegalStateException("DividerItemDecoration can only be used with a LinearLayoutManager.");
    }

}
