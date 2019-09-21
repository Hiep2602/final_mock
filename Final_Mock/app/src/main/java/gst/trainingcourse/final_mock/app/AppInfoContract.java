package gst.trainingcourse.final_mock.app;

import java.util.List;

import gst.trainingcourse.final_mock.models.AppInfo;

public interface AppInfoContract {
    interface View {
        void showAppInfo(List<AppInfo> appInfos);
    }

    interface Presenter {
        void getAppInfo();
    }
}
