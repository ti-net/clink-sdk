package com.tinet.tosclientkitdemo.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.IntDef;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public final class TLogUtils {

    private TLogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static final int V = 0x01;
    public static final int D = 0x02;
    public static final int I = 0x04;
    public static final int W = 0x08;
    public static final int E = 0x10;
    public static final int A = 0x20;

    @IntDef({V, D, I, W, E, A})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    private static final int FILE = 0xF1;
    private static final int JSON = 0xF2;
    private static final int XML = 0xF4;

    private static String dir;                      // log存储目录
    private static boolean sLogSwitch = false; // log总开关
    private static String sGlobalTag = "---Tinet---"; // log标签
    private static boolean sTagIsSpace = true; // log标签是否为空白
    private static boolean sLog2FileSwitch = false;// log写入文件开关
    private static boolean sLogBorderSwitch = true; // log边框开关
    private static int sLogFilter = V;    // log过滤器

    private static final String TOP_BORDER = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final String LEFT_BORDER = "║ ";
    private static final String BOTTOM_BORDER = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final int MAX_LEN = 4000;
    private static final String NULL_TIPS = "Log with null object.";
    private static final String NULL = "null";
    private static final String ARGS = "args";


    public static class Builder {

        private Context mContext;

        public Builder() {

        }

        /**
         * 设置context 才能进行存储
         *
         * @param context
         * @return
         */
        public Builder setContext(Context context) {
            this.mContext = context;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                dir = mContext.getExternalCacheDir() + File.separator + "log" + File.separator;
            } else {
                dir = mContext.getCacheDir() + File.separator + "log" + File.separator;
            }
            return this;
        }

        public Builder setGlobalTag(String tag) {
            if (!isSpace(tag)) {
                TLogUtils.sGlobalTag = tag;
                sTagIsSpace = false;
            } else {
                TLogUtils.sGlobalTag = "";
                sTagIsSpace = true;
            }
            return this;
        }

        public Builder setLogSwitch(boolean logSwitch) {
            TLogUtils.sLogSwitch = logSwitch;
            return this;
        }

        public Builder setLog2FileSwitch(boolean log2FileSwitch) {
            TLogUtils.sLog2FileSwitch = log2FileSwitch;
            return this;
        }

        public Builder setBorderSwitch(boolean borderSwitch) {
            TLogUtils.sLogBorderSwitch = borderSwitch;
            return this;
        }

        public Builder setLogFilter(@TYPE int logFilter) {
            TLogUtils.sLogFilter = logFilter;
            return this;
        }
    }

    public static void v(Object contents) {
        log(V, sGlobalTag, contents);
    }

    public static void v(String tag, Object... contents) {
        log(V, tag, contents);
    }

    public static void d(Object contents) {
        log(D, sGlobalTag, contents);
    }

    public static void d(String tag, Object... contents) {
        log(D, tag, contents);
    }

    public static void i(Object contents) {
        log(I, sGlobalTag, contents);
    }

    public static void i(String tag, Object... contents) {
        log(I, tag, contents);
    }

    public static void w(Object contents) {
        log(W, sGlobalTag, contents);
    }

    public static void w(String tag, Object... contents) {
        log(W, tag, contents);
    }

    public static void e(Object contents) {
        log(E, sGlobalTag, contents);
    }

    public static void e(String tag, Object... contents) {
        log(E, tag, contents);
    }

    public static void a(Object contents) {
        log(A, sGlobalTag, contents);
    }

    public static void a(String tag, Object... contents) {
        log(A, tag, contents);
    }

    public static void file(Object contents) {
        log(FILE, sGlobalTag, contents);
    }

    public static void file(String tag, Object contents) {
        log(FILE, tag, contents);
    }

    public static void json(String contents) {
        log(JSON, sGlobalTag, contents);
    }

    public static void json(String tag, String contents) {
        log(JSON, tag, contents);
    }

    public static void xml(String contents) {
        log(XML, sGlobalTag, contents);
    }

    public static void xml(String tag, String contents) {
        log(XML, tag, contents);
    }

    private static void log(int type, String tag, Object... contents) {
        if (!sLogSwitch) return;
        final String[] processContents = processContents(type, tag, contents);
        tag = processContents[0];
        String msg = processContents[1];
        switch (type) {
            case V:
            case D:
            case I:
            case W:
            case E:
            case A:
                if (V == sLogFilter || type >= sLogFilter) {
                    printLog(type, tag, msg);
                }
                if (sLog2FileSwitch) {
                    print2File(tag, msg);
                }
                break;
            case FILE:
                print2File(tag, msg);
                break;
            case JSON:
                printLog(D, tag, msg);
                break;
            case XML:
                printLog(D, tag, msg);
                break;
        }

    }

    private static String[] processContents(int type, String tag, Object... contents) {
        StackTraceElement targetElement = Thread.currentThread().getStackTrace()[5];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1];
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0];
        }
        if (!sTagIsSpace) {// 如果全局tag不为空，那就用全局tag
            tag = sGlobalTag;
        } else {// 全局tag为空时，如果传入的tag为空那就显示类名，否则显示tag
            tag = isSpace(tag) ? className : tag;
        }

        String head = new Formatter()
                .format("Thread: %s, %s(%s.java:%d)" + LINE_SEPARATOR,
                        Thread.currentThread().getName(),
                        targetElement.getMethodName(),
                        className,
                        targetElement.getLineNumber())
                .toString();
        String msg = NULL_TIPS;
        if (contents != null) {
            if (contents.length == 1) {
                Object object = contents[0];
                msg = object == null ? NULL : object.toString();
                if (type == JSON) {
                    msg = formatJson(msg);
                } else if (type == XML) {
                    msg = formatXml(msg);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0, len = contents.length; i < len; ++i) {
                    Object content = contents[i];
                    sb.append(ARGS)
                            .append("[")
                            .append(i)
                            .append("]")
                            .append(" = ")
                            .append(content == null ? NULL : content.toString())
                            .append(LINE_SEPARATOR);
                }
                msg = sb.toString();
            }
        }
        if (sLogBorderSwitch) {
            StringBuilder sb = new StringBuilder();
            String[] lines = msg.split(LINE_SEPARATOR);
            for (String line : lines) {
                sb.append(LEFT_BORDER).append(line).append(LINE_SEPARATOR);
            }
            msg = sb.toString();
        }
        return new String[]{tag, head + msg};
    }

    private static String formatJson(String json) {
        try {
            if (json.startsWith("{")) {
                json = new JSONObject(json).toString(4);
            } else if (json.startsWith("[")) {
                json = new JSONArray(json).toString(4);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static String formatXml(String xml) {
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(xmlInput, xmlOutput);
            xml = xmlOutput.getWriter().toString().replaceFirst(">", ">" + LINE_SEPARATOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    private static void printLog(int type, String tag, String msg) {
        if (sLogBorderSwitch) printBorder(type, tag, true);
        int len = msg.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            int index = 0;
            String sub;
            for (int i = 0; i < countOfSub; i++) {
                sub = msg.substring(index, index + MAX_LEN);
                printSubLog(type, tag, sub);
                index += MAX_LEN;
            }
            printSubLog(type, tag, msg.substring(index, len));
        } else {
            printSubLog(type, tag, msg);
        }
        if (sLogBorderSwitch) printBorder(type, tag, false);
    }

    private static void printSubLog(final int type, final String tag, String msg) {
        if (sLogBorderSwitch) msg = LEFT_BORDER + msg;
        switch (type) {
            case V:
                Log.v(tag, msg);
                break;
            case D:
                Log.d(tag, msg);
                break;
            case I:
                Log.i(tag, msg);
                break;
            case W:
                Log.w(tag, msg);
                break;
            case E:
                Log.e(tag, msg);
                break;
            case A:
                Log.wtf(tag, msg);
                break;
        }
    }

    private static void printBorder(int type, String tag, boolean isTop) {
        String border = isTop ? TOP_BORDER : BOTTOM_BORDER;
        switch (type) {
            case V:
                Log.v(tag, border);
                break;
            case D:
                Log.d(tag, border);
                break;
            case I:
                Log.i(tag, border);
                break;
            case W:
                Log.w(tag, border);
                break;
            case E:
                Log.e(tag, border);
                break;
            case A:
                Log.wtf(tag, border);
                break;
        }
    }

    private synchronized static void print2File(final String tag, final String msg) {
        Date now = new Date();
        String date = new SimpleDateFormat("MM-dd", Locale.getDefault()).format(now);
        final String fullPath = dir + "tiphone_sdk_10.0.6_" + date + ".txt";
        if (!createOrExistsFile(fullPath)) {
            Log.e(tag, "log to " + fullPath + " failed!");
            return;
        }
        String time = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ", Locale.getDefault()).format(now);
        StringBuilder sb = new StringBuilder();
        if (sLogBorderSwitch) sb.append(TOP_BORDER).append(LINE_SEPARATOR);
        sb.append(time)
                .append(tag)
                .append(": ")
                .append(msg)
                .append(LINE_SEPARATOR);
        if (sLogBorderSwitch) sb.append(BOTTOM_BORDER).append(LINE_SEPARATOR);
        final String dateLogContent = sb.toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(fullPath, true));
                    bw.write(dateLogContent);
                    Log.d(tag, "log to " + fullPath + " success!");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(tag, "log to " + fullPath + " failed!");
                } finally {
                    try {
                        if (bw != null) {
                            bw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static boolean createOrExistsFile(String filePath) {
        return createOrExistsFile(isSpace(filePath) ? null : new File(filePath));
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) return false;
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
