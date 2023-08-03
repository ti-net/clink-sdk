package com.tinet.oskit.adapter.holder;

import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlVideo;

import java.util.ArrayList;

/**
 * 富文本
 *
 * @ProjectName: TIMSDK
 * @ClassName: HtmlContentViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-10-08 16:35
 * @Description:
 */
public class HtmlContentVideoViewHolder extends HtmlContentViewHolder {

    /**
     * 进度条最大值
     */
    private static final int MAX_PROGRESS = 100;

    private ImageView ivPlay, ivFullScreen, ivCover;
    private ProgressBar pbProgress;

    private SurfaceView svVideoPlay;

    public HtmlContentVideoViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);
        pbProgress = itemView.findViewById(R.id.pbProgress);
        ivPlay = itemView.findViewById(R.id.ivPlay);
        ivFullScreen = itemView.findViewById(R.id.ivFullScreen);
        ivCover = itemView.findViewById(R.id.ivCover);
        svVideoPlay = itemView.findViewById(R.id.svVideoPlay);

        pbProgress.setMax(MAX_PROGRESS);
    }

    @Override
    public void update(final Html info) {
        super.update(info);
        if (info instanceof HtmlVideo) {
            final HtmlVideo videoContent = (HtmlVideo) info;
            TOSClientKit.getImageLoader().loadImage(ivCover, videoContent.getSrc(), R.drawable.ti_ic_load_default_image,
                    R.drawable.ti_ic_load_default_image);

            ivCover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> imgs = new ArrayList<>();
                    listener.videoPlay(videoContent.getSrc());
                }
            });
        }

    }
}
