package com.tinet.tosclientkitdemo.common.platform;

import android.text.TextUtils;
import com.tinet.oslib.common.PlatformDefine;

/**
 * @author: liuzeren
 * @date: 2022/5/27
 */
public class PlantformInfo {

  private String name;

  private long enterpriseId;

  private String accessId;

  private String accessSecret;

  private String type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getEnterpriseId() {
    return enterpriseId;
  }

  public void setEnterpriseId(long enterpriseId) {
    this.enterpriseId = enterpriseId;
  }

  public String getAccessId() {
    return accessId;
  }

  public void setAccessId(String accessId) {
    this.accessId = accessId;
  }

  public String getAccessSecret() {
    return accessSecret;
  }

  public void setAccessSecret(String accessSecret) {
    this.accessSecret = accessSecret;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public PlatformDefine getPlatform(){
    PlatformDefine define = PlatformDefine.Kt;

    if(!TextUtils.isEmpty(type)){
      if("Shanghai".equals(type)){
        define = PlatformDefine.Shanghai;
      }else if ("Test3".equals(type)){
        define = PlatformDefine.Test3;
      }else if ("Kt".equals(type)){
          define = PlatformDefine.Kt;
      }
    }

    return define;
  }
}
