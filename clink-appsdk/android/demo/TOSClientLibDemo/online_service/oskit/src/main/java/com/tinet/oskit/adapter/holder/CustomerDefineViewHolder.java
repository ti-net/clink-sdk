package com.tinet.oskit.adapter.holder;

import android.view.View;
import androidx.annotation.NonNull;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.manager.TUiManager;
import com.tinet.oslib.model.message.OnlineMessage;
import java.lang.ref.SoftReference;

/**
 * @author: liuzeren
 * @date: 2023/6/6
 */
public class CustomerDefineViewHolder extends SessionViewHolder{

  private SoftReference<TUiManager.UIProvider> provider;

  public CustomerDefineViewHolder(@NonNull View itemView,
      SessionClickListener listener) {
    super(itemView, listener);

    initView(itemView);
  }

  private View contentView;

  public CustomerDefineViewHolder(@NonNull View itemView,
      SessionClickListener listener, TUiManager.UIProvider provider) {
    super(itemView, listener);

    contentView = itemView;
    this.provider = new SoftReference<>(provider);
//    initView(itemView);
  }

  private void initView(View itemView){
    TUiManager.UIProvider p = provider.get();
    if(null != p) {
      p.initView(itemView);
    }
  }

  @Override
  public void update(OnlineMessage info) {
    super.update(info);
    if(null != contentView) {
      initView(contentView);
    }

    TUiManager.UIProvider p = provider.get();

    if(null != p) {
      p.update(info);
    }
  }
}
