package com.tinet.oskit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.tinet.oskit.R;
import com.tinet.oskit.widget.round.RoundedImageView;

/**
 * @author: liuzeren
 * @date: 2023/6/1
 */
public class UserHeaderView extends LinearLayout {

  public UserHeaderView(Context context) {
    this(context,null);
  }

  public UserHeaderView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public UserHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context,attrs);
  }

  /**
   * 是否为左边的头像，否则为右边的头像
   * */
  boolean isLeft;
  /**
   * 头像大小
   * */
  int tinetHeaderSize;
  /**
   * 头像与屏幕之间的距离
   * */
  int tinetHeaderMarginScreenHorizontal;
  /**
   * 头像与消息之间的距离
   * */
  int tinetHeaderMarginMessage;
  /**
   * 圆角大小
   * */
  int tinetHeaderRadius;

  /**
   * 是否为圆形头像
   */
  boolean tinetHeaderIsCircle;

  public void initView(Context context, AttributeSet attrs){
    View view = LayoutInflater.from(getContext()).inflate(R.layout.frg_session_item_avatar, this);
    initAttr(context,attrs);
    View viewStart = view.findViewById(R.id.viewStart);
    View viewEnd = view.findViewById(R.id.viewEnd);

    if(isLeft){
      viewStart.getLayoutParams().width = tinetHeaderMarginScreenHorizontal;
      viewEnd.getLayoutParams().width = tinetHeaderMarginMessage;
    }else{
      viewStart.getLayoutParams().width = tinetHeaderMarginMessage;
      viewEnd.getLayoutParams().width = tinetHeaderMarginScreenHorizontal;
    }

    RoundedImageView imageView = view.findViewById(R.id.ivAvatar);
    imageView.getLayoutParams().width = tinetHeaderSize;
    imageView.getLayoutParams().height = tinetHeaderSize;
    imageView.setCornerRadius(tinetHeaderRadius);
  }

  public void initAttr(Context context, AttributeSet attrs){
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UserHeaderView);
    isLeft = typedArray.getBoolean(R.styleable.UserHeaderView_tinetHeaderIsLeft,true);
    tinetHeaderSize = typedArray.getDimensionPixelSize(R.styleable.UserHeaderView_tinetHeaderSize,getContext().getResources().getDimensionPixelSize(R.dimen.ti_chat_avatar_size));
    tinetHeaderMarginScreenHorizontal = typedArray.getDimensionPixelSize(R.styleable.UserHeaderView_tinetHeaderMarginScreenHorizontal,getContext().getResources().getDimensionPixelSize(R.dimen.ti_msg_horizontal_span));
    tinetHeaderMarginMessage = typedArray.getDimensionPixelSize(R.styleable.UserHeaderView_tinetHeaderMarginMessage,getContext().getResources().getDimensionPixelSize(R.dimen.ti_chat_avatar_message_spacing));
    tinetHeaderRadius = typedArray.getDimensionPixelSize(R.styleable.UserHeaderView_tinetHeaderRadius,getContext().getResources().getDimensionPixelSize(R.dimen.ti_chat_avatar_radius));
    tinetHeaderIsCircle = typedArray.getBoolean(R.styleable.UserHeaderView_tinetHeaderIsCircle,getContext().getResources().getBoolean(R.bool.tinetHeaderIsCircle));

    if(tinetHeaderIsCircle){
      tinetHeaderRadius = tinetHeaderSize/2;
    }

    typedArray.recycle();
  }
}
