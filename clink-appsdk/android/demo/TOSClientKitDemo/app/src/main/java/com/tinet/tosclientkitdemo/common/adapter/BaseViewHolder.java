package com.tinet.tosclientkitdemo.common.adapter;

/**
 * @ProjectName: TOSClientKitDemo
 * @ClassName: BaseViewHolder
 * @Author: zhangping
 * @CreateDate: 2022/7/4 14:19
 * @Description: 描述说明
 */

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class BaseViewHolder extends RecyclerView.ViewHolder {


    private SparseArray<View> mViews = new SparseArray<>();//用来存储控件
    private View mConvertView;//布局


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        mConvertView = itemView;
    }

    public static BaseViewHolder getInstance(View itemView) {
        return new BaseViewHolder(itemView);
    }


    /**
     * 通过viewid获取控件
     */
    public View getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    public BaseViewHolder setText(@IdRes int viewId, String text) {
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    public BaseViewHolder setSrc(@IdRes int viewId, int resource) {
        ((ImageView) getView(viewId)).setImageResource(resource);
        return this;
    }

    public BaseViewHolder setSrc(@IdRes int viewId, Bitmap resource) {
        ((ImageView) getView(viewId)).setImageBitmap(resource);
        return this;
    }
    //可以自行添加你需要的方法

    //设置控件是否隐藏
    public BaseViewHolder setVisibility(@IdRes int viewId, @Visibility int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    /**
     * 条目点击事件
     *
     * @param onItemClickListener
     * @return
     */
    public BaseViewHolder setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick();
            }
        });
        return this;
    }

    /**
     * 条目上按钮点击事件
     *
     * @param viewId
     * @param onItemClickListener
     * @return
     */
    public BaseViewHolder setOnItemChildAtClickListener(@IdRes int viewId, final OnItemClickListener onItemClickListener) {
        getView(viewId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onClick();
            }
        });
        return this;
    }

    public interface OnItemClickListener {
        void onClick();
    }

    /**
     * @hide
     */
    @IntDef({VISIBLE, INVISIBLE, GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }
}