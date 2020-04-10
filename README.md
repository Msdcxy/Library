# 基于Mvp模式的项目基础框架
1.集成方式：
方式一、Module方式导入

1.下载https://github.com/Msdcxy/Library 链接中源码，切换到自己的项目选择

(1)File—>New—>Import Module

(2)选择文件中的library库导入到自己的项目，并添加到自己的App中，编译完成。

方式二、implementation 库方式的引用

（1）、在项目Build.grale文件夹添加maven { url"https://jitpack.io" }

（2）、在app 的build.grale中加入引用：
 implementation 'com.github.Msdcxy:Library:1.0.1'
编译成功表示集成完毕。

觉得有帮助的小伙伴，右上角给个星赞下！！


