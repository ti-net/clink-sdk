package com.tinet.tosclientkitdemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: TOSClientKitDemo
 * @ClassName: ThemeItemBean
 * @Author: zhangping
 * @CreateDate: 2022/7/4 14:29
 * @Description: 描述说明
 */
public class ThemeItemBean implements Serializable {

    private String themeName;

    private int previewImg;

    private ThemePropertyBean themePropertyBean;

    public ThemeItemBean(String themeName, int previewImg, ThemePropertyBean themePropertyBean) {
        this.themeName = themeName;
        this.previewImg = previewImg;
        this.themePropertyBean = themePropertyBean;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public int getPreviewImg() {
        return previewImg;
    }

    public void setPreviewImg(int previewImg) {
        this.previewImg = previewImg;
    }

    public ThemePropertyBean getThemePropertyBean() {
        return themePropertyBean;
    }

    public void setThemePropertyBean(ThemePropertyBean themePropertyBean) {
        this.themePropertyBean = themePropertyBean;
    }
}
