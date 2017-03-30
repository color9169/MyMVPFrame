package demo.httplib.com.pressenter;

import android.content.Context;

import java.io.File;

import demo.httplib.com.impl.GetData;
import demo.httplib.com.impl.IGetData;
import demo.httplib.com.listener.HttpListener;

/**
 * Created by color on 17/3/21.
 */

public abstract class BasePresenter {
    protected IGetData getData;
    protected Context mContext;
    protected String contentKey = "content";
    protected String dataKey = "data";

    public BasePresenter(Context context) {
        this.mContext = context;
        getData = new GetData(context);
    }

    /**
     * 子类重写  protected方法
     * 通过id来判断一些页面逻辑问题
     * 不重写该方法，默认走此流程，有特定需求可以重写
     * 根据id来区分具体业务，直接传递回V层就可以
     */
    protected void postData(String url, String msg, String params, int id, boolean isShow) {
        getData.postJson(url, msg, params, new HttpListener() {
            @Override
            public void success(String result, int id) {
                baseSuccess(result, id);
            }

            @Override
            public void error(String error, int id) {
                baseError(error, id);
            }

            @Override
            public void failed(String failed, int failedCode, int id) {
                baseFailed(failed, failedCode, id);
            }
        }, id, isShow);
    }

    protected void postUpdateFile(String url, String msg, String key, String fileName, File file, HttpListener listener, int id) {
        getData.postUpdateFile(url, msg, key, fileName, file, new HttpListener() {
            @Override
            public void success(String result, int id) {
                baseSuccess(result, id);
            }

            @Override
            public void error(String error, int id) {
                baseError(error, id);
            }

            @Override
            public void failed(String failed, int failedCode, int id) {
                baseFailed(failed, failedCode, id);
            }
        }, id);
    }

    public void baseSuccess(String result, int id) {
    }

    public void baseError(String error, int id) {

    }

    public void baseFailed(String failed, int failedCode, int id) {
    }

    /**
     * 对外暴露  public方法
     */

    public void postJson(String url, String msg, String params) {
        postData(url, msg, params, 0, true);
    }

    public void postJson(String url, String msg) {
        postData(url, msg, "{}", 0, true);
    }

    public void postJson(String url, String params, boolean isShow) {
        postData(url, "", params, 0, isShow);
    }

    public void postJson(String url, String msg, int id) {
        postData(url, msg, "{}", id, true);
    }

    public void postJson(String url, String msg, int id, boolean isShow) {
        postData(url, msg, "{}", id, isShow);
    }

    public void postJson(String url, String msg, String params, int id) {
        postData(url, msg, params, id, true);
    }

    public void postJson(String url, String msg, String params, int id, boolean isShow) {
        postData(url, msg, params, id, isShow);
    }

    public void postJsonUpdateFile(String url, String msg, String key, String fileName, File file, HttpListener listener, int id) {
        postUpdateFile(url, msg, key, fileName, file, listener, id);
    }


    public void postJsonUpdateFile(String url, String msg, String key, String fileName, File file, HttpListener listener) {
        postUpdateFile(url, msg, key, fileName, file, listener, 0);
    }

    public void onDestroy() {
        getData.onDestroy();
        getData = null;
        System.gc();
    }
}
