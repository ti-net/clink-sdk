package com.tinet.oskit.widget.round;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.tinet.oskit.R;

/**
 * @author: liuzeren
 * @date: 2023/6/14
 */
public class RoundCardView extends CardView {

  private float tlRadiu;
  private float trRadiu;
  private float brRadiu;
  private float blRadiu;

  public RoundCardView(@NonNull Context context) {
    super(context);
  }

  public RoundCardView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public RoundCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs){
    setRadius(0);
    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundCardView);
    tlRadiu = array.getDimension(R.styleable.RoundCardView_tinet_topLeftRadius, 0);
    trRadiu = array.getDimension(R.styleable.RoundCardView_tinet_topRightRadius, 0);
    brRadiu = array.getDimension(R.styleable.RoundCardView_tinet_bottomRightRadius, 0);
    blRadiu = array.getDimension(R.styleable.RoundCardView_tinet_bottomLeftRadius, 0);
    setBackground(new ColorDrawable());
  }

  @Override
  protected void onDraw(Canvas canvas) {
    Path path = new Path();
    RectF rectF = getRectF();
    float[] readius = {tlRadiu,tlRadiu,trRadiu,trRadiu,brRadiu,brRadiu,blRadiu,blRadiu};
    path.addRoundRect(rectF,readius,Path.Direction.CW);
    canvas.clipPath(path, Region.Op.INTERSECT);
    super.onDraw(canvas);
  }

  private RectF getRectF() {
    Rect rect = new Rect();
    getDrawingRect(rect);
    RectF rectF = new RectF(rect);
    return rectF;
  }
}
