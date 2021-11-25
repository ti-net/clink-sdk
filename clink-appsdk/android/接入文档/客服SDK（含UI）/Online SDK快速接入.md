# 
## 使用说明
欢迎使用“智慧服务平台”的在线客服移动端开发者工具套件（SDK）。本文针对安卓端使用做详细说明，通过SDK，可以在您的APP中快速集成访客端在线聊天的功能，以具备文本、图片、视频等类型消息收发及消息通知能力。
## 开发环境


- Android Studio 3.0以上
- Android minSdkVersion: 14
- Gradle: 4.4以上



### 1.申请AppKey
accessId   //移动端唯一标识，对应座席端渠道ID
accessSecret  //加密信息
enterpriseId  //企业ID
### 2.接入Online SDK


1. 在根目录build.gradle中添加maven仓库地址



```java
allprojects {
    repositories {
        ...
        //客服SDK maven 仓库地址
        maven {
            url 'https://maven.aliyun.com/repository/public'
        }
        maven {
            credentials {
                username '61965416dd32cb6444a19056'
                password 'VptyuwI3QX3j'
            }
            url 'https://packages.aliyun.com/maven/repository/2157415-release-qzT0Ka/'
        }
    }
}
```


2.  在app/build.gradle中添加库依赖，推送[依赖包](https://github.com/ti-net/clink-sdk/tree/master/clink-appsdk/android/lib/libs.zip)



```java
dependencies {
	//客服基础库，必须依赖
	implementation 'com.tinet.oskit:online:1.2.5'
	//客服Push依赖
	//华为 push
    implementation(name: 'hms-base-2.6.3.301', ext: 'aar')
    implementation(name: 'hms-push-2.6.3.301', ext: 'aar')
    //vivo push
    implementation(name: 'vivo_pushsdk-v2.9.0.0', ext: 'aar')
    //魅族 push
    implementation(name: 'meizu-push-internal-3.7.0', ext: 'aar')
}
```

3.  初始化SDK
3.1. 在自定义Application类的onCreate()中初始化SDK ，[OnlineInitOption参数配置](https://github.com/ti-net/clink-sdk/tree/master/clink-appsdk/android/接入文档/初始化参数说明.md)。
```java
   public class MyApplication extends Application {

   	@Override
   	public void onCreate() {
   		super.onCreate();
   		initOnlineSDK();
   		initPush();
   	}


   	/**
   	 * 初始化客服SDK
   	 */
   	private void initOnlineSDK(){
   		OnlineInitOption option = new OnlineInitOption();
   		option.setAccessId(accessId);
   		option.setAccessSecret(accessSecret);
   		option.setEnterpriseId(enterpriseId);
        // 请注意北京平台/上海平台
   		option.setApiUrl("https://octopus-api-1.vlink.cn/api/sdk/v1");
        // 请注意北京平台/上海平台
   		option.setOnlineUrl("http://chat-app-bj-test3.clink.cn");
   		option.setDebug(BuildConfig.DEBUG);

   		OnlineKitManager.init(this, option, new TImageLoader() {
       			@Override
       			public void loadImage(ImageView imageView, Object uri) {
   				//TODO 加载图片
   			}

       			@Override
       			public void loadImage(ImageView imageView, Object uri, int placeholderImg, int errorImg) {
   				//TODO 加载图片
   			}

       			@Override
       			public void loadImage(ImageView imageView, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener) {
   				//TODO 加载图片
   			});
   	}		

   }
```
3.2.  在AndroidManifest.xml中添加Online SDK所需要的权限  
```java
<!--网络通信权限-->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<!--录音权限-->
<uses-permission android:name="android.permission.RECORD_AUDIO"/>
<!--拍照权限-->
<uses-permission android:name="android.permission.CAMERA"/>
```


### 3.接入Online Push，推送配置需要在客服SDK初始化完毕（即客服连接成功后），才会自动进行推送的初始化。


```java
    /**
     * online 推送初始化
     */
    private void initPush(){
        OnlineServiceClient.addOnlineConntectStatusListener(new OnlineConnectStatusListener() {
            @Override
            public void onConnecting() {

            }

            @Override
            public void onConnected() {
                PushConfig config = new PushConfig.Builder()
                        .setMiPush("<XIAOMI_APP_ID>", "<XIAOMI_APP_key>")//小米push配置
                        .setHWPush(true)//华为push配置
                        .setOppoPush("<OPPO_APP_key>", "<OPPO_APP_SECRET>")//oppo push配置
                        .setVivoPush(true)//vivo push配置
                        .setMeiZuPush("<MEIZU_APP_ID>","<MEIZU_APP_key>")//meizu push配置
                        .build();

                TIMPushManager.init(this, OnlineServiceClient.getCurrentUserInfo().getTokenInfo().getAppId(), config, (pushType, token) -> TIMBaseManager.getInstance().updateDeviceToken(token, new TOperationCallback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(int errorCode, String errorDesc) {
                    }
                }));
            }

            @Override
            public void onDisconnected() {
                //TODO 连接断开
            }

            @Override
            public void onReConnecting() {
                //TODO 正在进行重连接
            }

            @Override
            public void onReconnected() {
                //TODO 重连接成功
            }

            @Override
            public void onKickOut() {
                //TODO 被踢出
            }
        });
    }
```


### 4.集成客服会话窗体
集成客服fragment（即SessionFragment）


```java
    SessionFragment sessionFragment = new SessionFragment();
    sessionFragment.setArguments(getIntent().getExtras());
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.container, sessionFragment);
    transaction.commitAllowingStateLoss();
```


### 5. 创建会话


连接在线客服，同一用户多次调用connect不会触发多次连接，id为用户侧的用户ID（可为空，为空的情况下，系统默认为UUID去除-号，不可包含中文或特殊符号，建议使用用户系统ID方便APP拓展功能），nickname为用户昵称（可为空），headUrl用户头像全链接地址（可为空）。mobile为绑定手机号（可以为空），extraInfo为额外的附加信息（没有可以传空）


```java
//同一用户不会导致重连
OnlineServiceClient.connect(id,nickname,headerUrl,mobile,extraInfo,new OnlineConnectResultCallback(){

    @Override
    public void onSuccess(String data) {
        //客服连接成功
        Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(int errorCode, String errorDesc) {

    }
});
```
### 6. 退出应用


退出应用，有两种方式：
1、退出应用，接收离线消息推送。isReceivePush = true
2、退出应用，且关闭消息推送。isReceivePush = false


```java
OnlineServiceClient.disConnect(boolean isReceivePush,null)
```
### 7. 资源下载
demo详见[online-demo.zip](https://github.com/ti-net/clink-sdk/tree/master/clink-appsdk/android/demo/online-sdk.zip)
