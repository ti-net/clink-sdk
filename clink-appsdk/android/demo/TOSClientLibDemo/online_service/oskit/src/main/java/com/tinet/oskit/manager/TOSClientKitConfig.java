package com.tinet.oskit.manager;


import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.model.TTextPatternRule;
import com.tinet.oslib.model.bean.OnlineSetting;

import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TConfigManager
 * @Author: zhangping
 * @CreateDate: 2022/8/1 10:36
 * @Description: 描述说明
 */
public class TOSClientKitConfig {

    /**
     * 自定义UI配置
     */
    private TCustomizationUI mTCustomizationUI;

    /**
     * 会话设置
     */
    private OnlineSetting mOnlineSetting;

    /**
     * 文本高亮显示规则数组
     */
    private List<TTextPatternRule> mTextHighLightRuleList;

    public TOSClientKitConfig() {
    }

    public TCustomizationUI getTCustomizationUI() {
        return mTCustomizationUI;
    }

    public OnlineSetting getOnlineSetting() {
        return mOnlineSetting;
    }

    public List<TTextPatternRule> getTextHighLightRuleList() {
        return mTextHighLightRuleList;
    }

    public static class Builder {
        private TCustomizationUI mTCustomizationUI;
        private OnlineSetting onlineSetting;
        private List<TTextPatternRule> mTextHighLightRuleList;

        public Builder() {
        }

        public TOSClientKitConfig.Builder setTCustomizationUI(TCustomizationUI mTCustomizationUI) {
            this.mTCustomizationUI = mTCustomizationUI;
            return this;
        }

        public TOSClientKitConfig.Builder setOnlineSetting(OnlineSetting onlineSetting) {
            this.onlineSetting = onlineSetting;
            return this;
        }

        public TOSClientKitConfig.Builder setTextHighLightRuleList(List<TTextPatternRule> textHighLightRuleList) {
            this.mTextHighLightRuleList = textHighLightRuleList;
            return this;
        }

        public TOSClientKitConfig build() {
            TOSClientKitConfig tosClientKitConfig = TOSClientKit.getTOSClientKitConfig();
            if (tosClientKitConfig == null) {
                tosClientKitConfig = new TOSClientKitConfig();
            }
            tosClientKitConfig.mTCustomizationUI = this.mTCustomizationUI != null ? this.mTCustomizationUI : tosClientKitConfig.getTCustomizationUI();
            tosClientKitConfig.mOnlineSetting = this.onlineSetting != null ? this.onlineSetting : tosClientKitConfig.getOnlineSetting();
            tosClientKitConfig.mTextHighLightRuleList = this.mTextHighLightRuleList != null ? this.mTextHighLightRuleList : tosClientKitConfig.mTextHighLightRuleList;
            return tosClientKitConfig;
        }


    }
}
