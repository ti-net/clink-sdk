package com.tinet.oskit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.tool.ModifyUiUtils;
import com.tinet.oslib.model.bean.QuickReplyTagInfo;
import com.tinet.timclientlib.utils.TLogUtils;
import com.tinet.timclientlib.utils.TStringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: QuickReplyTagAdapter
 * @Author: zhangping
 * @CreateDate: 2022/9/6 17:42
 * @Description: 快捷回复标签适配器
 */
public class QuickReplyTagAdapter extends BaseAdapter {

    private Context mContext;
    private List<QuickReplyTagInfo> mList;

    public QuickReplyTagAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<>();
    }

    public void setList(List<QuickReplyTagInfo> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ti_session_robot_quick_reply_tag_item, null);
        TextView tvTag = view.findViewById(R.id.tv_quick_reply_item);
        QuickReplyTagInfo quickReplyTagInfo = mList.get(position);
        tvTag.setText(quickReplyTagInfo.getText());
        if (TStringUtils.isNotEmpty(quickReplyTagInfo.getBgColor())) {
            try {
                ModifyUiUtils.modifySetBackground(tvTag, Color.parseColor(quickReplyTagInfo.getBgColor()));
            } catch (Exception e) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ModifyUiUtils.modifySetBackground(tvTag, mContext.getColor(R.color.ti_quick_reply_tag_bg_color));
                }
                TLogUtils.e(e.toString());
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ModifyUiUtils.modifySetBackground(tvTag, mContext.getColor(R.color.ti_quick_reply_tag_bg_color));
            }
        }
        return view;
    }
}
