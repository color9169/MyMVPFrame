package demo.myframe.com.presenter;

import android.content.Context;

import demo.httplib.com.pressenter.BasePresenter;
import demo.myframe.com.callback.IMainActivity;

/**
 * Created by color on 17/3/30.
 */

public class MainPresenter extends BasePresenter {
    private IMainActivity mainActivity;

    public MainPresenter(Context context, IMainActivity mainActivity) {
        super(context);
        this.mainActivity = mainActivity;
    }

    @Override
    public void baseSuccess(String result, int id) {
        mainActivity.success(result, id);
    }

    @Override
    public void baseError(String error, int id) {
        mainActivity.error(error, id);
    }

    @Override
    public void baseFailed(String failed, int failedCode, int id) {
        mainActivity.failed(failed, failedCode, id);
    }
}
