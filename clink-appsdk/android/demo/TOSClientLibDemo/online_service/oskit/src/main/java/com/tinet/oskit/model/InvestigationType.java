package com.tinet.oskit.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import com.tinet.oskit.R;

/**
 * 满意度级别配置信息
 * @author: liuzeren
 * @date: 2023/8/9
 */
public enum InvestigationType {

  /**
   * 很不满意
   */
  veryDissatisfied(R.drawable.very_dissatisfied_drawable,1,R.id.tinetInvestigationVeryDissatisfied),

  /**
   * 不满意
   */
  dissatisfaction(R.drawable.dissatisfaction_drawable,2,R.id.tinetInvestigationDissatisfaction),

  /**
   * 一般
   */
  commonly(R.drawable.commonly_drawable,3,R.id.tinetInvestigationCommonly),

  /**
   * 满意
   */
  satisfied(R.drawable.satisfied_drawable,4,R.id.tinetInvestigationSatisfied),

  /**
   * 很满意
   */
  verySatisfied(R.drawable.very_satisfied_drawable,5,R.id.tinetInvestigationVerySatisfied);

  /**
   * 等级图片
   */
  private @DrawableRes int drawable;

  /**
   * 等级数据
   */
  private int star;

  private @IdRes int id;

  InvestigationType(int drawable, int star,int id) {
    this.drawable = drawable;
    this.star = star;
    this.id = id;
  }

  public int getDrawable() {
    return drawable;
  }

  public int getStar() {
    return star;
  }

  public int getId() {
    return id;
  }

  public static InvestigationType getInvestigationTypeByStar(int star){
    InvestigationType mInvestigationType = InvestigationType.verySatisfied;

    for (InvestigationType investigationType : InvestigationType.values()){
      if(investigationType.star == star){
        mInvestigationType = investigationType;
        break;
      }
    }

    return mInvestigationType;
  }
}
