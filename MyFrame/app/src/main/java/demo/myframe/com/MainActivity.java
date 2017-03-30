package demo.myframe.com;

import android.os.Bundle;

import demo.httplib.com.util.ToastUtil;
import demo.myframe.com.base.BaseActivity;
import demo.myframe.com.callback.IMainActivity;
import demo.myframe.com.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements
        IMainActivity<String> {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this, this);
        //这个人比较懒。不仅地址没写，而且权限也木有加  o(*////▽////*)q
        presenter.postJson("接口地址自行查找", "加载中");
    }

    @Override
    public void success(String s, int id) {
        ToastUtil.showToast(s);
    }
}
