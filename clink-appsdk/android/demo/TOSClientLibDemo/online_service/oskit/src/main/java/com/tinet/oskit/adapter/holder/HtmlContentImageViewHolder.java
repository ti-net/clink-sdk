package com.tinet.oskit.adapter.holder;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.listener.TImageLoaderListener;
import com.tinet.oskit.tool.TCommonUtils;
import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlImage;

import java.util.ArrayList;

/**
 * 机器人 -- 图片
 *
 * @ProjectName: TIMSDK
 * @ClassName: HtmlContentViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-10-08 16:35
 * @Description:
 */
public class HtmlContentImageViewHolder extends HtmlContentViewHolder {

    private ImageView ivImg;

    public HtmlContentImageViewHolder(@NonNull View itemView, SessionClickListener listener) {
        super(itemView, listener);

        ivImg = itemView.findViewById(R.id.ivImg);
    }

    @Override
    public void update(final Html info) {
        super.update(info);

        if (info instanceof HtmlImage) {
            final HtmlImage imageContent = (HtmlImage) info;
            if (imageContent.getRatio() > 0) {
                // : 2023/1/5 根据图片宽高比进行展示
                ViewGroup.LayoutParams layoutParams = ivImg.getLayoutParams();
                layoutParams.width = (int) (TCommonUtils.getScreenWidth(itemView.getContext())*0.51);
                layoutParams.height = (int) (layoutParams.width / imageContent.getRatio());
                ivImg.setLayoutParams(layoutParams);
                TOSClientKit.getImageLoader().loadImage(ivImg, imageContent.getSrc(), layoutParams.width, layoutParams.height, new TImageLoaderListener() {
                    @Override
                    public void onResourceReady(Drawable drawable) {

                    }

                    @Override
                    public void onLoadFailed() {

                    }
                });
            } else {
                TOSClientKit.getImageLoader().loadImage(ivImg, imageContent.getSrc(), R.drawable.ti_ic_load_default_image, R.drawable.ti_ic_load_default_image);
            }

            ivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> imgs = new ArrayList<>();
                    imgs.add(imageContent.getSrc());
                    listener.onImageMessageClick(imgs, 0);
                }
            });
        }
    }
}
