package com.lcw.library.imagepicker.provider;

import android.content.Context;
import androidx.core.content.FileProvider;

/**
 * 自定义Provider，避免上层发生provider冲突
 * Date: 2019/1/24
 * Time: 4:03 PM
 */
public class ImagePickerProvider extends FileProvider {

    public static String getFileProviderName(Context context) {
        return context.getPackageName() + ".provider";
    }

}
