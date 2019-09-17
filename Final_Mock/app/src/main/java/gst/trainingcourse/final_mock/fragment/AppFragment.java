package gst.trainingcourse.final_mock.fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.AppInfo;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.AppInfoAdapter;

public class AppFragment extends BaseFragment {
    private List<AppInfo> mAppInfos;
    private AppInfoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvAppInfo = view.findViewById(R.id.rvappinfo);
        initData();
        adapter = new AppInfoAdapter();
        adapter.setData(mAppInfos);
        rvAppInfo.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvAppInfo.setAdapter(adapter);

    }

    private void initData() {
        mAppInfos = new ArrayList<>();
        List<ApplicationInfo> applicationInfos = getContext().getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo mInfo : applicationInfos) {
            if (getContext().getPackageManager().getLaunchIntentForPackage(mInfo.packageName) != null) {
                mAppInfos.add(new AppInfo(mInfo.loadLabel(getContext().getPackageManager()).toString(),
                        mInfo.packageName, new File(mInfo.sourceDir).
                        lastModified(), mInfo.loadIcon(getContext().getPackageManager())));
            }
        }
    }
}
