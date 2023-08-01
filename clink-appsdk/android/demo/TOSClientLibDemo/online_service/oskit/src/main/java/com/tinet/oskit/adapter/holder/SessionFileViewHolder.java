package com.tinet.oskit.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.UriTool;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatAttachmentMessage;
import com.tinet.oslib.model.message.content.FileMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionFileViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-06 14:26
 * @Description: 文件
 */
public class SessionFileViewHolder extends SessionViewHolder {

    private TextView tvFileName, tvDown;
    private ImageView ivBivPic;
    private LinearLayout layout;

    public SessionFileViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        layout = itemView.findViewById(R.id.layout);
        tvDown = itemView.findViewById(R.id.tvDown);
        tvFileName = itemView.findViewById(R.id.tvFileName);
        ivBivPic = itemView.findViewById(R.id.ivBivPic);
    }

    @Override
    public void update(OnlineMessage info) {
        super.update(info);

        String fileName = "";
        OnlineServiceMessage serviceMessage = info.getOnlineContent();
        if (serviceMessage instanceof FileMessage) {
            final FileMessage fileMessage = (FileMessage) serviceMessage;
            fileName = fileMessage.getName();
            tvFileName.setText(fileName);

            if (null != tvDown) {
                tvDown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != listener) {
                            listener.downloadFile(fileMessage.getUri(), fileMessage.getName());
                        }
                    }
                });
            }
        } else if (serviceMessage instanceof ChatAttachmentMessage) {
            final ChatAttachmentMessage fileMessage = (ChatAttachmentMessage) serviceMessage;
            fileName = fileMessage.getFileName();
            tvFileName.setText(fileMessage.getFileName());

            tvDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener) {
                        listener.downloadFile(fileMessage.getUri(), fileMessage.getFileName());
                    }
                }
            });
        }

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
            } else {
                ivBivPic.setImageResource(R.drawable.ti_ic_file_default);
            }
        } else {
            ivBivPic.setImageResource(R.drawable.ti_ic_file_default);
        }
    }

}
