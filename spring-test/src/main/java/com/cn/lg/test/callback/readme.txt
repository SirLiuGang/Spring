该package实现了简单的测试函数使用时间的功能。

通过回调函数进行计算时间。

MyCallBack为自定义的回调函数

Tools为计算方法执行时间的工具类，在里边进行了函数的回调，获取方法的执行时间。

CallBackTest为测试类，实例化Tool工具类，然后实现execute接口，在实现类中调用需要测试执行时间的方法，即可获取到执行时间。


效果：
say hello
say no
[use time]:1016