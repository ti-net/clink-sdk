package com.tinet.oskit.widget;

import static android.content.Context.MODE_PRIVATE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.tinet.oskit.fragment.SessionFragment.MODEL_EMO;
import static com.tinet.oskit.fragment.SessionFragment.MODEL_TEXT;
import static com.tinet.oskit.fragment.SessionFragment.TYPE_AUDIO;
import static com.tinet.oskit.fragment.SessionFragment.TYPE_TEXT;
import static com.tinet.oskit.fragment.SessionFragment.duration;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.tinet.oskit.R;
import com.tinet.oskit.tool.AudioPlayManager;
import com.tinet.threepart.audio.AudioRecordManager;
import com.tinet.threepart.emoji.LQREmotionKit;
import com.tinet.threepart.emoji.MoonUtils;
import com.tinet.threepart.tools.TKeyBoardUtils;

/**
 * 聊天输入框
 * @author: liuzeren
 * @date: 2023/6/1
 */
public class SessionInputView extends LinearLayout implements View.OnClickListener,TextWatcher {

  private LinearLayout viewChat;

  /**
   * 常用、语音、表情、更多菜单
   * */
  private ImageView ivNormal,ivVoice,ivEmo,ivAdd;

  private Drawable audioDrawable,emoDrawable,addDrawable,keyDrawable;

  /**
   * 发送、按住说话
   */
  public TextView btnSend;
  private TextView tvAudioPress;

  private String strAudioText,strAudioPressText;

  /**
   * 输入内容
   */
  public EditText etContent;

  public SessionInputView(Context context) {
    this(context,null);
  }

  public SessionInputView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public SessionInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context,attrs);
  }

  private void init(Context context,AttributeSet attrs){
    View view = LayoutInflater.from(getContext()).inflate(R.layout.frg_session_input_view, this);
    ivNormal = view.findViewById(R.id.ivNormal);
    ivVoice = view.findViewById(R.id.ivVoice);
    ivEmo = view.findViewById(R.id.ivEmo);
    ivAdd = view.findViewById(R.id.ivAdd);
    btnSend = view.findViewById(R.id.btnSend);
    viewChat = view.findViewById(R.id.viewInput);
    tvAudioPress = view.findViewById(R.id.tvAudioPress);
    etContent = view.findViewById(R.id.etContent);

    btnSend.setOnClickListener(this);
    ivVoice.setOnClickListener(this);

    ivEmo.setOnClickListener(this);
    ivAdd.setOnClickListener(this);

    tvAudioPress.setOnTouchListener((v, event) -> {
      switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
//          if(listener != null){
//            listener.requestAudioPermission();
//          }

          if(listener != null){
            listener.startRecord();
          }

          tvAudioPress.setText(strAudioPressText);
          tvAudioPress.setPressed(true);
          break;
        case MotionEvent.ACTION_MOVE:
          boolean isCancel = false;
          int[] location = new int[2];
          v.getLocationOnScreen(location);

          if (event.getRawX() < location[0] || event.getRawX() > location[0] + v.getWidth()
              || event.getRawY() < location[1] - 40) {
            isCancel = true;
          }

          if (isCancel) {
            AudioRecordManager.getInstance(context).willCancelRecord();
          } else {
            AudioRecordManager.getInstance(context).continueRecord();
          }
          break;
        case MotionEvent.ACTION_CANCEL:
        case MotionEvent.ACTION_UP:
          AudioRecordManager.getInstance(context).stopRecord();
          AudioRecordManager.getInstance(context).destroyRecord();
          tvAudioPress.setText(strAudioText);
          tvAudioPress.setPressed(false);
          break;
      }
      return true;
    });

    etContent.addTextChangedListener(this);
    etContent.setOnKeyListener(new OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
          btnSend.performClick();
        }

        return false;
      }
    });
    etContent.setOnFocusChangeListener((v, hasFocus) -> {
      if(null != listener){
        listener.onFocusChange(hasFocus);
      }
    });

    initAttr(context,view, attrs);
    initContent(context);
    if(null != btnSend && etContent != null){
      btnSend.setEnabled(!TextUtils.isEmpty(etContent.getText()));
    }
  }

  /**
   * 是否允许语音输入
   * */
  private boolean voiceEnable = true;

  /**
   * 更多按钮的是否允许
   * */
  private boolean moreEnable = true;

  /**
   * 表情是否允许
   */
  private boolean emoEnable = true;

  private void initAttr(Context context, View view,AttributeSet attrs){
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SessionInputView);
    voiceEnable = typedArray.getBoolean(R.styleable.SessionInputView_tinetSessionInputVoiceEnable,true);
    moreEnable = typedArray.getBoolean(R.styleable.SessionInputView_tinetSessionInputMoreEnable,true);
    emoEnable = typedArray.getBoolean(R.styleable.SessionInputView_tinetSessionInputEmoEnable,true);
    Drawable bg = typedArray.getDrawable(R.styleable.SessionInputView_android_background);
    if(null != bg) {
      view.setBackground(bg);
    }else{
      view.setBackgroundResource(R.drawable.ti_input_area_bg);
    }
    int paddingTop = typedArray.getDimensionPixelSize(R.styleable.SessionInputView_android_paddingTop,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingTop));
    int paddingBottom = typedArray.getDimensionPixelSize(R.styleable.SessionInputView_android_paddingBottom,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingBottom));
    int paddingLeft = typedArray.getDimensionPixelSize(R.styleable.SessionInputView_android_paddingLeft,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingStart));
    int paddingRight = typedArray.getDimensionPixelSize(R.styleable.SessionInputView_android_paddingRight,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingEnd));
    view.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);

    int tinetInputStyle = typedArray.getResourceId(R.styleable.SessionInputView_tinetInputStyle, -1);
    if (tinetInputStyle != -1) {
      initEditText(context,context.obtainStyledAttributes(tinetInputStyle,R.styleable.SessionInputView_TinetInputContent));
    }

    int tinetSendButtonStyle = typedArray.getResourceId(R.styleable.SessionInputView_tinetSendButtonStyle, -1);
    if (tinetSendButtonStyle != -1) {
      initSend(context,context.obtainStyledAttributes(tinetSendButtonStyle,R.styleable.SessionInputView_TinetSendButton));
    }

    addDrawable = ContextCompat.getDrawable(context,R.mipmap.ti_input_area_add_icon);
    emoDrawable = ContextCompat.getDrawable(context,R.mipmap.ti_input_area_emo_icon);
    audioDrawable = ContextCompat.getDrawable(context,R.mipmap.ti_input_area_voice_icon);
    keyDrawable = typedArray.getDrawable(R.styleable.SessionInputView_tinetKeyboardSrc);
    if(keyDrawable == null){
      keyDrawable = ContextCompat.getDrawable(context,R.mipmap.ti_chat_keyboard);
    }

    int addStyle = typedArray.getResourceId(R.styleable.SessionInputView_tinetMoreStyle, -1);
    if (addStyle != -1) {
      TypedArray addType = context.obtainStyledAttributes(addStyle,R.styleable.SessionInputView_TinetIcon);
      initIcon(context,ivAdd,addType);

      Drawable drawable = addType.getDrawable(R.styleable.SessionInputView_TinetIcon_android_src);
      if(drawable != null){
        addDrawable = drawable;
      }
      addType.recycle();
    }

    int emoStyle = typedArray.getResourceId(R.styleable.SessionInputView_tinetEmoStyle, -1);
    if (emoStyle != -1) {
      TypedArray emoType = context.obtainStyledAttributes(emoStyle,R.styleable.SessionInputView_TinetIcon);
      initIcon(context,ivEmo,emoType);
      Drawable drawable = emoType.getDrawable(R.styleable.SessionInputView_TinetIcon_android_src);
      if(drawable != null){
        emoDrawable = drawable;
      }
      emoType.recycle();
    }

    int audioStyle = typedArray.getResourceId(R.styleable.SessionInputView_tinetAudioStyle, -1);
    if (audioStyle != -1) {
      TypedArray audioType = context.obtainStyledAttributes(audioStyle,R.styleable.SessionInputView_TinetIcon);
      initIcon(context,ivVoice,audioType);
      Drawable drawable = audioType.getDrawable(R.styleable.SessionInputView_TinetIcon_android_src);
      if(drawable != null){
        audioDrawable = drawable;
      }
      audioType.recycle();
    }

    int voicePressStyle = typedArray.getResourceId(R.styleable.SessionInputView_tinetVoiceStyle, -1);
    if (voicePressStyle != -1) {
      TypedArray voiceType = context.obtainStyledAttributes(voicePressStyle,R.styleable.SessionInputView_TinetVoiceStyle);
      initVoicePress(context,voiceType);
      voiceType.recycle();
    }
    if(TextUtils.isEmpty(strAudioText)){
      strAudioText = context.getString(R.string.ti_pressed_up_speak);
    }

    if(TextUtils.isEmpty(strAudioPressText)){
      strAudioPressText = context.getString(R.string.ti_pressed_speak);
    }

    updateVoiceVisible(voiceEnable);
    updateEmoVisible(emoEnable);
    updateMoreVisible(moreEnable);
    updateSendVisible(false);

    typedArray.recycle();
  }

  private String hint;

  /**
   * 应用输入框样式
   * @param a
   */
  private void initEditText(Context context,TypedArray a){
    if(etContent == null){
      return;
    }

    MarginLayoutParams params = ((MarginLayoutParams)etContent.getLayoutParams());
    params.leftMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_layout_marginLeft,context.getResources().getDimensionPixelSize(R.dimen.ti_session_send_button_marginLeft));
    params.rightMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_layout_marginRight,0);
    params.topMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_layout_marginTop,0);
    params.bottomMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_layout_marginBottom,0);

    params.height = a.getLayoutDimension(R.styleable.SessionInputView_TinetInputContent_android_layout_height,WRAP_CONTENT);

    etContent.setHint(hint = a.getString(R.styleable.SessionInputView_TinetInputContent_android_hint));
    etContent.setTextColor(a.getColor(R.styleable.SessionInputView_TinetInputContent_android_textColor,ContextCompat.getColor(context,R.color.ti_input_box_text_color)));
    etContent.setHintTextColor(a.getColor(R.styleable.SessionInputView_TinetInputContent_android_textColorHint,ContextCompat.getColor(context,R.color.ti_input_text_color_hint)));
    etContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_textSize,context.getResources().getDimensionPixelSize(R.dimen.ti_input_box_text_size)));
    if (VERSION.SDK_INT >= VERSION_CODES.P) {
      etContent.setLineHeight(a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_lineHeight,context.getResources().getDimensionPixelSize(R.dimen.ti_input_box_high_line_height)));
    }
    etContent.setMaxLines(a.getInt(R.styleable.SessionInputView_TinetInputContent_android_maxLines,5));
    etContent.setBackground(a.getDrawable(R.styleable.SessionInputView_TinetInputContent_android_background));
    etContent.setGravity(a.getInt(R.styleable.SessionInputView_TinetInputContent_android_gravity,
        Gravity.CENTER_VERTICAL));
    int lines = a.getInt(R.styleable.SessionInputView_TinetInputContent_android_lines,0);
    if(lines > 0){
      etContent.setLines(lines);
    }

    int inputType = a.getInt(R.styleable.SessionInputView_TinetInputContent_android_inputType,-1);
    if(inputType > -1){
      etContent.setInputType(inputType);
    }

    int imeOptions = a.getInt(R.styleable.SessionInputView_TinetInputContent_android_imeOptions,-1);
    if(imeOptions > -1){
      etContent.setImeOptions(imeOptions);
    }

    int paddingTop = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_paddingTop,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingTop));
    int paddingBottom = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_paddingBottom,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingBottom));
    int paddingLeft = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_paddingLeft,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingStart));
    int paddingRight = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetInputContent_android_paddingRight,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingEnd));

    etContent.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
  }

  private void initVoicePress(Context context,TypedArray a){
    if(tvAudioPress == null){
      return;
    }

    MarginLayoutParams params = ((MarginLayoutParams)tvAudioPress.getLayoutParams());
    params.leftMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_layout_marginLeft,0);
    params.rightMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_layout_marginRight,0);
    params.topMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_layout_marginTop,0);
    params.bottomMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_layout_marginBottom,0);
    params.width = a.getLayoutDimension(R.styleable.SessionInputView_TinetVoiceStyle_android_layout_width,MATCH_PARENT);
    params.height = a.getLayoutDimension(R.styleable.SessionInputView_TinetVoiceStyle_android_layout_height,WRAP_CONTENT);

    strAudioText = a.getString(R.styleable.SessionInputView_TinetVoiceStyle_android_text);
    strAudioPressText = a.getString(R.styleable.SessionInputView_TinetVoiceStyle_tinetPressText);

    Drawable drawable = a.getDrawable(R.styleable.SessionInputView_TinetVoiceStyle_android_background);
    if(drawable == null){
      tvAudioPress.setBackgroundResource(R.drawable.ti_input_area_voice_press_up_bg);
    }else{
      tvAudioPress.setBackground(drawable);
    }

    try {
      tvAudioPress.setTextColor(
          a.getColor(R.styleable.SessionInputView_TinetVoiceStyle_android_textColor,
              ContextCompat.getColor(context, R.color.ti_input_area_voice_press_text_color)));

      ColorStateList colorStateList = a.getColorStateList(R.styleable.SessionInputView_TinetVoiceStyle_android_textColor);
      if(null != colorStateList) {
        tvAudioPress.setTextColor(colorStateList);
      }
    }catch (Exception e){}

    tvAudioPress.setTextSize(TypedValue.COMPLEX_UNIT_PX,a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_textSize,context.getResources().getDimensionPixelSize(R.dimen.ti_input_box_text_size)));
    tvAudioPress.setGravity(a.getInt(R.styleable.SessionInputView_TinetVoiceStyle_android_gravity,
        Gravity.CENTER_VERTICAL));

    int paddingTop = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_paddingTop,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingTop));
    int paddingBottom = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_paddingBottom,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingBottom));
    int paddingLeft = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_paddingLeft,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingStart));
    int paddingRight = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetVoiceStyle_android_paddingRight,context.getResources().getDimensionPixelSize(R.dimen.ti_session_paddingEnd));

    tvAudioPress.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
  }

  /**
   * 发送按钮样式
   * @param context
   * @param a
   */
  private void initSend(Context context,TypedArray a){
    if(btnSend == null){
      return;
    }

    MarginLayoutParams params = ((MarginLayoutParams)btnSend.getLayoutParams());

    params.width = a.getLayoutDimension(R.styleable.SessionInputView_TinetSendButton_android_layout_width,context.getResources().getDimensionPixelSize(R.dimen.ti_send_out_wide));
    params.height = a.getLayoutDimension(R.styleable.SessionInputView_TinetSendButton_android_layout_height,context.getResources().getDimensionPixelSize(R.dimen.ti_send_out_high));
    params.leftMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_layout_marginLeft,context.getResources().getDimensionPixelSize(R.dimen.ti_session_send_button_marginLeft));
    params.rightMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_layout_marginRight,context.getResources().getDimensionPixelSize(R.dimen.ti_session_send_button_marginRight));
    params.topMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_layout_marginTop,0);
    params.bottomMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_layout_marginBottom,0);

    Drawable drawable = a.getDrawable(R.styleable.SessionInputView_TinetSendButton_android_background);
    if(drawable == null){
      btnSend.setBackgroundResource(R.drawable.ti_send_btn_bg);
    }else{
      btnSend.setBackground(drawable);
    }

    btnSend.setGravity(a.getInt(R.styleable.SessionInputView_TinetSendButton_android_gravity,
        Gravity.CENTER_VERTICAL));


    try {
      btnSend.setTextColor(
          a.getColor(R.styleable.SessionInputView_TinetSendButton_android_textColor,
              ContextCompat.getColor(context, R.color.ti_input_box_text_color)));

      ColorStateList colorStateList = a.getColorStateList(R.styleable.SessionInputView_TinetSendButton_android_textColor);
      if(null != colorStateList) {
        btnSend.setTextColor(colorStateList);
      }
    }catch (Exception e){}

    int paddingTop = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_paddingTop,0);
    int paddingBottom = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_paddingBottom,0);
    int paddingLeft = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_paddingLeft,0);
    int paddingRight = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_paddingRight,0);

    btnSend.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);

    btnSend.setTextSize(TypedValue.COMPLEX_UNIT_PX,a.getDimensionPixelSize(R.styleable.SessionInputView_TinetSendButton_android_textSize,context.getResources().getDimensionPixelSize(R.dimen.ti_input_box_text_size)));
    btnSend.setText(a.getString(R.styleable.SessionInputView_TinetSendButton_android_text));
  }

  private void initIcon(Context context,ImageView icon,TypedArray a){
    if(icon == null){
      return;
    }

    MarginLayoutParams params = ((MarginLayoutParams)icon.getLayoutParams());
    params.width = a.getLayoutDimension(R.styleable.SessionInputView_TinetIcon_android_layout_width,context.getResources().getDimensionPixelSize(R.dimen.ti_input_area_icon_size));
    params.height = a.getLayoutDimension(R.styleable.SessionInputView_TinetIcon_android_layout_height,context.getResources().getDimensionPixelSize(R.dimen.ti_input_area_icon_size));
    params.leftMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetIcon_android_layout_marginLeft,0);
    params.rightMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetIcon_android_layout_marginRight,0);
    params.topMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetIcon_android_layout_marginTop,0);
    params.bottomMargin = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetIcon_android_layout_marginBottom,0);

    Drawable drawable = a.getDrawable(R.styleable.SessionInputView_TinetIcon_android_src);
    if(drawable != null){
      icon.setImageDrawable(drawable);
    }

    try {
      int scaleType = a.getInt(R.styleable.SessionInputView_TinetIcon_android_scaleType, -1);
      if (scaleType > 0) {
        for (ScaleType st : ScaleType.values()) {
          if (scaleType == st.ordinal()) {
            icon.setScaleType(st);
            break;
          }
        }
      }
    }catch (Exception e){}

    int paddingTop = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetIcon_android_paddingTop,0);
    int paddingBottom = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetIcon_android_paddingBottom,0);
    int paddingLeft = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetIcon_android_paddingLeft,0);
    int paddingRight = a.getDimensionPixelSize(R.styleable.SessionInputView_TinetIcon_android_paddingRight,0);

    icon.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
  }

  /**
   * 更新语音输入是否可见
   * @param visible
   */
  private void updateVoiceVisible(boolean visible){
    if(ivVoice == null){
      return;
    }

    if(visible && voiceEnable){
      ivVoice.setVisibility(View.VISIBLE);
    }else{
      ivVoice.setVisibility(View.GONE);
    }
  }

  /**
   * 表情输入按钮是否可见
   * @param visible
   */
  private void updateEmoVisible(boolean visible){
    if(ivEmo == null){
      return;
    }

    if(visible && emoEnable){
      ivEmo.setVisibility(View.VISIBLE);
    }else{
      ivEmo.setVisibility(View.GONE);
    }
  }

  /**
   * 更新更多是否可见
   * @param visible
   */
  public void updateMoreVisible(boolean visible){
    if(ivAdd == null){
      return;
    }

    if(visible && moreEnable){
      ivAdd.setVisibility(View.VISIBLE);
    }else{
      ivAdd.setVisibility(View.GONE);
    }
  }

  /**
   * 发送按钮是否可见
   * @param visible
   */
  public void updateSendVisible(boolean visible){
    if(btnSend == null){
      return;
    }

    if(visible){
      if(listener != null){
        btnSend.setVisibility(listener.canShowSend()?View.VISIBLE:View.GONE);
      }else {
        btnSend.setVisibility(View.VISIBLE);
      }
    }else{
      if(moreEnable || emoEnable){
        btnSend.setVisibility(View.GONE);
      }else{
        if(listener != null){
          btnSend.setVisibility(listener.canShowSend()?View.VISIBLE:View.GONE);
        }else {
          btnSend.setVisibility(View.VISIBLE);
        }
      }
    }
  }

  private void initContent(Context context){
    //如果SoundCode,获取的值是空的,则会弹出后面的默认值
    SharedPreferences sp1 = context.getSharedPreferences("inputContent", MODE_PRIVATE);
    String obtain = sp1.getString("content", "");
    if (!TextUtils.isEmpty(obtain)) {
//      updateMoreVisible(false);
//      updateSendVisible(true);
      MoonUtils.identifyFaceExpression(context, etContent, obtain, ImageSpan.ALIGN_BOTTOM);
      updateButtons();
    }
  }

  public void setListener(OnActionListener listener) {
    this.listener = listener;
    updateButtons();
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.btnSend){
      if(null != listener){
        if(listener.onSend(etContent.getText().toString())){
          etContent.setText("");
        }
      }
    }else if(v.getId() == R.id.ivVoice){
      if(null != listener){
        listener.onVoiceClick();
      }
    }else if(v.getId() == R.id.ivEmo || v.getId() == R.id.ivAdd){
      if(null != listener){
        listener.onMoreClick(v);
      }
    }
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    updateButtons();
  }

  /**
   * 更新状态
   */
  private void updateButtons(){
    // 按钮状态变更
    if (null != listener && listener.isAudio()) {
      //当前如果是语音输入模式，则切换为文本输入模式
      if (etContent.getText().toString().trim().length() > 0) {
        etContent.setHint("");
        updateMoreVisible(false);
        updateSendVisible(true);
      } else {
        etContent.setHint(hint);
        updateSendVisible(false);
        updateMoreVisible(true);
      }
    }
  }

  @Override
  public void afterTextChanged(Editable editable) {
    String text = editable.toString();
    if(listener != null){
      listener.onInputHintSend(text);
    }
    MoonUtils.replaceEmoticons(LQREmotionKit.getContext(), editable, 0, text.length(), etContent);

    if(null != btnSend){
      btnSend.setEnabled(!TextUtils.isEmpty(text));
    }
  }

  private OnActionListener listener;

  public interface OnActionListener{

    /**
     * @return true 消息发送成功
     */
    boolean onSend(String message);

    /**
     * 坐席预知
     * @param message
     * @return
     */
    boolean onInputHintSend(String message);

    void onVoiceClick();

    /**
     * 开启录制
     */
    void startRecord();

    void onFocusChange(boolean hasFocus);

    void onMoreClick(View v);

    /**
     * 发送按钮是否可以显示
     * @return
     */
    boolean canShowSend();

    /**
     * 是否为语音输入模式
     * @return
     */
    boolean isAudio();
  }

  /**
   * 获取当前输入
   * @return
   */
  public String getContent(){
    if(etContent != null){
      return etContent.getText().toString();
    }

    return "";
  }

  /**
   * 更新输入
   * @param content
   */
  public void updateContent(String content){
    etContent.setText(content);
  }

  /**
   * 更新输入模型
   * @param model
   */
  public void setEmoByModel(int model) {
    switch (model) {
      case MODEL_TEXT:
        ivEmo.setImageDrawable(emoDrawable);
        break;
      case MODEL_EMO:
        ivEmo.setImageDrawable(keyDrawable);
        break;
    }

    //触发一下发送按钮的显示状态
    updateSendVisible(btnSend.getVisibility()==View.VISIBLE);
  }

  @Override
  protected void onWindowVisibilityChanged(int visibility) {
    super.onWindowVisibilityChanged(visibility);

    if(visibility == View.GONE) {
      if (ivVoice != null && ivVoice.getBackground() instanceof AnimationDrawable) {
        AnimationDrawable animation = (AnimationDrawable) ivVoice.getBackground();
        if (animation.isRunning()) {
          AudioPlayManager.getInstance().stopPlay();
        }
      }
      TKeyBoardUtils.closeKeybord(etContent, getContext());
    }
  }

  /**
   * 是否请求焦点
   * @param focus
   */
  public void setFocus(boolean focus){
    if(focus){
      if(!TextUtils.isEmpty(getContent())){
        updateMoreVisible(false);
        updateSendVisible(true);
      }
    }else{
      etContent.clearFocus();
    }
  }

  /**
   * 关闭键盘
   */
  public void closeKeyboard() {
    TKeyBoardUtils.closeKeybord(etContent, getContext());
  }

  /**
   * 打开软键盘
   */
  public void openKeyboard() {
    etContent.requestFocus();
    TKeyBoardUtils.openKeybord(etContent, getContext());
  }

  public void setType(int type) {
    updateSendVisible(false);

    switch (type) {
      case TYPE_TEXT:
        tvAudioPress.animate().alpha(0f).setDuration(duration).setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            tvAudioPress.setVisibility(View.GONE);
          }
        });
        etContent.setAlpha(0f);
        etContent.setVisibility(View.VISIBLE);
        etContent.animate().alpha(1f).setDuration(duration).setListener(null);
        ivVoice.setImageDrawable(audioDrawable);
        updateButtons();
        break;
      case TYPE_AUDIO:
        etContent.animate().alpha(0f).setDuration(duration).setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            etContent.setVisibility(View.GONE);
          }
        });
        tvAudioPress.setAlpha(0f);
        tvAudioPress.setVisibility(View.VISIBLE);
        tvAudioPress.animate().alpha(1f).setDuration(duration).setListener(null);
        ivVoice.setImageDrawable(keyDrawable);
        ivEmo.setImageDrawable(emoDrawable);
        updateMoreVisible(true);
        closeKeyboard();
        break;
    }
  }

  /**
   * 更新输入框的hint
   * */
  public void updateHint(String hint){
    this.hint = hint;

    if(etContent != null){
      etContent.setHint(hint);
    }
  }
}
