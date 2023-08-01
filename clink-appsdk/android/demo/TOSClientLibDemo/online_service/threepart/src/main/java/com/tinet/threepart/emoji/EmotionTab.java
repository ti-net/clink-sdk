package com.tinet.threepart.emoji;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tinet.threepart.R;

/**
 * CSDN_LQR
 * 表情底部tab
 */
public class EmotionTab extends RelativeLayout {

    private ImageView mIvIcon;
    private String mStickerCoverImgPath;
    private int mIconSrc = R.drawable.ti_ic_tab_add;

    public EmotionTab(Context context, int iconSrc) {
        super(context);
        mIconSrc = iconSrc;
        init(context);
    }

    public EmotionTab(Context context, String stickerCoverImgPath) {
        super(context);
        mStickerCoverImgPath = stickerCoverImgPath;
        init(context);
    }


    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ti_emotion_tab, this);

        mIvIcon = (ImageView) findViewById(R.id.ti_ivIcon);

        if (TextUtils.isEmpty(mStickerCoverImgPath)) {
            mIvIcon.setImageResource(mIconSrc);
        } else {
            LQREmotionKit.getImageLoader().displayImage(context, mStickerCoverImgPath, mIvIcon);
        }
    }

}
