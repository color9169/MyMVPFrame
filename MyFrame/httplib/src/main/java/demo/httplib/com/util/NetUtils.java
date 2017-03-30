package demo.httplib.com.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by color on 15/12/10.
 */
public class NetUtils {
    private NetUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * 判断网络是否连接
     *
     * @param
     * @return
     */
    public static boolean isConnected() {

        ConnectivityManager connectivity = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();

            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断网络是否连接
     *
     * @return
     */
    public static boolean isConnectShowToast() {

        ConnectivityManager connectivity = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();

            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                } else {

                    ToastUtil.showToastInWindow("网络未连接");
                }
            } else {
                ToastUtil.showToastInWindow("网络未连接");
            }
        } else {
            ToastUtil.showToastInWindow("网络未连接");
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi() {
        try {
            ConnectivityManager cm = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null)
                return false;
            return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
        } catch (Exception ee) {
            return false;
        }
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }


    /**
     * url 转码
     *
     * @param json
     * @param name
     * @param format
     * @return
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    public static String decoderURL(String json, String name, String format) throws JSONException, UnsupportedEncodingException {
        String url = URLDecoder.decode(new JSONObject(json).getString(name), format);

        return url;
    }

}
