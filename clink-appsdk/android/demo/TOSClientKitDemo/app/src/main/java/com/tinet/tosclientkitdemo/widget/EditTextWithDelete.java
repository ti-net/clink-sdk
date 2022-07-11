package com.tinet.tosclientkitdemo.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.utils.TLogUtils;

import androidx.appcompat.widget.AppCompatEditText;


/**
 * Time:2019/10/8
 * Author:zhaixs
 * Description:
 */
public class EditTextWithDelete extends AppCompatEditText implements TextWatcher,
        View.OnFocusChangeListener {
    private Paint mPaint;
    private int textColor;
    private int baselineColor;
    public static final int STATUS_FOCUSED = 1;
    public static final int STATUS_UNFOCUSED = 2;
    public static final int STATUS_ERROR = 3;
    private int status = 2;
    private Drawable del_btn;
    private Drawable del_btn_down;
    private int focusedDrawableId;// 默认的
    private int unfocusedDrawableId;
    private int errorDrawableId = R.mipmap.indicator_input_error;
    private boolean isShowLeftIcon;//是否显示左侧图标
    private boolean isHideLine; //是否隐藏下划线
    private boolean isHideDelIcon; //是否隐藏删除图标
    Drawable left = null;
    private Context mContext;
    /**
     * 是不是属于下拉框
     */
    private boolean isSelecter = false;
    /**
     * 是否获取焦点，默认没有焦点
     */
    private boolean hasFocus = false;
    /**
     * 手指抬起时的X坐标
     */
    private int xUp = 0;
    private int leftIconSize;

    public EditTextWithDelete(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDelete(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EditTextWithDelete);
        focusedDrawableId = a.getResourceId(R.styleable.EditTextWithDelete_drawableFocus, focusedDrawableId);
        unfocusedDrawableId = a.getResourceId(R.styleable.EditTextWithDelete_drawableUnFocus, unfocusedDrawableId);
        errorDrawableId = a.getResourceId(R.styleable.EditTextWithDelete_drawableError, errorDrawableId);
        isShowLeftIcon = a.getBoolean(R.styleable.EditTextWithDelete_isShowLeftIcon, true);
        isHideLine = a.getBoolean(R.styleable.EditTextWithDelete_isHideLine, false);
        isHideDelIcon = a.getBoolean(R.styleable.EditTextWithDelete_isHideDelIcon, false);
        a.recycle();
        init();
    }

    public EditTextWithDelete(Context context, AttributeSet attrs, int defStryle) {
        super(context, attrs, defStryle);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1.0f);
        textColor = mContext.getResources().getColor(R.color.login_text_unfocus);
        setTextColor(textColor);
        baselineColor = mContext.getResources().getColor(R.color.login_baseline_unfocus);
        setBaselineColor(baselineColor);
        del_btn = mContext.getResources().getDrawable(R.drawable.ic_icon_delete);
        int delBtnSize = mContext.getResources().getDimensionPixelOffset(R.dimen.text_size_normal);
        del_btn.setBounds(0, 0, delBtnSize, delBtnSize);
        del_btn_down = mContext.getResources().getDrawable(R.drawable.ic_icon_delete);
        del_btn_down.setBounds(0, 0, delBtnSize, delBtnSize);
        left = getResources().getDrawable(focusedDrawableId);
        leftIconSize = mContext.getResources().getDimensionPixelOffset(R.dimen.login_input_icon_size);
        setStatus(status);
        addListeners();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(baselineColor);
        if (!isHideLine) {
            canvas.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1, mPaint);
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isSelecter) return false;
        if (del_btn != null && event.getAction() == MotionEvent.ACTION_UP) {
            // 获取点击时手指抬起的X坐标
            xUp = (int) event.getX();
            int drawableLeftX = getWidth() - getCompoundPaddingRight();
            if (xUp >= drawableLeftX) {
                if (xUp < drawableLeftX + del_btn.getBounds().width()) {
                    // 删除图标最右侧之前， 此外限定点击触发删除功能的x轴范围，即认为是点击了删除图标
                    if (getText() != null && !TextUtils.isEmpty(getText().toString())) {
                        setText("");
                        if (onDeleteListener != null) {
                            onDeleteListener.onDelete();
                        }
                    }
                }
            }
        } else if (del_btn != null && event.getAction() == MotionEvent.ACTION_DOWN && getText().length() != 0) {
            if (!isHideDelIcon) {
                setCompoundDrawables(left, null, del_btn_down, null);
            }
        } else if (getText().length() != 0) {
            if (!isHideDelIcon) {
                setCompoundDrawables(left, null, del_btn, null);
            }
        }
        return super.onTouchEvent(event);
    }

    // 监听删除事件
    public interface OnDeleteListener {
        void onDelete();
    }

    private OnDeleteListener onDeleteListener;

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public void setStatus(int status) {
        this.status = status;
        int colorPrimary = getResources().getColor(R.color.colorPrimary);
        switch (status) {
            case STATUS_ERROR:
                try {
                    left = getResources().getDrawable(errorDrawableId);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                setTextColor(colorPrimary);
                setBaselineColor(colorPrimary);
                if (isShowLeftIcon) {
                    left.setBounds(0, 0, leftIconSize, leftIconSize);
                    setCompoundDrawables(left, null, null, null);
                } else {
                    left = null;
                    setCompoundDrawables(null, null, null, null);
                }
                break;
            case STATUS_UNFOCUSED:
                try {
                    left = getResources().getDrawable(unfocusedDrawableId);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                setTextColor(getResources().getColor(R.color.login_text_unfocus));
                setBaselineColor(getResources().getColor(R.color.login_baseline_unfocus));
                if (isShowLeftIcon) {
                    left.setBounds(0, 0, leftIconSize, leftIconSize);
                    setCompoundDrawables(left, null, null, null);
                } else {
                    left = null;
                    setCompoundDrawables(null, null, null, null);
                }
                break;
            case STATUS_FOCUSED:
                try {
                    left = getResources().getDrawable(focusedDrawableId);
                } catch (Resources.NotFoundException e) {
//                    e.printStackTrace();
                }
                setTextColor(colorPrimary);
                setBaselineColor(colorPrimary);
                if (isShowLeftIcon) {
                    // 如果非空，则要显示删除图标
                    if (!isHideDelIcon && !TextUtils.isEmpty(getText().toString().trim())) {
                        left.setBounds(0, 0, leftIconSize, leftIconSize);
                        setCompoundDrawables(left, null, del_btn, null);
                    }
                } else {
                    left = null;
                    setCompoundDrawables(left, null, null, null);
                    if (!isHideDelIcon && !TextUtils.isEmpty(getText().toString().trim())) {
                        left.setBounds(0, 0, leftIconSize, leftIconSize);
                        setCompoundDrawables(left, null, del_btn, null);
                    }
                }
                break;
        }
        postInvalidate();
    }

    /**
     * 设置左边的图标
     *
     * @param focusedDrawableId   获取焦点的图标
     * @param unfocusedDrawableId 默认图标
     * @param errorDrawableId     错误状态图标
     */
    public void setLeftDrawable(int focusedDrawableId, int unfocusedDrawableId, int errorDrawableId) {
        this.focusedDrawableId = focusedDrawableId;
        this.unfocusedDrawableId = unfocusedDrawableId;
        this.errorDrawableId = errorDrawableId;
        setStatus(status);
    }

    private void addListeners() {
        try {
            setOnFocusChangeListener(this);
            addTextChangedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        this.hasFocus = focused;
        if (focused) {
            setStatus(STATUS_FOCUSED);
        } else {
            setStatus(STATUS_UNFOCUSED);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public void setTextColor(int color) {
        this.textColor = color;
        super.setTextColor(color);
    }

    public void setBaselineColor(int color) {
        this.baselineColor = color;
    }


    @Override
    public void afterTextChanged(Editable arg0) {
        postInvalidate();
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        if (TextUtils.isEmpty(arg0)) {
            // 如果为空，则不显示删除图标
            left.setBounds(0, 0, leftIconSize, leftIconSize);
            setCompoundDrawables(left, null, null, null);
        } else if (!isHideDelIcon) {
            // 如果非空，则要显示删除图标
            left.setBounds(0, 0, leftIconSize, leftIconSize);
            setCompoundDrawables(left, null, del_btn, null);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int after) {

        if (hasFocus) {
            if (TextUtils.isEmpty(s)) {
                // 如果为空，则不显示删除图标
                if (left != null) {
                    left.setBounds(0, 0, leftIconSize, leftIconSize);
                }
                setCompoundDrawables(left, null, null, null);
            } else if (!isHideDelIcon) {
                // 如果非空，则要显示删除图标
                if (left != null) {
                    left.setBounds(0, 0, leftIconSize, leftIconSize);
                }
                setCompoundDrawables(left, null, del_btn, null);
            }
        } else {
            // 如果没有焦点， 则不显示删除图标
            if (left != null) {
                left.setBounds(0, 0, leftIconSize, leftIconSize);
            }
            setCompoundDrawables(left, null, null, null);
        }
        //如果是下拉框类型
        if (isSelecter) {
            if (left != null) {
                left.setBounds(0, 0, leftIconSize, leftIconSize);
            }
            setCompoundDrawables(left, null, null, null);
        }
    }

    @Override
    public void onFocusChange(View arg0, boolean arg1) {
        try {
            this.hasFocus = arg1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSelecter(boolean selecter) {
        isSelecter = selecter;
    }

    public void setShowLeftIcon(boolean showLeftIcon) {
        if (isShowLeftIcon != showLeftIcon) {
            isShowLeftIcon = showLeftIcon;
            setStatus(status);
        }
    }
}