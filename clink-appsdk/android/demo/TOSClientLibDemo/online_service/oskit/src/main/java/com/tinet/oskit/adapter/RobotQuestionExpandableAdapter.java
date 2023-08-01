package com.tinet.oskit.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.RobotGroupQuestionClassicViewHolder;
import com.tinet.oskit.adapter.holder.RobotQuestionExpandableViewHolder;
import com.tinet.oslib.model.bean.OnlineQuestionData;
import com.tinet.timclientlib.utils.TLogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: RobotQuestionExpandableAdapter
 * @Author: zhangping
 * @CreateDate: 2022/12/26 13:53
 * @Description: 描述说明
 */
public class RobotQuestionExpandableAdapter extends BaseExpandableListAdapter {

    /**
     * 数据集合
     */
    private List<OnlineQuestionData> mExpandableModeList;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * ViewHolder
     */
    private RobotQuestionExpandableViewHolder mViewHolder;

    private static final int CHILD_TYPE_DEFAULT = 0;

    private static final int CHILD_TYPE_CHANGE_END_ICON = 1;

    /**
     * 折叠默认显示个数（换一换显示个数控制）
     */
    public static final int CHANGE_LIMIT = RobotGroupQuestionClassicViewHolder.LIMIT;

    public RobotQuestionExpandableAdapter(Context context) {
        mContext = context;
        mViewHolder = RobotQuestionExpandableViewHolder.getInstance();
    }

    public RobotQuestionExpandableAdapter(List<OnlineQuestionData> expandableModeList, Context context) {
        mExpandableModeList = expandableModeList;
        mContext = context;
        mViewHolder = RobotQuestionExpandableViewHolder.getInstance();
    }

    /**
     * @return 返回组item数量
     */
    @Override
    public int getGroupCount() {
        return mExpandableModeList == null ? 0 : mExpandableModeList.size();
    }

    /**
     * 根据出入的组groupPosition返回子item的数量。
     *
     * @param groupPosition groupPosition
     * @return 返回子item的数量。
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if (mExpandableModeList == null) return 0;
        List<String> childDataBeans = mExpandableModeList.get(groupPosition).getShowTopics();
        return childDataBeans == null ? 0 : childDataBeans.size();
    }

    /**
     * 获取组实体对象
     *
     * @param groupPosition groupPosition
     * @return 返回组实体对象
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mExpandableModeList == null ? null : mExpandableModeList.get(groupPosition);
    }

    /**
     * 获取子实体对象
     *
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @return 返回子实体对象
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mExpandableModeList == null) return null;
        List<String> childDataBeans = mExpandableModeList.get(groupPosition).getShowTopics();
        return childDataBeans == null ? null : childDataBeans.get(childPosition);
    }

    /**
     * 获取组id,我理解为获取组的唯一ID，一般自己返回groupPosition即可
     *
     * @param groupPosition groupPosition
     * @return Position
     */
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    /**
     * 获取子ID
     *
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @return 返回子id
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }


    /**
     * 用来判断ExpandableListView内容id是否有效的,我也不太明白，它具体有什么作用，设置返回true和false的效果是一样的。
     *
     * @return true or false
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 获取给定分组的视图，主要重写的方法。
     *
     * @param groupPosition groupPosition
     * @param isExpanded    该组是展开状态还是收起状态
     * @param convertView   convertView
     * @param parent        parent
     * @return View
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.frg_session_item_elv_question_group, parent, false);
        }
        // 获取组的实体对象
        OnlineQuestionData expandableMode = mExpandableModeList.get(groupPosition);
        // 获取组名
        String groupName = expandableMode.getName();

        // 设置TextView的文字
        mViewHolder.setText(convertView, R.id.tvGroupName, groupName);

        // 设置组item展开或者关闭的右图片
        Drawable drawableRight = ContextCompat.getDrawable(mContext, R.mipmap.ti_grey_right);
        if (isExpanded) {
            // 展开
            drawableRight = ContextCompat.getDrawable(mContext, R.mipmap.ti_grey_down);
        }
        mViewHolder.setImageTextRight(convertView, R.id.tvGroupName, drawableRight);

        return convertView;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if (mExpandableModeList.get(groupPosition).getTopics().size() > 5 && childPosition == mExpandableModeList.get(groupPosition).getShowTopics().size() - 1) {
            return CHILD_TYPE_CHANGE_END_ICON;
        }
        return CHILD_TYPE_DEFAULT;
    }

    /**
     * 获取给定分组给定子位置的数据用的视图，主要重写的方法。
     *
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @param isLastChild   是否为改组最后一个子视图
     * @param convertView   convertView
     * @param parent        parent
     * @return View
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        OnlineQuestionData onlineQuestionData = mExpandableModeList.get(groupPosition);
        if (getChildType(groupPosition, childPosition) == CHILD_TYPE_DEFAULT) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.frg_session_item_elv_question_child, parent, false);
            }
            List<String> childDataBeans = onlineQuestionData.getShowTopics();
            String childName = childDataBeans.get(childPosition);
            // 设置文字，并手动添加下标索引
            mViewHolder.setText(convertView, R.id.tvChildName, (childPosition + 1) + "." + childName);

        } else {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.frg_session_item_elv_question_child_end_change, parent, false);
            }

            mViewHolder.get(convertView, R.id.tvChanged).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onlineQuestionData.setCurrentPage(onlineQuestionData.getCurrentPage() + 1);
                    detailIsShowChangeIconData(mExpandableModeList);
                    notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }

    /**
     * 子item是否可点击
     * 当isChildSelectable方法返回false的时候，子item不可点击，且不会绘制分割线
     *
     * @param groupPosition groupPosition
     * @param childPosition childPosition
     * @return true可点击 false不可点击
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setExpandableModeList(List<OnlineQuestionData> expandableModeList) {
        if (expandableModeList != null) {
            mExpandableModeList = detailIsShowChangeIconData(expandableModeList);
        }
    }

    private List<OnlineQuestionData> detailIsShowChangeIconData(List<OnlineQuestionData> expandableModeList) {
        List<OnlineQuestionData> onlineQuestionDataList = new ArrayList<>();
        for (int i = 0; i < expandableModeList.size(); i++) {
            OnlineQuestionData onlineQuestionData = expandableModeList.get(i);
            if (onlineQuestionData.getTopics().size() > CHANGE_LIMIT) {
                if (onlineQuestionData.getTopics().size() < onlineQuestionData.getCurrentPage() * CHANGE_LIMIT) {
                    onlineQuestionData.setCurrentPage(0);
                }
                List<String> showTopics = new ArrayList<>();
                for (int j = onlineQuestionData.getCurrentPage() * CHANGE_LIMIT; j < onlineQuestionData.getTopics().size(); j++) {
                    showTopics.add(onlineQuestionData.getTopics().get(j));
                    if (showTopics.size() == CHANGE_LIMIT) {
                        break;
                    }
                }
                // : 2023/1/11 数组末尾再添加占位数据，用于显示换一换icon，不做其他处理，数据内容无任何实质意义
                showTopics.add("换一换icon");
                onlineQuestionData.setShowTopics(showTopics);
            } else {
                onlineQuestionData.setShowTopics(onlineQuestionData.getTopics());
            }
            onlineQuestionDataList.add(onlineQuestionData);
        }

        return onlineQuestionDataList;
    }
}
