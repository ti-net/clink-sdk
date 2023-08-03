package com.tinet.oskit.listener.impl;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.tinet.oskit.R;
import com.tinet.oskit.aty.ImageAty;
import com.tinet.oskit.aty.VideoPlayAty;
import com.tinet.oskit.fragment.ImageFragment;
import com.tinet.oskit.fragment.VideoPlayFragment;
import com.tinet.oskit.popup.NewsPopupWindow;
import com.tinet.oskit.tool.TCommonUtils;
import com.tinet.oslib.OnlineServiceClient;
import com.tinet.oslib.manager.OnlineDownloadManager;
import com.tinet.oslib.model.bean.LogisticsCardInfo;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.message.OnlineMessage;
import com.tinet.oslib.model.message.content.ChatInvestigationMessage;
import com.tinet.oslib.model.message.content.ChatMiniProgramCardMessage;
import com.tinet.oslib.model.message.content.OnlineServiceMessage;
import com.tinet.oslib.model.message.content.RobotGroupMessage;
import com.tinet.oslib.model.message.content.TextMessage;
import com.tinet.oskit.fragment.SessionFragment;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlImage;
import com.tinet.spanhtml.bean.HtmlKnowledge;
import com.tinet.spanhtml.bean.HtmlLi;
import com.tinet.spanhtml.bean.HtmlLink;
import com.tinet.spanhtml.bean.HtmlOl;
import com.tinet.spanhtml.bean.HtmlText;
import com.tinet.spanhtml.bean.HtmlTextList;
import com.tinet.spanhtml.bean.HtmlUl;
import com.tinet.spanhtml.bean.HtmlVideo;
import com.tinet.timclientlib.utils.TLogUtils;
import com.tinet.timclientlib.utils.TStringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionClickListenerImpl
 * @Author: liuzr
 * @CreateDate: 2021-08-25 09:30
 * @Description:
 */
public class SessionClickListenerImpl implements SessionClickListener {

    private SessionFragment sessionFragment;

    public SessionClickListenerImpl(SessionFragment sessionFragment) {
        this.sessionFragment = sessionFragment;
    }

    @Override
    public void onClick(View itemView, OnlineMessage message) {
        sessionFragment.closeKeyboard();
        sessionFragment.hideMoreLayout();
    }

    @Override
    public void onLongClick(View itemView, final OnlineMessage info) {
        final OnlineServiceMessage message = info.getOnlineContent();
        if (message instanceof TextMessage) {
            View anchor = itemView.findViewById(R.id.tvText);
            if (anchor != null) {
                sessionFragment.closeKeyboard();
                NewsPopupWindow window = new NewsPopupWindow(anchor, new NewsPopupWindow.CopyListener() {
                    @Override
                    public void onCopy() {
                        String test = null;
                        if (info.getOnlineContent() instanceof RobotGroupMessage) {
                            RobotGroupMessage robotgroupmessage = (RobotGroupMessage) info.getOnlineContent();
                            List<OnlineQuestion> onlineQuestion = robotgroupmessage.getQuestions();
                            test = onlineQuestion.get(0).getText();
                        } else if (info.getOnlineContent() instanceof TextMessage) {
                            OnlineServiceMessage textmessage = info.getOnlineContent();
                            ArrayList<Html> htmls = textmessage.getRichContent();
                            if (htmls != null && htmls.size() > 0) {
                                StringBuilder builder = new StringBuilder();
                                for (int i = 0; i < htmls.size(); i++) {
                                    if (htmls.get(i) instanceof HtmlTextList) {
                                        detailHtmlTextList(builder, (HtmlTextList) htmls.get(i));
                                    } else if (htmls.get(i) instanceof HtmlUl) {
                                        HtmlUl htmlUl = (HtmlUl) htmls.get(i);
                                        ArrayList<HtmlLi> lis = htmlUl.getLis();
                                        for (int k = 0; k < lis.size(); k++) {
                                            detailHtmlTextList(builder, lis.get(k).getTextList());
                                        }
                                    } else if (htmls.get(i) instanceof HtmlOl) {
                                        HtmlOl htmlOl = (HtmlOl) htmls.get(i);
                                        ArrayList<HtmlLi> lis = htmlOl.getLis();
                                        for (int k = 0; k < lis.size(); k++) {
                                            detailHtmlTextList(builder, lis.get(k).getTextList());
                                        }
                                    } else if (htmls.get(i) instanceof HtmlImage) {
                                        builder.append("[图片]");
                                    } else if (htmls.get(i) instanceof HtmlVideo) {
                                        builder.append("[视频]");
                                    }
                                }
                                test = builder.toString();
                            } else {
                                test = textmessage.getContent();
                            }
                        }
                        if (test != null) {
                            // 获取系统剪贴板
                            ClipboardManager clipboard = (ClipboardManager) sessionFragment.requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clipData = ClipData.newPlainText(null, test);
                            // 把数据集设置（复制）到剪贴板
                            clipboard.setPrimaryClip(clipData);
                            sessionFragment.showToast("复制成功", true);
                        }
                    }

                    private void detailHtmlTextList(StringBuilder builder, HtmlTextList htmlTextList) {
                        if (htmlTextList == null) {
                            return;
                        }
                        for (int j = 0; j < htmlTextList.size(); j++) {
                            if (htmlTextList.get(j) instanceof HtmlText) {
                                HtmlText htmlText = (HtmlText) htmlTextList.get(j);
                                if (builder.toString().length() > 0) {
                                    builder.append(" ");
                                }
                                builder.append(htmlText.getText());
                            } else if (htmlTextList.get(j) instanceof HtmlLink) {
                                HtmlLink htmlLink = (HtmlLink) htmlTextList.get(j);
                                if (builder.toString().length() > 0) {
                                    builder.append(" ");
                                }
                                if (htmlLink.getTitle() != null && htmlLink.getTitle().size() > 0 && htmlLink.getTitle().get(0) instanceof HtmlText) {
                                    builder.append(((HtmlText) htmlLink.getTitle().get(0)).getText());
                                } else {
                                    builder.append(htmlLink.getHref());
                                }
                            } else if (htmlTextList.get(j) instanceof HtmlKnowledge) {
                                HtmlKnowledge htmlKnowledge = (HtmlKnowledge) htmlTextList.get(j);
                                if (builder.toString().length() > 0) {
                                    builder.append(" ");
                                }
                                if (htmlKnowledge.getTitle() != null && htmlKnowledge.getTitle().size() > 0 && htmlKnowledge.getTitle().get(0) instanceof HtmlText) {
                                    builder.append(((HtmlText) htmlKnowledge.getTitle().get(0)).getText());
                                } else {
                                    builder.append(htmlKnowledge.getDataFrontend());
                                }
                            }
                        }
                    }
                });
                window.show();
            }
        }
    }

    @Override
    public void reEditMessage(TextMessage message) {
        sessionFragment.reEditMessage(message);
    }

    @Override
    public void resendMessage(OnlineMessage message) {
        if (OnlineServiceClient.isConnected()) sessionFragment.getPresent().sendMessage(message);
    }

    @Override
    public void onImageMessageClick(ArrayList<String> messages, int index) {
        Intent intent = new Intent(sessionFragment.requireContext(), ImageAty.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ImageFragment.IMAGES, messages);
        bundle.putInt(ImageFragment.INDEX, index);
        intent.putExtras(bundle);

        sessionFragment.requireContext().startActivity(intent);
    }

    //发送卡片消息
    @Override
    public void onCardMessageClickSendOut(OnlineMessage info) {
        try {
            OnlineServiceMessage serviceMessage = info.getOnlineContent();
            sessionFragment.getPresent().sendCard(new JSONObject(serviceMessage.getContent()));
            sessionFragment.onMessageDelete(new ArrayList<OnlineMessage>(Arrays.asList(info)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //点击卡片消息
    @Override
    public void onCardMessageClick(View itemView, OnlineMessage message) {

    }

    @Override
    public void onLogisticsCardButtonClick(LogisticsCardInfo logisticsCardInfo) {
        if (logisticsCardInfo != null && TStringUtils.isNotEmpty(logisticsCardInfo.getOrderLink()))
            TCommonUtils.openUrlLink(sessionFragment.getActivity(), logisticsCardInfo.getOrderLink());
    }

    @Override
    public void onQuestionRequest(String info) {
        sessionFragment.getPresent().sendText(info);
    }

    @Override
    public void onQuestionRequest(String info, String knowledge) {
        sessionFragment.getPresent().sendText(info, knowledge);
    }

    @Override
    public void onLinkClick(String url) {
        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            sessionFragment.requireContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLinkClick(String content, String messageEventType) {

    }

    @Override
    public void onMiniProgramCardClick(ChatMiniProgramCardMessage miniProgramCardMessage) {
        if (TStringUtils.isNotEmpty(miniProgramCardMessage.getPagePath())) {
            TCommonUtils.openUrlLink(sessionFragment.getActivity(), miniProgramCardMessage.getPagePath());
        }
    }

    @Override
    public void videoPlay(String url) {
        try {
            Uri videoUri = Uri.parse(url);

            Intent intent = new Intent(sessionFragment.requireContext(), VideoPlayAty.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(VideoPlayFragment.VIDEO_URI, videoUri);
            intent.putExtras(bundle);

            sessionFragment.requireContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelQueue() {
        OnlineServiceClient.cancelQueue(null);
    }

    @Override
    public void downloadFile(String url, String name) {
        TLogUtils.d("------", "下载文件:" + url);

        try {
            File file = new File(url);
            if (file.exists()) {
                //本地文件不需要下载
                sessionFragment.showToast(sessionFragment.getString(R.string.ti_file_save_path, url), true);
                return;
            }
        } catch (Exception e) {
        }

        if (ContextCompat.checkSelfPermission(sessionFragment.requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(sessionFragment.requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            OnlineDownloadManager.download(sessionFragment.requireContext().getApplicationContext(), url, name);
        } else {
            //没权限
            sessionFragment.shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE);
            sessionFragment.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            sessionFragment.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, SessionFragment.REQUEST_FILE_PERMISSION);
        }
    }

    @Override
    public void onEvaluateInvestigationClick(ChatInvestigationMessage message) {
        //屏蔽收到满意度时显示弹窗
//        sessionFragment.investigation(message);
    }

    @Override
    public void onStartRequestPermissionsCallback(@NonNull String[] permissions, int requestCode) {

    }
}
