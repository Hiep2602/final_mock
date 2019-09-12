package gst.trainingcourse.final_mock;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private CharSequence label;
    private CharSequence packageName;
    private long installTime;
    private Drawable icon;


    public AppInfo(CharSequence label, CharSequence packageName, long installTime, Drawable icon) {
        this.label = label;
        this.packageName = packageName;
        this.installTime = installTime;
        this.icon = icon;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CharSequence label) {
        this.label = label;
    }

    public CharSequence getPackageName() {
        return packageName;
    }

    public void setPackageName(CharSequence packageName) {
        this.packageName = packageName;
    }

    public long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(long installTime) {
        this.installTime = installTime;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AppInfo) {

            AppInfo inforApp = (AppInfo) obj;
            return this.getLabel().equals(inforApp.getLabel());
        }
        return false;
    }

}
