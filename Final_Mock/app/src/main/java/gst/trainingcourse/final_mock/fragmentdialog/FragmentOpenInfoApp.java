package gst.trainingcourse.final_mock.fragmentdialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.AppInfo;

@SuppressLint("ValidFragment")
public class FragmentOpenInfoApp extends DialogFragment {
    private AppInfo mAppInfo;
    private Button mButtonLaunchApp;

    public FragmentOpenInfoApp(AppInfo appInfo) {
        this.mAppInfo = appInfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infoapp, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvPackageName, tvTargetSDK, tvVersionName, tvTime;
        tvPackageName = view.findViewById(R.id.tvpackage);
        tvTargetSDK = view.findViewById(R.id.tv_tagertversion);
        tvVersionName = view.findViewById(R.id.tv_version);
        tvTime = view.findViewById(R.id.tv_time);
        if (mAppInfo != null) {
            tvPackageName.setText(mAppInfo.getPackageName());
            tvVersionName.setText(mAppInfo.getVersionName());
            tvTime.setText(mAppInfo.getLabel());
        }
        mButtonLaunchApp = view.findViewById(R.id.btnlaunch);
        mButtonLaunchApp.setOnClickListener(mLaunchApp);

    }

    private final View.OnClickListener mLaunchApp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(mAppInfo.getPackageName().toString());
            startActivity(intent);
        }
    };
}
