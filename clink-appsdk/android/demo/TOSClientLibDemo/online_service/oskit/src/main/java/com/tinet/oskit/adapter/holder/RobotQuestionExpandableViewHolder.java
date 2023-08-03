package com.tinet.oskit.adapter.holder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oslib.model.bean.OnlineQuestionData;

import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: RobotQuestionExpandableAdapter
 * @Author: zhangping
 * @CreateDate: 2022/12/26 13:53
 * @Description: 描述说明
 */
public class RobotQuestionExpandableViewHolder{

    /**
     * 静态内部类单例
     */
    private static class SingleHolder {
        private static final RobotQuestionExpandableViewHolder sViewHolder = new RobotQuestionExpandableViewHolder();
    }

    /**
     * 获取SingleHolder实例
     *
     * @return ViewHolder 对象
     */
    public static RobotQuestionExpandableViewHolder getInstance() {
        return SingleHolder.sViewHolder;
    }

    /**
     * 私有的构造方法，避免这个类在外部被实例化
     */
    private RobotQuestionExpandableViewHolder() {
    }

    /***
     * 获取资源对象
     * @param convertView  视图
     * @param id    控件ID
     * @param <T>   泛型
     * @return 对应的视图类型
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T get(View convertView, int id) {
        // 通过getTag获取控件view的标签，并强转成SparseArray对象,如果它为空这进行实例化，并把起设置成view的标签
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            convertView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }


    /**
     * 设置文字
     *
     * @param convertView 布局View
     * @param childViewId 子控件id
     * @param s           内容
     * @return ViewHolder
     */
    public RobotQuestionExpandableViewHolder setText(View convertView, int childViewId, String s) {
        TextView textView = get(convertView, childViewId);
        textView.setText(s);
        return this;
    }

    /**
     * 设置文字
     *
     * @param convertView 布局View
     * @param childViewId 子控件id
     * @param s           内容
     * @return ViewHolder
     */
    public RobotQuestionExpandableViewHolder setText(View convertView, int childViewId, int s) {
        TextView textView = get(convertView, childViewId);
        textView.setText(s);
        return this;
    }

    /**
     * 设置图片
     *
     * @param convertView 布局View
     * @param childViewId 子控件id
     * @param drawable    drawable
     * @return ViewHolder
     */
    public RobotQuestionExpandableViewHolder setImage(View convertView, int childViewId, Drawable drawable) {
        ImageView imageView = get(convertView, childViewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置图文数据
     *
     * @param convertView   布局View
     * @param childViewId   子控件id
     * @param txt           文本
     * @param leftDrawable  左边图片
     * @param rightDrawable 右图片
     * @return ViewHolder ViewHolder
     */
    public RobotQuestionExpandableViewHolder setImageText(View convertView, int childViewId, String txt, Drawable leftDrawable, Drawable rightDrawable) {
        TextView view = get(convertView, childViewId);
        if (isStrNull(txt)) {
            txt = "";
        }
        view.setText(txt);
        view.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, rightDrawable, null);
        return this;
    }

    /**
     * 设置文字的右图片
     *
     * @param convertView 布局View
     * @param childViewId 子控件id
     * @return ViewHolder ViewHolder
     */
    public RobotQuestionExpandableViewHolder setImageTextRight(View convertView, int childViewId, Drawable rightDrawable) {
        TextView view = get(convertView, childViewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, rightDrawable, null);
        return this;
    }


    /**
     * 空字符串判断
     *
     * @param string 要判空的字符串
     * @return true 空串， false非空
     */
    private boolean isStrNull(String string) {
        if (string == null || "null".equals(string) || "NULL".equals(string)) {
            return true;
        }
        // TextUtils.isEmpty(string);
        String str = string.replace(" ", "");
        return str.length() == 0;
    }
}
