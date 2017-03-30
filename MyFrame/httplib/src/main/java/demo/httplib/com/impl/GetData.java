package demo.httplib.com.impl;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.File;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import demo.httplib.com.R;
import demo.httplib.com.entity.Result;
import demo.httplib.com.listener.HttpListener;
import demo.httplib.com.util.HttpUtil;
import demo.httplib.com.util.NetUtils;
import okhttp3.Call;


/**
 * Created by color on 17/3/21.
 */

public class GetData implements IGetData {
    private String ok = "ok";
    private String result = "result";
    private int default_id = 0;
    private int success = 0;
    private KProgressHUD hud;
    private Context context;

    private boolean isShow = true;

    public GetData(Context context) {
        this.context = context;
        hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
    }

    @Override
    public void postJson(String url, String params, HttpListener listener) {
        String loading = context.getResources().getString(R.string.loading);
        postJson(url, loading, params, listener, default_id);
    }

    @Override
    public void postJson(String url, String msg, String params, HttpListener listener) {
        postJson(url, msg, params, listener, default_id, isShow);
    }

    @Override
    public void postJson(String url, String params, final HttpListener listener, final int id) {
        String loading = context.getResources().getString(R.string.loading);
        postJson(url, loading, params, listener, id, isShow);
    }

    @Override
    public void postJson(String url, String msg, String params, HttpListener listener, int id) {
        postJson(url, msg, params, listener, id, isShow);
    }

    @Override
    public void postJson(String url, String msg, String params, HttpListener listener, boolean isShow) {
        postJson(url, msg, params, listener, default_id, isShow);
    }


    @Override
    public void postJson(String url, String msg, String params, final HttpListener listener, final int id, boolean isShow) {
        if (NetUtils.isConnectShowToast()) {
            hud.setLabel(msg);
            if (isShow)
                hud.show();

            HttpUtil.post(url, params, id, new StringCallback() {
                @Override
                public void onResponse(String response, int id) {
                    hud.dismiss();
                    Result result = getResult(response);
                    if (success == result.getErr()) {
                        listener.success(response, id);
                    } else {
                        listener.failed(result.getMsg(), result.getErr(), id);
                    }
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    if (e instanceof EOFException ||
                            e instanceof SocketException ||
                            e instanceof ConnectException ||
                            e instanceof BindException ||
                            e instanceof UnknownHostException ||
                            e instanceof UnknownServiceException) {
                        hud.setLabel(context.getResources().getString(R.string.network_exception));
                        hud.dismiss();
                        listener.error(e.getMessage(), id);
                    }
                }
            });
        }
    }

    @Override
    public void postUpdateFile(String url, String msg, String key, String fileName, File file, HttpListener listener) {
        postUpdateFile(url, msg, key, fileName, file, listener, default_id);
    }

    @Override
    public void postUpdateFile(String url, String msg, String key, String fileName, File file, final HttpListener listener, final int id) {
        if (NetUtils.isConnectShowToast()) {
            HttpUtil.postFile(url, key, fileName, file, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    if (e instanceof EOFException ||
                            e instanceof SocketException ||
                            e instanceof ConnectException ||
                            e instanceof BindException ||
                            e instanceof UnknownHostException ||
                            e instanceof UnknownServiceException) {
                        hud.setLabel(context.getResources().getString(R.string.network_exception));
                        hud.dismiss();
                        listener.error(e.getMessage(), id);
                    }
                }

                @Override
                public void onResponse(String response, int id) {
                    hud.dismiss();
                    Result result = getResult(response);
                    if (success == result.getErr()) {
                        listener.success(response, id);
                    } else {
                        listener.failed(result.getMsg(), result.getErr(), id);
                    }
                }
            });
        }
    }


    /**
     * 获取result对象
     *
     * @param json
     * @return
     */
    private Result getResult(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String resultStr = jsonObject.getJSONObject(result).toString();
            Result r = JSON.parseObject(resultStr, Result.class);
            return r;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onDestroy() {
        HttpUtil.cancel(context);
        hud = null;
        System.gc();
    }

}
