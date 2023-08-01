package com.tinet.oskit.adapter.holder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.message.OnlineMessage;

import java.util.ArrayList;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupImageViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-09 16:18
 * @Description: 机器人组合消息 -- 图片
 */
public class RobotGroupImageViewHolder extends RobotGroupBaseViewHolder {

    private ImageView ivBivPic;

    public RobotGroupImageViewHolder(@NonNull View itemView, OnlineMessage message, SessionClickListener listener) {
        super(itemView, message, listener);
        ivBivPic = itemView.findViewById(R.id.ivBivPic);
    }

    @Override
    public void update(final OnlineQuestion info) {
        super.update(info);
        TOSClientKit.getImageLoader().loadImage(ivBivPic, info.getUri(message.getOnlineContent()), R.drawable.ti_ic_load_default_image,
                R.drawable.ti_ic_load_default_image);

        ivBivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(info.getUri(message.getOnlineContent(), false));
                    listener.onImageMessageClick(arr, 0);
                }
            }
        });
    }
}
