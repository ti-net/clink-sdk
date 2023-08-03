package com.tinet.threepart.tools;

import java.io.Closeable;
import java.io.IOException;

/**
 * @创建者
 * @描述 IO流工具类
 */
public class TIOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
            }
        }
        return true;
    }
}