package com.tinet.oskit.tool;

import android.graphics.Color;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: TClickSpan
 * @Author: zhangping
 * @CreateDate: 2022/10/12 11:34
 * @Description: 描述说明
 */
public class TClickSpan extends ClickableSpan {

    private int mHighLightColor = Color.BLUE;

    private boolean mUnderLine = false;

    private View.OnClickListener mClickListener;

    public TClickSpan(View.OnClickListener listener) {
        this.mClickListener = listener;
    }

    public TClickSpan(int mHighLightColor, View.OnClickListener mClickListener) {
        this.mHighLightColor = mHighLightColor;
        this.mClickListener = mClickListener;
    }

    public TClickSpan(int color, boolean underline, View.OnClickListener listener) {
        this.mHighLightColor = color;
        this.mUnderLine = underline;
        this.mClickListener = listener;
    }


    @Override
    public void onClick(View widget) {
        if (mClickListener != null)
            mClickListener.onClick(widget);
    }


    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(mHighLightColor);
        ds.setUnderlineText(mUnderLine);
    }

}
