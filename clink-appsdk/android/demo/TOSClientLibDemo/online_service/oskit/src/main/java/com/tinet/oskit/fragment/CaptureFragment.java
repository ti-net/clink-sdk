package com.tinet.oskit.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tinet.widget.cameralibrary.JCameraView;
import com.tinet.widget.cameralibrary.listener.ClickListener;
import com.tinet.widget.cameralibrary.listener.JCameraListener;
import com.tinet.oskit.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * @ProjectName: TIMSDK
 * @ClassName: CaptureFragment
 * @Author: liuzr
 * @CreateDate: 2021-08-26 13:54
 * @Description: 拍摄
 */
public class CaptureFragment extends TinetFragment {

    public static final String TAKE_PHOTO = "take_photo";
    public static final String PATH = "path";

    @Override
    protected int layoutId() {
        return R.layout.frg_capture;
    }

    private static final int REQUEST_CODE = 100;

    private JCameraView mJCameraView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVideo();
    }

    private void initVideo() {

        mJCameraView = requireView().findViewById(R.id.cameraView);

        /**
         * 设置视频保存路径
         */
        mJCameraView.setSaveVideoPath(requireActivity().getExternalCacheDir().getPath() + File.separator + "TCamera");
        /**
         * JCameraView监听
         */
        mJCameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                String path = requireActivity().getExternalCacheDir().getPath() + File.separator + "TCamera" + File.separator + System.currentTimeMillis() + ".png";
                saveBitmap(bitmap, path);
                Intent data = new Intent();
                data.putExtra(TAKE_PHOTO, true);
                data.putExtra(PATH, path);
                requireActivity().setResult(RESULT_OK, data);
                requireActivity().finish();
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                //获取成功录像后的视频路径
                Intent data = new Intent();
                data.putExtra(TAKE_PHOTO, false);
                data.putExtra(PATH, url);
                requireActivity().setResult(RESULT_OK, data);
                requireActivity().finish();
            }
        });

        //左边按钮点击事件
        mJCameraView.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                requireActivity().finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode && data != null) {
            requireActivity().setResult(RESULT_OK, data);
            requireActivity().finish();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mJCameraView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mJCameraView.onPause();
    }

    public String saveBitmap(Bitmap bm, String fileAbsolutePath) {
        File f = new File(fileAbsolutePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAbsolutePath;
    }
}
