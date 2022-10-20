
# TOSClientKit_Android_开发文档
> 版本: 1.5.8


## 目录
-  [引入SDK](#import)
-  [初始化](#init)
-  [连接服务](#connect)
-  [全局事件监听](#listenter)
-  [会话界面](#session)
-  [其他](#other)
-  [附加说明](#otherRemark)


### 简介
欢迎使用AICC的在线客服移动端开发者工具套件（SDK）。本文针对安卓端使用做详细说明，通过SDK，可以在您的APP中快速集成访客端在线聊天的功能，以具备文本、图片、视频等类型消息收发。

### 环境要求

name | 说明
---|---
Android SDK Build-tools | 21.0.0 及以上
Android SDK | Android API 21 及以上
JAVA |JDK 1.7 及以上
Gradle | 3.0 及以上版本

## <span id="import">引入 SDK</span>
#### Maven依赖方式
在项目根目录的 build.gradle 文件中添加如下配置

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
在主app的 build.gradle 文件中添加如下依赖配置

```
    //implementation "com.ti-net.oskit:online:1.4.4" (不再更新)
	implementation 'com.github.ti-net-project:OnlineSDK-Android:x.x.x'//x.x.x为最新版本号
```

其他
``` 
    //如您的应用内从未使用到以下依赖，则需要在app的 build.gradle 文件中添加如下依赖配置
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.0.0'
    implementation 'com.github.chrisbanes:PhotoView:2.3.0'
```

#### aar 依赖方式
aar下载地址：https://tinet-sdk-release.s3.cn-north-1.amazonaws.com.cn/online-sdk/sdk/release/online_sdk_x.x.x_release.aar //x.x.x为最新版本号

在主 app 的 build.gradle 文件中添加如下依赖配置

```
	api files('libs/online_sdk_x.x.x_release.aar')//x.x.x为最新版本号
```

### 混淆配置
```
-keep class com.tinet.** { *; }
```

### 权限适配
AndroidManifest.xml中添加如下所需要的权限

```
<!--SD卡读写权限，发送文件、保存文件。-->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<!--录音权限，用于录音。-->
<uses-permission android:name="android.permission.RECORD_AUDIO"/>
<!--拍照权限，用于拍照（发送照片）-->
<uses-permission android:name="android.permission.CAMERA"/>

<!-- 安卓11+选择文件进行发送时查看所有文件必须权限  该权限属敏感权限，如有问题请咨询SDK技术支持 -->
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
<uses-permission
    android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"
    tools:ignore="ProtectedPermissions" />
```

##  <span id="init">初始化</span>
#### 功能描述
SDK 需要开发者在工程中调用下面方法来初始化 SDK。在 App 的整个生命周期中，开发者只需要将 SDK 初始化一次。

#### 参数说明

参数    |	类型    |	必填          |	说明
---     |---        |---              |---
context |	Context |	是            |	上下文对象
tOSInitOption       |	TOSInitOption |是 |	初始化参数option

##### TOSInitOption 参数说明
参数           |	类型             |必填 |	说明
---            |---                  |---  |---
accessId       |	String           |	是 |	访问标识
accessSecret   |	String           |	是 |	访问秘钥
enterpriseId   |	String           |	是 |	企业号
apiUrl         |	String           |	是 |	平台apiUrl
onlineUrl      |	String           |	是 |	平台onlineUrl
debug          |	boolean          |	否 |	是否开启debug模式
advanceParams  | Map<String, Object> |	否 |	自定义可配参数,可为空

#### 代码示例
说明：只需要在继承Application类的onCreate初始化一次
```
        TOSInitOption tOSInitOption = new TOSInitOption();
        tOSInitOption.setAccessId(accessId); 
        tOSInitOption.setAccessSecret(accessSecret);
        tOSInitOption.setEnterpriseId(enterpriseId);
        tOSInitOption.setApiUrl(define.getApiUrl());
        tOSInitOption.setOnlineUrl(define.getOnlineUrl());
        tOSInitOption.setDebug(BuildConfig.DEBUG);

        TOSClientKit.initSDK(this, tOSInitOption, new TImageLoader() {
            @Override
            public void loadImage(ImageView imageView, Object uri) {
                // 加载图片,加载图片的略图，合理利用缓存
            }

            @Override
            public void loadImage(ImageView imageView, Object uri, int placeholderImg, int errorImg) {
                // 加载图片,指定加载中的图片资源和加载图片异常资源，加载图片的略图，合理利用缓存
            }

            @Override
            public void loadImage(ImageView imageView, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener) {
                // 加载图片，指定图片加载的大小，用于需要查看大图或详图

            }

            @Override
            public void loadImage(Context context, Object uri, int originalWidth, int originalHeight, TImageLoaderListener listener) {
                // 加载图片，指定图片加载的大小，用于需要查看大图或详图，且加载控件非ImageView
            }

        });

```

## <span id="connect">连接服务</span>
### 连接
#### 功能描述
连接在线客服，同一用户多次调用connect不会触发多次连接

#### 参数说明

参数    |	类型    |	必填    |	说明
---     |---        |---        |---
tOSConnectOption    |	TOSConnectOption |是 |	初始化参数option

##### TOSConnectOption 参数说明
参数           |	类型             |必填 |	说明
---            |---                  |---  |---
visitorId      |	String           |	否 |	用户App的userID（为空的情况下，系统默认为UUID去除-号，不可包含中文或特殊符号，建议使用用户系统ID方便APP拓展功能）
nickname       |	String           |	否 |	昵称
headUrl        |	String           |	否 |	头像地址
mobile         |	String           |	否 |	手机号
advanceParams  | Map<String, Object> |	否 |	自定义可配参数

#### 代码示例
说明：只需要在继承Application类的onCreate初始化一次
```
        TOSConnectOption tOSConnectOption = new TOSConnectOption();
        tOSConnectOption.setVisitorId(id);
        tOSConnectOption.setNickname(nickname);
        tOSConnectOption.setHeadUrl(headerUrl);
        tOSConnectOption.setMobile(phone);
        tOSConnectOption.setAdvanceParams(extraInfo);

        TOSClientKit.connect(tOSConnectOption, new OnlineConnectResultCallback() {
            @Override
            public void onSuccess() {
            //连接成功回调
            }

            @Override
            public void onError(int errorCode, String errorDesc) {
            //连接失败回调
            }
        });
```

### 断开连接
#### 功能描述
断开连接在线客服，当用户退出登录时调用


#### 参数说明

参数          |	类型                     |必填|	说明
---           |---                       |--- |---
isReceivePush |	Boolean                  |是  |	是否需要推送
listener      |	OnlineDisconnectListener |否  |	断开连接监听

#### 代码示例
```
        TOSClientKit.disConnect(false, null);
        
```

##  <span id="listenter">全局事件监听</span>


### 消息监听

```
        TOSClientKit.addOnlineMessageListener(new OnlineMessageListener() {
                    @Override
                    public void onMessage(OnlineMessage message) {
                        // 收到的消息信息
                    }
                });
```

### 会话事件监听

```
        TOSClientKit.addOnlineEventListener(new OnlineEventListener() {
                    @Override
                    public void chatOpen(OnlineMessage message) {
                    //会话开始
                    }
        
                    @Override
                    public void chatClose(OnlineMessage message) {
                    //会话结束
                    }
        
                    @Override
                    public void chatBridge(OnlineMessage message) {
                    //接通座席
                    }
        
                    @Override
                    public void chatQueue(OnlineMessage message) {
                    //进入排队
                    }
        
                    @Override
                    public void chatLocation(OnlineMessage message) {
                    //排队位置播报
                    }
        
                    @Override
                    public void chatLeaveMessage(OnlineMessage message) {
                    //留言
                    }
        
                    @Override
                    public void chatInvestigation(OnlineMessage message) {
                    //满意度
                    }
        
                    @Override
                    public void robotBridge(OnlineMessage message) {
                    //接通机器人
                    }
        
                    @Override
                    public void withdraw(String messageId) {
                    //座席撤回消息
                    }
        
                    @Override
                    public void chatSwitch(OnlineMessage message) {
                    //分支节点
                    }
        
                    @Override
                    public void chatLeadingWords(OnlineMessage message) {
                    //引导语消息
                    }
        
                    @Override
                    public void chatInquiry(OnlineMessage message) {
                    //询前表单消息
                    }
        
                    @Override
                    public void chatResponse(OnlineMessage message) {
                    //消息发送响应
                    }
                });
```

##  <span id="session">会话界面</span>
说明：TOSClientKit 已经实现了一个默认的会话视图界面，直接使用此类，即可快速启动和使用聊天界面。
注：只有TOSClientKit调用connect成功时，才可进入客服会话聊天界面
### 集成客服会话窗体 集成客服fragment

集成客服fragment（即SessionFragment），建议实现自己的Fragment继承SessionFragment，可实现高度自定义。
如下代码：

```
        SessionFragment sessionFragment = new SessionFragment();
        sessionFragment.setArguments(getIntent().getExtras());
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, sessionFragment);
        transaction.commitAllowingStateLoss();
```

### 修改UI配置

#### xml修改示例如下：


colors.xml可修改项


```	
        <!--  ++++++++++++++ 聊天背景设置 ++++++++++++++  -->
        <!--聊天背景颜色-->
        <color name="ti_session_background_color">#FFF2F6FD</color>
    
        <!--  ++++++++++++++ 聊天消息样式设置 ++++++++++++++  -->
        <!--昵称文字颜色-->
        <color name="ti_nickname_text_color">#FF595959</color>
        <!--发送端气泡背景颜色-->
        <color name="ti_send_bubble_background_color">#D5E1F7</color>
        <!--发送端文字颜色-->
        <color name="ti_send_msg_text_color">@color/ti_dark</color>
        <!--发送端超链接文字颜色-->
        <color name="ti_send_super_line_text_color">#1366dc</color>
        <!--接收端文字颜色-->
        <color name="ti_receive_msg_text_color">@color/ti_dark</color>
        <!--接收端超链接文字颜色-->
        <color name="ti_receive_super_line_text_color">#1366dc</color>
        <!--接收端气泡背景颜色-->
        <color name="ti_receive_bubble_background_color">@android:color/white</color>
        <!--猜你所想-->
        <color name="ti_guess_text_color">#FAAD14</color>
        <!--排队文字颜色-->
        <color name="ti_line_up_text_color">@color/ti_blue</color>
        <!--会话提示字体颜色-->
        <color name="ti_tips_text_color">#FF595959</color>
        <!--会话提示气泡颜色-->
        <color name="ti_tips_bubble_bg_color">#a6ffffff</color>
        <!--时间字体颜色-->
        <color name="ti_msg_time_color">#FF8C8C8C</color>
        <!--富文本视频播放器的背景-->
        <color name="ti_rich_video_bg_color">#80000000</color>
        <!--富文本视频播放器进度条的背景色-->
        <color name="ti_rich_video_progress_bg_color">#4D000000</color>
        <!--富文本视频播放器进度条的前景色-->
        <color name="ti_rich_video_foreground_color">@color/ti_white</color>
        <!--满意度文字颜色选中-->
        <color name="satisfaction_select_text_color">#FAAD14</color>
        <!--满意度文字颜色未选-->
        <color name="satisfaction_unselected_text_color">#595959</color>
        <!--满意度标签文字颜色选中-->
        <color name="satisfaction_label_select_text_color">#FAAD14</color>
        <!--满意度标签文字颜色未选-->
        <color name="satisfaction_label_unselected_text_color">#8C8C8C</color>
        <!--满意度标签文字颜色选中边框线颜色-->
        <color name="satisfaction_label_select_border_color">#FAAD14</color>
        <!--满意度标签文字颜色未选边框线颜色-->
        <color name="satisfaction_label_unselected_border_color">#D9D9D9</color>
        <!--满意度标签文字颜色选中背景色-->
        <color name="satisfaction_label_select_color">#30FAAD14</color>
        <!--满意度标签文字颜色未选背景色-->
        <color name="satisfaction_label_unselected_color">#30D9D9D9</color>
        <!--满意度提交文字-->
        <color name="satisfaction_submit_tv_color">#4385FF</color>
        <!--满意度提交背景色-->
        <color name="satisfaction_submit_bg">#4385FF</color> 

        <!--  ++++++++++++++  输入栏设置 ++++++++++++++  -->
        <!--输入区域背景颜色-->
        <color name="ti_input_area_bg_color">#F5F7F9</color>
        <!--聊天输入框文字颜色-->
        <color name="ti_input_box_text_color">#FF262626</color>
        <!--聊天输入框边框线颜色-->
        <color name="ti_input_box_border_color">#F3F6F7</color>
        <!--聊天输入框背景色-->
        <color name="ti_input_box_bg_color">#ffffff</color>
        <!--输入区域按住录音按钮文字颜色-->
        <color name="ti_input_area_voice_press_text_color">#7E7E7E</color>
        <!--输入区域按住录音按钮抬起背景颜色-->
        <color name="ti_input_area_voice_press_up_bg_color">#ffffff</color>
        <!--输入区域按住录音按钮按下背景颜色-->
        <color name="ti_input_area_voice_press_bg_color">#ffe5e5e5</color>
        <!--输入区域按住录音pop提示文字颜色-->
        <color name="ti_input_area_voice_press_hint_text_color">#A6FFFFFF</color>
        <!--输入区发送按钮背景颜色-->
        <color name="ti_send_btn_bg_color">#FF4385FF</color>
        <!--输入区发送按钮字体颜色-->
        <color name="ti_send_btn_text_color">#ffffff</color>
        <!--拓展面板条目背景颜色-->
        <color name="ti_session_func_item_bg_color">#ffffff</color>
        <!--扩展面板条目文字颜色-->
        <color name="ti_session_func_item_text_color">#8B8F9D</color>
    
        <!--  ++++++++++++++ 快捷入口设置 ++++++++++++++  -->
        <!--快捷入口标签字体颜色-->
        <color name="ti_quick_entry_text_color">#000000</color>
        <!--快捷入口标签背景色-->
        <color name="ti_quick_entry_bg_color">#F3F6F7</color>
        <!--快捷入口底部背景色-->
        <color name="ti_quick_entry_rv_bg_color">#ffffff</color>
    
        <!--  ++++++++++++++ 商品卡片设置 ++++++++++++++  -->
        <!--商品卡片一发送按钮背景色-->
        <color name="ti_commodity_card_bg_color">#4384ff</color>
        <!--商品卡片一发送文字颜色-->
        <color name="ti_commodity_card__color">#ffffff</color>
        <!--聊天接受商品卡片分割线颜色-->
        <color name="ti_msg_card_receive_dividing_line_color">#80000000</color>
        <!--聊天发送商品卡片分割线颜色-->
        <color name="ti_msg_card_send_dividing_line_color">#80000000</color>
        <!--聊天接受商品卡片描述颜色-->
        <color name="ti_msg_card_receive_text_color">#80000000</color>
        <!--聊天发送商品卡片描述颜色-->
        <color name="ti_msg_card_send_text_color">#80000000</color>
        <!--聊天文件名颜色-->
        <color name="ti_msg_receive_filename_text_color">@color/ti_send_bubble_background_color</color>
    
        <!--  ++++++++++++++ 选择图片页面 ++++++++++++++  -->
        <!--相册导航栏背景色-->
        <color name="ti_album_navigation_bar_background">#ffffff</color>
        <!--相册导航栏文字颜色-->
        <color name="ti_album_navigation_bar_ext">#303030</color>
        <!--相册导航栏确定按钮背景色-->
        <color name="ti_send_picture_button_bg">#4385ff</color>
```

dimen可修改项


```
        <!--  ++++++++++++++ 聊天消息样式设置 ++++++++++++++  -->
        <!--头像大小-->
        <dimen name="ti_chat_avatar_size">40dp</dimen>
        <!--头像与消息气泡的间距-->
        <dimen name="ti_chat_avatar_message_spacing">8dp</dimen>
        <!--头像与消息气泡的间距-->
        <dimen name="ti_chat_avatar_screen_spacing">8dp</dimen>
        <!--  头像圆角弧度  -->
        <dimen name="ti_chat_avatar_radius">4dp</dimen>
        <!--昵称文字大小-->
        <dimen name="ti_chat_username_text_size">13sp</dimen>
        <!--发送端文字大小-->
        <dimen name="ti_send_bubble_text_size">16sp</dimen>
        <!--接收端文字大小-->
        <dimen name="ti_receive_bubble_text_size">16sp</dimen>
        <!--聊天气泡背景圆角弧度-->
        <dimen name="ti_msg_bubble_background_corner">10dp</dimen>
        <!--消息的最大限制宽度-->
        <dimen name="ti_message_max_width">260dp</dimen>
        <!--图片消息的默认高度-->
        <dimen name="ti_msg_img_height">195dp</dimen>
        <!--富文本视频播放器的宽-->
        <dimen name="ti_rich_video_width">150dp</dimen>
        <!--富文本视频播放器的高-->
        <dimen name="ti_rich_video_height">180dp</dimen>
        <!--会话提示文字大小-->
        <dimen name="ti_notification_size">12sp</dimen>
        <!--时间文字大小-->
        <dimen name="ti_time_size">12sp</dimen>
        <!--富文本消息之间的默认空格大小-->
        <dimen name="ti_msg_html_divider">4dp</dimen>
    
        <!--  ++++++++++++++ 输入栏样式设置 ++++++++++++++  -->
        <!--输入框行高-->
        <dimen name="ti_input_box_high_line_height">24dp</dimen>
        <!--输入框文字大小-->
        <dimen name="ti_input_box_text_size">16sp</dimen>
        <!--输入框、录音按钮最小高度-->
        <dimen name="ti_input_box_min_height">40dp</dimen>
        <!--输入框边框大小-->
        <dimen name="ti_input_box_border_width">1dp</dimen>
        <!--输入框边框圆角弧度-->
        <dimen name="ti_input_box_corner_radius">5dp</dimen>
        <!--输入区域语音，表情，"+"图标大小-->
        <dimen name="ti_input_area_icon_size">36dp</dimen>
        <!--输入区域按住录音按钮文字大小-->
        <dimen name="ti_input_area_voice_press_text_size">14sp</dimen>
        <!--输入区域按住录音按钮边框大小-->
        <dimen name="ti_input_area_voice_press_border_width">1dp</dimen>
        <!--输入区域按住录音按钮圆角弧度-->
        <dimen name="ti_input_area_voice_press_corner_radius">5dp</dimen>
        <!--输入区发送按钮背景圆角弧度-->
        <dimen name="ti_send_btn_bg_corner_radius">8dp</dimen>
        <!--拓展面板条目底层背景圆角弧度-->
        <dimen name="ti_session_func_item_bg_corner_radius">15dp</dimen>
        <!--扩展面板条目背景大小-->
        <dimen name="ti_session_func_item_bg_size">56dp</dimen>
        <!--扩展面板条目图片大小-->
        <dimen name="ti_session_func_item_img_size">32dp</dimen>
        <!--扩展面板条目文字大小-->
        <dimen name="ti_session_func_item_text_size">12sp</dimen>
        <!--发送按钮高-->
        <dimen name="ti_send_out_high">40dp</dimen>
        <!--发送按钮宽-->
        <dimen name="ti_send_out_wide">62dp</dimen>
        <!--满意度标签圆角-->
        <dimen name="satisfaction_label_radius">5dp</dimen>
        <!--满意度标签高-->
        <dimen name="satisfaction_label_high">35dp</dimen>
        <!--满意度提交按钮圆角-->
        <dimen name="satisfaction_submit_radius">5dp</dimen>
    
        <!--  ++++++++++++++ 快捷入口设置 ++++++++++++++  -->
        <!--快捷入口内容距边框区域垂直间距-->
        <dimen name="ti_quick_entry_vertical_spec">10dp</dimen>
        <!--快捷入口文字大小-->
        <dimen name="ti_quick_entry_item_text_size">13sp</dimen>
        <!--快捷入口标签背景圆角弧度-->
        <dimen name="ti_quick_entry_item_corner_radius">10dp</dimen>
        <!--快捷入口标签水平间距-->
        <dimen name="ti_quick_entry_item_horizontal_spacing">10dp</dimen>
    
        <!--  ++++++++++++++ 商品卡片设置 ++++++++++++++  -->
        <!--商品卡片一图片大小-->
        <dimen name="ti_msg_img_one">60dp</dimen>
        <!--商品卡片一发送按钮背景圆角-->
        <dimen name="ti_commodity_card_file">30dp</dimen>
        <!--商品卡片一发送文字大小-->
        <dimen name="ti_commodity_card_size">14sp</dimen>
        <!--商品卡片订单号、日期文字大小-->
        <dimen name="ti_message_tv_size">12sp</dimen>
        <!--商品卡片二图片大小-->
        <dimen name="ti_message_img">80dp</dimen>
```

string.xml可修改项

```
    		<!--录音按钮文案-->
        <string name="ti_pressed_speak">按住说话</string>
        <!--发送消息按钮文案-->
        <string name="ti_send">发送</string>
        <!--会话页面输入框hint文案-->
        <string name="ti_input_box_hint_text">请输入内容</string>
        <!--选择图片页面标题-->
        <string name="ti_camera">相册</string>
```

#### 代码修改示例如下：

说明：只需要在继承Application类的onCreate初始化一次

```
       //以下自定义界面为UI配置代码实现
       TCustomizationUI tCustomizationUI = new TCustomizationUI();//自定义UI对象
       tCustomizationUI.sendBubbleBackground = R.drawable.ti_bg_color;//发送端气泡背景
       tCustomizationUI.receiveBubbleBackground = R.drawable.ti_bg_color;//接收端气泡背景
       tCustomizationUI.sendBubbleBackgroundColor = getResources().getColor(R.color.ti_red);//发送端气泡背景颜色
       tCustomizationUI.receiveBubbleBackgroundColor = getResources().getColor(R.color.ti_red);//接收端气泡背景颜色
       //聊天背景颜色
       tCustomizationUI.sessionBackgroundColor = getResources().getColor(R.color.ti_session_func_item_text_color);
       tCustomizationUI.msgTimeColor = getResources().getColor(R.color.ti_guess_text_color);//时间字体文字颜色
       tCustomizationUI.inputAreaBgColor = getResources().getColor(R.color.ti_guess_text_color);//输入区域背景颜色
       //输入区域按住录音按钮文字颜色
       tCustomizationUI.inputAreaVoicePressTextColor = getResources().getColor(R.color.ti_guess_text_color);
       tCustomizationUI.inputBoxHintText = "hint_text";//会话页面输入框hint文案
       tCustomizationUI.chatAvatarRadius = R.dimen.fab_margin;//头像圆角弧度
    
    	 tCustomizationUI.showVisitorNickname = false;//访客昵称
       tCustomizationUI.showVisitAvatar = false;//访客头像
       tCustomizationUI.showAgentRobotNickname = false;//客服、机器人昵称
       tCustomizationUI.showAgentRobotAvatar = false;//客服、机器人头像
       tCustomizationUI.showVoiceButton = false;//语音按钮
       
       
        // : 2022/10/13 以下为配置文本消息含超链接高亮显示规则
        List<TTextPatternRule> tTextPatternRules = new ArrayList<>();
        tTextPatternRules.add(
                new TTextPatternRule(Pattern.compile("这里填正则表达式", Pattern.CASE_INSENSITIVE),//正则Pattern变量,是否忽略大小写可自行修改compile第二个参数
                        Color.parseColor("#1366dc"),//高亮显示颜色int值，可自行修改为所需颜色
                        "phoneNumber"));//类型说明，用于onLinkClick方法内回调messageEventType，区分匹配类型


       TOSClientKitConfig tosClientKitConfig = new TOSClientKitConfig.Builder()
                .setTCustomizationUI(tCustomizationUI)//配置自定义UI
                .setTextHighLightRuleList(tTextPatternRules)//配置文本消息含超链接高亮显示规则数组
                .build();
    
       TOSClientKit.setTosClientKitConfig(tosClientKitConfig);
```

### 会话界面相关操作

#### 会话内事件监听
##### 会话状态监听

```
        TOSClientKit.setOnlineStatusListener(new OnlineMessageManager.OnlineStatusListener() {
                    @Override
                    public void onStatusChanged(int status) {
                        //当前状态
                    }
                });
```

##### 会话内消息事件监听
请继承SessionFragment来实现

```
        @Override
        protected SessionClickListener getListener() {
           return new SessionClickListenerImpl(this){
           
                    @Override
                    public void onClick(View itemView, OnlineMessage message) {
                        super.onClick(itemView,message);
                        //消息点击事件
                    }
           
                    @Override
                    public void onLinkClick(String url) {
                        //super.onLinkClick(url);
                        //屏蔽super，可实现自定义超链接点击事件
                    }
        
                    /**
                    * 
                    * @param content          点击文本内容
                    * @param messageEventType 文本类型
                    */
                    @Override
                    public void onLinkClick(String content, String messageEventType) {
                        //可根据messageEventType实现自有业务逻辑
                    }
        
                    @Override
                    public void videoPlay(String url) {
                        super.videoPlay(url);
                        //自定义视频播放，如果需要自己实现视频播放，则需要屏蔽super.videoPlay(url);父类的实现方式
                    }
        
                    @Override
                    public void downloadFile(String url, String name) {
                        super.downloadFile(url, name);
                       //下载文件回调，屏蔽super可自定义而下载功能
                    }
                    
                    /**
                     *
                     * @param permissions
                     * @param requestCode
                     *   申请语音权限                1661
                     *   申请相机权限                1662
                     *   申请相机、语音权限 -- 拍摄   1663
                     *   申请文件权限 -- 文件        1664
                     *
                     */
                    @Override
                    public void onStartRequestPermissionsCallback(@NonNull String[] permissions, int requestCode) {
                        super.onStartRequestPermissionsCallback(permissions, requestCode);
                        // : 2022/9/19 权限申请回调 
                        TLogUtils.i("onStartRequestPermissionsCallback:" + requestCode);
                    }
                    
                };
            }
```

#### 拓展面板功能
请继承SessionFragment来实现
```
            @Override
            protected FuncListener getFuncListener() {
                // 自定义底部功能栏
        
                return new FuncListenerImpl(this){
                    @Override
                    public void onFuncClick(Function func) {
                        super.onFuncClick(func);
                       //拓展面板Item点击回调
                    }
                };
            }
```

#### 快捷入口功能

```
        ArrayList message = new ArrayList<>();
        message.add(new LabeInfo("订单号", "1234567890"));
        message.add(new LabeInfo("服务地区", "北京市"));
        message.add(new LabeInfo("服务", "满意"));
        message.add(new LabeInfo("师傅", "金师傅"));
        message.add(new LabeInfo("产品类型", "电子产品"));
        message.add(new LabeInfo("师傅电话", "12345678900"));
        message.add(new LabeInfo("订单状态", "已完成"));
        
        //更新快捷入口可实时更新至会话窗体
        TOSClientKit.updateSessionWindowQuickEntrys(message);
        
        //清空快捷入口数据
        TOSClientKit.clearSessionWindowQuickEntrys();
        
        //快捷入口的点击回调
        //请继承SessionFragment来实现
        @Override
        protected LabelListener getLabelListener() {
            return new LabelListenerImpl(this) {
                @Override
                public void onLabelClick(LabeInfo info) {
                    super.onLabelClick(info);
                    Toast.makeText(requireContext(), info.toString(), Toast.LENGTH_SHORT).show();
                }
            };
        }

```


#### 商品卡片功能
商品卡片数据可以通过argument传递到会话窗体，参数名：tinetCard，可直接引用SessionFragment.ARGS_CARD，参数实体为CardInfo。

```
        //定义CardInfo，卡片实体
        CardInfo message = new CardInfo();
        message.setSubTitle("华为P40麒麟990 5G SoC芯片 5000万超感知徕卡三摄 30倍数字变焦");
        message.setDescription("这是商品描述，啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
        message.setImg(
                    "https://img1.baidu.com/it/u=1963848283,2056721126&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500");
        message.setPrice("￥ 100.99");
        message.setTime(TimeUtils.getDate(System.currentTimeMillis()));
        message.setStatus("已到货");
        
        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("订单号", "1234567890");
        extraInfo.put("服务地区", "北京市");
        extraInfo.put("服务", "满意");
        extraInfo.put("师傅", "金师傅");
        extraInfo.put("产品类型", "电子产品");
        extraInfo.put("师傅电话", "12345678900");
        extraInfo.put("订单状态", "已完成");
        message.setExtraInfo(extraInfo);
        
        //传递至会话窗体
        intent.putExtra(ChatFragment.ARGS_CARD,message);
```

#### 会话相关接口

##### 获取会话当前在线状态
代码示例

```
    /**
     * 获取当前在线状态
     *
     * @return 0:不在线（会话结束）  1:机器人   2:人工座席
     */
     TOSClientKit.getCurrentOnlineStatus()
```

##### 获取当前会话信息

代码示例

```
    /**
     * 获取当前会话信息
     *
     * @return SessionInfo
     */
     TOSClientKit.getCurrentSessionInfo()
```
###### 返回参数 SessionInfo 说明
参数           |	类型              |	说明
---            |---                   |---
mainUniqueId   |	String           |	会话ID
startTime      |	String            |	会话开始时间
status         |	int           |	会话状态(新打开 1;路由中 2;排队中 3;接通座席 4;留言中 5;满意度 6;关闭 7;接通机器人 8;座席主动发起会话 9;)
enterpriseId   |	String           |	企业id
visitorId      |	String            |	访客id

##### 获取会话最后一条消息信息

代码示例

```
     TOSClientKit.getLastMessageInfo(visitorId, new OnLastMessageResult() {
            @Override
            public void onLastMessage(int unreadCount, String lastMessage) {
               
            }
        });
```
###### 返回参数说明
参数           |	类型              |	说明
---            |---                   |---
unreadCount    |	String            |	未读数
lastMessage    |	String            |	最后一条消息

##  <span id="other">其他</span>

#### 获取版本号
代码示例

```
    /**
     * 获取SDK版本号
     *
     * @return 版本号字符串
     */
     TOSClientKit.getSDKVersion()
```


##  <span id="otherRemark">附加说明</span>
###  会话详情（SessionInfo）
| 字段名 | 字段类型 | 字段描述 | 备注 |
| --- | --- | --- | --- |
| mainUniqueId | String | 会话ID |  |
| startTime | long | 会话开始时间 |  |
| status | int | 当前会话状态 | 暂未使用 |
enterpriseId   |	String           |	企业id |
visitorId      |	String            |	访客id |

### **在线状态（OnlineMessageManager）**
| 字段名 | 字段类型 | 字段描述 | 备注 |
| --- | --- | --- | --- |
| STATUS_OUTLINE | int | 不在线（会话结束） |  |
| STATUS_ROBOT | int | 机器人在线 |  |
| STATUS_ONLINE | int | 人工座席 |  |

### **消息**
| **消息类型** | **objectName** | **描述** |
| --- | --- | --- |
| 文本消息 | TextMessage | 文本消息（包含表情） |
| 图片消息 | ImageMessage | 图片消息 |
| 语音消息 | VoiceMessage | 语音消息 |
| 视频消息 | VideoMessage | 视频消息 |
| 文件消息 | FileMessage | 文件消息 |

### **消息类型**
| 参数名 | 参数值 | 说明 |
| --- | --- | --- |
| TEXT | 1 | 文本消息 |
| IMAGE | 2 | 图片消息 |
| FILE | 3 | 文件消息 |
| VIDEO | 4 | 视频消息 |
| ROBOT_HTML | 5 | 机器人富文本消息 |
| ROBOT_CHOOSE | 6 | 机器人选项消息 |
| VOICE | 7 | 语音消息 |
| KNOW_LEDGE | 8 | 知识库文件消息 |
| CARD | 10 | 卡片消息 |
| ROBOT_GROUP | 14 | 机器人组合消息 |
| ROBOT_ABOUT_QUESTION | 15 | 机器人相关问题 |
| ROBOT_GUESS_QUESTION | 16 | 机器人猜你想问 |
| ROBOT_COMMON_QUESTION | 17 | 机器人常见问题 |
| ROBOT_APPROX_QUESTION | 18 | 机器人近似问题 |
| ROBOT_COMMENT_QUESTION | 19 | 机器人推荐问题 |
| ROBOT_COMMON_QUESTION_CLASSIC | 20 | 机器人常见问题（分类） |

### **消息发送状态**
| **参数名** | **参数值** | **说明** |
| --- | --- | --- |
| NORMAL | 1 | 正常 |
| SENSITIVE_WORD | 2 | 包含敏感词 |
| REVOKE | 3 | 已被撤回 |


### **消息状态**
| **参数名** | **参数值** | **说明** |
| --- | --- | --- |
| MSG_STATUS_SEND_FAIL | -1 | 消息发送失败状态 |
| MSG_STATUS_SENDING | 0 | 消息发送中状态 |
| MSG_STATUS_SEND_SUCCESS | 1 | 消息已发送状态 |


###  **事件**
| **参数名** | **参数值** | **说明** |
| --- | --- | --- |
| CHAT_MESSAGE | chatMessage | 聊天消息 |
| CHAT_BRIDGE | chatBridge | 接通座席 |
| CHAT_CLOSE | chatClose | 关闭座席 |
| CHAT_LEAVE_MESSAGE | chatLeaveMessage | 留言消息 |
| ROBOT_BRIDGE | robotBridge | 接通机器人 |
| CHAT_OPEN | chatOpen | 会话开始 |
| CHAT_QUEUE | chatQueue | 进入排队 |
| CHAT_LOCATION | chatLocation | 排队位置播报 |
| CHAT_INVESTIGATION | chatInvestigation | 满意度 |
| WITHDRAW | withdraw | 座席撤回消息 |
| CHAT_SWITCH | chatSwitch | 分支节点 |
| CHAT_LEADING_WORDS | chatLeadingWords | 引导语 |
| CHAT_INQUIRY | chatInquiry | 询前表单 |
| CHAT_OFFLINE | chatOffline | 访客离线 |
| CHAT_ONLINE | chatOnline | 访客上线 |
| CHAT_LEAVE_QUEUE | chatLeaveQueue | 访客退出排队 |
| CHAT_SWITCH_NEXT | chatSwitchNext | 访客选择节点 |
| CHAT_SUBMIT_INVESTIGATION | chatSubmitInvestigation | 访客提交满意度 |
| CHAT_SUBMIT_INQUIRY | chatSubmitInquiry | 访客提交询前表单 |

###  **初始化参数说明**
| **参数** | **类型** | **说明** |
| --- | --- | --- |
| accessId | String | 移动端唯一标识，对应座席端渠道ID |
| accessSecret | String | 加密信息 |
| enterpriseId | String | 企业ID |
| mPlatformDefine | PlatformDefine | 平台，提供北京、上海和测试三个平台，也可以自定义平台 |
| debug | Boolean | 是否为debug模式，debug模式下可以输出日志信息 |

###  **消息发送人类型**
消息发送人类型封装在类OnlineMessageSenderType类中。

| **参数名** | **参数值** | **说明** |
| --- | --- | --- |
| ONLINE | 1 | 座席 |
| VISITOR | 2 | 访客 |
| SYSTEM | 3 | 系统 |
| ROBOT | 4 | 机器人 |
| NOTIFY | 5 | 系统通知 |

###  **消息状态**
系统支持的消息状态封装在TMessageStatus类中。

| **参数名** | **参数值** | **说明** |
| --- | --- | --- |
| MSG_STATUS_SEND_FAIL | -1 | 消息发送失败状态 |
| MSG_STATUS_SENDING | 0 | 消息发送中状态 |
| MSG_STATUS_SEND_SUCCESS | 1 | 消息已发送状态 |

###  商品卡片消息（**CardInfo）**
| 参数名 | 参数类型 | 说明 |
| --- | --- | --- |
| title | String | 卡片消息标题 |
| subTitle | String | 卡片消息副标题 |
| description | String | 卡片消息的描述，例如商品的简单描述 |
| price | String | 商品卡片的价格 |
| time | String | 卡片消息的时间，例如订单的生成时间 |
| img | String | 卡片消息的图片地址，例如商品的图片地址 |
| url | String | 卡片消息详情，例如商品的详情页 |
| subUrl | String | 卡片消息详情副地址 |
| buttonText | String | 按钮文本内容 |
| status | String | 商品状态 |
| extraInfo | HashMap<String, String> | 额外信息 |
| extraData | String | 额外信息 |

