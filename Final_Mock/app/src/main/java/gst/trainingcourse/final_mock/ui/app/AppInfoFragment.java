package gst.trainingcourse.final_mock.ui.app;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.RecyclerItemClickListener;
import gst.trainingcourse.final_mock.fragmentdialog.FragmentOpenInfoApp;
import gst.trainingcourse.final_mock.fragmentdialog.FragmentPlayMusic;
import gst.trainingcourse.final_mock.models.AppInfo;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.AppInfoAdapter;
import gst.trainingcourse.final_mock.models.ItemMusic;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.utils.Constants;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class AppInfoFragment extends Fragment implements AppInfoContract.View {
    private RecyclerView rvAppInfo;
    private AppInfoPresenter mAppInfoPresenter;
    private AppInfoAdapter mAppInfoAdapter;
    private List<Uri> uris = new ArrayList<>();
    private final List<String> itemSelected = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_fragment, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getInfoApp();
    }

    private void initView(View view) {
        rvAppInfo = view.findViewById(R.id.rvappinfo);
        rvAppInfo.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getInfoApp() {
        mAppInfoPresenter = new AppInfoPresenter(this, getContext());
        mAppInfoPresenter.getAppInfo();
    }


    @Override
    public void showAppInfo(List<AppInfo> appInfos) {
        mAppInfoAdapter = new AppInfoAdapter(getContext());
        mAppInfoAdapter.setData(appInfos);
        rvAppInfo.setAdapter(mAppInfoAdapter);
        rvAppInfo.addOnItemTouchListener(new RecyclerItemClickListener(getContext()
                , rvAppInfo, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (itemSelected.size() > 0) {
                    multiSelect(position);
                } else {
                    FragmentOpenInfoApp fragmentOpenInfoApp = new FragmentOpenInfoApp(mAppInfoAdapter.getData(position));
                    fragmentOpenInfoApp.show(getFragmentManager(), "infoapp");
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                multiSelect(position);
            }
        }));
    }

    private void multiSelect(int position) {
        AppInfo data = mAppInfoAdapter.getData(position);
        if (data != null) {
            if (itemSelected.contains(data.getFilePathApk()))
                itemSelected.remove(data.getFilePathApk());
            else
                itemSelected.add(data.getFilePathApk());
            mAppInfoAdapter.setmSelectItem(itemSelected);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.senddata:
                if (itemSelected.size() != 0) {
                    for (int i = 0; i < itemSelected.size(); i++) {
                        Uri u = FileProvider.getUriForFile(Objects.requireNonNull(getContext()),
                                BuildConfig.APPLICATION_ID + ".provider", new File(itemSelected.get(i)));
                        uris.add(u);
                    }
                    Constants.shareData(uris, "*/*", getContext());
                } else {
                    Toast.makeText(getContext(), "Please selected item", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
