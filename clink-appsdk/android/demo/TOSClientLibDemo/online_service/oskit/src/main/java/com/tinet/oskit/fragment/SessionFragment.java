package com.tinet.oskit.fragment;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.org.gzuliyujiang.filepicker.FilePicker;
import com.org.gzuliyujiang.filepicker.annotation.ExplorerMode;
import com.org.gzuliyujiang.filepicker.contract.OnFilePickedListener;
import com.lcw.library.imagepicker.ImagePicker;
import com.lcw.library.imagepicker.activity.ImagePickerActivity;
import com.tinet.oskit.TOSClientKit;
import com.tinet.oskit.adapter.LabelAdapter;
import com.tinet.oskit.adapter.decoration.LinearLayoutManagerDecoration;
import com.tinet.oskit.aty.ChatLeaveMessageAty;
import com.tinet.oskit.dialog.EvaluatingDialog;
import com.tinet.oskit.dialog.EvaluatingDialog.OnEvaluatingListener;
import com.tinet.oskit.dialog.SureDialog;
import com.tinet.oskit.dialog.SureDialog.OnResultListener;
import com.tinet.oskit.listener.LabelListener;
import com.tinet.oskit.listener.impl.LabelListenerImpl;
import com.tinet.oskit.tool.LinkMovementClickMethod;
import com.tinet.oskit.tool.UriTool;
import com.tinet.oskit.view.SatisfactionStatusChange;
import com.tinet.oskit.widget.SessionInputView;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.common.OnlineEvent;
import com.tinet.oslib.common.OnlineMessageSenderType;
import com.tinet.oslib.common.OnlineMessageType;
import com.tinet.oslib.listener.ChatInfoCallback;
import com.tinet.oslib.listener.RequestInvestigationListener;
import com.tinet.oslib.listener.SubmitInvestigationListener;
import com.tinet.oslib.manager.InvestigationManager;
import com.tinet.oslib.manager.OnlineManager;
import com.tinet.oslib.manager.OnlineMessageFailureManager;
import com.tinet.oslib.manager.OnlineQuickManager;
import com.tinet.oslib.manager.OnlineResourceManager;
import com.tinet.oslib.manager.OnlineSessionManager;
import com.tinet.oslib.model.bean.CardInfo;
import com.tinet.oslib.model.bean.InvestigationStar;
import com.tinet.oslib.model.bean.LabeInfo;
import com.tinet.oslib.model.bean.SessionInfo;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.CardMessage;
import com.tinet.oslib.model.message.content.ChatBridgeMessage;
import com.tinet.oslib.model.message.content.ChatCloseMessage;
import com.tinet.oslib.model.message.content.ChatInputHintMessage;
import com.tinet.oslib.model.message.content.ChatLeaveMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oslib.model.message.content.TextMessage;
import com.tinet.threepart.tools.TMediaFile;
import com.tinet.timclientlib.common.constans.TMessageDirection;
import com.tinet.timclientlib.utils.TNtpUtils;
import com.tinet.threepart.audio.AudioRecordManager;
import com.tinet.threepart.emoji.EmotionLayout;
import com.tinet.threepart.emoji.IEmotionSelectedListener;
import com.tinet.threepart.keyboard.KeyBoardObservable;
import com.tinet.threepart.keyboard.KeyBoardObserver;
import com.tinet.threepart.keyboard.MeasureLinearLayout;
import com.tinet.threepart.tools.TClickUtil;
import com.tinet.oskit.aty.CaptureAty;
import com.tinet.oskit.listener.impl.SessionClickListenerImpl;
import com.tinet.oskit.popup.AudioPopupWindow;
import com.tinet.oskit.R;
import com.tinet.oskit.adapter.FuncAdapter;
import com.tinet.oskit.adapter.SessionAdapter;
import com.tinet.oskit.listener.FuncListener;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.listener.impl.FuncListenerImpl;
import com.tinet.oskit.model.DataTemplate;
import com.tinet.oskit.model.Function;
import com.tinet.oskit.present.SessionPresent;
import com.tinet.oskit.tool.AudioPlayHelper;
import com.tinet.oskit.view.SessionView;
import com.tinet.threepart.tools.TUIUtils;
import com.tinet.timclientlib.utils.TLogUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionFragment
 * @Author: liuzr
 * @CreateDate: 2021-08-20 13:24
 * @Description: 聊天窗口
 */
public class SessionFragment extends TinetFragment implements IEmotionSelectedListener, SessionView, KeyBoardObserver, SatisfactionStatusChange,SessionInputView.OnActionListener {

    /**
     * 商品卡片传参数据
     */
    public static final String ARGS_CARD = "tinetCard";

    private static final String TO_ONLINE = "转人工";

    private static final String SESSIONCARD_UUID = "1234567a-a000-1d1a-bd1e-d1e1d11c0dd1";

    /**
     * 是否首次加载会话，用于判断是否展示发送商品卡片
     */
    private boolean isFirstLoadSession = true;

    /**
     * 申请语音权限
     */
    private static final int REQUEST_AUDIO_PERMISSION = 1661;

    /**
     * 申请相机权限
     */
    public static final int REQUEST_CAMERA_PERMISSION = 1662;

    /**
     * 申请相机、语音权限 -- 拍摄
     */
    public static final int REQUEST_CAMERA_SHOOT_PERMISSION = 1663;

    /**
     * 申请文件权限 -- 文件
     */
    public static final int REQUEST_FILE_PERMISSION = 1664;

    /**
     * 选择一张照片
     */
    private static final int SELECT_IMAGE = 2001;

    /**
     * 拍摄
     */
    public static final int CAPTURE = 2002;

    /**
     * 文件
     */
    public static final int FILE = 2003;

    /**
     * 输入法闪烁问题解决
     */
    private MeasureLinearLayout viewRoot;

    /**
     * 语音管理
     */
    private AudioPopupWindow audioPopupWindow;

    /**
     * 聊天
     */
    private RecyclerView recyclerView;

    /**
     * 更多
     */
    private RecyclerView recyclerViewFunc;

    /**
     * 快接入口
     */
    private RecyclerView recyclerViewLabel;

    /**
     * 消息适配器
     */
    private SessionAdapter adapter;

    private FuncAdapter funcAdapter;

    private LabelAdapter labelAdapter;

    /**
     * present
     */
    private SessionPresent present;

    /**
     * 表情控件
     */
    private EmotionLayout emotionLayout;

    /**
     * 底部功能栏
     */
    private View moreLayout;

    /**
     * 会话结束
     */
    private TextView tvOver;

    /**
     * 聊天输入框
     */
    protected SessionInputView viewChat;

    /**
     * 表情和更多功能
     */
    private View viewMore,layout;
    /**
     * 刷新控件
     */
    private SwipeRefreshLayout refreshLayout;

    /**
     * 表情发送
     */
    private ViewStub vsEmoSend;

    private View viewBottomDivider;

    /**
     * 文本输入模式
     */
    public static final int TYPE_TEXT = 1;

    /**
     * 语音输入模式
     */
    public static final int TYPE_AUDIO = 2;

    /**
     * 输入文本
     */
    public static final int MODEL_TEXT = 1;

    /**
     * 表情输入
     */
    public static final int MODEL_EMO = 2;
    private boolean isInitiative;

    /**
     * 输入模式定义
     */
    @IntDef({TYPE_TEXT, TYPE_AUDIO})
    private @interface INPUT_TYPE {
    }

    /**
     * 表情
     */
    private static final int BOTTOM_SHOW_EMO = 1;

    /**
     * 功能
     */
    private static final int BOTTOM_SHOW_FUNC = 2;

    /**
     * 底部功能栏显示的
     */
    @IntDef({BOTTOM_SHOW_EMO, BOTTOM_SHOW_FUNC})
    private @interface BottomShow {
    }

    /**
     * 是否需要显示底部功能栏
     */
    private boolean needShowBottom = false;

    /**
     * 底部功能栏显示的
     */
    private @BottomShow
    int bottomShow = BOTTOM_SHOW_EMO;

    /**
     * 输入类型，表情或文字
     */
    @IntDef({MODEL_TEXT, MODEL_EMO})
    private @interface MODEL {
    }

    private @MODEL
    int model = MODEL_TEXT;

    /**
     * 当前的输入模式
     */
    private @INPUT_TYPE
    int type = TYPE_TEXT;

    //动画的持续时间
    public static final long duration = 100;

    @Override
    protected int layoutId() {
        return R.layout.frg_session;
    }

    /**
     * 获取数据适配器
     * 自定义适合器，可重写此方法
     */
    protected SessionAdapter getAdapter() {
        return new SessionAdapter(getListener());
    }

    /**
     * 获取事件处理
     * 自定义消息点击事件，可重写此方法
     */
    protected SessionClickListener getListener() {
        return new SessionClickListenerImpl(this);
    }

    /**
     * 获取更多事件处理
     * 自定义更多，可重写此方法
     */
    protected FuncListener getFuncListener() {
        return new FuncListenerImpl(this);
    }

    /**
     * 获取快接入口处理
     * 自定义更多，可重写此方法
     */
    protected LabelListener getLabelListener() {
        return new LabelListenerImpl(this);
    }

    private KeyBoardObservable observable;


    //卡片信息
    private CardInfo cardInfo;

    @Override
    protected void initView() {
        TLogUtils.i("进入聊天页面...");
        Bundle args = getArguments();
        if (null != args && args.containsKey(ARGS_CARD)) {
            cardInfo = args.getParcelable(ARGS_CARD);
        }

        present = new SessionPresent(this);
        viewRoot = requireView().findViewById(R.id.viewRootForInput);
        observable = viewRoot.getKeyBoardObservable();
        if (null != observable) {
            observable.register(this);
        }

        recyclerViewFunc = requireView().findViewById(R.id.recyclerViewFunc);
        funcAdapter = new FuncAdapter(getFuncListener());
        viewBottomDivider = requireView().findViewById(R.id.viewBottomDivider);
        recyclerViewFunc.setAdapter(funcAdapter);
        viewChat = requireView().findViewById(R.id.inputView);
        viewChat.setListener(this);
        tvOver = requireView().findViewById(R.id.tvOver);
        initOver();
        emotionLayout = requireView().findViewById(R.id.emotionLayout);
        moreLayout = requireView().findViewById(R.id.moreLayout);
        vsEmoSend = requireView().findViewById(R.id.vsEmoSend);
        recyclerView = requireView().findViewById(R.id.tinetMessageRecyclerView);
        recyclerView.setItemAnimator(getItemAnimator());
        layout = requireView().findViewById(R.id.tinetMessageContainer);
        recyclerView.addItemDecoration(
            new LinearLayoutManagerDecoration(requireContext(), getResources().getDimensionPixelSize(R.dimen.ti_msg_msg_span), Color.TRANSPARENT,true));
        adapter = getAdapter();
        adapter.setListener(
            () -> onMessageListLayoutChanged(recyclerView.getHeight(),layout!=null?layout.getHeight():-1));
        recyclerView.setAdapter(adapter);
        recyclerViewLabel = requireView().findViewById(R.id.recyclerViewLabel);
        labelAdapter = new LabelAdapter(getLabelListener());
        recyclerViewLabel.setAdapter(labelAdapter);
        viewMore = requireView().findViewById(R.id.viewMore);

//        if (ModifyUiUtils.kitConfigState() != null) {
//            TCustomizationUI customizationUI = ModifyUiUtils.kitConfigState();
//            ivVoice.setVisibility(customizationUI.showVoiceButton ? View.VISIBLE : View.GONE);
//            ModifyUiUtils.modifySetTextColor(tvAudioPress, customizationUI.inputAreaVoicePressTextColor);
//            ModifyUiUtils.modifySetBackground(requireView().findViewById(R.id.layout), customizationUI.sessionBackgroundColor);
//            ModifyUiUtils.modifySetBackground(viewChat, customizationUI.inputAreaBgColor);
//            if (!TextUtils.isEmpty(customizationUI.inputBoxHintText))
//                etContent.setHint(customizationUI.inputBoxHintText);
//        } /*else {
//            ivVoice.setVisibility(View.VISIBLE);
//        }*/

        refreshLayout = requireView().findViewById(R.id.tinetRefreshLayout);
        if(null != refreshLayout) {
            refreshLayout.setOnRefreshListener(() -> onMessageRefresh());
        }

        audioPopupWindow = new AudioPopupWindow(requireContext(), requireView().findViewById(R.id.viewRootForInput));
        audioPopupWindow.setListener(voicePath -> {
            //发送语音
            present.send(OnlineMessageType.VOICE, voicePath);
        });

        //绑定表情控件
        emotionLayout.attachEditText(viewChat.etContent);
        emotionLayout.setEmotionSelectedListener(this);

        recyclerView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (viewMore.isShown()) {
                    hideMoreLayout();
                } else {
                    viewChat.setEmoByModel(MODEL_TEXT);

                    viewChat.setFocus(false);
                    viewChat.closeKeyboard();
                }
            }

            return false;
        });

        present.initFunctions();
        present.registerListener();

        OnlineQuickManager.getInstance().setOnlineQuickChangeListener(
            quicks -> updateQuick(quicks));
        updateQuick(OnlineQuickManager.getInstance().getQuicks());
    }

    /**
     * 刷新
     */
    protected void onMessageRefresh(){
        if(null != present) {
            present.loadHistory();
        }
    }

    /**
     * 结束刷新
     * @param hasMore 是否还有更多
     * @param lastMessageTime 最后一条消息的时间
     */
    protected void finishMessageRefresh(Long lastMessageTime,boolean hasMore,boolean isRefresh){
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    /**
     * 消息动画
     * @return
     */
    protected RecyclerView.ItemAnimator getItemAnimator(){
        return null;
    }

    /**
     * 更新快捷数据入口
     *
     * @param quicks
     */
    private void updateQuick(final ArrayList<LabeInfo> quicks) {
        TUIUtils.postTaskSafely(() -> {
            labelAdapter.setData(quicks);

            if (null != quicks && !quicks.isEmpty()) {
                recyclerViewLabel.setVisibility(View.VISIBLE);
            } else {
                recyclerViewLabel.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 关闭软键盘
     */
    public void closeKeyboard() {
        viewChat.closeKeyboard();
    }

    @Override
    public void onResume() {
        super.onResume();
        present.setUserVisibleHint(true);
    }

    @Override
    public void update(boolean keyBoardVisibile, int keyBoardHeight) {
        if (keyBoardVisibile) {
            hideMoreLayout();
            recyclerView.smoothScrollBy(0, Integer.MAX_VALUE);
        } else {
            if (!needShowBottom || viewMore.isShown()) {
                return;
            }

            needShowBottom = false;

            if (!isInitiative)
                showMoreLayout();
        }
    }

    private void initOver() {
        String str = getString(R.string.ti_chat_over_hint);
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ClickableSpan() {
                       @Override
                       public void onClick(@NonNull View widget) {
                           present.openSession(null);
                       }
                   },
                str.length() - 4, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.ti_blue)),
                str.length() - 4, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvOver.setText(ss);
        tvOver.setMovementMethod(LinkMovementClickMethod.getInstance());
    }

    @Override
    public void onEmojiSelected(String key) {

    }

    @Override
    public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
        present.send(OnlineMessageType.IMAGE, stickerBitmapPath);
    }

    private void closeBottomAndKeyboard() {
        hideMoreLayout();
        setModel(MODEL_TEXT);
        setType(TYPE_TEXT);
        viewChat.closeKeyboard();
    }

    @Override
    public boolean onSend(String message) {
        if (TClickUtil.isNotFastClick()) {
            present.sendText(message);
            TUIUtils.postTaskDelay(new Runnable() {
                @Override
                public void run() {
                    sendInputHint("");
                }
            }, SEND_INPUT_HINT_LIMIT);

            return true;
        }

        return false;
    }

    @Override
    public boolean onInputHintSend(String message) {
        return false;
    }

    @Override
    public void onVoiceClick() {
        if(!hasAudioPermission()) {
            requestAudioPermission();
            return;
        }

        needShowBottom = false;

        if (type == TYPE_AUDIO) {
            //当前如果是语音输入模式，则切换为文本输入模式
            setType(TYPE_TEXT);
            openKeyboard();
        } else {
            setType(TYPE_AUDIO);
            hideMoreLayout();
        }
    }

    @Override
    public void startRecord() {
        AudioRecordManager.getInstance(requireContext()).startRecord();
    }

    /**
     * 申请语音权限
     * 长按录音时，申请录音权限
     */
    private void requestAudioPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            AudioRecordManager.getInstance(requireContext()).startRecord();
        } else {
            //没权限
            if(!isOpenRequestPermissionTip()){
                SessionFragment.this.shouldShowRequestPermissionRationale(
                    Manifest.permission.RECORD_AUDIO);
                SessionFragment.this.startRequestPermissions(
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_AUDIO_PERMISSION);
            }else {
                new SureDialog(new OnResultListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onSure() {
                        SessionFragment.this.shouldShowRequestPermissionRationale(
                            Manifest.permission.RECORD_AUDIO);
                        SessionFragment.this.startRequestPermissions(
                            new String[]{Manifest.permission.RECORD_AUDIO},
                            REQUEST_AUDIO_PERMISSION);
                    }
                }, getString(R.string.ti_permission_title), getString(R.string.ti_permission_audio),
                    getString(R.string.ti_cancel), getString(R.string.ti_permission_open)).show(
                    getChildFragmentManager(), SessionFragment.class.getName());
            }
        }
    }

    /**
     * 是否有录音权限
     * @return
     */
    private boolean hasAudioPermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onFocusChange(boolean hasFocus) {
        if (hasFocus) {
            setModel(MODEL_TEXT);
        }

        if(viewChat != null) {
            viewChat.setFocus(hasFocus);
        }
    }

    @Override
    public void onMoreClick(View v) {
        isInitiative = false;

        if (viewMore.isShown()) {
            if (v.getId() == R.id.ivAdd) {
                viewChat.setEmoByModel(MODEL_TEXT);
                if (bottomShow == BOTTOM_SHOW_EMO) {
                    setBottomShow(BOTTOM_SHOW_FUNC);
                } else if (bottomShow == BOTTOM_SHOW_FUNC) {
                    openKeyboard();
                }
            } else if (v.getId() == R.id.ivEmo) {
                viewChat.setEmoByModel(MODEL_EMO);
                if (bottomShow == BOTTOM_SHOW_FUNC) {
                    setBottomShow(BOTTOM_SHOW_EMO);
                } else if (bottomShow == BOTTOM_SHOW_EMO) {
                    viewChat.setEmoByModel(MODEL_TEXT);
                    openKeyboard();
                }
            }
        } else {
            needShowBottom = true;

            if (observable.isKeyBoardVisibile()) {
                viewChat.setFocus(false);
                viewChat.closeKeyboard();
            }

            if (v.getId() == R.id.ivAdd) {
                viewChat.setEmoByModel(MODEL_TEXT);
                setBottomShow(BOTTOM_SHOW_FUNC);
                showMoreLayout();

                viewChat.updateMoreVisible(true);
                viewChat.updateSendVisible(false);
            } else if (v.getId() == R.id.ivEmo) {
                viewChat.setEmoByModel(MODEL_EMO);
                setBottomShow(BOTTOM_SHOW_EMO);
                showMoreLayout();

                if(!TextUtils.isEmpty(viewChat.getContent())) {
                    viewChat.updateMoreVisible(false);
                }
            } else {
                openKeyboard();
            }
        }
    }

    @Override
    public boolean canShowSend() {
        if(emotionLayout == null){
            return true;
        }

        if(emotionLayout.isTinetShowDelete()){
            return true;
        }

        if(model == MODEL_TEXT){
            return true;
        }

        return false;
    }

    @Override
    public boolean isAudio() {
        return type != TYPE_AUDIO;
    }

    /**
     * 是否开启权限申请提示
     * */
    protected boolean isOpenRequestPermissionTip(){
        return false;
    }

    /**
     * 申请照相机权限
     */
    public void requestCameraPermission(final int requestCode) {
        if (REQUEST_CAMERA_SHOOT_PERMISSION == requestCode || REQUEST_CAMERA_PERMISSION == requestCode) {
            //相机和语音权限
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //有权限
                takeOrShoot(requestCode);
            } else {
                //没权限
                if(!isOpenRequestPermissionTip()){
                    doRequestCameraPermission(requestCode);
                }else {
                    new SureDialog(new OnResultListener() {
                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onSure() {
                            doRequestCameraPermission(requestCode);
                        }
                    }, getString(R.string.ti_permission_title),
                        getString(R.string.ti_permission_camera), getString(R.string.ti_cancel),
                        getString(R.string.ti_permission_open)).show(getChildFragmentManager(),
                        SessionFragment.class.getName());
                }
            }
        } else {
            takeOrShoot(requestCode);
        }
    }

    private void doRequestCameraPermission(int requestCode){
        if (ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            SessionFragment.this.shouldShowRequestPermissionRationale(
                Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            SessionFragment.this.shouldShowRequestPermissionRationale(
                Manifest.permission.RECORD_AUDIO);
        }
        if (ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            SessionFragment.this.shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            SessionFragment.this.shouldShowRequestPermissionRationale(
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        SessionFragment.this.startRequestPermissions(
            new String[]{Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
    }

    /**
     * 拍摄或获取图片
     */
    private void takeOrShoot(final int requestCode) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            startActivityForResult(new Intent(requireContext(), ImagePickerActivity.class), SELECT_IMAGE);
        } else if (requestCode == REQUEST_CAMERA_SHOOT_PERMISSION) {
            startActivityForResult(new Intent(requireContext(), CaptureAty.class), CAPTURE);
        }
    }

    /**
     * 会话变化监听
     * 当currentHeight >= messageHeight 滚动出现
     * @param currentHeight 当前消息框的高度
     * @param messageHeight 消息框总高度
     */
    protected void onMessageListLayoutChanged(int currentHeight,int messageHeight){}

    /**
     * 开始选择文件，权限获取判断
     */
    public void selectFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 先判断有没有权限
            if (Environment.isExternalStorageManager()) {
                showSelectFilePop();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } else {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                showSelectFilePop();
            } else {
                //没权限
                if(!isOpenRequestPermissionTip()){
                    SessionFragment.this.shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE);
                    SessionFragment.this.startRequestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_FILE_PERMISSION);
                }else {
                    new SureDialog(new OnResultListener() {
                        @Override
                        public void onCancel() {

                        }

                        @Override
                        public void onSure() {
                            SessionFragment.this.shouldShowRequestPermissionRationale(
                                Manifest.permission.READ_EXTERNAL_STORAGE);
                            SessionFragment.this.startRequestPermissions(
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_FILE_PERMISSION);
                        }
                    }, getString(R.string.ti_permission_title),
                        getString(R.string.ti_permission_file), getString(R.string.ti_cancel),
                        getString(R.string.ti_permission_open)).show(getChildFragmentManager(),
                        SessionFragment.class.getName());
                }
            }
        }
    }

    /**
     * 选择文件弹窗
     */
    private void showSelectFilePop() {
        FilePicker filePicker = new FilePicker(requireActivity());
        filePicker.setInitDir(ExplorerMode.FILE, Environment.getExternalStorageDirectory());
        filePicker.setOnFilePickedListener(new OnFilePickedListener() {
            @Override
            public void onFilePicked(@NonNull File file) {
                if (TMediaFile.isImage(file.getPath())) {
                    present.send(OnlineMessageType.IMAGE, file.getPath());
                } else if (TMediaFile.isVideo(file.getPath())) {
                    present.send(OnlineMessageType.VIDEO, file.getPath());
                } else {
                    present.send(OnlineMessageType.FILE, file.getPath());
                }
            }
        });
        filePicker.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        boolean hasCameraPermission = false;
        boolean hasAudioPermission = false;
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            int grantResult = grantResults[i];

            switch (requestCode) {
                case REQUEST_AUDIO_PERMISSION:
                    /*if (permission.equals(Manifest.permission.RECORD_AUDIO)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            //有了录音权限
                            AudioRecordManager.getInstance(requireContext());
                        } else {
                            //用户拒绝了录音权限
                        }
                    }*/
                    break;
                case REQUEST_CAMERA_SHOOT_PERMISSION:
                    if (permission.equals(Manifest.permission.CAMERA)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            hasCameraPermission = true;
                        }
                    }
                    if (permission.equals(Manifest.permission.RECORD_AUDIO)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            hasAudioPermission = true;
                        }
                    }
                    if (hasCameraPermission && hasAudioPermission)
                        takeOrShoot(requestCode);
                    break;
                case REQUEST_FILE_PERMISSION:
                    //发送图片
                    if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        if (grantResult == PackageManager.PERMISSION_GRANTED) {
                            //有了读取文件
                            selectFile();
                        } else {
                            //用户拒绝了读取文件权限
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case SELECT_IMAGE:
                present.send(data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES));
                break;
            case CAPTURE: {
                Boolean isImage = data.getBooleanExtra(CaptureFragment.TAKE_PHOTO, true);
                String path = data.getStringExtra(CaptureFragment.PATH);
                present.send(isImage ? OnlineMessageType.IMAGE : OnlineMessageType.VIDEO, path);
                break;
            }
            case FILE: {
                Uri uri = data.getData();

                String path = "";
                if ("file".equalsIgnoreCase(uri.getScheme())) {//使用第三方应用打开
                    path = uri.getPath();
                }
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                    path = UriTool.getPath(requireContext(), uri);
                } else {//4.4以下下系统调用方法
                    path = UriTool.getRealPathFromURI(requireContext(), uri);
                }

                if (TextUtils.isEmpty(path)) {
                    showToast(R.string.ti_path_resove_failure, true);
                } else {
                    present.send(OnlineMessageType.FILE, path);
                }
                break;
            }
        }

        closeBottomAndKeyboard();
    }

    @Override
    public void loadHistoryResult(final DataTemplate<OnlineMessage> template, Boolean isSuccess) {
        finishMessageRefresh(present.lastMessageTime,template == null?false:template.hasMore(),template == null?true:template.isRefresh());
        if (null != template && template.getCount() > 0) {
            if (isSuccess) {
                if (template.isRefresh()) {
                    if (checkMessageListIdEquals(template.getList(), adapter.getData())) {
                        return;
                    }
                    detailIsAddCardInfoToHistory(template.getList(), adapter.getData());
                    initSetData(template.getList());
                } else {
                    adapter.appendData(template.getList());
                }
            }
        }

        if (isFirstLoadSession) {
            isFirstLoadSession = false;
            addCardInfo();
        }
    }

    /**
     * adapter setData时，可能会把新数据覆盖掉，这里处理一下10条新数据
     * */
    private void initSetData(List<OnlineMessage> messages){
        List<OnlineMessage> data = new ArrayList<>();
        if(adapter.getItemCount()>0){
            for(int i=0;i<10&&i<adapter.getItemCount();i++){
                data.add(adapter.getItem(i));
            }
        }

        if(adapter != null) {
            adapter.setData(messages);
        }

        OnlineMessage message = null;
        if(adapter.getItemCount()>0){
            message = adapter.getItem(0);
        }

        if(data.size()>0){
            //取第0个数据
            for(int i=data.size()-1;i>=0;i--){
                OnlineMessage mOnlineMessage = data.get(i);
                if(message == null){
                    adapter.addFirstItem(mOnlineMessage);
                    present.lastMessageTime = mOnlineMessage.getSendTime();
                    message = mOnlineMessage;
                    continue;
                }

                Long sendTime1 = message.getSendTime();
                Long sendTime2 = mOnlineMessage.getSendTime();

                if(sendTime1 == null || sendTime2 == null){
                    continue;
                }

                if(sendTime2>sendTime1){
                    if(handleMessage(mOnlineMessage)) {
                        adapter.addFirstItem(mOnlineMessage);
                        message = mOnlineMessage;
                    }
                }


            }
        }
    }

    /**
     * 检查消息列表是否已展示发送商品卡片 如存在则添加对应位置
     *
     * @param newList
     * @param orderList
     */
    private void detailIsAddCardInfoToHistory(List<OnlineMessage> newList, List<OnlineMessage> orderList) {
        try {
            for (int i = 0; i < orderList.size(); i++) {
                if (SESSIONCARD_UUID.equals(orderList.get(i).getMessageUUID())) {
                    if (newList.size() > 0 && i < newList.size()) {
                        OnlineMessage onlineMessage = orderList.get(i);
                        for (int j = 0; j < newList.size(); j++) {
                            if (i == 0 && newList.get(j).getMessageUUID().equals(orderList.get(i + 1).getOnlineContent().getMessageUniqueId())) {
                                newList.add(j, onlineMessage);
                                break;
                            } else if (i >= 1 && (newList.get(j).getMessageUUID().equals(orderList.get(i - 1).getOnlineContent().getMessageUniqueId()) || (newList.get(j).getMessageUUID().equals(orderList.get(i - 1).getMessageUUID())))) {
                                newList.add(j + 1, onlineMessage);
                                break;
                            } else if (newList.get(j).getMessageUUID().equals(orderList.get(i + 1).getOnlineContent().getMessageUniqueId()) || newList.get(j).getMessageUUID().equals(orderList.get(i + 1).getMessageUUID())) {
                                newList.add(j, onlineMessage);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断最新一条消息ID与列表现有消息ID是否一致 ，一致则不需要添加数据，刷新页面
     *
     * @param newList
     * @param orderList
     * @return
     */
    private boolean checkMessageListIdEquals(List<OnlineMessage> newList, List<OnlineMessage> orderList) {
        try {
            if (orderList != null && orderList.size() > 0 && newList != null && newList.size() > 0) {
                OnlineMessage tempOrderMessage = new OnlineMessage(orderList.get(0));
                OnlineMessage firstOrderMessage = orderList.get(0);
                // : 2023/2/9 最新一条消息为待发送的卡片消息不计入
                if (firstOrderMessage.getOnlineMessageType() != null && firstOrderMessage.getOnlineMessageType() == OnlineMessageType.SENDOUT_CARD && orderList.size() > 1) {
                    tempOrderMessage = orderList.get(1);
                }
                if (tempOrderMessage.getMessageUUID().equals(newList.get(0).getMessageUUID())
                        ||(tempOrderMessage.getOnlineContent() != null && tempOrderMessage.getOnlineContent().getMessageUniqueId() != null && tempOrderMessage.getOnlineContent().getMessageUniqueId().equals(newList.get(0).getMessageUUID()) )
                        || tempOrderMessage.getOnlineContent() instanceof ChatBridgeMessage
                ) {
                    TLogUtils.i("历史消息ID一致，不更新页面");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将发送的消息加入到聊天列表
     */
    @Override
    public void onMessageSend(OnlineMessage message) {
        //可以直接调用sendMessageProgress方法
        //如果是文本消息，需要清空当前输入框
        if (message.getOnlineContent() instanceof TextMessage) {
            viewChat.updateContent("");
        }

        adapter.addFirstItem(message);
        recyclerView.scrollToPosition(0);
    }

    /**
     * 消息发送进度
     */
    @Override
    public void sendMessageProgress(OnlineMessage message, int progress) {
        if (!adapter.updateItem(message)) {
            recyclerView.scrollToPosition(0);
        }
    }

    @Override
    public void funcList(List<Function> funcs) {
        funcAdapter.setData(funcs);
    }

    public void setType(int type) {
        this.type = type;

        viewChat.setType(type);
    }

    /**
     * 显示更多功能
     */
    private void showMoreLayout() {
        viewChat.closeKeyboard();
        viewChat.setFocus(false);
        viewMore.setAlpha(0f);
        viewMore.animate().alpha(1f).setDuration(duration).setListener(null);
        setBottomShow(this.bottomShow);
        viewMore.setVisibility(View.VISIBLE);
        viewBottomDivider.setVisibility(View.VISIBLE);
        recyclerView.smoothScrollBy(0, Integer.MAX_VALUE);
    }

    private void setBottomShow(int bottomShow) {
        this.bottomShow = bottomShow;

        setType(TYPE_TEXT);

        if (bottomShow == BOTTOM_SHOW_EMO) {
            //底部显示为表情
            moreLayout.animate().alpha(0f).setDuration(duration).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    moreLayout.setVisibility(View.GONE);
                }
            });
            emotionLayout.setAlpha(0f);
            emotionLayout.setVisibility(View.VISIBLE);
            setModel(MODEL_EMO);
            notifyEmoManagerVisible(true);
            emotionLayout.animate().alpha(1f).setDuration(duration).setListener(null);
        } else {
            //底部显示为更多
            emotionLayout.animate().alpha(0f).setDuration(duration).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    emotionLayout.setVisibility(View.GONE);
//                    setModel(MODEL_TEXT);
                    notifyEmoManagerVisible(false);
                }
            });
            moreLayout.setAlpha(0f);
            moreLayout.setVisibility(View.VISIBLE);
            moreLayout.animate().alpha(1f).setDuration(duration).setListener(null);
        }
    }

    /**
     * 隐藏更多功能
     */
    public void hideMoreLayout() {
        viewMore.animate().alpha(0f).setDuration(duration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                viewMore.setVisibility(View.GONE);
                if(bottomDividerShowInMore()){
                    viewBottomDivider.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 打开软键盘
     */
    public void openKeyboard() {
        isInitiative = true;
        viewChat.openKeyboard();
        hideMoreLayout();
    }

    @Override
    public void onPause() {
        if (audioPopupWindow != null && audioPopupWindow.isShowing()) {
            audioPopupWindow.dismiss();
        }
        present.setUserVisibleHint(false);
        super.onPause();
    }

    public void setModel(int model) {
        this.model = model;

        switch (model) {
            case MODEL_TEXT:
                viewMore.animate().alpha(0f).setDuration(duration).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewMore.setVisibility(View.GONE);
                        if(bottomDividerShowInMore()){
                            viewBottomDivider.setVisibility(View.GONE);
                        }
                    }
                });
                break;
            case MODEL_EMO:
                setType(TYPE_TEXT);
                break;
        }

        viewChat.setEmoByModel(model);
    }

    public SessionPresent getPresent() {
        return present;
    }

    /**
     * 重新编辑消息
     */
    public void reEditMessage(TextMessage message) {
        setType(TYPE_TEXT);
        setModel(MODEL_TEXT);
        viewChat.updateContent(message.getContent());
    }

    @Override
    public void onRevokeMessage(OnlineMessage message) {
        adapter.updateItem(message);
    }

    @Override
    public void onReceiverMessage(OnlineMessage message) {
        String event = message.getEvent();

        if (handleMessage(message)) {
            adapter.addFirstItem(message);
            recyclerView.scrollToPosition(0);
        }

        if (OnlineEvent.CHAT_BRIDGE.equals(event) || OnlineEvent.ROBOT_BRIDGE.equals(event)) {
            addCardInfo();
        }
    }

    /**
     * 由于网速等相关的原因，导致历史消息可能拉到最新的欢迎语信息
     * @return true可以添加进列表
     *          false 不可以添加进列表，可能是重复数据
     */
    private boolean handleMessage(OnlineMessage message){
        OnlineServiceMessage mOnlineServiceMessage = message.getOnlineContent();
        if(mOnlineServiceMessage == null){
            return true;
        }

        String messageId = mOnlineServiceMessage.getMessageUniqueId();
        if(TextUtils.isEmpty(messageId)){
            return true;
        }

        if(adapter.getItemCount() == 0){
            return true;
        }

        boolean isFind = false;

        int receiverScannerCount = 0;
        //最多检测最下面的10条数据，检测多了影响性能。
        for(int i=0;receiverScannerCount<10 && i<adapter.getItemCount();i++){
            OnlineMessage lastMessage = adapter.getItem(i);
            OnlineServiceMessage onlineServiceMessage = lastMessage.getOnlineContent();
            if(onlineServiceMessage != null && onlineServiceMessage.getSenderType() != OnlineMessageSenderType.VISITOR) {
                receiverScannerCount++;
                String lastMessageId = onlineServiceMessage.getMessageUniqueId();

                isFind = TextUtils.equals(messageId, lastMessageId);
                if (isFind) {
                    break;
                }
            }
        }

        return !isFind;
    }

    @Override
    public void onMessageDelete(List<OnlineMessage> message) {
        if (message != null && message.size() > 0) {
            for (OnlineMessage deleteMessage : message) {
                adapter.deleteMessage(deleteMessage);
            }
        }
    }

    @Override
    public void onClearMessage() {
        adapter.setData(null);
    }

    @Override
    public void setMessageRead(String msgId) {
    }

    @Override
    public void onSessionOpen() {
        tvOver.setVisibility(View.GONE);
        viewChat.setVisibility(View.VISIBLE);
        viewChat.animate().alpha(1f).setDuration(duration).setListener(null);
        updateQuick(OnlineQuickManager.getInstance().getQuicks());

        OnlineSessionManager.clearCache();
    }

    private void addCardInfo() {
        if (cardInfo != null) {
            if (adapter.getData().size() > 0) {
                List<OnlineMessage> onlineMessages = adapter.getData();
                for (int i = 0; i < onlineMessages.size(); i++) {
                    if (SESSIONCARD_UUID.equals(onlineMessages.get(i).getMessageUUID()))
                        return;
                }
                JSONObject object = CardInfo.putCardInfo(cardInfo);
                final CardMessage onlineCardMessage = CardMessage.obtain(object, OnlineMessageType.SENDOUT_CARD);
                OnlineMessage onlineMessage = OnlineMessage.obtain(onlineCardMessage);
                onlineMessage.setSendTime(TNtpUtils.getRealTimeMillis());
                onlineMessage.setStatus(1);
                onlineMessage.setMessageUUID(SESSIONCARD_UUID);
                adapter.insertData(new ArrayList<OnlineMessage>(Arrays.asList(onlineMessage)));
                recyclerView.scrollToPosition(0);
            }
        }
    }

    @Override
    public void onSessionClosed() {
        viewChat.setVisibility(View.GONE);
        viewMore.setVisibility(View.GONE);
        if(bottomDividerShowInMore()){
            viewBottomDivider.setVisibility(View.GONE);
        }
        recyclerViewLabel.setVisibility(View.GONE);
        viewChat.closeKeyboard();
        tvOver.setAlpha(0f);
        tvOver.setVisibility(View.VISIBLE);
        tvOver.animate().alpha(1f).setDuration(duration).setListener(null);
        // : 2022/7/1 关闭正在上传的资源请求
        OnlineResourceManager.cancelAllRequest();
    }

    @Override
    public void withdraw(String messageId) {
        adapter.withdraw(messageId);
    }

    @Override
    public void fileSizeLimit(int fileType) {
        showToast(R.string.ti_file_limit_size, true);
    }

    @Override
    public void chatLeaveMessage(ChatLeaveMessage message) {
        getActivity().finish();
        Intent intent = new Intent(requireContext(), ChatLeaveMessageAty.class);
        intent.putExtra(ChatLeaveMessageFragment.CHAT_LEAVE_MESSAGE_KEY, message.getBodyJson());
        startActivity(intent);
    }

    @Override
    public void satisfactionStatus(final String mainUniqueId, final String uniqueId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                present.getChatInfo(mainUniqueId, uniqueId);
            }
        }, 500);
    }

    @Override
    public void investigation(String uniqueId) {
        List<OnlineMessage> onlineMessages = adapter.getData();
        for (int i = 0; i < onlineMessages.size(); i++) {
            if (uniqueId.equals(onlineMessages.get(i).getOnlineContent().getUniqueId())) {
                onlineMessages.get(i).getOnlineContent().setAlreadyInvestigation(1);
                adapter.setItem(i, onlineMessages.get(i));
            }
        }
    }

    @Override
    public void updateMessageStatusByMessageUUID(String messageUUID, int status) {
        adapter.updateMessageStatusByMessageUUID(messageUUID, status);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewRoot.getKeyBoardObservable().unRegister(this);
        present.unRegisterListener();
//        OnlineServiceClient.closeClientSession();
        // : 2022/7/1 关闭正在上传的资源请求，避免内存泄露
        OnlineResourceManager.cancelAllRequest();
        OnlineQuickManager.getInstance().setOnlineQuickChangeListener(null);
        if (audioPopupWindow != null) {
            audioPopupWindow.release();
            audioPopupWindow = null;
        }

        OnlineMessageFailureManager.getInstance().clearFailureMessage();
        OnlineSessionManager.clearCache();
    }

    @Override
    public void onStop() {
        SharedPreferences sp = requireContext().getSharedPreferences("inputContent", MODE_PRIVATE);
        sp.edit().putString("content", viewChat.getContent()).apply();
        sendInputHint("");
        AudioPlayHelper.getHelper().stopPlay();
        super.onStop();
    }

    /**
     * 转人工
     */
    public void toOnline() {
        present.sendText(TO_ONLINE);
        closeBottomAndKeyboard();
    }

    /**
     * 关闭会话
     */
    public void closeSession() {
        SharedPreferences sp = requireContext().getSharedPreferences("inputContent", MODE_PRIVATE);
        sp.edit().putString("content", "").apply();
        present.sendMessage(OnlineMessage.obtain(ChatCloseMessage.obtain()));
    }

    /**
     * 上一次发送坐席预知的时间
     */
    private Long lastSendInputHintTime = 0L;

    /**
     * 坐席预知发送的时间间隔限制
     */
    private static final int SEND_INPUT_HINT_LIMIT = 500;

    /**
     * 上一次发送的hintText信息
     * 避免重复发送同样的hintText数据
     */
    private String lastHintText = null;

    /**
     * 坐席预知
     *
     * @param content
     */
    protected void sendInputHint(String content) {
        long now = TNtpUtils.getRealTimeMillis();
        if (content.length() != 0)
            if (now - lastSendInputHintTime < SEND_INPUT_HINT_LIMIT) {
                return;
            }

        boolean allowSend = false;
        if (lastHintText == null) {
            allowSend = true;
        } else if (!lastHintText.equals(content)) {
            allowSend = true;
        }

        if (allowSend) {
            lastSendInputHintTime = now;
            present.sendEvent(OnlineMessage.obtain(ChatInputHintMessage.obtain(content)));
        }
    }

    /**
     * 开始获取权限
     *
     * @param permissions
     * @param requestCode
     */
    public void startRequestPermissions(@NonNull String[] permissions, int requestCode) {
        // : 2022/9/15 回调权限申请
        getListener().onStartRequestPermissionsCallback(permissions, requestCode);
        this.requestPermissions(permissions, requestCode);
    }

    private View emoSendView = null;

    /**
     * 触发表情框是否展示
     * @param visible
     */
    private void notifyEmoManagerVisible(boolean visible){
        if(emoSendView == null || emotionLayout == null){
            emoSendView = vsEmoSend.inflate();
        }

        if(!visible){
            emoSendView.setVisibility(View.GONE);
        }else{
            if(!emotionLayout.isTinetShowDelete()){
                emoSendView.setVisibility(View.VISIBLE);
            }else{
                emoSendView.setVisibility(View.GONE);
            }
        }

        View tinetMessageSend = emoSendView.findViewById(R.id.tinetMessageSend);
        if(null != tinetMessageSend){
            tinetMessageSend.setOnClickListener(v -> viewChat.btnSend.performClick());
        }

        View tinetEmoDelete = emoSendView.findViewById(R.id.tinetEmoDelete);
        if(null != tinetEmoDelete){
            tinetEmoDelete.setOnClickListener(
                v -> viewChat.etContent.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)));
        }
    }

    /**
     * 底部分隔栏是否在显示更多时才显示
     * false:常驻
     * true:只有显示更多时才显示
     * */
    protected boolean bottomDividerShowInMore(){
        return true;
    }

    @Override
    public void investigationResult(boolean result) {
        if(result){
            //需要拉起满意度
            EvaluatingDialog dialog = new EvaluatingDialog(
                TOSClientKit.getTOSClientKitConfig().getOnlineSetting()
                    .getInvestigation(), new OnEvaluatingListener() {
                @Override
                public void submitEvaluating(InvestigationStar investigationStar,
                    List<String> starTabs) {
                    present.submitInvestigation(investigationStar,starTabs);
                }

                @Override
                public void cancelEvaluating() {
                    onSubmitInvestigationResult(false,null,null);
                }
            });

            dialog.show(getChildFragmentManager(),SessionFragment.class.getName());
        }else{
            onSubmitInvestigationResult(false,null,null);
        }
    }

    /**
     * 处理第一次退出时的满意度
     */
    public void handleFirstOutInvestigation(){
        present.handleFirstOutInvestigation(requireContext());
    }

    /**
     * 提交满意度结果
     */
    @Override
    public void onSubmitInvestigationResult(boolean result,String msg,Exception e){}

}
