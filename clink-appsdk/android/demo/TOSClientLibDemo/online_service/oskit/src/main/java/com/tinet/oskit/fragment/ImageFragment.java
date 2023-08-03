package com.tinet.oskit.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.tinet.oskit.R;
import com.tinet.oskit.adapter.ImageAdapter;
import com.tinet.threepart.audio.AudioRecordManager;
import com.tinet.timclientlib.utils.TLogUtils;

import java.util.ArrayList;

/**
 * @ProjectName: TIMSDK
 * @ClassName: ImageFragment
 * @Author: liuzr
 * @CreateDate: 2021-08-27 09:36
 * @Description: 图片查看器
 */
public class ImageFragment extends TinetFragment {

    public static final String IMAGES = "images";
    public static final String INDEX = "index";
    public static final String PATH = "path";

    private ImageAdapter adapter;
    private ViewPager viewPager;
    private ImageView ivClose;


    /**
     * 申请文件权限 -- 文件
     */
    public static final int REQUEST_FILE_PERMISSION = 1664;


    @Override
    protected int layoutId() {
        return R.layout.frg_image;
    }

    @Override
    protected void initView() {
        super.initView();

        viewPager = (ViewPager) requireView().findViewById(R.id.viewPager);
        ivClose = requireView().findViewById(R.id.ivClose);
        adapter = new ImageAdapter(this, new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                TLogUtils.i("点击了返回");
                requireActivity().finish();
                requireActivity().overridePendingTransition(R.anim.ti_screen_zoom_in, R.anim.ti_screen_zoom_out);
            }
        });

        if (getArguments() == null) {
            return;
        }
        ArrayList<String> list = getArguments().getStringArrayList(IMAGES);
        int index = getArguments().getInt(INDEX);
        adapter.setDatas(list);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(index);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            int grantResult = grantResults[i];
            switch (requestCode) {
                case REQUEST_FILE_PERMISSION:
                    //发送图片
                    if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            //有了读取文件
                            TLogUtils.i("有了读取文件");
                            adapter.downloadFile();
                        } else {
                            //用户拒绝了读取文件权限
                            TLogUtils.i("拒绝了读取文件");
                        }
                    }
                    break;
            }
        }
    }

}

