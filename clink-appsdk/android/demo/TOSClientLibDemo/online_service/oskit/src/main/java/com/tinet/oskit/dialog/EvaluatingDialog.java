package com.tinet.oskit.dialog;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tinet.oskit.R;
import com.tinet.oskit.adapter.TinetAdapter;
import com.tinet.oskit.adapter.decoration.GridLayoutManagerDecoration;
import com.tinet.oskit.adapter.holder.TinetViewHolder;
import com.tinet.oskit.model.InvestigationType;
import com.tinet.oslib.model.bean.Investigation;
import com.tinet.oslib.model.bean.InvestigationContentOptions;
import com.tinet.oslib.model.bean.InvestigationContentOptionsStar;
import com.tinet.oslib.model.bean.InvestigationStar;
import java.util.ArrayList;
import java.util.List;

/**
 * 满意度
 *
 * @author: liuzeren
 * @date: 2022/3/30
 */
public class EvaluatingDialog extends TinetDialog {

    public interface OnEvaluatingListener{

        /**
         * 提交评论
         */
        void submitEvaluating(InvestigationStar investigationStar,List<String> starTabs);

        /**
         * 取消
         */
        void cancelEvaluating();
    }

    /**
     * 满意度评价回调
     */
    private OnEvaluatingListener listener;

    /**
     * 满意度评价信息
     */
    private Investigation investigation;

    private RadioGroup radioGroup;

    /**
     * 欢迎
     */
    private TextView tvWelcome;

    private RecyclerView recyclerView;
    private InvestigationContentOptionsAdapter adapter;

    /**
     * 当前选中的星级
     */
    private InvestigationStar currentInvestigationStar;

    /**
     * 当前选中的星级对应的标签
     */
    private List<String> currentInvestigationStarTabs = new ArrayList<>();

    public EvaluatingDialog(Investigation investigation,OnEvaluatingListener listener) {
        this.listener = listener;
        this.investigation = investigation;
    }

    @Override
    protected int layoutId() {
        return R.layout.dlg_evaluating;
    }


    @Override
    void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter = new InvestigationContentOptionsAdapter());
        recyclerView.addItemDecoration(new GridLayoutManagerDecoration(getResources().getDimensionPixelSize(R.dimen.ti_msg_msg_span)));
        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.removeAllViews();
        view.findViewById(R.id.tvSubmit).setOnClickListener(v -> {
            if(null != listener){
                listener.submitEvaluating(currentInvestigationStar,currentInvestigationStarTabs);
            }
        });
        view.findViewById(R.id.ivClose).setOnClickListener(v -> {
            if(listener != null){
                listener.cancelEvaluating();
            }
            dismiss();
        });
        if(isInvestigationValidate()){
            InvestigationContentOptions options = investigation.getContent().getOptions().get(0);

            for (int i=0;i<investigation.getStar().size();i++) {
                InvestigationStar investigationStar = investigation.getStar().get(i);
                //star与options中的star的顺序刚好相反

                LinearLayout.LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.weight = 1;

                RadioButton radioButton = newRadioButton(investigationStar,options.getStar().get(investigation.getStar().size() - i - 1));
                //默认好评,第一个就是好评
                if(currentInvestigationStar == null) {
                    currentInvestigationStar = investigationStar;
                    radioButton.setChecked(true);
                    notifyRadioButtonChanged(radioButton);
                }
                radioGroup.addView(radioButton,0,params);
            }
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = group.findViewById(checkedId);
            notifyRadioButtonChanged(radioButton);
        });

        tvWelcome = view.findViewById(R.id.tvWelcome);
        tvWelcome.setText(investigation.getWelcome());
    }

    private void notifyRadioButtonChanged(RadioButton radioButton){
        if(radioButton == null){
            return;
        }
        Object[] objects = (Object[]) radioButton.getTag();
        currentInvestigationStar = (InvestigationStar)objects[0];
        InvestigationContentOptionsStar investigationContentOptionsStar = (InvestigationContentOptionsStar)objects[1];
        currentInvestigationStarTabs.clear();
        adapter.setData(investigationContentOptionsStar.getTabBar());
    }

    /**
     * 检测数据是否有效
     * 1、满意度不为空
     * 2、满意度star不为空
     * 3、满意度options个数必须大于0
     * 4、options[0]不为空且个数与满意度star的个数相同
     * @return
     */
    private boolean isInvestigationValidate(){
        boolean isValidate = investigation != null && investigation.getStar() != null && investigation.getStar().size()>0;
        isValidate = isValidate && investigation.getContent() != null && investigation.getContent().getOptions() != null && investigation.getContent().getOptions().size()>0;
        if(isValidate) {
            InvestigationContentOptions options = investigation.getContent().getOptions().get(0);
            isValidate = isValidate && options.getStar() != null
                && options.getStar().size() >= investigation.getStar().size();
        }

        return isValidate;
    }

    private RadioButton newRadioButton(InvestigationStar investigationStar,InvestigationContentOptionsStar investigationContentOptionsStar){
        RadioButton radioButton = (RadioButton)LayoutInflater.from(getContext()).inflate(R.layout.dlg_evaluating_ratio,null);
        InvestigationType investigationType = InvestigationType.getInvestigationTypeByStar(investigationStar.getStar());
        radioButton.setText(investigationStar.getDesc());
        radioButton.setTag(new Object[]{investigationStar,investigationContentOptionsStar});
        radioButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0,investigationType.getDrawable(),0,0);
        radioButton.setId(investigationType.getId());
        return radioButton;
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//
//        if(null != listener) {
//            change.satisfactionStatus(message.getMainUniqueId(), message.getUniqueId());
//        }
//    }


    @Override
    protected boolean outsideClose() {
        return false;
    }

    private class InvestigationContentOptionsAdapter extends TinetAdapter<String,InvestigationContentOptionsViewHolder>{


        @Override
        protected InvestigationContentOptionsViewHolder viewHolder(View itemView, int viewType) {
            return new InvestigationContentOptionsViewHolder(itemView);
        }

        @Override
        public int getItemViewType(int position) {
            return R.layout.dlg_evaluating_item;
        }

    }

    private class InvestigationContentOptionsViewHolder extends TinetViewHolder<String>{

        private CheckBox cbItem;

        public InvestigationContentOptionsViewHolder(@NonNull View itemView) {
            super(itemView);

            cbItem = itemView.findViewById(R.id.cbItem);
        }

        @Override
        public void update(String info) {
            super.update(info);
            cbItem.setChecked(currentInvestigationStarTabs.contains(info));
            cbItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked){
                    currentInvestigationStarTabs.add(info);
                }else{
                    currentInvestigationStarTabs.remove(info);
                }
            });
            cbItem.setText(info);
        }
    }
}
