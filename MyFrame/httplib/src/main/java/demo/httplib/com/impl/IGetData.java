package demo.httplib.com.impl;


import java.io.File;

import demo.httplib.com.listener.HttpListener;

/**
 * Created by color on 17/3/21.
 */

public interface IGetData {
    void postJson(String url, String params, final HttpListener listener);

    void postJson(String url, String msg, String params, final HttpListener listener);

    void postJson(String url, String params, final HttpListener listener, final int id);

    void postJson(String url, String msg, String params, final HttpListener listener, final int id);

    void postJson(String url, String msg, String params, HttpListener listener, boolean isShow);

    void postJson(String url, String msg, String params, final HttpListener listener, final int id, boolean isShow);

    void postUpdateFile(String url, String msg, String key, String fileName, File file, HttpListener listener);

    void postUpdateFile(String url, String msg, String key, String fileName, File file, HttpListener listener, final int id);

    void onDestroy();
}
