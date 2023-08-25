package com.tinet.oskit.adapter.holder;

import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tinet.oskit.R;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.adapter.SatisfactionLevelAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.listener.ChatInfoCallback;
import com.tinet.oslib.manager.InvestigationManager;
import com.tinet.oslib.manager.OnlineManager;
import com.tinet.oslib.model.bean.Investigation;
import com.tinet.oslib.model.bean.OnlineSetting;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;
import com.tinet.timclientlib.utils.TLogUtils;
import com.tinet.timclientlib.utils.TToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SessionInvestigationViewHolder extends SessionViewHolder {

    private TextView welcome, submit;
    private RecyclerView rv_label;
    private RadioGroup radioGroup;
    private RadioButton very_dissatisfied, dissatisfaction, commonly, satisfied, very_satisfied;
    private SatisfactionLevelAdapter adapter;
    private Investigation investigation;
    private String name;
    private Integer star;
    private JSONArray label = new JSONArray();
    private ChatInvestigationMessage chatInvestigationMessage;
    private int starSize;

    private Map<String, Object> checkedRecord = new HashMap<>();

    public SessionInvestigationViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        welcome = itemView.findViewById(R.id.welcome);
        rv_label = itemView.findViewById(R.id.rv_label);
        radioGroup = itemView.findViewById(R.id.radioGroup);
        very_dissatisfied = itemView.findViewById(R.id.very_dissatisfied);
        dissatisfaction = itemView.findViewById(R.id.dissatisfaction);
        commonly = itemView.findViewById(R.id.commonly);
        satisfied = itemView.findViewById(R.id.satisfied);
        very_satisfied = itemView.findViewById(R.id.very_satisfied);
        submit = itemView.findViewById(R.id.submit);
    }

    @Override
    public void update(final OnlineMessage info) {
        super.update(info);

        radioGroup.clearCheck();

        if (TOSClientKit.getTOSClientKitConfig() == null)
            return;
        OnlineSetting onlineSetting = TOSClientKit.getTOSClientKitConfig().getOnlineSetting();
        if (onlineSetting == null || onlineSetting.getInvestigation() == null)
            return;
        investigation = onlineSetting.getInvestigation();
        welcome.setText(investigation.getWelcome());

        rv_label.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
        rv_label.setNestedScrollingEnabled(false);
        adapter = new SatisfactionLevelAdapter(listener);

        very_dissatisfied.setVisibility(View.GONE);
        dissatisfaction.setVisibility(View.GONE);
        commonly.setVisibility(View.GONE);
        satisfied.setVisibility(View.GONE);
        very_satisfied.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
        starSize = investigation.getStar().size();
        switch (starSize) {
            case 2:
                satisfied.setVisibility(View.VISIBLE);
                dissatisfaction.setVisibility(View.VISIBLE);
                dissatisfaction.setText(investigation.getStar().get(1).getDesc());
                satisfied.setText(investigation.getStar().get(0).getDesc());
                break;
            case 3:
                satisfied.setVisibility(View.VISIBLE);
                dissatisfaction.setVisibility(View.VISIBLE);
                commonly.setVisibility(View.VISIBLE);
                dissatisfaction.setText(investigation.getStar().get(2).getDesc());
                commonly.setText(investigation.getStar().get(1).getDesc());
                satisfied.setText(investigation.getStar().get(0).getDesc());
                break;
            case 4:
                very_dissatisfied.setVisibility(View.VISIBLE);
                commonly.setVisibility(View.VISIBLE);
                satisfied.setVisibility(View.VISIBLE);
                very_satisfied.setVisibility(View.VISIBLE);
                very_dissatisfied.setText(investigation.getStar().get(3).getDesc());
                commonly.setText(investigation.getStar().get(2).getDesc());
                satisfied.setText(investigation.getStar().get(1).getDesc());
                very_satisfied.setText(investigation.getStar().get(0).getDesc());
                break;
            case 5:
                very_dissatisfied.setVisibility(View.VISIBLE);
                dissatisfaction.setVisibility(View.VISIBLE);
                commonly.setVisibility(View.VISIBLE);
                satisfied.setVisibility(View.VISIBLE);
                very_satisfied.setVisibility(View.VISIBLE);
                very_dissatisfied.setText(investigation.getStar().get(4).getDesc());
                dissatisfaction.setText(investigation.getStar().get(3).getDesc());
                commonly.setText(investigation.getStar().get(2).getDesc());
                satisfied.setText(investigation.getStar().get(1).getDesc());
                very_satisfied.setText(investigation.getStar().get(0).getDesc());
                break;
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton_checked = group.findViewById(checkedId);
                if (radioButton_checked != null && radioButton_checked.isChecked()) {
                    submit.setVisibility(View.VISIBLE);
                    rv_label.setVisibility(View.VISIBLE);
                    name = radioButton_checked.getText().toString();
                    for (int n = 0; n < investigation.getStar().size(); n++) {
                        if (name.equals(investigation.getStar().get(n).getDesc())) {
                            star = investigation.getStar().get(n).getStar();
                            rv_label.setAdapter(adapter);
                            adapter.setData(investigation.getContent().getOptions().get(0).getStar().get(star - 1).getTabBar());
                            label = new JSONArray();
                        }
                    }
                }
            }
        });

        chatInvestigationMessage = (ChatInvestigationMessage) info.getOnlineContent();
        if (chatInvestigationMessage.getAlreadyInvestigation() == 1) {
            //已评价
            rv_label.setVisibility(View.VISIBLE);
            if (checkedRecord != null && checkedRecord.get(chatInvestigationMessage.getUniqueId()) != null) {
                checkInvestigationResult();
            } else {
                getRecordInvestigation();
            }
        } else {
            //未评价
            checkInvestigationResult();
            rv_label.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
        }

        adapter.setListener(new SatisfactionLevelAdapter.ItemClickListener() {
            @Override
            public void onItemClick(String tabBar) {
                try {
                    if (label.length() > 0 && label.toString().contains(tabBar)) {
                        for (int i = 0; i < label.length(); i++) {
                            if (tabBar.equals(label.get(i)))
                                label.remove(i);
                        }
                        return;
                    }
                    label.put(tabBar);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                try {
                    JSONObject object = new JSONObject();
                    object.put("accessId", OnlineServiceClient.getAccessId());//访问id
                    object.put("visitorId", OnlineServiceClient.getCurrentUserInfo().getVisitorId());//访问id
                    object.put("mainUniqueId", chatInvestigationMessage.getMainUniqueId());//主会话标识
                    object.put("uniqueId", chatInvestigationMessage.getUniqueId());//从会话唯一标识
                    object.put("remark", "");//评价备注
                    object.put("solve", null);//解决状态
                    final JSONArray array = new JSONArray();
                    JSONObject options = new JSONObject();
                    options.put("name", name);//选项名称
                    options.put("star", star);//星级
                    options.put("label", label);
                    array.put(options);
                    object.put("options", array);//评价信息
                    InvestigationManager.submitInvestigation(object.toString(), new ChatInfoCallback() {
                        @Override
                        public void onSuccess(JSONObject data) {
                            try {
                                if (data != null && data.has("code")) {
                                    if (data.getInt("code") == 0) {
                                        enableView(false);
                                        checkedRecord.put(chatInvestigationMessage.getUniqueId(), array);
                                        TToastUtils.showShortToast(v.getContext(), "提交成功");
                                    } else {
                                        closeSubmitBoard();
                                        TToastUtils.showShortToast(v.getContext(), data.getString("msg"));
                                    }
                                }
                            } catch (Exception e) {
                                closeSubmitBoard();
                                TToastUtils.showShortToast(v.getContext(), "提交失败，请重试");
                                TLogUtils.e(e.toString());
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            TLogUtils.i("submitInvestigation onError:" + e.toString());
                            closeSubmitBoard();
                            TToastUtils.showShortToast(v.getContext(), "提交失败，请重试");
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        if (chatInvestigationMessage.getAlreadyInvestigation() != 1){
            setDefaultValue();
        }
    }

    private void setDefaultValue(){
        int id = radioGroup.getCheckedRadioButtonId();
        if(id == -1){
            RadioButton radioButton = null;
            int count = radioGroup.getChildCount()-1;
            while (radioButton == null && count >=0){
                View view = radioGroup.getChildAt(count--);
                if(view instanceof RadioButton){
                    radioButton = (RadioButton) view;
                }
            }

            if(radioButton != null){
                radioButton.setChecked(true);
            }
        }
    }


    private void closeSubmitBoard() {
        radioGroup.clearCheck();
        adapter.setData(new ArrayList<String>());
        adapter.notifyDataSetChanged();
        submit.setVisibility(View.GONE);
    }

    private void getRecordInvestigation() {
        InvestigationManager.getRecordInvestigation(new ChatInfoCallback() {
            @Override
            public void onSuccess(JSONObject data) {
                TLogUtils.i("RecordInvestigation onSuccess:" + data.toString());
                try {
                    if (data != null && data.has("options")) {
                        JSONArray options = data.getJSONArray("options");
                        if (options != null) {
                            checkedRecord.put(chatInvestigationMessage.getUniqueId(), options);
                        }
                    }
                } catch (Exception e) {
                    TLogUtils.e(e.toString());
                }
                // : 2022/8/22 展示已评价内容 设置按钮不可点击
                checkInvestigationResult();
                enableView(false);
            }

            @Override
            public void onError(Exception e) {
                TLogUtils.i("RecordInvestigation onError:" + e.toString());
            }
        });
    }

    /**
     * 检查满意度结果，并选中对应已评价结果
     */
    private void checkInvestigationResult() {
        if (checkedRecord != null && checkedRecord.get(chatInvestigationMessage.getUniqueId()) != null) {
            JSONArray options = (JSONArray) checkedRecord.get(chatInvestigationMessage.getUniqueId());
            try {
                if (options != null && options.length() > 0) {
                    final JSONObject resultObj = options.getJSONObject(0);
                    String name = resultObj.getString("name");
                    for (int i = 0; i < radioGroup.getChildCount(); i++) {
                        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                        if (radioButton.getText().toString().equals(name)) {
                            radioButton.setChecked(true);
                        }
                    }
                    submit.setVisibility(View.GONE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray labelArray = resultObj.getJSONArray("label");
                                if (labelArray.length() > 0) {
                                    List<String> labelList = new ArrayList<>();
                                    for (int i = 0; i < labelArray.length(); i++) {
                                        labelList.add(labelArray.getString(i));
                                    }
                                    for (int i = 0; i < rv_label.getChildCount(); i++) {
                                        CheckBox checkBox = rv_label.getChildAt(i).findViewById(R.id.cb_satisfaction_level);
                                        checkBox.setChecked(labelList.contains(checkBox.getText().toString()));
                                        checkBox.setEnabled(false);
                                    }
                                }
                                enableView(false);
                            } catch (Exception e) {
                                TLogUtils.e(e.toString());
                            }
                        }
                    }, 100);
                }
            } catch (Exception e) {
                TLogUtils.e(e.toString());
            }
        } else {
            enableView(true);
        }

    }

    /**
     * 控制已评价状态下所有按钮不可点击
     *
     * @param enable
     */
    private void enableView(boolean enable) {
        if (!enable) {
            submit.setVisibility(View.GONE);
        }
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(enable);
            if (enable) {
                ((RadioButton) (radioGroup.getChildAt(i))).setChecked(false);
            }
        }
        for (int i = 0; i < rv_label.getChildCount(); i++) {
            CheckBox checkBox = rv_label.getChildAt(i).findViewById(R.id.cb_satisfaction_level);
            checkBox.setEnabled(enable);
        }
    }

    public void setCurrentCheckedRecord(Map<String, Object> checkedRecord) {
        this.checkedRecord = checkedRecord;
    }
}
