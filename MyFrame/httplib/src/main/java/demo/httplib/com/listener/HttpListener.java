package demo.httplib.com.listener;

/**
 * Created by color on 17/3/21.
 */

public interface HttpListener {


    void success(String result, int id);

    void error(String error, int id);

    void failed(String failed, int failedCode, int id);


}
