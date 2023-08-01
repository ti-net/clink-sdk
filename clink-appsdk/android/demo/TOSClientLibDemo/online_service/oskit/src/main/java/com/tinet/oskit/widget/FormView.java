package com.tinet.oskit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.tinet.oskit.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ProjectName: TIMSDK
 * @ClassName: FormView
 * @Author: liuzr
 * @CreateDate: 2021-09-09 17:39
 * @Description: 表单控件
 */
public class FormView extends LinearLayout {

    /**
     * 可编辑状态
     */
    public static final int TYPE_EDIT = 0;

    /**
     * 只读状态
     */
    public static final int TYPE_READONLY = 1;

    /**
     * 输入-单行
     */
    public static final int MODEL_INPUT_SINGLE = 0;

    /**
     * 输入-多行
     */
    public static final int MODEL_INPUT_MULTI = 1;

    /**
     * 选择
     */
    public static final int MODEL_SELECT = 2;

    public FormView(Context context) {
        this(context, null);
    }

    public FormView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FormView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    public interface OnTextChanged {
        void onChanged(String text);
    }

    private TextView tvText, tvValue, tvUnit;
    private EditText etValue;
    private ImageView ivMore, ivStar;

    private OnTextChanged onTextChanged;

    /**
     * 两种状态：可编辑，只读
     */
    @IntDef({TYPE_EDIT, TYPE_READONLY})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }

    /**
     * 两种模式：输入，选择
     */
    @IntDef({MODEL_INPUT_SINGLE, MODEL_INPUT_MULTI, MODEL_SELECT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Model {
    }

    /**
     * 两种状态：可编辑，只读
     */
    private @FormView.Type
    int type = TYPE_READONLY;

    public void setType(int type) {
        this.type = type;
        handleView();
    }

    private @Model
    int model = MODEL_INPUT_SINGLE;

    public void setModel(int model) {
        this.model = model;
        handleView();
    }

    private void init(AttributeSet attrs) {
        initView();
        retrieveAttributes(attrs);
    }

    /**
     * 显示的数据内容
     */
    private String formValue = "";

    public void setFormValue(String formValue) {
        this.formValue = formValue;

        if (null != tvValue) {
            tvValue.setText(formValue);
        }

        if (null != etValue) {
            etValue.setText(formValue);
        }
    }

    public void setFormTitle(String formTitle) {
        tvText.setText(formTitle);
    }

    public void setIsRequired(boolean isRequired) {
        ivStar.setVisibility(isRequired ? View.VISIBLE : View.GONE);
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_form, this);
        tvText = view.findViewById(R.id.tvTitle);
        tvValue = view.findViewById(R.id.tvValue);
        etValue = view.findViewById(R.id.etValue);
        tvUnit = view.findViewById(R.id.tvUnit);
        ivStar = view.findViewById(R.id.ivStar);
        ivMore = view.findViewById(R.id.ivMore);
        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (formValue != s.toString()) {
                    formValue = s.toString();

                    if (null != onTextChanged) {
                        onTextChanged.onChanged(formValue);
                    }
                }

                etValue.setSelection(TextUtils.isEmpty(formValue) ? 0 : formValue.length());
            }
        });
    }

    private void retrieveAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FormView);
        tvText.setText(typedArray.getText(R.styleable.FormView_formTitle));

        CharSequence unit = typedArray.getText(R.styleable.FormView_unit);
        tvUnit.setText(unit);
        tvUnit.setVisibility(TextUtils.isEmpty(unit) ? GONE : VISIBLE);

        model = typedArray.getInt(R.styleable.FormView_formModel, MODEL_INPUT_SINGLE);
        type = typedArray.getInt(R.styleable.FormView_formType, TYPE_READONLY);
        boolean isRequired = typedArray.getBoolean(R.styleable.FormView_android_required, false);
        ivStar.setVisibility(isRequired ? View.VISIBLE : View.GONE);
        formValue = typedArray.getString(R.styleable.FormView_formValue);

        etValue.setInputType(typedArray.getInt(R.styleable.FormView_android_inputType, InputType.TYPE_CLASS_TEXT));

        typedArray.recycle();
    }

    private void handleView() {
        switch (type) {
            case TYPE_READONLY: {
                //只读状态，只读状态下不需要关心[this.model]
                ivMore.setVisibility(View.GONE);
                etValue.setVisibility(View.GONE);
                tvValue.setVisibility(View.VISIBLE);
                //只读状态下tvValue是没有hint的
                tvValue.setHint("");
                break;
            }
            case TYPE_EDIT: {
                //可编辑状态
                switch (model) {
                    case MODEL_INPUT_SINGLE: {
                        //输入状态
                        etValue.setLines(1);
                        etValue.setInputType(etValue.getInputType() & ~InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                        etValue.setVisibility(View.VISIBLE);
                        tvValue.setVisibility(View.GONE);
                        ivMore.setVisibility(View.GONE);
                        break;
                    }
                    case MODEL_INPUT_MULTI: {
                        etValue.setLines(3);
                        etValue.setInputType(etValue.getInputType() | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                        etValue.setVisibility(View.VISIBLE);
                        tvValue.setVisibility(View.GONE);
                        ivMore.setVisibility(View.GONE);
                        break;
                    }
                    case MODEL_SELECT: {
                        //选择状态
                        etValue.setVisibility(View.GONE);
                        tvValue.setVisibility(View.VISIBLE);
                        ivMore.setVisibility(View.VISIBLE);

                        tvValue.setHint(etValue.getHint());
                        break;
                    }
                }
                break;
            }
        }
    }

    public void setOnTextChanged(OnTextChanged onTextChanged) {
        this.onTextChanged = onTextChanged;
    }
}
