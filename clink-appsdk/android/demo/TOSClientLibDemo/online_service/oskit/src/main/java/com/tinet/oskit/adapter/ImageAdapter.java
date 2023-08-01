package com.tinet.oskit.adapter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.R;
import com.tinet.oskit.fragment.ImageFragment;
import com.tinet.oskit.listener.TImageLoaderListener;
import com.tinet.oslib.manager.OnlineDownloadManager;

import com.tinet.threepart.tools.TFileUtils;
import com.tinet.timclientlib.utils.TLogUtils;
import com.tinet.timclientlib.utils.TStringUtils;
import com.tinet.timclientlib.utils.TToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @ProjectName: TIMSDK
 * @ClassName: ImageAdapter
 * @Author: liuzr
 * @CreateDate: 2021-08-27 09:41
 * @Description:
 */
public class ImageAdapter extends PagerAdapter {

    private ArrayList<String> datas = null;

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
    }

    private OnPhotoTapListener listener;
    private ImageFragment imageFragment;
    private String currentDownPath;


    public ImageAdapter(ImageFragment imageFragment, OnPhotoTapListener listener) {
        this.listener = listener;
        this.imageFragment = imageFragment;
    }

    @Override
    public int getCount() {
        if (datas == null) return 0;
        return datas.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.frg_image_item, container, false);
        if (view != null) {
            final PhotoView imageView = view.findViewById(R.id.pvImage);
            final ProgressBar pbLoadIng = view.findViewById(R.id.pbLoadIng);
            FrameLayout.LayoutParams loadingLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            loadingLayoutParams.gravity = Gravity.CENTER;
            final String path = datas.get(position);
            view.findViewById(R.id.ivDownloadAndSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TLogUtils.d("下载路径：" + path);
                    if (!TextUtils.isEmpty(path)) {
                        currentDownPath = path;
                        if (ContextCompat.checkSelfPermission(imageFragment.requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                                ContextCompat.checkSelfPermission(imageFragment.requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            downloadFile();
                        } else {
                            //没权限
                            imageFragment.shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE);
                            imageFragment.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            imageFragment.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, ImageFragment.REQUEST_FILE_PERMISSION);
                        }
                    }
                }
            });
            TOSClientKit.getImageLoader().loadImage(imageView, path, Integer.MIN_VALUE, Integer.MIN_VALUE, new TImageLoaderListener() {
                @Override
                public void onResourceReady(Drawable drawable) {
                    pbLoadIng.setVisibility(View.GONE);
                }

                @Override
                public void onLoadFailed() {
                    pbLoadIng.setVisibility(View.VISIBLE);
                }
            });
            container.addView(view, 0);

            imageView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                    if (null != listener) {
                        listener.onPhotoTap(view, x, y);
                    }
                }
            });
        }

        return view;
    }

    public void downloadFile() {
        if (TStringUtils.isNotEmpty(currentDownPath)) {
            String path = currentDownPath;
            if (path.startsWith("http") || path.startsWith("https")) {
                OnlineDownloadManager.download(imageFragment.requireContext().getApplicationContext(), path, "");
            } else {
                try {
                    File file = new File(path);
                    if (file.exists() && file.isFile()) {
                        String name = UUID.randomUUID().toString();//OnlineDownloadManager.getName(path);
                        String ext = TFileUtils.getExtensionName(path);
                        if (!TextUtils.isEmpty(ext)) {
                            name = name + "." + ext;
                        }

                        File toFile = new File(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), name);

                        if (TFileUtils.copyFile(file, toFile, false)) {
                            TToastUtils.showLongToast(imageFragment.getContext(), imageFragment.getContext().getString(
                                    com.tinet.oslib.R.string.tinet_file_download_success_and_save, toFile.getAbsolutePath()));
                        }
                    }
                } catch (Exception e) {
                    TLogUtils.e(e.toString());
                }
            }
        }
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}
