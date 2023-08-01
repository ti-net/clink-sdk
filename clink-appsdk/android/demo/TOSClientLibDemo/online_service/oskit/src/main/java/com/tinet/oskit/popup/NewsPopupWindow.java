package com.tinet.oskit.popup;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

import com.tinet.oskit.R;

/**
 * @ProjectName: TIMSDK
 * @ClassName: NewsPopupWindow
 * @Author: liuzr
 * @CreateDate: 2021-08-25 10:30
 * @Description: 撤销消息
 */
public class NewsPopupWindow extends PopupWindow {

    public interface CopyListener {

        void onCopy();

    }

    private View anchorView;

    public NewsPopupWindow(View anchorView, final CopyListener listener) {
        this.anchorView = anchorView;

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);

        setContentView(LayoutInflater.from(anchorView.getContext()).inflate(R.layout.frg_session_copy_pop, null, false));
        getContentView().findViewById(R.id.tvCopy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onCopy();
                    dismiss();
                }
            }
        });
    }

    public void show() {
        final int gravity = Gravity.CENTER;
        int offsetX = 0;

        int contentViewHeight = getContentView().getHeight();
        int offsetY = -contentViewHeight - anchorView.getHeight();
        if (contentViewHeight == 0) {
            // 为0时，说明控件没准备好
            getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // 2. 弹窗内控件更新后重新显示弹窗, 重新计算偏移量
                    final View contentView = getContentView();
                    int contentViewHeight = contentView.getHeight();
                    int offsetY = -contentViewHeight - anchorView.getHeight();
                    int offsetX = anchorView.getWidth() / 2 - getContentView().getWidth() / 2;
                    dismiss();
                    showAsDropDown(anchorView, offsetX, offsetY, gravity);
                    contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            // 1.需要调用一下showAsDropDown让控件高度更新
            showAsDropDown(anchorView, 0, offsetY, gravity);
        } else {
            offsetX = anchorView.getWidth() / 2 - this.getContentView().getWidth() / 2;
            showAsDropDown(anchorView, offsetX, offsetY, gravity);
        }
    }
}
