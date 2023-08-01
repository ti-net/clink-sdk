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

package com.org.gzuliyujiang.filepicker.sort;

import java.io.File;
import java.util.Comparator;

/**
 * @author 贵州山野羡民（1032694760@qq.com）
 * @since 2014/4/18
 */
public class SortByName implements Comparator<File> {
    private static final boolean CASE_SENSITIVE = false;

    @Override
    public int compare(File f1, File f2) {
        if (f1 == null || f2 == null) {
            if (f1 == null) {
                return -1;
            } else {
                return 1;
            }
        } else {
            if (f1.isDirectory() && f2.isFile()) {
                return -1;
            } else if (f1.isFile() && f2.isDirectory()) {
                return 1;
            } else {
                String s1 = f1.getName();
                String s2 = f2.getName();
                if (CASE_SENSITIVE) {
                    return s1.compareTo(s2);
                } else {
                    return s1.compareToIgnoreCase(s2);
                }
            }
        }
    }

}
