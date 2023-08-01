package com.tinet.oskit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: CustomExpandableListView
 * @Author: zhangping
 * @CreateDate: 2022/12/26 14:51
 * @Description: 描述说明
 */
public class TiCustomExpandableListView extends ExpandableListView {
    public TiCustomExpandableListView(Context context) {
        super(context);
    }

    public TiCustomExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TiCustomExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 处理嵌套场景下，数据显示不完整的问题，更换绘制高度
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}
