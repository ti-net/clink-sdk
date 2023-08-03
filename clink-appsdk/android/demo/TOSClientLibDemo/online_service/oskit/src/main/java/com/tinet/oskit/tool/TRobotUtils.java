package com.tinet.oskit.tool;

import com.tinet.oskit.adapter.RobotQuestionExpandableAdapter;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.bean.OnlineKnowledgeItem;
import com.tinet.timclientlib.utils.TStringUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * @ProjectName: OnlineSDK
 * @ClassName: TRobotUtils
 * @Author: zhangping
 * @CreateDate: 2022/12/27 18:47
 * @Description: 描述说明
 */
public class TRobotUtils {

    /**
     * 获取对应回传knowledge json字符串 (通过分页及下标获取)
     *
     * @param knowledgeList
     * @param listener
     * @param childName
     * @param currentPage
     * @param childPosition
     */
    public static void detailTransferToKnowledge(List<OnlineKnowledgeItem> knowledgeList, SessionClickListener listener, String childName, int currentPage, int childPosition) {
        if (TStringUtils.isNotEmpty(childName) && knowledgeList != null && knowledgeList.size() > 0) {
            // : 2022/12/26 处理id回传
            String knowledgeStr = "";
            int knowledgeIndex = currentPage * RobotQuestionExpandableAdapter.CHANGE_LIMIT + childPosition;
            if (knowledgeList.size() > knowledgeIndex) {
                OnlineKnowledgeItem onlineKnowledgeItem = knowledgeList.get(knowledgeIndex);
                knowledgeStr = new JSONObject(TStringUtils.getObjectToMapObject(onlineKnowledgeItem)).toString();
            }
            if (TStringUtils.isNotEmpty(knowledgeStr)) {
                listener.onQuestionRequest(childName, knowledgeStr);
            } else {
                listener.onQuestionRequest(childName);
            }
        } else {
            listener.onQuestionRequest(childName);
        }
    }

    /**
     * 获取对应回传knowledge json字符串 (通过字符串比较)
     *
     * @param knowledgeList
     * @param listener
     * @param childName
     */
    public static void detailTransferToKnowledge(List<OnlineKnowledgeItem> knowledgeList, SessionClickListener listener, String childName) {
        if (TStringUtils.isNotEmpty(childName) && knowledgeList != null && knowledgeList.size() > 0) {
            // : 2022/12/26 处理id回传
            String knowledgeStr = "";
            for (int i = 0; i < knowledgeList.size(); i++) {
                OnlineKnowledgeItem onlineKnowledgeItem = knowledgeList.get(i);
                if (onlineKnowledgeItem.getContent().equals(childName)) {
                    knowledgeStr = new JSONObject(TStringUtils.getObjectToMapObject(onlineKnowledgeItem)).toString();
                }
            }
            if (TStringUtils.isNotEmpty(knowledgeStr)) {
                listener.onQuestionRequest(childName, knowledgeStr);
            } else {
                listener.onQuestionRequest(childName);
            }
        } else {
            listener.onQuestionRequest(childName);
        }
    }
}
