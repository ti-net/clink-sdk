package com.tinet.oskit.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tinet.oskit.R;

/**
 * @author: liuzeren
 * @date: 2022/3/30
 */
public abstract class TinetBottomDialog extends BottomSheetDialogFragment {

    private View behaviorView = null;

    protected void setBehaviorView(View behaviorView) {
        this.behaviorView = behaviorView;
    }

    /**
     * 布局id
     * 普通布局即可实现滑动功能
     */
    protected abstract int layoutId();

    protected int behaviorLayoutId() {
        return 0;
    }

    /**
     * 对话框展开时的默认高度
     */
    public int peekHeight() {
        return BottomSheetBehavior.PEEK_HEIGHT_AUTO;
    }

    /**
     * behavior
     */
    private BottomSheetBehavior<FrameLayout> mBottomSheetBehavior;

    public void setBottomSheetBehavior(BottomSheetBehavior<FrameLayout> mBottomSheetBehavior) {
        this.mBottomSheetBehavior = mBottomSheetBehavior;

        if (null != mBottomSheetBehavior) {
            mBottomSheetBehavior.setPeekHeight(peekHeight());
        }
        mBottomSheetBehavior.addBottomSheetCallback(mBottomSheetBehaviorCallback);
    }

    /**
     * bottomsheet行为处理
     */
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
                case BottomSheetBehavior.STATE_HIDDEN:
                    dismiss();
                    break;
                case BottomSheetBehavior.STATE_COLLAPSED:
                    onCollapsed();
                    break;
                case BottomSheetBehavior.STATE_EXPANDED:
                    onExpanded();
                    break;
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            if (null != behaviorView) {
                if (slideOffset < 0) {
                    int range = 0;
                    if (null != mBottomSheetBehavior) {
                        range = mBottomSheetBehavior.getPeekHeight();
                    }

                    behaviorView.animate().translationY(-range * slideOffset).setDuration(0).start();
                } else {
                    if (0 != behaviorView.getY()) {
                        behaviorView.animate().translationY(0).setDuration(0).start();
                    }
                }
            }
        }
    };

    private View designBottomSheet = null;

    protected void setBackground(Drawable color) {
        if (null != designBottomSheet) {
            designBottomSheet.setBackground(color);
        }
    }

    private void clearBackground() {
        setBackground(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NORMAL, R.style.Theme_Design_BottomSheetDialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        setBottomSheetBehavior(dialog.getBehavior());

        final View contentView = LayoutInflater.from(requireContext()).inflate(layoutId(), null, false);
        initView(contentView);
        dialog.setContentView(contentView);

        int behaviorLayoutId = behaviorLayoutId();
        if (behaviorLayoutId != 0) {
            setBehaviorView(View.inflate(requireContext(), behaviorLayoutId, null));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                behaviorView.setElevation(
                        getResources().getDimension(com.google.android.material.R.dimen.design_bottom_sheet_modal_elevation));
            }

            waitForHeight(behaviorView, new OnHeightChangedListener() {
                @Override
                public void onHeightChanged() {
                    contentView.setPadding(contentView.getPaddingLeft(),
                            contentView.getPaddingTop(),
                            contentView.getPaddingRight(),
                            contentView.getPaddingBottom() + behaviorView.getHeight());
                }
            });

            CoordinatorLayout mCoordinatorLayout = dialog.findViewById(com.google.android.material.R.id.coordinator);
            if (null != mCoordinatorLayout) {
                CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.BOTTOM;
                mCoordinatorLayout.addView(behaviorView, params);
            }
        }

        designBottomSheet = dialog.getWindow().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        clearBackground();

        return dialog;
    }

    /**
     * 初始化视图
     */
    protected void initView(View view) {
    }

    public void show(FragmentManager manager) {
        super.show(manager, this.getClass().getName());
    }

    @Override
    public void dismiss() {
        if (null != mBottomSheetBehavior) {
            mBottomSheetBehavior.removeBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        super.dismiss();
    }

    /**
     * dialog完全展示时调用
     */
    protected void onExpanded() {
    }

    /**
     * dialog收缩底部（非关闭）时调用
     */
    protected void onCollapsed() {
    }

    private interface OnHeightChangedListener {

        void onHeightChanged();

    }

    private void waitForHeight(final View v, final OnHeightChangedListener listener) {
        if (v.getMeasuredWidth() > 0 && v.getMeasuredHeight() > 0) {
            listener.onHeightChanged();
            return;
        }

        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private Integer lastHeight = null;

            @Override
            public void onGlobalLayout() {
                if (lastHeight != null && lastHeight == v.getMeasuredHeight()) {
                    v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                if (v.getMeasuredWidth() > 0
                        && v.getMeasuredHeight() > 0
                        && (lastHeight == null || lastHeight != v.getMeasuredHeight())) {
                    lastHeight = v.getMeasuredHeight();
                    listener.onHeightChanged();
                }
            }
        });
    }
}
