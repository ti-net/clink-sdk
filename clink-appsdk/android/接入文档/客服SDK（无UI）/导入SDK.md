## 1、在根目录build.gradle中添加maven仓库地址。
```java
allprojects {
    repositories {
        ...
        //客服SDK maven 仓库地址
        maven {url "http://dev.clink.cn:8081/repository/maven-releases/"}
    }
}
```
## 2、在app/build.gradle中添加库依赖。
```java
dependencies {
  //客服基础库，必须依赖
  implementation 'com.tinet.oslib:online:1.0.1'
}
```
## 3、 在AndroidManifest.xml中添加Online SDK所需要的权限。
```java
<!--网络通信权限-->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```
  
