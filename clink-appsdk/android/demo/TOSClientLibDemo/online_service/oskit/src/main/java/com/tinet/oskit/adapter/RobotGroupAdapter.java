package com.tinet.oskit.adapter;

import android.view.View;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.RobotGroupBaseViewHolder;
import com.tinet.oskit.adapter.holder.RobotGroupFileViewHolder;
import com.tinet.oskit.adapter.holder.RobotGroupImageViewHolder;
import com.tinet.oskit.adapter.holder.RobotGroupQuestionClassicViewHolder;
import com.tinet.oskit.adapter.holder.RobotGroupQuestionVerticalViewHolder;
import com.tinet.oskit.adapter.holder.RobotGroupVideoViewHolder;
import com.tinet.oskit.adapter.holder.RobotGroupViewHolder;
import com.tinet.oskit.adapter.holder.RobotGroupVoiceViewHolder;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oskit.tool.HtmlHelper;
import com.tinet.oslib.common.OnlineMessageType;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.message.OnlineMessage;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupAdapter
 * @Author: liuzr
 * @CreateDate: 2021-08-30 17:11
 * @Description: 机器人组合消息项
 */
public class RobotGroupAdapter extends TinetAdapter<OnlineQuestion, RobotGroupBaseViewHolder> {

    private SessionClickListener listener;
    private OnlineMessage message;

    public void setMessage(OnlineMessage message) {
        this.message = message;
    }

    public RobotGroupAdapter(SessionClickListener listener) {
        this.listener = listener;
    }

    private SessionHtmlAdapter.LongClickListener longClickListener;

    public void setLongClickListener(SessionHtmlAdapter.LongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public interface ClickListener {
        void onClickListener();
    }

    private SessionHtmlAdapter.ClickListener clickListener;

    public void setClickListener(SessionHtmlAdapter.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    protected RobotGroupBaseViewHolder viewHolder(View itemView, int viewType) {
        RobotGroupBaseViewHolder viewHolder = null;
        if (viewType == R.layout.frg_session_robot_group_image) {
            viewHolder = new RobotGroupImageViewHolder(itemView, message, listener);
        } else if (viewType == R.layout.frg_session_robot_group_file) {
            viewHolder = new RobotGroupFileViewHolder(itemView, message, listener);
        } else if (viewType == R.layout.frg_session_robot_group_video) {
            viewHolder = new RobotGroupVideoViewHolder(itemView, message, listener);
        } else if (viewType == R.layout.frg_session_robot_group_voice) {
            viewHolder = new RobotGroupVoiceViewHolder(itemView, message, listener);
        } else if (viewType == R.layout.frg_session_robot_group_question_classic) {
            viewHolder = new RobotGroupQuestionClassicViewHolder(itemView, message, listener);
        } else if (viewType == R.layout.frg_session_robot_group_question_vertical) {
            viewHolder = new RobotGroupQuestionVerticalViewHolder(itemView, message, listener);
        } else {
            viewHolder = new RobotGroupViewHolder(itemView, message, listener);
        }
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onLongClickListener();
                }
                return false;
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener!=null){
                    clickListener.onClickListener();
                }
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        OnlineQuestion question = getItem(position);

        int type = R.layout.frg_session_robot_group_text;
        switch (question.getType()) {
            case OnlineMessageType.IMAGE: {
                type = R.layout.frg_session_robot_group_image;
                break;
            }
            case OnlineMessageType.FILE: {
                type = R.layout.frg_session_robot_group_file;
                break;
            }
            case OnlineMessageType.VIDEO: {
                type = R.layout.frg_session_robot_group_video;
                break;
            }
            case OnlineMessageType.ROBOT_HTML: {
                String text = question.getText();
                if (HtmlHelper.hasHTMLTags(text)) {
                    type = R.layout.frg_session_robot_group_html;
                } else {
                    type = R.layout.frg_session_robot_group_text;
                }
                break;
            }
            case OnlineMessageType.ROBOT_CHOOSE:
            case OnlineMessageType.ROBOT_ABOUT_QUESTION:
            case OnlineMessageType.ROBOT_GUESS_QUESTION:
            case OnlineMessageType.ROBOT_COMMON_QUESTION:
            case OnlineMessageType.ROBOT_APPROX_QUESTION:
            case OnlineMessageType.ROBOT_COMMENT_QUESTION:
                type = R.layout.frg_session_robot_group_questions;
                break;
            case OnlineMessageType.VOICE:
                type = R.layout.frg_session_robot_group_voice;
                break;
            case OnlineMessageType.ROBOT_COMMON_QUESTION_CLASSIC:
                type = R.layout.frg_session_robot_group_question_classic;
                break;
            case OnlineMessageType.ROBOT_COMMON_QUESTION_VERTICAL:
                type = R.layout.frg_session_robot_group_question_vertical;
                break;
            default: {
                String text = question.getText();
                if (HtmlHelper.hasHTMLTags(text)) {
                    type = R.layout.frg_session_robot_group_html;
                } else {
                    type = R.layout.frg_session_robot_group_text;
                }
                break;
            }
        }

        return type;
    }
}
