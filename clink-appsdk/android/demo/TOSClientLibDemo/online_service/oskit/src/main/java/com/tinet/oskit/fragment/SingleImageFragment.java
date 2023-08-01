package com.tinet.oskit.fragment;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.listener.TImageLoaderListener;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SingleImageFragment
 * @Author: liuzr
 * @CreateDate: 2021-09-02 11:03
 * @Description: 单张图片查看，主要用于富文本图片查看
 */
public class SingleImageFragment extends TinetFragment {

    public static final String PATH = "path";

    private PhotoView pvImage;
    private TextView tvTime;
    private ProgressBar pbLoadIng;
    private ImageView ivClose;

    @Override
    protected int layoutId() {
        return R.layout.frg_single_image;
    }

    @Override
    protected void initView() {
        super.initView();

        pvImage = requireView().findViewById(R.id.pvImage);
        tvTime = requireView().findViewById(R.id.tvTime);
        pbLoadIng = requireView().findViewById(R.id.pbLoadIng);
        ivClose = requireView().findViewById(R.id.ivClose);

        String path = getArguments().getString(PATH);

        TOSClientKit.getImageLoader().loadImage(pvImage, path, Integer.MIN_VALUE, Integer.MIN_VALUE, new TImageLoaderListener() {
            @Override
            public void onResourceReady(Drawable drawable) {
                pbLoadIng.setVisibility(View.GONE);
            }

            @Override
            public void onLoadFailed() {
                pbLoadIng.setVisibility(View.VISIBLE);
            }
        });

        pvImage.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                requireActivity().finish();
                requireActivity().overridePendingTransition(R.anim.ti_screen_zoom_in, R.anim.ti_screen_zoom_out);
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
    }
}
