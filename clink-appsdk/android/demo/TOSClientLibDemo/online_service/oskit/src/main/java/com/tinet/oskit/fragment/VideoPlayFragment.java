package com.tinet.oskit.fragment;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.tinet.oskit.R;
import com.tinet.oslib.manager.OnlineDownloadManager;
import com.tinet.threepart.tools.TFileUtils;
import com.tinet.timclientlib.manager.TIMBaseManager;
import com.tinet.timclientlib.utils.TLogUtils;
import com.tinet.timclientlib.utils.TToastUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import androidx.core.content.ContextCompat;

/**
 * @ProjectName: TIMSDK
 * @ClassName: VideoPlayFragment
 * @Author: liuzr
 * @CreateDate: 2021-08-27 11:08
 * @Description: 视频播放器
 */
public class VideoPlayFragment extends TinetFragment {

    public static final String VIDEO_URI = "videoUri";

    private SurfaceView svVideoPlay;

    private SurfaceHolder mSurfaceHolder;
    private Uri videoUri;
    private MediaPlayer mMediaPlayer;
    private ImageView mIvPlay;
    private SeekBar mPbLoadIng;
    private ImageView ivLoading;

    /**
     * 申请文件权限 -- 文件
     */
    public static final int REQUEST_FILE_PERMISSION = 1664;
    private String currentDownPath;

    @Override
    protected int layoutId() {
        return R.layout.frg_video_play;
    }

    //进度
    private static final int PROGRESS_CHANGE = 1110;

    //时间间隔
    private static final int TIMES_SPAN = 500;

    //手动拖动
    private boolean isHandSeek = false;

    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case PROGRESS_CHANGE:
                    if (mMediaPlayer != null && mMediaPlayer.isPlaying() && !isHandSeek) {
                        mPbLoadIng.setProgress(mMediaPlayer.getCurrentPosition());
                        handler.sendEmptyMessageDelayed(PROGRESS_CHANGE, TIMES_SPAN);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void initView() {
        super.initView();

        ivLoading = requireView().findViewById(R.id.ivLoading);
        ivLoading.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.tinet_rotate));
        svVideoPlay = requireView().findViewById(R.id.svVideoPlay);
        mIvPlay = requireView().findViewById(R.id.ivPlay);
        mPbLoadIng = requireView().findViewById(R.id.pbLoadIng);
        mPbLoadIng.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (isHandSeek) {
                    mMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isHandSeek = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isHandSeek = false;
                handler.sendEmptyMessage(PROGRESS_CHANGE);
            }
        });

        if(getArguments() == null){
            return;
        }
        videoUri = getArguments().getParcelable(VIDEO_URI);

        requireView().findViewById(R.id.ivDownloadAndSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = videoUri.toString();
                if (!TextUtils.isEmpty(url)) {
                    if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        downLoadFile(url);
                    } else {
                        currentDownPath = url;
                        //没权限
                        shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE);
                        shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, ImageFragment.REQUEST_FILE_PERMISSION);
                    }
                }
            }
        });

        mIvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer.isPlaying()) {
                    mIvPlay.setImageResource(R.mipmap.ti_play_video);
                    mMediaPlayer.pause();
                    handler.removeMessages(PROGRESS_CHANGE);
                } else {
                    mIvPlay.setImageResource(R.mipmap.ti_pause_video);
                    mMediaPlayer.start();
                    handler.sendEmptyMessage(PROGRESS_CHANGE);
                }
            }
        });

        requireView().findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();
                requireActivity().overridePendingTransition(R.anim.ti_screen_zoom_in, R.anim.ti_screen_zoom_out);//取消原有默认的Activity到Activity的过渡动画
            }
        });

        svVideoPlay.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initMedia();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
    }

    private void downLoadFile(String url) {
        if (url.startsWith("http") || url.startsWith("https")) {
            OnlineDownloadManager.download(requireContext(), url, "");
        } else {
            try {
                File file = new File(url);
                if (file.exists() && file.isFile()) {
                    String name = UUID.randomUUID().toString();//OnlineDownloadManager.getName(path);
                    String ext = TFileUtils.getExtensionName(url);
                    if (!TextUtils.isEmpty(ext)) {
                        name = name + "." + ext;
                    }

                    File toFile = new File(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), name);

                    if (TFileUtils.copyFile(file, toFile, false)) {
                        TToastUtils
                                .showLongToast(requireContext(), requireContext().getString(
                                        com.tinet.oslib.R.string.tinet_file_download_success_and_save, toFile.getAbsolutePath()));
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void initMedia() {
        mMediaPlayer = new MediaPlayer();
        mSurfaceHolder = svVideoPlay.getHolder();
        mSurfaceHolder.setKeepScreenOn(true);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            Map<String, Object> headers = TIMBaseManager.getInstance().getInitOption().getAdvanceParams();
            HashMap<String, String> hashMap = new HashMap<>();
            if (videoUri.toString().contains("https") && headers.containsKey("deBugEnv")) {
                if ("ktTest".equals(headers.get("deBugEnv")))
                    hashMap.put("X-Virtual-Env", "dev.chat");
                mMediaPlayer.setDataSource(requireActivity(), videoUri, hashMap);
            } else
                mMediaPlayer.setDataSource(videoUri.toString());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                try {
                    mPbLoadIng.setMax(mMediaPlayer.getDuration());
                    mMediaPlayer.setLooping(false);
                    mMediaPlayer.setDisplay(mSurfaceHolder);
                    mMediaPlayer.start();
                    handler.sendEmptyMessage(PROGRESS_CHANGE);
                    mIvPlay.setImageResource(R.mipmap.ti_pause_video);

                    mIvPlay.setVisibility(View.VISIBLE);
                    mPbLoadIng.setVisibility(View.VISIBLE);

                    ivLoading.clearAnimation();
                    ivLoading.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                changeVideoSize();
            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mPbLoadIng.setProgress(mMediaPlayer.getDuration());
                mIvPlay.setImageResource(R.mipmap.ti_play_video);
            }
        });

    }

    public void changeVideoSize() {
        int videoWidth = mMediaPlayer.getVideoWidth();
        int videoHeight = mMediaPlayer.getVideoHeight();

        //根据视频尺寸去计算->视频可以在sufaceView中放大的最大倍数。
        float max;
        if (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //竖屏模式下按视频宽度计算放大倍数值
            max = Math.max((float) videoWidth / (float) svVideoPlay.getWidth()
                    , (float) videoHeight / (float) svVideoPlay.getHeight());
        } else {
            //横屏模式下按视频高度计算放大倍数值
            max = Math.max(((float) videoWidth / (float) svVideoPlay.getHeight()
            ), (float) videoHeight / (float) svVideoPlay.getWidth());
        }

        //视频宽高分别/最大倍数值 计算出放大后的视频尺寸
        videoWidth = (int) Math.ceil((float) videoWidth / max);
        videoHeight = (int) Math.ceil((float) videoHeight / max);

        //无法直接设置视频尺寸，将计算出的视频尺寸设置到surfaceView 让视频自动填充。
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(videoWidth, videoHeight);
        svVideoPlay.setLayoutParams(layoutParams);
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
                            downLoadFile(currentDownPath);
                        } else {
                            //用户拒绝了读取文件权限
                            TLogUtils.i("拒绝了读取文件");
                        }
                    }
                    break;
            }
        }
    }


    @Override
    public void onDestroyView() {
        handler.removeMessages(PROGRESS_CHANGE);
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
        }

        super.onDestroyView();
    }
}
