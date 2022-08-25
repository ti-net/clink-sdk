package com.tinet.tosclientkitdemo.ui.activity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.tinet.tosclientkitdemo.R;
import com.tinet.tosclientkitdemo.bean.ThemeItemBean;
import com.tinet.tosclientkitdemo.bean.ThemePropertyBean;
import com.tinet.tosclientkitdemo.common.adapter.BaseViewHolder;
import com.tinet.tosclientkitdemo.common.adapter.CommonAdapter;
import com.tinet.tosclientkitdemo.common.app.App;
import com.tinet.tosclientkitdemo.common.base.BaseActivity;
import com.tinet.tosclientkitdemo.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ThemeListActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView rvThemeList;
    private List<ThemeItemBean> mThemeList;
    private int mCurrentChooseIndex;

    @Override
    protected int getLayoutId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_theme_list;
    }

    @Override
    protected void initView() {
        ((TextView) findViewById(R.id.toolbar_title)).setText("聊天窗口UI样式");
        findViewById(R.id.toolbar_back).setOnClickListener(this);
        findViewById(R.id.tv_save_choose).setOnClickListener(this);

        rvThemeList = findViewById(R.id.rv_theme_list);
        rvThemeList.setLayoutManager(new LinearLayoutManager(ThemeListActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        initThemeData();
        CommonAdapter<ThemeItemBean> commonAdapter = new CommonAdapter<ThemeItemBean>(mThemeList) {
            @Override
            protected int getItemType(ThemeItemBean item) {
                return R.layout.item_rv_theme_list;
            }

            @Override
            protected void convert(BaseViewHolder viewHolder, int layoutId, ThemeItemBean item, int position) {
                viewHolder.setText(R.id.tv_theme_title, item.getThemeName());
                viewHolder.setSrc(R.id.iv_theme_preview_img, item.getPreviewImg());
                if (mCurrentChooseIndex == position) {
                    viewHolder.setSrc(R.id.iv_choose_tag_icon, R.mipmap.choose_active);
                } else {
                    viewHolder.setSrc(R.id.iv_choose_tag_icon, R.mipmap.choose_grey);
                }

                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mCurrentChooseIndex != position) {
                            mCurrentChooseIndex = position;
                            notifyDataSetChanged();
                        }
                    }
                };
                viewHolder.getView(R.id.iv_choose_tag_icon).setOnClickListener(onClickListener);
                viewHolder.getView(R.id.tv_theme_title).setOnClickListener(onClickListener);


                viewHolder.getView(R.id.tv_show_theme_detail).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ThemeListActivity.this, ThemeDetailActivity.class);
                        intent.putExtra("ThemeItemBean", (Serializable) item);
                        startActivity(intent);
                    }
                });
            }
        };
        rvThemeList.setAdapter(commonAdapter);
    }

    private void initThemeData() {

        mCurrentChooseIndex = Integer.valueOf(App.CHOOSE_THEME_INDEX);

        if (mThemeList == null) {
            mThemeList = new ArrayList<>();
        } else {
            mThemeList.clear();
        }

        mThemeList.add(new ThemeItemBean(
                "经典样式",
                R.mipmap.theme_default,
                new ThemePropertyBean("#EFF1F5", 10, "#D5E1F7", 5, "#FFFFFF", 5)));

        mThemeList.add(new ThemeItemBean(
                "蓝色商务风",
                R.mipmap.theme_blue,
                new ThemePropertyBean("#EFF3F5", 4, "#CBF3FF", 2, "#FFFFFF", 2)));

        mThemeList.add(new ThemeItemBean(
                "黄色圆润风",
                R.mipmap.theme_yellow,
                new ThemePropertyBean("#FBF9ED", 10, "#FFF5C0", 10, "#FFFFFF", 10)));

        mThemeList.add(new ThemeItemBean(
                "红色绅士风",
                R.mipmap.theme_red,
                new ThemePropertyBean("#F9F3F2", 5, "#D5E1F7", 4, "#FFFFFF", 4)));

        mThemeList.add(new ThemeItemBean(
                "绿色田园风",
                R.mipmap.theme_green,
                new ThemePropertyBean("#EFF5F1", 8, "#BEF6CD", 6, "#FFFFFF", 6)));

    }

    private void saveChoose() {
        App.CHOOSE_THEME_INDEX = mCurrentChooseIndex;
        ToastUtils.showToast(getApplicationContext(),"已保存");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        },1000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                onBackPressed();
                break;
            case R.id.tv_save_choose:
                saveChoose();
                break;
        }
    }

}