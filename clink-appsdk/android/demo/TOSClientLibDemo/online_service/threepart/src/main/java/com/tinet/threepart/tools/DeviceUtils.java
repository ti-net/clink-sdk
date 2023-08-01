package com.tinet.threepart.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * @ProjectName: TIMSDK
 * @ClassName: DeviceUtils
 * @Author: zhangping
 * @CreateDate: 2020/7/6 17:39
 * @Description: 描述说明
 */
public class DeviceUtils {
    public DeviceUtils() {
    }

    public static String getDeviceId(Context context, String appKey) {
        SharedPreferences sp = context.getSharedPreferences("Statistics", 0);
        String deviceId = sp.getString("deviceId", "");
        if (TextUtils.isEmpty(deviceId)) {
            String[] params = new String[]{getDeviceId(context), appKey, context.getPackageName()};
            deviceId = ShortMD5(params);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("deviceId", deviceId);
            editor.commit();
        }

        return deviceId;
    }

    public static String getDeviceId(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Statistics", 0);
        String deviceId = sp.getString("ANDROID_ID", "");
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "android_id");
            if (TextUtils.isEmpty(deviceId)) {
                SecureRandom random = new SecureRandom();
                deviceId = (new BigInteger(64, random)).toString(16);
            }

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("ANDROID_ID", deviceId);
            editor.commit();
        }

        return deviceId;
    }

    public static String ShortMD5(String... args) {
        try {
            StringBuilder builder = new StringBuilder();
            String[] var2 = args;
            int var3 = args.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String arg = var2[var4];
                builder.append(arg);
            }

            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(builder.toString().getBytes());
            byte[] mds = mdInst.digest();
            mds = Base64.encode(mds, 0);
            String result = new String(mds);
            result = result.replace("=", "").replace("+", "-").replace("/", "_").replace("\n", "");
            return result;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String getDeviceManufacturer() {
        String manufacturer = Build.MANUFACTURER.replace("-", "_");
        long outTime = 3000L;
        if (!TextUtils.isEmpty(manufacturer)) {
            if ("vivo".equals(manufacturer)) {
                manufacturer = manufacturer.toUpperCase();
            }

            return manufacturer;
        } else {
            Runtime runtime = Runtime.getRuntime();
            String line = "";
            Worker worker = new Worker(runtime);
            long startTime = System.currentTimeMillis();
            worker.start();

            try {
                worker.join(outTime);
                line = worker.line;
            } catch (InterruptedException var12) {
                worker.interrupt();
            } finally {
                worker.interrupt();
            }

            long endTime = System.currentTimeMillis();
            if (endTime - startTime >= outTime) {
                Log.e("DeviceUtils", "getDeviceManufacturer====OutTime");
            }

            return !TextUtils.isEmpty(line) ? "Xiaomi" : "";
        }
    }

    private static class Worker extends Thread {
        String propName;
        Runtime runtime;
        BufferedReader input;
        String line;
        Process process;

        private Worker(Runtime runtime) {
            this.propName = "ro.miui.ui.version.name";
            this.input = null;
            this.runtime = runtime;
        }

        public void run() {
            try {
                this.process = this.runtime.exec("getprop " + this.propName);
                this.input = new BufferedReader(new InputStreamReader(this.process.getInputStream()), 1024);
                this.line = this.input.readLine();
                this.input.close();
            } catch (IOException var10) {
                Log.e("DeviceUtils", "Unable to read sysprop ");
            } finally {
                if (this.input != null) {
                    try {
                        this.input.close();
                    } catch (IOException var9) {
                        var9.printStackTrace();
                    }
                }

                if (this.process != null) {
                    this.process.destroy();
                }

            }

        }
    }
}
