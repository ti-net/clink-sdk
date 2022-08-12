package com.tinet.tosclientkitdemo.common.adapter;

/**
 * @ProjectName: TOSClientKitDemo
 * @ClassName: CommonAdapter
 * @Author: zhangping
 * @CreateDate: 2022/7/4 14:18
 * @Description: 描述说明
 */

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

abstract public class CommonAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;


    private List<T> mDataList = new ArrayList<>();//数据集合
    private SparseArrayCompat<Integer> mItemTypes = new SparseArrayCompat();    //类型集合


    //头集合 尾结合
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    public CommonAdapter(List<T> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        //按照TYPE类型分类
        for (int i = 0; i < mDataList.size(); i++) {
            int type = getItemType(mDataList.get(i));
            mItemTypes.put(i, type);
        }
    }

    public void setData(List<T> dataList){
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }


    /**
     * 添加头部方法
     *
     * @param view
     */
    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    /**
     * 添加尾部方法
     *
     * @param view
     */
    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mHeaderViews.size()) {
            return mHeaderViews.keyAt(position);
        } else if (position >= mHeaderViews.size() + mDataList.size()) {
            return mFootViews.keyAt(position - mHeaderViews.size() + mDataList.size());
        }
        return mItemTypes.keyAt(position - mHeaderViews.size());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return BaseViewHolder.getInstance(mHeaderViews.get(viewType));
        } else if (mFootViews.get(viewType) != null) {
            return BaseViewHolder.getInstance(mFootViews.get(viewType));
        } else if (mItemTypes.get(viewType) != null) {
            int viewId = mItemTypes.get(viewType);
            Context context = viewGroup.getContext();
            View view = LayoutInflater.from(context).inflate(viewId, viewGroup, false);
            return BaseViewHolder.getInstance(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);

        if (mHeaderViews.get(viewType) != null) {
            // convert(viewHolder,mHeaderViews.get(viewType).getId(),mDataList.get(position));
        } else if (mFootViews.get(viewType) != null) {
            // convert(viewHolder,mFootViews.get(viewType).getId(),mDataList.get(position));
        } else if (mItemTypes.get(viewType) != null) {
            convert(viewHolder, mItemTypes.get(viewType), mDataList.get(position - mHeaderViews.size()), position);
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mDataList.size() + mFootViews.size();
    }

    /**
     * 设置类型
     *
     * @param item 数据
     * @return 返回的布局类型
     */
    protected abstract int getItemType(T item);//设置类型

    protected abstract void convert(BaseViewHolder viewHolder, int layoutId, T item, int position);


}