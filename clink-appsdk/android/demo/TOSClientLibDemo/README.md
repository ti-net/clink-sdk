# 打包发布注意事项
## 发布到jitpack库
### 打包 （online-sdk 项目）
###### 在gradle侧边栏执行所需要打包模块的assemble命令，打包完成后会再对应模块build/outputs下生成aar文件
==> ps:如mqtt模块有更新时，打包mqtt模块需要单独打包，并临时删除src/main下整个resources文件夹再进行打包，以避免资源冲突，打包完成再还原即可==

###### 拷贝生成的aar文件到OnlineSDK-Android项下对应模块即可
==> 为便于将打包后的aar文件拷贝到OnlineSDK-Android项目对应目录下，可直接执行gradle侧边栏tasks目录下的build module aar中buildModuleAAR自定义task任务，只需将online-sdk项目与OnlineSDK-Android项目放在同一目录，即可自动打包，自动将aar拷贝到对应目录

### 上传（OnlineSDK-Android 项目）
###### 打包aar完成后，在OnlineSDK-Android 项目对应模块下，需要在build.gradle文件中修改依赖aar为最新版本的aar，并删除旧版本aar
###### git直接将更新aar提交到远程仓库
###### 使用git命令或直接在远程仓库新建对应版本tag
所用命令示例如下：

```
//提交aar包到远程仓库
git add .
git commit -m "1.4.0版本提交"
git push

//打对应版本tag并提交到远程仓库作为正式发版版本号
git tag 1.4.0
git push origin 1.4.0
```


### 编译发布
###### 打开网址 [https://jitpack.io/](https://jitpack.io//) 查找框内输入https://github.com/ti-net-project/OnlineSDK-Android点击查找，查找成功可看到上传的tag版本，点击“Get it”进行编译，编译成功后即可在测试demo中引入新版本进行测试

> 以上操作完成便成功发布到jitpack库




## 打包所有模块aar合并后的完整SDK本地aar包
### 打包SDK完整aar包同样也需要基于（OnlineSDK-Android 项目）
###### 打开OnlineSDK-Android 项目，在gradle侧边栏执行tasks目录下的build os aar中buildOnlineServiceSDK自定义任务，即可完成完整AAR的打包。
###### 为便于aar包测试，只需将demo项目（TOSClientKitDemo）与 OnlineSDK-Android 放同一目录文件夹下，打包后的aar自动复制到TOSClientKitDemo项目app/libs目录下，只需修改app模块下build.gradle文件在线客服SDK依赖本地依赖即可进行本地完整aar调试。

