package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.manager.TCustomizationUI;
import com.tinet.oskit.manager.TOSClientKitConfig;
import com.tinet.threepart.tools.TKeyBoardUtils;
import com.tinet.timclientlib.utils.TLogUtils;
import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.bean.CustomItemBean;
import com.tinet.tosclientkitdemo.common.adapter.BaseViewHolder;
import com.tinet.tosclientkitdemo.common.adapter.CommonAdapter;
import com.tinet.tosclientkitdemo.common.app.App;
import com.tinet.tosclientkitdemo.common.base.BaseActivity;
import com.tinet.tosclientkitdemo.utils.ToastUtils;
import com.tinet.tosclientkitdemo.widget.EditTextWithDelete;
import com.zp.customdialoglib.dialog.CommonDialogFactory;
import com.zp.customdialoglib.dialog.DialogUtil;
import com.zp.customdialoglib.dialog.ICommonDialog;

import java.util.ArrayList;
import java.util.List;

public class CustomStyleActivity extends BaseActivity implements View.OnClickListener {

    //会话样式主题对应索引
    private static final int SESSION_STYLE_THEME_DEFAULT = 0;
    private static final int SESSION_STYLE_THEME_BLUE = 1;
    private static final int SESSION_STYLE_THEME_YELLOW = 2;
    private static final int SESSION_STYLE_THEME_RED = 3;
    private static final int SESSION_STYLE_THEME_GREEN = 4;

    private static final String INPUT_BOX_HINT_TEXT_TAG = "inputBoxHintText";
    private static final String COLOR_INPUT_HINT = "6/8 位数色值";
    private static final String COLOR_INPUT_ERROR_HINT = "格式错误，请输入有效的 6/8 位数色值";
    private static final String INPUT_BOX_HINT = "不超过12个字";

    private RecyclerView rvCustomList;
    private List<CustomItemBean> mCustomDataList;
    private TCustomizationUI mTCustomizationUI;
    private CommonAdapter<CustomItemBean> mCommonAdapter;
    private Dialog mInputDialog;

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_custom_style;
    }

    @Override
    protected void initView() {
        ((TextView) findViewById(R.id.toolbar_title)).setText("自定义样式");
        findViewById(R.id.toolbar_back).setOnClickListener(this);
        View viewRightTitle = findViewById(R.id.toolbar_right_title);
        viewRightTitle.setVisibility(View.VISIBLE);
        viewRightTitle.setOnClickListener(this);

        rvCustomList = findViewById(R.id.rv_custom_set_list);
        rvCustomList.setLayoutManager(new LinearLayoutManager(CustomStyleActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        initThemeData();
        mCommonAdapter = new CommonAdapter<CustomItemBean>(mCustomDataList) {
            @Override
            protected int getItemType(CustomItemBean item) {
                switch (item.getViewType()) {
                    case CustomItemBean.CUSTOM_ITEM_TYPE_SEPARATE:
                        return R.layout.item_rv_custom_separate_view;
                    case CustomItemBean.CUSTOM_ITEM_TYPE_GROUP_TITLE:
                        return R.layout.item_rv_custom_group_title_view;
                    case CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW:
                        return R.layout.item_rv_custom_content_view;
                }
                return R.layout.item_rv_custom_separate_view;
            }

            @Override
            protected void convert(BaseViewHolder viewHolder, int layoutId, CustomItemBean item, int position) {
                if (item.getViewType() != CustomItemBean.CUSTOM_ITEM_TYPE_SEPARATE) {
                    viewHolder.setText(R.id.tv_custom_title, item.getTitle());
                }
                if (item.getViewType() == CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW) {
                    if (item.getTagName().equals(INPUT_BOX_HINT_TEXT_TAG)) {
                        viewHolder.setText(R.id.tv_custom_remark, item.getStrValue());
                        viewHolder.getView(R.id.tv_custom_remark).setVisibility(View.VISIBLE);
                        viewHolder.getView(R.id.tv_custom_value).setVisibility(View.GONE);
                    } else {
                        viewHolder.getView(R.id.tv_custom_remark).setVisibility(View.GONE);
                        viewHolder.getView(R.id.tv_custom_value).setVisibility(View.VISIBLE);
                        viewHolder.setText(R.id.tv_custom_value, item.getStrValue());
                    }
                    if (item.getContentType() == CustomItemBean.CUSTOM_CONTENT_TYPE_SWITCH) {
                        viewHolder.getView(R.id.sv_custom_value).setVisibility(View.VISIBLE);
                        viewHolder.getView(R.id.ll_custom_value).setVisibility(View.GONE);
                    } else {
                        viewHolder.getView(R.id.sv_custom_value).setVisibility(View.GONE);
                        viewHolder.getView(R.id.ll_custom_value).setVisibility(View.VISIBLE);
                    }
                    viewHolder.getView(R.id.ll_custom_content).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (item.getViewType() == CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW) {
                                switch (item.getContentType()) {
                                    case CustomItemBean.CUSTOM_CONTENT_TYPE_CHOOSE_PAGE:
                                        startActivity(new Intent(CustomStyleActivity.this, ThemeListActivity.class));
                                        break;
                                    case CustomItemBean.CUSTOM_CONTENT_TYPE_CHOOSE_DIALOG:
                                        showInputDialog(item, position);
                                        break;
                                }
                            }
                        }
                    });
                    Switch aSwitch = (Switch) viewHolder.getView(R.id.sv_custom_value);
                    aSwitch.setChecked(item.isBoolValue());
                    aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            item.setBoolValue(isChecked);
                        }
                    });
                }
            }
        };
        rvCustomList.setAdapter(mCommonAdapter);
    }

    private void showInputDialog(CustomItemBean item, int position) {
        if (mInputDialog == null) {
            mInputDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        }
        if (mInputDialog.isShowing()) {
            mInputDialog.dismiss();
        }
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_dialog_input_control, null);
        TextView tvDialogTitle = inflate.findViewById(R.id.tv_custom_input_dialog_title);
        tvDialogTitle.setText(item.getTitle());
        TextView tvDialogHint = inflate.findViewById(R.id.tv_custom_input_dialog_hint);
        EditTextWithDelete editTextWithDelete = inflate.findViewById(R.id.et_custom_input_dialog_value);
        inflate.findViewById(R.id.iv_custom_input_dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputDialog.dismiss();
            }
        });
        if (item.getTagName().equals(INPUT_BOX_HINT_TEXT_TAG)) {
            tvDialogHint.setText(INPUT_BOX_HINT);
            tvDialogHint.setMaxEms(12);
        } else {
            tvDialogHint.setText(COLOR_INPUT_HINT);
        }
        tvDialogHint.setTextColor(Color.GRAY);
        editTextWithDelete.setText(item.getStrValue());
        editTextWithDelete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence inputContent, int start, int before, int count) {
                if (inputContent.toString().length() > 0) {
                    if (!item.getTagName().equals(INPUT_BOX_HINT_TEXT_TAG)) {
                        try {
                            Color.parseColor(inputContent.toString());
                            tvDialogHint.setText(COLOR_INPUT_HINT);
                            tvDialogHint.setTextColor(Color.GRAY);
                        } catch (Exception e) {
                            tvDialogHint.setText(COLOR_INPUT_ERROR_HINT);
                            tvDialogHint.setTextColor(Color.RED);
                        }
                    }
                } else {
                    tvDialogHint.setText(COLOR_INPUT_HINT);
                    tvDialogHint.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inflate.findViewById(R.id.tv_custom_input_dialog_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // : 2022/8/2 检测输入数据是否符合规范，保存到变量
                String inputContent = editTextWithDelete.getText().toString();
                if (inputContent.length() > 0) {
                    if (!item.getTagName().equals(INPUT_BOX_HINT_TEXT_TAG)) {
                        try {
                            Color.parseColor(inputContent);
                            // : 2022/8/2 保存色值
                            item.setStrValue(inputContent);
                        } catch (Exception e) {
                            ToastUtils.showToast(CustomStyleActivity.this, tvDialogHint.getText().toString());
                            return;
                        }
                    } else {
                        // : 2022/8/2 保存暗文
                        item.setStrValue(inputContent);
                    }
                }
                mCommonAdapter.notifyItemChanged(position);
                mInputDialog.dismiss();
            }
        });
        //将布局设置给Dialog
        mInputDialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = mInputDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
        //    将属性设置给窗体
        dialogWindow.setAttributes(lp);
        //设置占满宽，高度自适应
        dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mInputDialog.show();//显示对话框
    }

    private void initThemeData() {

        TOSClientKitConfig tosClientKitConfig = TOSClientKit.getTOSClientKitConfig();
        if (tosClientKitConfig != null && tosClientKitConfig.getTCustomizationUI() != null) {
            mTCustomizationUI = tosClientKitConfig.getTCustomizationUI();
        } else {
            if (mTCustomizationUI == null)
                mTCustomizationUI = new TCustomizationUI();
            setDefaultCustomizationUIData();
        }

        if (mCustomDataList == null) {
            mCustomDataList = new ArrayList<>();
        } else {
            mCustomDataList.clear();
        }

        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_SEPARATE));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_GROUP_TITLE, "对话区域配置"));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "聊天窗口UI样式", "sessionStyle", CustomItemBean.CUSTOM_CONTENT_TYPE_CHOOSE_PAGE, getStyleDesc()));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "时间文本色值", "msgTimeColor", CustomItemBean.CUSTOM_CONTENT_TYPE_CHOOSE_DIALOG, getHexString(mTCustomizationUI.msgTimeColor)));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "客服/机器人昵称显示", "robotName", CustomItemBean.CUSTOM_CONTENT_TYPE_SWITCH, mTCustomizationUI.showAgentRobotNickname));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "访客昵称显示", "visitorName", CustomItemBean.CUSTOM_CONTENT_TYPE_SWITCH, mTCustomizationUI.showVisitorNickname));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "客服/机器人头像显示", "acceptMessageAvatar", CustomItemBean.CUSTOM_CONTENT_TYPE_SWITCH, mTCustomizationUI.showAgentRobotAvatar));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "访客头像显示", "visitorAvatar", CustomItemBean.CUSTOM_CONTENT_TYPE_SWITCH, mTCustomizationUI.showVisitAvatar));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_SEPARATE));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_GROUP_TITLE, "输入框配置"));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "输入框背景色值", "inputAreaBgColor", CustomItemBean.CUSTOM_CONTENT_TYPE_CHOOSE_DIALOG, getHexString(mTCustomizationUI.inputAreaBgColor)));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "语音按钮文字色值", "inputAreaVoicePressTextColor", CustomItemBean.CUSTOM_CONTENT_TYPE_CHOOSE_DIALOG, getHexString(mTCustomizationUI.inputAreaVoicePressTextColor)));
        mCustomDataList.add(new CustomItemBean(CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW, "输入框暗文提示语", INPUT_BOX_HINT_TEXT_TAG, CustomItemBean.CUSTOM_CONTENT_TYPE_CHOOSE_DIALOG, mTCustomizationUI.inputBoxHintText));

    }

    private void setDefaultCustomizationUIData() {

        mTCustomizationUI.msgTimeColor = Color.parseColor("#FF595959");
        mTCustomizationUI.inputAreaBgColor = Color.parseColor("#FFF3F6F9");
        mTCustomizationUI.inputAreaVoicePressTextColor = Color.parseColor("#FFBFBFBF");
        mTCustomizationUI.inputBoxHintText = "请输入您要咨询的问题";

        mTCustomizationUI.showVisitorNickname = true;//访客昵称
        mTCustomizationUI.showVisitAvatar = true;//访客头像
        mTCustomizationUI.showAgentRobotNickname = true;//客服、机器人昵称
        mTCustomizationUI.showAgentRobotAvatar = true;//客服、机器人头像
        mTCustomizationUI.showVoiceButton = true;//语音按钮
    }

    private String getHexString(int color) {
        String s = "#";
        int colorStr = (color & 0xff000000) | (color & 0x00ff0000) | (color & 0x0000ff00) | (color & 0x000000ff);
        s = s + Integer.toHexString(colorStr);
        return s;
    }

    private void showSetDefaultStylePopHint() {

        final ICommonDialog dialogByType2 = CommonDialogFactory.createDialogByType(CustomStyleActivity.this, DialogUtil.DIALOG_TYPE_103);
        dialogByType2.setOkBtnStyleType(DialogUtil.OK_BTN_LARGE_WHITE_BG_BLUE_TEXT);
        dialogByType2.setTitleText("恢复默认配置");
        dialogByType2.setContentText("确认要恢复默认配置吗，恢复后已修改的配置项将不会被保存。");
        dialogByType2.setCancelBtn("取消", null);
        dialogByType2.setOkBtn("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.CHOOSE_THEME_INDEX = SESSION_STYLE_THEME_DEFAULT;//设置默认主题
                TOSClientKit.setTOSClientKitConfig(null);
                initThemeData();
                mCommonAdapter.setData(mCustomDataList);
                dialogByType2.dismiss();
            }
        });
        dialogByType2.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCustomDataList != null && mCustomDataList.size() > 0 && mCommonAdapter != null) {
            CustomItemBean customItemBean = mCustomDataList.get(2);
            customItemBean.setStrValue(getStyleDesc());
            mCommonAdapter.notifyItemChanged(2);
        }
    }

    private String getStyleDesc() {
        switch (App.CHOOSE_THEME_INDEX) {
            case SESSION_STYLE_THEME_DEFAULT:
                return "经典样式";
            case SESSION_STYLE_THEME_BLUE:
                return "蓝色商务风";
            case SESSION_STYLE_THEME_YELLOW:
                return "黄色圆润风";
            case SESSION_STYLE_THEME_RED:
                return "红色绅士风";
            case SESSION_STYLE_THEME_GREEN:
                return "绿色田园风";
        }
        return "经典样式";
    }

    @Override
    protected void onPause() {
        if (mTCustomizationUI != null) {
            checkCustomListData();
            TOSClientKitConfig tosClientKitConfig = new TOSClientKitConfig.Builder()
                    .setTCustomizationUI(mTCustomizationUI)
                    .build();
            TOSClientKit.setTOSClientKitConfig(tosClientKitConfig);
        }
        super.onPause();
    }

    private void checkCustomListData() {
        for (int i = 0; i < mCustomDataList.size(); i++) {
            CustomItemBean customItemBean = mCustomDataList.get(i);
            if (customItemBean.getViewType() == CustomItemBean.CUSTOM_ITEM_TYPE_CONTENT_VIEW) {
                switch (customItemBean.getTagName()) {
                    case "msgTimeColor":
                        mTCustomizationUI.msgTimeColor = Color.parseColor(customItemBean.getStrValue());
                        break;
                    case "robotName":
                        mTCustomizationUI.showAgentRobotNickname  = customItemBean.isBoolValue();
                        break;
                    case "visitorName":
                        mTCustomizationUI.showVisitorNickname = customItemBean.isBoolValue();
                        break;
                    case "visitorAvatar":
                        mTCustomizationUI.showVisitAvatar = customItemBean.isBoolValue();
                        break;
                    case "acceptMessageAvatar":
                        mTCustomizationUI.showAgentRobotAvatar = customItemBean.isBoolValue();
                        break;
                    case "inputAreaBgColor":
                        mTCustomizationUI.inputAreaBgColor = Color.parseColor(customItemBean.getStrValue());
                        break;
                    case "inputAreaVoicePressTextColor":
                        mTCustomizationUI.inputAreaVoicePressTextColor = Color.parseColor(customItemBean.getStrValue());
                        break;
                    case INPUT_BOX_HINT_TEXT_TAG:
                        mTCustomizationUI.inputBoxHintText = customItemBean.getStrValue();
                        break;
                    case "sessionStyle"://根据选择主题，设置不同主题对应的UI样式
                        switch (App.CHOOSE_THEME_INDEX) {
                            case SESSION_STYLE_THEME_DEFAULT:
                                mTCustomizationUI.sessionBackgroundColor = Color.parseColor("#FFEFF1F5");
                                mTCustomizationUI.chatAvatarRadius = R.dimen.chat_avatar_radius_default;
                                mTCustomizationUI.sendBubbleBackground = R.drawable.shape_message_send_bg_default;
                                mTCustomizationUI.receiveBubbleBackground = R.drawable.shape_message_receive_bg_default;
                                break;
                            case SESSION_STYLE_THEME_BLUE:
                                mTCustomizationUI.sessionBackgroundColor = Color.parseColor("#FFEFF3F5");
                                mTCustomizationUI.chatAvatarRadius = R.dimen.chat_avatar_radius_blue;
                                mTCustomizationUI.sendBubbleBackground = R.drawable.shape_message_send_bg_blue;
                                mTCustomizationUI.receiveBubbleBackground = R.drawable.shape_message_receive_bg_blue;
                                break;
                            case SESSION_STYLE_THEME_YELLOW:
                                mTCustomizationUI.sessionBackgroundColor = Color.parseColor("#FFFBF9ED");
                                mTCustomizationUI.chatAvatarRadius = R.dimen.chat_avatar_radius_yellow;
                                mTCustomizationUI.sendBubbleBackground = R.drawable.shape_message_send_bg_yellow;
                                mTCustomizationUI.receiveBubbleBackground = R.drawable.shape_message_receive_bg_yellow;
                                break;
                            case SESSION_STYLE_THEME_RED:
                                mTCustomizationUI.sessionBackgroundColor = Color.parseColor("#FFF9F3F2");
                                mTCustomizationUI.chatAvatarRadius = R.dimen.chat_avatar_radius_red;
                                mTCustomizationUI.sendBubbleBackground = R.drawable.shape_message_send_bg_red;
                                mTCustomizationUI.receiveBubbleBackground = R.drawable.shape_message_receive_bg_red;
                                break;
                            case SESSION_STYLE_THEME_GREEN:
                                mTCustomizationUI.sessionBackgroundColor = Color.parseColor("#FFEFF5F1");
                                mTCustomizationUI.chatAvatarRadius = R.dimen.chat_avatar_radius_green;
                                mTCustomizationUI.sendBubbleBackground = R.drawable.shape_message_send_bg_green;
                                mTCustomizationUI.receiveBubbleBackground = R.drawable.shape_message_receive_bg_green;
                                break;
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                onBackPressed();
                break;
            case R.id.toolbar_right_title:
                showSetDefaultStylePopHint();
                break;
        }
    }

}