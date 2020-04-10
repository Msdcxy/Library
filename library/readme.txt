git地址 ： https://github.com/Msdcxy/Library.git

打包成arr命令
                    gradlew install
                    gradlew uploadArchives
                    gradlew clean uploadArchives
远程地址：https://jitpack.io/


===========项目参考=======================
状态栏：https: //github.com/gyf-dev/ImmersionBar

版本介绍：
1.0.1 ：增加状态栏的颜色配置，

    覆盖颜色值
     <!-- 状态栏颜色值-->
    <color name="color_status_bar">#008577</color>

    针对状态栏颜色做修改(根据自己项目去做修改)
    1 状态栏为白色： 在setContentView之前调用 setDarkFont（true）

    2 透明则需要重
