# 中通天鸿 Android SDK 开发指南
## 1.简介
中通天鸿 Android SDK 是一个 Android 端客服系统访客解决方案，既包含了客服聊天逻辑管理，也提供了聊天界面，开发者可方便的将客服功能集成到自己的 APP 中。
## 2.快速集成
只需简单 3 步，即可将客服功能加入你的 APP：

#### 1.添加 SDK 到项目中。
快速接入（包含逻辑界面以及Lib库）


Android Studio: 
首先在project根目录的build.gralde中添加：

```
allprojects {
    repositories {
        ...
        //add this
        maven { url "https://github.com/ztthgly/mobile-android-im-sdk/raw/master" }
        ...
    }
}
```

而后在对应Module的build.gradle 文件中添加依赖即可。

``` 
implementation 'net.icsoc.im:kit:+'
```
“+”为当前版本号

Eclipse接入: 

先下载 SDK，然后解压缩，将得到的 kit和core对应的aar包导入到你的工程中的libs目录，而后build path即可。

#### 2.在你的 Application 类的 onCreate 函数中，加入以下初始化代码：

```
  public class YourApplication extends Application {
    public void onCreate() {
        // ... your codes
        // appKey 可以在中通天鸿->设置->APP接入 页面找到
        ZTIMCore.init(this, "appKey");
        // ... your codes
        //对应UI选项设置
        ZTIMOptions options = new ZTIMOptions();
        options.uiCustomization = uiCustomization();
        IMKitCore.initOptions(this, options);
    }
   
    /**
	  * 如果返回值为null或者对应属性值未设置，则使用默认参数。
	  * 对应可调整的UI属性，见UICustomization
	  */
    private UICustomization uiCustomization() {
        // 以下示例的图片均无版权，请勿使用
        UICustomization customization = new UICustomization();
        customization.msgBackgroundUri = R.mipmap.msg_bg;
        customization.msgItemBackgroundLeft = R.drawable.my_message_left_bg;
        customization.msgItemBackgroundRight = R.drawable.my_message_right_bg;
        customization.textMsgColorLeft = Color.BLACK;
        customization.textMsgColorRight = Color.WHITE;
        customization.hideLeftAvatar = false;
        customization.hideRightAvatar = false;
        customization.tipsTextColor = 0xFF76838F;
        return customization;
    }
}
```

#### 3.关联用户信息
坐席端想了解APP用户信息，以及主动给用户发送消息，就需要APP关联用户信息
发送用户信息到客服端，调用_setUserInfo_实现

```
//用户信息包含tid，userName，以及avatar，分别对应用户唯一标识、昵称、头像
ZTIMUserInfo userInfo = new ZTIMUserInfo();
userInfo.tid = "用户唯一标识";
userInfo.userName = "用户昵称";
userInfo.avatar = "用户头像";
ZTIMCore.setUserInfo(userInfo);

//建议将setUserInfo写在APP用户信息获取逻辑成功之后，注意tid为用户唯一标识符
//注：请务必调用该方法，否则无法确保用户唯一性
```

#### 4.切换用户信息
如若需要切换用户，请在_setUserInfo_前调用

```
ZTIMCore.logout();
//建议与APP登出操作逻辑同步
```

#### 5.在你的 APP 的合适页面添加客服入口按钮，并在响应函数中加入如下代码：

```
 String title = "聊天窗口的标题";
 /**
  * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
  * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
  * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
  */
 ConsultSource source = new ConsultSource();
 source.title = title;
 source.landingPageTitle = "当前APP端着陆页";
 /**
  * @param context 上下文
  * @param source  咨询的发起来源，包括发起咨询的title，描述信息等
  */
 IMKitCore.openServiceActivity(context, source);
在打开的页面中，用户就可以咨询客服了。
```

由于开发环境的不同，很多情况下会出现集成报错或者配置无效的问题。我们官网提供了demo源码，开发者可以参考源码；更多的时候是需要开发者自己本地调试代码，可以通过断点跟踪等基本且有效的方法来定位和排查问题。

## 3.单独引用lib
假若APP想界面UI完全自定义也可单独引入lib库（常规接入逻辑如下，具体可参考kit源码）

根目录build.gralde与2.1一样

```
allprojects {
    repositories {
        ...
        //add this
        maven { url "https://github.com/ztthgly/mobile-android-im-sdk/raw/master" }
        ...
    }
}
```

引入lib

```
implementation 'net.icsoc.im:core:+'
```
“+”为当前版本号

当用户APP需要界面完全自定义时，可单独引用lib库来实现。Application中对应初始化操作与步骤2相同，自定义界面设置步骤如下：
#### 1.初始化咨询服务
在对应Activity的界面的onCreate方法中添加

```
ZTIMCore.startService();
```

#### 2.发送用户信息
```
//用户信息包含tid，userName，以及avatar，分别对应用户唯一标识、昵称、头像
ZTIMUserInfo userInfo = new ZTIMUserInfo();
userInfo.tid = "用户唯一标识";
userInfo.userName = "用户昵称";
userInfo.avatar = "用户头像";
ZTIMCore.setUserInfo(userInfo);

//建议将setUserInfo写在APP用户信息获取逻辑成功之后，注意tid为用户唯一标识符
//注：请务必调用该方法，否则无法确保用户唯一性
```

#### 3.切换用户信息
如若需要切换用户，请在_setUserInfo_前调用

```
ZTIMCore.logout();
//建议与APP登出操作逻辑同步
```

#### 4.订阅消息
在对应Activity中添加

```
// IMessageSubscriber为当前消息接收监听
ConversationManager conversationManager = ZTIMCore.getConversationManager();
conversationManager.subscribe(this);
// subscribe方法中传入IMessageSubscriber实现类，实现对应的方法即可收到对应的聊天消息
```

#### 5.发送消息

```
ConversationManager conversationManager = ZTIMCore.getConversationManager();

//通过conversationManager即可完成消息发送逻辑 
//例如文本消息 conversationManager.setTextMessage("文字内容", MessageCallback);
```


## 4.UI自定义
为了咨询客服窗口的界面风格与集成中通天鸿IMSDK的APP能够尽量统一，中通天鸿IMSDK提供精简的UI自定义配置。
配置选项接口名为 UICustomization，配置参数放在 ZTIMOptions 的 uiCustomization 变量中，开发者可在初始化 SDK 或者在运行时任意时候修改配置，当需要与 SDK 提供的默认界面不一样表现的地方，就修改对应的项，否则不赋值即可，界面会保留默认表现。修改各设置项后，都需要等到下次进入会话界面才会看到相应的更改。

各配置项说明如下：

|	参数名	|	类型	|	参数说明	| 取值说明 |
|	:---	|	:--		|	:---		| :--|
msgBackgroundUri	| int | 客服消息窗口背景图片设置 | drawable resId
msgBackgroundColor	| int | 客服消息窗口背景颜色设置 | 32 位颜色值
hideLeftAvatar	| boolean | 是否隐藏左边头像 | 默认为false，不隐藏
hideRightAvatar	| boolean | 是否隐藏右边头像 | 默认为false，不隐藏
avatarShape | int | 头像对应形状风格 | 0为圆形，1为方形，默认为圆形
tipsTextColor	| int | 提示类消息的字体颜色（包括分配客服消息，消息时间标签等） | 32 位颜色值
tipsTextSize	| float | 提示类消息的字体大小（包括分配客服消息，消息时间标签等） | 单位为 sp
msgItemBackgroundLeft	| int |客服聊天窗口，左边消息项背景 | drawable resId
msgItemBackgroundRight	| int |客服聊天窗口，右边消息项背景 | drawable resId
textMsgColorLeft	| int | 客服聊天窗口, 左边文本消息的字体颜色 | 32 位颜色值
textMsgColorRight	| int | 客服聊天窗口, 右边文本消息的字体颜色 | 32 位颜色值
textMsgSize	| float | 文本消息的字体大小 | 单位为 sp
titleBackgroundResId	| int | 标题栏背景资源drawable | drawable resId
titleBackgroundColor	| int |标题栏背景颜色 | 32 位颜色值
headerIcon | int | 标题栏显示logo | drawable resId
hideHeaderIcon | boolean | 是否隐藏标题栏logo | 默认为false，不隐藏
headerTitle | String | 标题栏显示title | 默认为对应坐席昵称
hideHeaderTitle | boolean | 是否隐藏标题栏title | 默认为false，不隐藏
hideAudio | boolean | 是否隐藏语音按钮 | 默认为false，不隐藏
hideEmoji | boolean | 是否隐藏emoji表情按钮 | 默认为false，不隐藏
hideKeyboardOnEnterConsult | boolean | 是否进入咨询时隐藏键盘 | 默认为false，不隐藏
hidePhotographButton | boolean | 是否隐藏拍照按钮 | 默认为false，不隐藏
hideSendPictureButton | boolean | 是否隐藏相册选择按钮 | 默认为false，不隐藏

## 5.APP浏览轨迹
ZTIMCore中提供方法trackUserAccess(String title, boolean isEnterPage)，
进入界面在Activity或者Fragment中添加

```
	@Override
	protected void onResume() {
        super.onResume();
        ZTIMCore.userTrackHistory("MainActivity", true);
    }
```

对应的离开该页面时

```
	@Override
    protected void onPause() {
        super.onPause();
        ZTIMCore.userTrackHistory("MainActivity", false);
    }
```


## 6.对应权限添加

```
	<uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.GET_TASKS" />
```


## 7.混淆配置
如果你的 apk 最终会经过代码混淆，请在 proguard 配置文件中加入以下代码:

```
-dontwarn net.icsoc.**
-keep class net.icsoc.** {*;}
```





















