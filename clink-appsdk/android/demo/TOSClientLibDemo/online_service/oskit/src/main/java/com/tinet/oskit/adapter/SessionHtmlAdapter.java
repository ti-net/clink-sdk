package com.tinet.oskit.adapter;

import android.view.View;

import com.tinet.oskit.R;
import com.tinet.oskit.adapter.holder.HtmlContentAudioViewHolder;
import com.tinet.oskit.adapter.holder.HtmlContentFileViewHolder;
import com.tinet.oskit.adapter.holder.HtmlContentImageViewHolder;
import com.tinet.oskit.adapter.holder.HtmlContentOlViewHolder;
import com.tinet.oskit.adapter.holder.HtmlContentRichTextViewHolder;
import com.tinet.oskit.adapter.holder.HtmlContentVideoViewHolder;
import com.tinet.oskit.adapter.holder.HtmlContentViewHolder;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.spanhtml.bean.Html;
import com.tinet.spanhtml.bean.HtmlBr;
import com.tinet.spanhtml.bean.HtmlImage;
import com.tinet.spanhtml.bean.HtmlKnowledge;
import com.tinet.spanhtml.bean.HtmlLink;
import com.tinet.spanhtml.bean.HtmlOl;
import com.tinet.spanhtml.bean.HtmlTextList;
import com.tinet.spanhtml.bean.HtmlUl;
import com.tinet.spanhtml.bean.HtmlVideo;

/**
 * @ProjectName: TIMSDK
 * @ClassName: SessionHtmlAdapter
 * @Author: liuzr
 * @CreateDate: 2021-10-08 16:34
 * @Description:
 */
public class SessionHtmlAdapter extends TinetAdapter<Html, HtmlContentViewHolder> {

    private SessionClickListener listener;

    public SessionHtmlAdapter(SessionClickListener listener) {
        this.listener = listener;
    }


    public interface LongClickListener {
        void onLongClickListener();
    }

    private LongClickListener longClickListener;

    public void setLongClickListener(LongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public interface ClickListener {
        void onClickListener();
    }

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    protected HtmlContentViewHolder viewHolder(final View itemView, int viewType) {
        HtmlContentViewHolder viewHolder = null;

        if (viewType == R.layout.frg_session_receive_html_text) {
            viewHolder = new HtmlContentRichTextViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_receive_html_video) {
            viewHolder = new HtmlContentVideoViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_receive_html_file) {
            viewHolder = new HtmlContentFileViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_receive_html_audio) {
            viewHolder = new HtmlContentAudioViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_receive_html_image) {
            viewHolder = new HtmlContentImageViewHolder(itemView, listener);
        } else if (viewType == R.layout.frg_session_receive_html_ol) {
            viewHolder = new HtmlContentOlViewHolder(itemView, listener);
        }else if(viewType == R.layout.frg_session_html_empty){
            viewHolder = new HtmlContentViewHolder(itemView, listener);
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
                if (clickListener != null) {
                    clickListener.onClickListener();
                }
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {

        int type = R.layout.frg_session_receive_html_text;
        Html htmlContent = getItem(position);
        if (htmlContent instanceof HtmlTextList || htmlContent instanceof HtmlLink || htmlContent instanceof HtmlKnowledge) {
            type = R.layout.frg_session_receive_html_text;
        } else if (htmlContent instanceof HtmlImage) {
            type = R.layout.frg_session_receive_html_image;
        } else if (htmlContent instanceof HtmlVideo) {
            type = R.layout.frg_session_receive_html_video;
        } else if (htmlContent instanceof HtmlOl || htmlContent instanceof HtmlUl) {
            type = R.layout.frg_session_receive_html_ol;
        }else if(htmlContent instanceof HtmlBr){
            type = R.layout.frg_session_html_empty;
        }

        return type;
    }
}
