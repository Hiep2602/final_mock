package gst.trainingcourse.final_mock.ui.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.models.AppInfo;

public class GetInfoApp {
    private List<AppInfo> mAppInfos;

    public List<AppInfo> getmInfoApp(Context context) {
        mAppInfos = new ArrayList<>();
        List<ApplicationInfo> applicationInfos = context.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo mInfo : applicationInfos) {
            if (context.getPackageManager().getLaunchIntentForPackage(mInfo.packageName) != null) {
                try {
                    mAppInfos.add(new AppInfo(mInfo.publicSourceDir, mInfo.loadLabel(context.getPackageManager())
                            .toString(), mInfo.packageName, mInfo.loadIcon(context.getPackageManager()), context.getPackageManager().getPackageInfo(mInfo.packageName, 0).versionName));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return mAppInfos;
    }
}
