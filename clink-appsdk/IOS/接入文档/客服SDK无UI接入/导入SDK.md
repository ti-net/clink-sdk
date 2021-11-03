1. 在工程项目中添加以下包:

TIMClientLib.framework
TIMClientKit.framework
WebP.framework
TIMClient.bundle
另外需要增加libc++.tbd来支持c++环境
​

2.  在Info.plist中添加Online SDK所需要的权限  
```objectivec
Privacy - Camera Usage Description

Privacy - Microphone Usage Description

Privacy - Photo Library Additions Usage Description

Privacy - Photo Library Usage Description
```
