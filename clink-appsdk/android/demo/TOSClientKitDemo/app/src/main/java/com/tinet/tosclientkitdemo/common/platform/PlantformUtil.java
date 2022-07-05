package com.tinet.tosclientkitdemo.common.platform;

import android.content.Context;
import android.text.TextUtils;
import com.tinet.threepart.tools.TSPUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author: liuzeren
 * @date: 2022/5/27
 */
public class PlantformUtil {

  private static final String PLANTFORM_NAME = "name";

  public static void updatePlantform(Context context, PlantformInfo plantformInfo) {
    TSPUtils.getInstance(context).putString(PLANTFORM_NAME, plantformInfo.getName());
  }

  public static void updatePlantform(Context context, String name) {
    TSPUtils.getInstance(context).putString(PLANTFORM_NAME, name);
  }

  public static PlantformInfo getPlantform(Context context) {
    String name = TSPUtils.getInstance(context).getString(PLANTFORM_NAME, null);
    return getPlantform(context, name);
  }

  private static String getSdkJson(Context context) {
    InputStream inputStream = null;
    InputStreamReader dis = null;
    BufferedReader br = null;

    StringBuffer sb = new StringBuffer();

    try {
      inputStream = context.getAssets().open("platform.json");
      dis = new InputStreamReader(inputStream);
      br = new BufferedReader(dis);

      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line).append("\n");
      }
    } catch (IOException e) {
    } finally {
      safeClose(inputStream, dis, br);
    }

    return sb.toString();
  }

  public static ArrayList<PlantformInfo> getPlantforms(Context context) {
    String jsonString = getSdkJson(context);
    ArrayList<PlantformInfo> list = new ArrayList<>();

    if (!TextUtils.isEmpty(jsonString)) {
      try {
        JSONArray array = new JSONArray(jsonString);
        if (array.length() > 0) {
          for (int index = 0; index < array.length(); index++) {
            JSONObject object = array.getJSONObject(index);

            PlantformInfo info = new PlantformInfo();
            info.setName(object.getString("name"));
            info.setEnterpriseId(object.getLong("enterpriseId"));
            info.setAccessId(object.getString("accessId"));
            info.setAccessSecret(object.getString("accessSecret"));
            info.setType(object.getString("type"));

            list.add(info);
          }
        }
      } catch (JSONException e) {
      }
    }

    return list;
  }

  private static PlantformInfo getPlantform(Context context, String name) {
    if (TextUtils.isEmpty(name)) {
      return null;
    }

    String jsonString = getSdkJson(context);

    if (!TextUtils.isEmpty(jsonString)) {
      try {
        PlantformInfo info = null;

        JSONArray array = new JSONArray(jsonString);
        if (array.length() > 0) {
          for (int index = 0; index < array.length(); index++) {
            JSONObject object = array.getJSONObject(index);
            String objName = object.getString("name");

            if (name.equals(objName)) {
              info = new PlantformInfo();
              info.setName(objName);
              info.setEnterpriseId(object.getLong("enterpriseId"));
              info.setAccessId(object.getString("accessId"));
              info.setAccessSecret(object.getString("accessSecret"));
              info.setType(object.getString("type"));

              break;
            }
          }
        }

        return info;
      } catch (JSONException e) {

      }
    }

    return null;
  }

  private static void safeClose(Closeable... c) {
    if (c != null && c.length > 0) {
      for (Closeable closeable : c) {
        try {
          if (null != closeable) {
            closeable.close();
          }
        } catch (IOException e) {
        }
      }
    }
  }
}
