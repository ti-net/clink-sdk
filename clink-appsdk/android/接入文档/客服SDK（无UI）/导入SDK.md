
## 1、在app/build.gradle中添加库依赖。
```java
dependencies {
  //客服基础库，必须依赖
  implementation 'com.ti-net.oskit:online:1.1.9'
}
```
## 2、 在AndroidManifest.xml中添加Online SDK所需要的权限。
```java
<!--网络通信权限-->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```
  
