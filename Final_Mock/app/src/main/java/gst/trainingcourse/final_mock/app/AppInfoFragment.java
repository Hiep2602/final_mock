package gst.trainingcourse.final_mock.app;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.models.AppInfo;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.AppInfoAdapter;
import gst.trainingcourse.final_mock.fragment.BaseFragment;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class AppInfoFragment extends BaseFragment implements AppInfoContract.View {
    private RecyclerView rvAppInfo;
    private AppInfoPresenter mAppInfoPresenter;
    private AppInfoAdapter mAppInfoAdapter;
    private FloatingActionButton fab;
    private List<Uri> uris;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getInfoApp();
    }

    private void initView(View view) {
        fab = getActivity().findViewById(R.id.fab);
        rvAppInfo = view.findViewById(R.id.rvappinfo);
        fab.setOnClickListener(clickFab);
    }

    private void getInfoApp() {
        mAppInfoPresenter = new AppInfoPresenter(this, getContext());
        mAppInfoPresenter.getAppInfo();


    }

    private void toggleSelection(int position) {
        mAppInfoAdapter.toggleSelection(position);
        int count = mAppInfoAdapter.getSelectedItemCount();

        uris = new ArrayList<>();
        if (count == 0) {

        } else {
            for (int i = 0; i < count; i++) {
                Uri u = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider", new File(mAppInfoAdapter.getmData()
                                .get(position).getFilePathApk()));
                uris.add(u);
            }
            Log.d("hsize", "toggleSelection: " + uris.size());
        }
    }

    @Override
    public void showAppInfo(List<AppInfo> appInfos) {
        mAppInfoAdapter = new AppInfoAdapter();
        mAppInfoAdapter.setData(appInfos);
        mAppInfoAdapter.setOnItemClick(mOnclickItem);
        rvAppInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAppInfo.setAdapter(mAppInfoAdapter);
    }

    private OnItemClick mOnclickItem = new OnItemClick() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(getContext(), "asfaf" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onITemOnLongClick(int position) {
            Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            toggleSelection(position);
            Log.d("zzz", "onITemOnLongClick: " + uris.size());
        }
    };


    private View.OnClickListener clickFab = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (uris == null) {
                Snackbar.make(v, "No Item Selects", Toast.LENGTH_SHORT).show();
            } else {
                shareData(uris, "*/*");
            }
        }
    };
}
