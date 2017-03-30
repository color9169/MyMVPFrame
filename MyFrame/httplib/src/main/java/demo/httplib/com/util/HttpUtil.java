package demo.httplib.com.util;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 网络请求工具类
 * Created by color on 17/3/21.
 */

public class HttpUtil {

    public static Map<String, String> headers;

    private static int id = 0;

    public static Map<String, String> getHttpHeaders() {
        if (headers == null) {
            headers = new LinkedHashMap<>();
        }
        return headers;
    }

    /**
     * 获取http请求参数
     * 传递空参数用
     *
     * @return http请求params 集合
     */
    public static Map<String, String> getHttpParams() {
        Map<String, String> params = new LinkedHashMap<>();
        return params;
    }

    public static void cancel(String url) {
        RequestCall call = OkHttpUtils.get().url(url).build();
        call.cancel();
    }

    public static void cancel(Context context) {
        OkHttpUtils.getInstance().cancelTag(context);
    }

    public static void get(String url, Map<String, String> params) {
        OkHttpUtils.get()
                .url(url)
                .params(params)
                .build()
                .buildCall(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });

    }

    public static void post(String url, String params, final StringCallback callback) {
        post(url, params, id, callback, getHttpHeaders());
    }

    /**
     * post请求方式，带id
     * 通过id判断请求逻辑类型，可以做一些逻辑处理
     *
     * @param url
     * @param params
     * @param id
     * @param callback
     */
    public static void post(String url, String params, int id, final StringCallback callback) {
        post(url, params, id, callback, getHttpHeaders());
    }


    public static void post(String url, String params, int id, final StringCallback callback, Map<String, String> headers) {
        String mediaType = "application/json;charset=utf-8";
        OkHttpUtils.postString()
                .url(url)
                .id(id)
                .mediaType(MediaType.parse(mediaType))
                .content(params)
                .headers(headers)
                .build()
                .execute(callback);
    }


    public static void post(String url, Map<String, String> params, StringCallback callback) {
        OkHttpUtils.post()
                .url(url)
                .params(params)
                .build()
                .execute(callback);
    }


    public static void post(String url, Map<String, String> params, StringCallback callback, Map<String, String> headers) {
        post(url, params, callback, getHttpHeaders());
    }


    public static void postFile(String url, String key, String fileName, File file, StringCallback callback) {
        postFile(url, key, fileName, file, callback, getHttpHeaders());
    }


    public static void postFile(String url, String key, String fileName, File file, StringCallback callback, Map<String, String> headers) {
        OkHttpUtils.post()
                .url(url)
                .addFile(key, fileName, file)
                .headers(headers)
                .build()
                .execute(callback);
    }


}
