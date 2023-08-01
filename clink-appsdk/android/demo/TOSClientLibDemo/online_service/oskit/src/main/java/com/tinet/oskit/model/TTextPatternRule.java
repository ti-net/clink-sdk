package com.tinet.oskit.model;

import android.graphics.Color;

import java.util.regex.Pattern;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: TTextPatternRuleModule
 * @Author: zhangping
 * @CreateDate: 2022/10/13 11:04
 * @Description: 描述说明
 */
public class TTextPatternRule {

    private Pattern pattern;

    private int highLightColor = Color.BLUE;

    private String messageEventType;

    public TTextPatternRule(Pattern pattern, String messageEventType) {
        this.pattern = pattern;
        this.messageEventType = messageEventType;
    }

    public TTextPatternRule(Pattern pattern, int highLightColor, String messageEventType) {
        this.pattern = pattern;
        this.highLightColor = highLightColor;
        this.messageEventType = messageEventType;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public int getHighLightColor() {
        return highLightColor;
    }

    public void setHighLightColor(int highLightColor) {
        this.highLightColor = highLightColor;
    }

    public String getMessageEventType() {
        return messageEventType;
    }

    public void setMessageEventType(String messageEventType) {
        this.messageEventType = messageEventType;
    }
}
