package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.model.Function;

/**
 * @ProjectName: TIMSDK
 * @ClassName: FuncViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-08-24 15:20
 * @Description:
 */
public class FuncViewHolder extends TinetViewHolder<Function> {

    private TextView tvTitle;
    private ImageView ivLogo;

    public FuncViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tvTitle);
        ivLogo = itemView.findViewById(R.id.ivLogo);
    }

    @Override
    public void update(Function info) {
        super.update(info);

        if (info.getType() == Function.TYPE_SYSTEM) {
            if (!TextUtils.isEmpty(info.getTitle()) || info.getLogo() != null) {
                if (!TextUtils.isEmpty(info.getTitle()))
                    tvTitle.setText(info.getTitle());
                if (info.getLogo() != null)
                    ivLogo.setImageDrawable(info.getLogo());
            } else if (info.getTypeId() == Function.SEND_IMAGE) {
                tvTitle.setText(R.string.ti_camera);
                ivLogo.setImageResource(R.mipmap.session_image);
            } else if (info.getTypeId() == Function.SEND_VIDEO) {
                tvTitle.setText(R.string.ti_video);
                ivLogo.setImageResource(R.mipmap.session_camera);
            } else if (info.getTypeId() == Function.SEND_FILE) {
                tvTitle.setText(R.string.ti_file);
                ivLogo.setImageResource(R.mipmap.session_file);
            } else if (info.getTypeId() == Function.CHAT_OVER) {
                tvTitle.setText(R.string.ti_chat_over);
                ivLogo.setImageResource(R.mipmap.session_chat_over);
            } else if (info.getTypeId() == Function.TO_ONLINE) {
                tvTitle.setText(R.string.ti_to_online);
                ivLogo.setImageResource(R.mipmap.session_to_online);
            }
        } else {
            tvTitle.setText(info.getTitle());
            ivLogo.setImageDrawable(info.getLogo());
        }
    }
}
