package com.tinet.oskit.view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 解决符号换行问题
 * @author: liuzeren
 * @date: 2023/7/7
 */
public class TinetTextView extends AppCompatTextView {

  public TinetTextView(Context context) {
    super(context);
  }

  public TinetTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TinetTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    int maxWidth = (int)Math.ceil(getMaxLineWidth(getLayout()));
    maxWidth += getPaddingRight() + getPaddingLeft();

    setMeasuredDimension(maxWidth, getMeasuredHeight());
  }

  private float getMaxLineWidth(Layout layout)  {
    float maximumWidth = 0.0f;
    int lines = layout.getLineCount();
    for (int i=0;i< lines;i++) {
      maximumWidth = Math.max(layout.getLineWidth(i), maximumWidth);
    }

    return maximumWidth;
  }
}
