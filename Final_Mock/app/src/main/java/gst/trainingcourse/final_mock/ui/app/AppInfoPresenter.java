package gst.trainingcourse.final_mock.ui.app;

import android.content.Context;

import java.util.List;

import gst.trainingcourse.final_mock.models.AppInfo;

public class AppInfoPresenter implements AppInfoContract.Presenter {
    private AppInfoContract.View view;
    private GetInfoApp getInfoApp;
    private Context context;

    public AppInfoPresenter(AppInfoContract.View view, Context context) {
        this.view = view;
        getInfoApp = new GetInfoApp();
        this.context = context;
    }

    @Override
    public void getAppInfo() {
        List<AppInfo> appInfos = getInfoApp.getmInfoApp(context);
        view.showAppInfo(appInfos);
    }
}
