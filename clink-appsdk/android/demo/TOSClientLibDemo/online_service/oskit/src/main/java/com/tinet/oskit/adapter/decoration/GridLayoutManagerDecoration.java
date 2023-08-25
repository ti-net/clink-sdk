package com.tinet.oskit.adapter.decoration;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;

/**
 * @author: liuzeren
 * @date: 2023/8/9
 */
public class GridLayoutManagerDecoration extends ItemDecoration {

  private int distance;

  public GridLayoutManagerDecoration(int distance) {
    this.distance = distance;
  }

  @Override
  public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
      @NonNull RecyclerView parent, @NonNull State state) {
    super.getItemOffsets(outRect, view, parent, state);
    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if(layoutManager instanceof GridLayoutManager){
      GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
      int spanCount = gridLayoutManager.getSpanCount();
      int count = gridLayoutManager.getItemCount();
      int position = parent.getChildAdapterPosition(view);

      int left = 0;
      int right = 0;
      int top = 0;
      int bottom = 0;

      if(isFirstRow(position,spanCount)){
        top = 0;
      }else{
        top = distance;
      }

      if(!isFirstColum(position,spanCount) && !isLastColum(position,spanCount)){
        left = distance/2;
        right = distance/2;
      }else if(isFirstColum(position,spanCount) && !isLastColum(position,spanCount)){
        right = distance/2;
      }else if(!isFirstColum(position,spanCount) && isLastColum(position,spanCount)){
        left = distance/2;
      }else{
        if(isFirstColum(position,spanCount)) {
          left = 0;
        }else if(isLastColum(position,spanCount)){
          right = 0;
        }
      }

      outRect.set(left,top,right,bottom);
    }
  }

  private boolean isFirstRow(int position,int spanCount){
    if(position < spanCount){
      return true;
    }

    return false;
  }

  private boolean isLastRow(int position,int spanCount,int count){
    int rowCount = count%spanCount == 0?count/spanCount:count/spanCount+1;
    int positionRowCount = (position+1)%spanCount == 0?(position+1)/spanCount:(position+1)/spanCount+1;
    return rowCount == positionRowCount;
  }

  private boolean isFirstColum(int position,int spanCount){
    int value = position%spanCount;
    if(value == 0){
      return true;
    }

    return false;
  }

  private boolean isLastColum(int position,int spanCount){
    int value = (position+1)%spanCount;
    if(value == 0){
      return true;
    }

    return false;
  }
}
