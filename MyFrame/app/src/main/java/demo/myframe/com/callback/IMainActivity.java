package demo.myframe.com.callback;

import demo.httplib.com.impl.BaseCallBack;

/**
 * Created by color on 17/3/30.
 */

public interface IMainActivity<T> extends BaseCallBack {
    void success(T t, int id);
}
