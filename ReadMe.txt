
sdk 算法相关主要在uicameraopenailab.java


所有ui基于uikit

moon:
UIViewController和UIVIew

moon:
UIVIew是具体的ui


然后UIview里面调用android 系统的view 布局那套东西



HomeViewController是app的启动controller


所有场景切换通过UIviewcontroller跳转实现


UIViewController 的ViewDidLoad表示加载成功

一般在这里创建具体的UIView


ViewDidUnLoad是销毁时候调用


NaviViewController 这个是导航型的controller


PopViewController这个是在某个controller上弹出新的controller


其他的就和android 系统的UI一样了


FaceSDK 这个文件夹是人脸识别的聚合接口


openailab 和arc 是两个厂商的人脸识别sdk


现在设置的是openailab


Home 是主界面的人脸检测识别UI


Register是添加人脸的注册UI


FaceManager 是人脸数据库管理UI


Setting 是设置UI
