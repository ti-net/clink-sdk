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

package com.org.gzuliyujiang.filepicker.contract;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.org.gzuliyujiang.filepicker.adapter.ViewHolder;

/**
 * @author 贵州山野羡民（1032694760@qq.com）
 * @since 2021/6/10 20:15
 */
public interface OnPathClickedListener {

    void onPathClicked(RecyclerView.Adapter<ViewHolder> adapter, int position, @NonNull String path);

}
