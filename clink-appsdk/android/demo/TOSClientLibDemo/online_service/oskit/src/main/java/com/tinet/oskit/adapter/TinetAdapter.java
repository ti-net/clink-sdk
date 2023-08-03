package com.tinet.oskit.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tinet.oskit.adapter.holder.TinetViewHolder;

import com.tinet.oslib.model.message.OnlineMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetAdapter
 * @Author: liuzr
 * @CreateDate: 2021-08-20 11:21
 * @Description: 数据适配器基类
 */
public abstract class TinetAdapter<T, VH extends TinetViewHolder<T>> extends RecyclerView.Adapter<VH> {

    public interface TinetAdapterDataListener{

        void onDataChanged();

    }

    private TinetAdapterDataListener listener;

    public void setListener(TinetAdapterDataListener listener) {
        this.listener = listener;
    }

    /**
     * 数据集
     */
    private List<T> mData = new ArrayList<>();

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return viewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (null != holder) {
            holder.update(getItem(position));
            holder.update(getItem(position),position);
        }
    }

    protected abstract VH viewHolder(View itemView, int viewType);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取指定索引位置的数据模型
     */
    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * 获取数据集合
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * 在集合头部添加新的数据集合
     */
    public void insertData(List<T> data) {
        if (data == null) {
            this.mData.clear();
            notifyDataSetChanged();
        } else {
            mData.addAll(0, data);
            notifyItemRangeInserted(0, data.size());
        }

        updateData();
    }

    public void notifyItemChanged(T obj){
        if(null == getData() || obj == null){
            return;
        }

        int index = getData().indexOf(obj);
        if(index < 0 || index >= getData().size()){
            return;
        }

        notifyItemChanged(index);

        updateData();
    }

    /**
     * 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合）
     */
    public void appendData(List<T> data) {
        if (data == null || data.size() == 0) {
            return;
        }

        int originCount = getItemCount();
        mData.addAll(data);
        notifyItemRangeInserted(originCount, getItemCount() - 1);

        updateData();
    }

    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据列表）
     */
    public void setData(List<T> data) {
        mData.clear();
        if (null != data && data.size() > 0) {
            mData.addAll(data);
        }

        notifyDataSetChanged();

        updateData();
    }

    /**
     * 清空数据列表
     */
    public void clearData() {
        setData(null);
        updateData();
    }

    /**
     * 删除指定索引数据条目
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        updateData();
    }

    /**
     * 删除指定数据条目
     */
    public void removeItem(T model) {
        int index = mData.indexOf(model);
        if (index < 0 || index > getItemCount()) {
            return;
        }

        removeItem(index);
    }

    /**
     * 在指定位置添加数据条目
     */
    public void addItem(int position, T model) {
        if (position < 0 || position > getItemCount()) {
            return;
        }

        mData.add(position, model);
        notifyItemInserted(position);
        updateData();
    }

    /**
     * 在集合头部添加数据条目
     */
    public void addFirstItem(T model) {
        addItem(0, model);
    }

    /**
     * 在集合末尾添加数据条目
     */
    public void addLastItem(T model) {
        addItem(mData.size(), model);
    }

    /**
     * 替换指定索引的数据条目
     */
    public void setItem(int position, T newModel) {
        mData.set(position, newModel);
        notifyItemChanged(position);

        updateData();
    }

    /**
     * 替换指定数据条目
     */
    public void setItem(T oldModel, T newModel) {
        setItem(mData.indexOf(oldModel), newModel);
    }

    /**
     * 移动数据条目的位置
     */
    public void moveItem(int fromPosition, int toPosition) {
        T item = getItem(fromPosition);
        removeItem(item);
        addItem(toPosition, item);
    }

    /**
     * 获取第一个数据模型
     */
    public T firstItem() {
        return getItemCount() > 0 ? getItem(0) : null;
    }

    /**
     * 得到最后一个数据模型
     */
    public T lastItem() {
        return getItemCount() > 0 ? getItem(getItemCount() - 1) : null;
    }

    private void updateData(){
        if(null != listener){
            new Handler().postDelayed(() -> listener.onDataChanged(), 500);

        }
    }
}
