package demo.httplib.com.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by color on 17/3/22.
 */

public class ToastUtil {
    public static int Error = 0;
    public static int Success = 1;

    private static Context mContext;

    private ToastUtil() {
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    public static void showToastInWindow(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public enum ToastStyle {
        /**
         * 成功
         */
        Success,
        /**
         * 失败
         */
        Fail
    }
}
