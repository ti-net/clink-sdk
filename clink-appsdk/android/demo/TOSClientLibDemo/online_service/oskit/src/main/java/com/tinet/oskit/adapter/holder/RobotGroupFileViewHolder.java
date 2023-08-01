package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.UriTool;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.message.OnlineMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupImageViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-09 16:18
 * @Description: 机器人组合消息 -- 文件
 */
public class RobotGroupFileViewHolder extends RobotGroupBaseViewHolder {

    private TextView tvFileName, tvDown;
    private ImageView ivBivPic;

    public RobotGroupFileViewHolder(@NonNull View itemView, OnlineMessage message, SessionClickListener listener) {
        super(itemView, message, listener);
        tvFileName = itemView.findViewById(R.id.tvFileName);
        tvDown = itemView.findViewById(R.id.tvDown);
        ivBivPic = itemView.findViewById(R.id.ivBivPic);
    }

    @Override
    public void update(final OnlineQuestion info) {
        super.update(info);

        tvFileName.setText(info.getName());

        tvDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.downloadFile(info.getUri(message.getOnlineContent()), TextUtils.isEmpty(info.getName()) ? info.getText() : info.getName());
                }
            }
        });

        String fileName = info.getName();
        if (!TextUtils.isEmpty(fileName)) {
            if (fileName.toLowerCase().endsWith(UriTool.PDF)) {
                ivBivPic.setImageResource(R.drawable.ti_ic_file_pdf);
            } else if (fileName.toLowerCase().endsWith(UriTool.XLS) || fileName.toLowerCase().endsWith(UriTool.XLSX)) {
                ivBivPic.setImageResource(R.drawable.ti_ic_file_xls);
            } else if (fileName.toLowerCase().endsWith(UriTool.DOC) || fileName.toLowerCase().endsWith(UriTool.DOCX)) {
                ivBivPic.setImageResource(R.drawable.ti_ic_file_doc);
            } else if (fileName.toLowerCase().endsWith(UriTool.PPT) || fileName.toLowerCase().endsWith(UriTool.PPTX)) {
                ivBivPic.setImageResource(R.drawable.ti_ic_file_pdf);
            } else if (fileName.toLowerCase().endsWith(UriTool.MP3)) {
                ivBivPic.setImageResource(R.drawable.ti_ic_file_mp3);
            } else if (fileName.toLowerCase().endsWith(UriTool.TXT)) {
                ivBivPic.setImageResource(R.drawable.ti_ic_file_txt);
            }else {
                ivBivPic.setImageResource(R.drawable.ti_ic_file_default);
            }
        } else {
            ivBivPic.setImageResource(R.drawable.ti_ic_file_default);
        }
    }
}
