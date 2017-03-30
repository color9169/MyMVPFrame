# MyMVPFrame
重构项目时候抽象封装出来的一个小框架，
MVP结构按照自己的理解来创建的。

>所有的Presenter继承BasePresenter，而在BasePresenter中，除了暴露出对外调用的方法外，还处理了默认情况下的网络请求处理，并提供了三个子类，来处理不同的返回结果。

>网络请求部分，返回值中都带了一个id，通过id可以判断返回的数据需要在做何种处理，比如说刷新和加载的时候需要做不同的处理。

>BaseCallBack接口中统一提供了  error 和 failed两个方法，用来处理网络请求异常或者失败的情况下，对应的处理，页面对应的接口可直接继承自此接口，而在本身中只对正确结果做处理就好了。

>BaseCallBack可以实现在BaseActivity或者BaseFragment中，在父类中统一处理，可以保证自身代码的简洁性。没有特定需求的时候，已经足够了。

用法很简单，看下代码基本就会了。

代码中用到了两个第三方的库
*自定义Loading框[https://github.com/Kaopiz/KProgressHUD]
*鸿洋的网络请求 [https://github.com/hongyangAndroid/okhttputils]
