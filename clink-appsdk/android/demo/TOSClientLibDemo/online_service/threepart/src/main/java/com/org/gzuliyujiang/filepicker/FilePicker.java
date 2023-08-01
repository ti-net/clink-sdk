/*
 * Copyright (c) 2016-present 贵州纳雍穿青人李裕江<1032694760@qq.com>
 *
 * The software is licensed under the Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *     http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package com.org.gzuliyujiang.filepicker;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.recyclerview.widget.RecyclerView;

import com.org.gzuliyujiang.dialog.DialogLog;
import com.org.gzuliyujiang.dialog.ModalDialog;
import com.org.gzuliyujiang.filepicker.annotation.ExplorerMode;
import com.org.gzuliyujiang.filepicker.contract.OnFileClickedListener;
import com.org.gzuliyujiang.filepicker.contract.OnFilePickedListener;

import java.io.File;

/**
 * 文件目录选择器
 *
 * @author 贵州山野羡民（1032694760@qq.com）
 * @since 2015/9/29
 */
@SuppressWarnings("unused")
public class FilePicker extends ModalDialog {
    private int explorerMode = ExplorerMode.FILE;
    private File initDir;
    private FileExplorer fileExplorer;
    private OnFilePickedListener onFilePickedListener;
    private boolean initialized = false;

    public FilePicker(Activity activity) {
        super(activity);
    }

    public FilePicker(@NonNull Activity activity, @StyleRes int themeResId) {
        super(activity, themeResId);
    }

    @NonNull
    @Override
    protected View createBodyView() {
        fileExplorer = new FileExplorer(activity);
        return fileExplorer;
    }

    @Override
    protected void initView() {
        super.initView();
        setHeight((int) (activity.getResources().getDisplayMetrics().heightPixels * 0.6f));
        if (explorerMode == ExplorerMode.FILE) {
            okView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        initialized = true;
        setInitDir(explorerMode, initDir);
    }

    @Override
    protected void onCancel() {

    }

    @Override
    protected void onOk() {
        File currentFile = fileExplorer.getCurrentFile();
        DialogLog.print("picked directory: " + currentFile);
        if (onFilePickedListener != null) {
            onFilePickedListener.onFilePicked(currentFile);
        }
    }

    public void setInitDir(@ExplorerMode int explorerMode, File initDir) {
        this.explorerMode = explorerMode;
        this.initDir = initDir;
        if (initialized) {
            fileExplorer.setInitDir(explorerMode, initDir);
        }
    }

    public void setOnFilePickedListener(OnFilePickedListener listener) {
        this.onFilePickedListener = listener;
        fileExplorer.setOnFileClickedListener(new OnFileClickedListener() {
            @Override
            public void onFileClicked(@NonNull File file) {
                if (explorerMode == ExplorerMode.FILE) {
                    dismiss();
                    onFilePickedListener.onFilePicked(file);
                }
            }
        });
    }

    public final File getCurrentFile() {
        return fileExplorer.getCurrentFile();
    }

    public final FileExplorer getFileExplorer() {
        return fileExplorer;
    }

    public final RecyclerView getFileListView() {
        return fileExplorer.getFileListView();
    }

    public final TextView getEmptyHintView() {
        return fileExplorer.getEmptyHintView();
    }

    public final RecyclerView getPathListView() {
        return fileExplorer.getPathListView();
    }

}
