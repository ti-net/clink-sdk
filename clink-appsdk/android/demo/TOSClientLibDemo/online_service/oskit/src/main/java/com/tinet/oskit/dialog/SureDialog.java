package com.tinet.oskit.dialog;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.tinet.oskit.R;

/**
 * @author: liuzeren
 * @date: 2023/8/2
 */
public class SureDialog extends TinetDialog implements View.OnClickListener{

  public interface OnResultListener{

    /**
     * 左按钮 - 取消
     * */
    void onCancel();

    /**
     * 右按钮 - 确认
     */
    void onSure();

  }

  private OnResultListener listener;

  private String title, message, cancel, sure;
  private View tinetCloseView;

  public SureDialog(OnResultListener listener,String title,String message,String cancel,String sure){
    this.listener = listener;

    this.title = title;
    this.message = message;
    this.cancel = cancel;
    this.sure = sure;
  }

  private TextView tinetCancel,tinetSure,tinetMessage,tinetTitle;

  @Override
  int layoutId() {
    return R.layout.tinetSureDialogLayout;
  }

  @Override
  void initView(View view) {
    tinetCancel = view.findViewById(R.id.tinetCancel);
    if(null != tinetCancel) {
      tinetCancel.setOnClickListener(this);

      if(!TextUtils.isEmpty(cancel)){
        tinetCancel.setText(cancel);
      }
    }
    tinetSure = view.findViewById(R.id.tinetSure);
    if(null != tinetSure) {
      tinetSure.setOnClickListener(this);

      if(!TextUtils.isEmpty(sure)){
        tinetSure.setText(sure);
      }
    }
    tinetMessage = view.findViewById(R.id.tinetMessage);
    if(tinetMessage != null && !TextUtils.isEmpty(message)){
      tinetMessage.setText(message);
    }
    tinetTitle = view.findViewById(R.id.tinetTitle);
    if(tinetTitle != null && !TextUtils.isEmpty(title)){
      tinetTitle.setText(title);
    }

    tinetCloseView = view.findViewById(R.id.tinetCloseView);
    if(tinetCloseView != null){
      tinetCloseView.setOnClickListener(v -> dismiss());
    }
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.tinetCancel){
      if(listener != null){
        listener.onCancel();
      }
      dismiss();
    }else if(v.getId() == R.id.tinetSure){
      if(listener != null){
        listener.onSure();
      }
      dismiss();
    }
  }
}
