package gst.trainingcourse.final_mock.app;

import android.content.Context;

import java.util.List;

import gst.trainingcourse.final_mock.models.AppInfo;

public class AppInfoPresenter implements AppInfoContract.Presenter {
    private AppInfoContract.View view;
    private InfoApp infoApp;
    private Context context;

    public AppInfoPresenter(AppInfoContract.View view, Context context) {
        this.view = view;
        infoApp = new InfoApp();
        this.context = context;
    }

    @Override
    public void getAppInfo() {
        List<AppInfo> appInfos = infoApp.getmInfoApp(context);
        view.showAppInfo(appInfos);
    }
}
