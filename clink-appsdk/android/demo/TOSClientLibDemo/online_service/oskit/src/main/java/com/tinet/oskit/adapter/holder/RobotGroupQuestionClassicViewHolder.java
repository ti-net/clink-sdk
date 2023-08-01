package com.tinet.oskit.adapter.holder;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import com.tinet.oskit.R;
import com.tinet.oskit.adapter.QuestionAdapter;
import com.tinet.oskit.adapter.decoration.LinearLayoutManagerDecoration;
import com.tinet.oskit.listener.SessionClickListener;
import com.tinet.oslib.model.bean.OnlineKnowledgeItem;
import com.tinet.oslib.model.bean.OnlineQuestion;
import com.tinet.oslib.model.bean.OnlineQuestionData;
import com.tinet.oslib.model.message.OnlineMessage;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.material.tabs.TabLayout.MODE_FIXED;
import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

/**
 * @ProjectName: TIMSDK
 * @ClassName: RobotGroupImageViewHolder
 * @Author: liuzr
 * @CreateDate: 2021-09-09 16:18
 * @Description: 机器人组合消息 -- 分类
 */
public class RobotGroupQuestionClassicViewHolder extends RobotGroupBaseViewHolder {

    private TabLayout tabLayout;
    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private TextView tvGuess, tvChanged;

    /**
     * 一页显示个数
     */
    public static int LIMIT = 5;

    public RobotGroupQuestionClassicViewHolder(@NonNull View itemView, OnlineMessage message,
                                               SessionClickListener listener) {
        super(itemView, message, listener);
        tvGuess = itemView.findViewById(R.id.tvGuess);
        tvChanged = itemView.findViewById(R.id.tvChanged);
        tabLayout = itemView.findViewById(R.id.tabLayout);
        recyclerView = itemView.findViewById(R.id.recyclerView);
        clearItemDecoration(recyclerView);
        recyclerView.addItemDecoration(new LinearLayoutManagerDecoration(itemView.getContext(), itemView.getContext().getResources().getDimensionPixelSize(R.dimen.ti_question_span), Color.TRANSPARENT));
        adapter = new QuestionAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    private void clearItemDecoration(RecyclerView recyclerView){
        while (recyclerView.getItemDecorationCount() > 0){
            recyclerView.removeItemDecorationAt(0);
        }
    }

    private class ChangeTabLayoutListener implements TabLayout.OnTabSelectedListener {


        /**
         * 当前页数据
         */
        class CurrentPage {

            /**
             * 起始索引
             */
            int start;
            /**
             * 最后位置
             */
            int end;

            public CurrentPage(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        //最多展示5条数据，超过5条则自动分页
        private SparseArray<CurrentPage> index = new SparseArray();

        /**
         * 当前选中tab
         */
        private int currentTabIndex = 0;
        /**
         * 当前选中tab的数据
         */
        private List<String> topics = null;

        List<OnlineKnowledgeItem> knowledgeList;

        @Override
        public void onTabSelected(Tab tab) {
            currentTabIndex = tab.getPosition();
            topics = (List<String>) ((OnlineQuestionData) tab.getTag()).getTopics();
            knowledgeList = (List<OnlineKnowledgeItem>) ((OnlineQuestionData) tab.getTag()).getKnowledgeList();
            exchange(false);
        }

        @Override
        public void onTabUnselected(Tab tab) {

        }

        @Override
        public void onTabReselected(Tab tab) {

        }

        /**
         * 换一批
         *
         * @param change 是否需要换
         */
        public void exchange(boolean change) {
            List<String> data = new ArrayList<>();
            if (topics.size() > 0) {
                CurrentPage currentPage = index.get(currentTabIndex, new CurrentPage(0,
                        topics != null ? (topics.size() > LIMIT ? LIMIT : topics.size()) : 0));
                int start = currentPage.start;
                int end = currentPage.end;

                if (change) {
                    start = end;
                    end = end + LIMIT;
                }

                if (start >= topics.size()) {
                    //没有更多了，再次从第一页开始展示
                    start = 0;
                    end = start + LIMIT;
                }

                if (end > topics.size()) {
                    end = topics.size();
                }

                for (int i = start; i < end; i++) {
                    data.add(topics.get(i));
                }

                index.put(currentTabIndex, new CurrentPage(start, end));
            }

            adapter.setData(data);
            adapter.setKnowledgeList(knowledgeList);
        }
    }

    private ChangeTabLayoutListener tabListener = new ChangeTabLayoutListener();

    @Override
    public void update(OnlineQuestion info) {
        super.update(info);

        tvChanged.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //换一批
                tabListener.exchange(true);
            }
        });

        tvGuess.setText((!TextUtils.isEmpty(info.getText()) && info.getText() != "null") ? info.getText() : "猜你想问");

        tvChanged.setVisibility(View.GONE);

        tabLayout.removeAllTabs();
        tabLayout.removeOnTabSelectedListener(tabListener);
        if (null == info.getData() || info.getData().size() == 0) {
            tabLayout.setVisibility(View.GONE);
        } else {
            tabLayout.setVisibility(View.VISIBLE);
            for (int i = 0; i < info.getData().size(); i++) {
                OnlineQuestionData data = info.getData().get(i);
                if (i == 0 && data.getTopics() != null && data.getTopics().size() > RobotGroupQuestionClassicViewHolder.LIMIT) {
                    tvChanged.setVisibility(View.VISIBLE);
                }

                TabLayout.Tab tab = tabLayout.newTab();
                tab.setTag(data);
                tab.setText(data.getName() == null ? "" : data.getName());
                tabLayout.addTab(tab);

                if (tabLayout.getTabCount() == 1) {
                    tab.select();
                    tabListener.onTabSelected(tab);
                }
            }

            tabLayout.setTabMode(MODE_SCROLLABLE);

            tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
                @Override
                public void onTabSelected(Tab tab) {
                    if (null != tabListener) {
                        tabListener.onTabSelected(tab);
                    }

                    List<String> topics = (List<String>) ((OnlineQuestionData) tab.getTag()).getTopics();
                    if (topics == null || topics.size() <= RobotGroupQuestionClassicViewHolder.LIMIT) {
                        tvChanged.setVisibility(View.GONE);
                    } else {
                        tvChanged.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onTabUnselected(Tab tab) {
                    if (null != tabListener) {
                        tabListener.onTabUnselected(tab);
                    }
                }

                @Override
                public void onTabReselected(Tab tab) {
                    if (null != tabListener) {
                        tabListener.onTabReselected(tab);
                    }
                }
            });

            recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });

        }

    }
}
