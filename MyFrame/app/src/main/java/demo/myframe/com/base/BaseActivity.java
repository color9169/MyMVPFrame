package demo.myframe.com.base;

import android.support.v7.app.AppCompatActivity;

import demo.httplib.com.impl.BaseCallBack;
import demo.httplib.com.util.ToastUtil;

/**
 * Created by color on 17/3/30.
 */

public class BaseActivity extends AppCompatActivity implements BaseCallBack {


    //可在父类实现BaseCallback 来集中统一处理错误回调
    @Override
    public void error(String err, int id) {
        ToastUtil.showToast("error");
    }

    @Override
    public void failed(String failed, int errCode, int id) {
        ToastUtil.showToast("failed");
    }
}
