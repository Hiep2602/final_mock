package gst.trainingcourse.final_mock.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.models.AppInfo;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.AppInfoAdapter;
import gst.trainingcourse.final_mock.utils.Constants;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class AppInfoFragment extends Fragment implements AppInfoContract.View {
    private RecyclerView rvAppInfo;
    private AppInfoPresenter mAppInfoPresenter;
    private AppInfoAdapter mAppInfoAdapter;
    private List<Uri> uris;
    private ActionMode actionMode;
    private ActionModeCallback actionModeCallback;


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
        actionModeCallback = new ActionModeCallback();
    }

    private void getInfoApp() {
        mAppInfoPresenter = new AppInfoPresenter(this, getContext());
        mAppInfoPresenter.getAppInfo();
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = ((AppCompatActivity) getContext()).startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        mAppInfoAdapter.toggleSelection(position);
        int count = mAppInfoAdapter.getSelectedItemCount();
        Log.d("count", "toggleSelection: " + count);
        if (count == 0) {
//            actionMode.finish();
        } else {
            uris = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Uri u = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider", new File(mAppInfoAdapter.getmData()
                                .get(position).getFilePathApk()));
                uris.add(u);
            }
            Log.d("hsize", "toggleSelection: " + uris.size());
//            actionMode.invalidate();
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
            if (mAppInfoAdapter.getSelectedItemCount() > 0) {
                enableActionMode(position);
            } else {
                Toast.makeText(getContext(), "asfaf" + position, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onITemOnLongClick(View v, int position) {
            Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            enableActionMode(position);
            Log.d("zzz", "onITemOnLongClick: " + uris.size());
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.senddata:
                if (uris != null) {
                    Constants.shareData(uris, "*/*", getContext());
                } else {
                    Toast.makeText(getContext(), "Please select item", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            mode.finish();
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAppInfoAdapter.clearSelections();
            actionMode = null;
        }


    }

}
