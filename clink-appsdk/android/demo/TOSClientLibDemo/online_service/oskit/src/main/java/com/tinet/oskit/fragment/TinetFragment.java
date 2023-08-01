package com.tinet.oskit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tinet.timclientlib.utils.TToastUtils;
import com.tinet.oskit.view.TinetView;

/**
 * @ProjectName: TIMSDK
 * @ClassName: TinetFragment
 * @Author: liuzr
 * @CreateDate: 2021-08-20 13:23
 * @Description: fragment基类
 */
public abstract class TinetFragment extends Fragment implements TinetView {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutId(), container, false);
    }

    /**
     * 界面布局ID
     */
    protected abstract @LayoutRes
    int layoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    protected void initView() {
    }

    @Override
    public void showToast(int stringID, boolean isShort) {
        showToast(getString(stringID), isShort);
    }

    @Override
    public void showToast(String message, boolean isShort) {
        try {
            if (isShort) {
                TToastUtils.showShortToast(requireContext(), message);
            } else {
                TToastUtils.showLongToast(requireContext(), message);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void showToast(int stringID, boolean isShort, Object... message) {
        showToast(getString(stringID, message), isShort);
    }
}
