package com.tinet.oskit.manager;

import android.view.View;
import androidx.annotation.LayoutRes;
import com.tinet.oskit.adapter.holder.SessionViewHolder;
import com.tinet.oslib.model.message.OnlineMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义UI管理
 * @author: liuzeren
 * @date: 2023/6/6
 */
public class TUiManager {

  /**
   * UI 配置
   */
  public static class UIConfig{

    /**
     * 布局文件
     * */
    private @LayoutRes int layoutId;

    private UIProvider provider;

    public UIConfig(int layoutId, UIProvider provider) {
      this.layoutId = layoutId;
      this.provider = provider;
    }

    public int getLayoutId() {
      return layoutId;
    }

    public UIProvider getProvider() {
      return provider;
    }
  }

  /**
   * 视图数据提供器
   */
  public interface UIProvider{

    /**
     * 视图初始化
     * @param itemView
     */
    void initView(View itemView);

    /**
     * 数据绑定
     * @param info
     */
    void update(OnlineMessage info);

  }

  /**
   * 事件与自定义UI之前的映射
   * */
  private static Map<String,UIConfig> map = new HashMap<>();

  /**
   * 注册自定义UI
   * @param event
   * @param uiConfig
   */
  public static void registerViewHolder(String event,UIConfig uiConfig){
    if(!map.containsKey(event)){
      map.put(event, uiConfig);
    }
  }

  public static boolean hasUiConfig(String event){
    return map.containsKey(event);
  }

  public static UIConfig getUiConfigByLayoutId(@LayoutRes int layoutId){
    for (String keys : map.keySet()){
      UIConfig uiConfig = map.get(keys);

      if(uiConfig.getLayoutId() == layoutId){
        return uiConfig;
      }
    }

    return null;
  }

  public static UIConfig getUIConfig(String event){
    if(hasUiConfig(event)){
      return map.get(event);
    }

    return null;
  }
}
