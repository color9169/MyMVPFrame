package demo.httplib.com.impl;

/**
 * Created by color on 17/3/22.
 */

public interface BaseCallBack {

    void error(String err, int id);

    void failed(String failed, int errCode, int id);
}
