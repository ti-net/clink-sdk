package com.tinet.online;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.tinet.oskit.adapter.TinetAdapter;
import com.tinet.oskit.adapter.holder.TinetViewHolder;

/**
 * @author: liuzeren
 * @date: 2023/7/7
 */
public class TextActivity extends AppCompatActivity {

  private RecyclerView recyclerView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.aty_text);

    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setAdapter(new TinetAdapter<String,TextViewHoder>() {
      @Override
      protected TextViewHoder viewHolder(View itemView, int viewType) {
        return new TextViewHoder(itemView);
      }

      @Override
      public void onBindViewHolder(@NonNull TextViewHoder holder, int position) {
        holder.update("第"+position+"个");
      }

      @Override
      public int getItemCount() {
        return 50;
      }

      @Override
      public int getItemViewType(int position) {
        return R.layout.aty_text_item;
      }
    });
  }

  class TextViewHoder extends TinetViewHolder<String>{

    private TextView tvItem;

    public TextViewHoder(@NonNull View itemView) {
      super(itemView);

      tvItem = itemView.findViewById(R.id.tvItem);
    }

    @Override
    public void update(String info) {
      super.update(info);
      tvItem.setText(info);
    }
  }
}
