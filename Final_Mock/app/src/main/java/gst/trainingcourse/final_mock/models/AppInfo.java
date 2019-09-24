package gst.trainingcourse.final_mock.models;

import android.graphics.drawable.Drawable;


public class AppInfo {
    private String filePathApk;
    private CharSequence label;
    private CharSequence packageName;
    private Drawable icon;
    private String versionName;

    public AppInfo(String filePathApk, CharSequence label, CharSequence packageName, Drawable icon, String versionName) {
        this.filePathApk = filePathApk;
        this.label = label;
        this.packageName = packageName;
        this.icon = icon;
        this.versionName = versionName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getFilePathApk() {
        return filePathApk;
    }

    public void setFilePathApk(String filePathApk) {
        this.filePathApk = filePathApk;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AppInfo) {

            AppInfo inforApp = (AppInfo) obj;
            return this.getLabel().equals(inforApp.getLabel());
        }
        return false;
    }
}
