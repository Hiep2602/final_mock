package gst.trainingcourse.final_mock.ui.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.RecyclerItemClickListener;
import gst.trainingcourse.final_mock.adapter.VideoAdapter;
import gst.trainingcourse.final_mock.models.ITemVideo;
import gst.trainingcourse.final_mock.utils.Constants;

public class VideoFragment extends Fragment implements VideoContract.View {
    private RecyclerView rvVideo;
    private VideoAdapter mVideoAdapter;
    private final List<Uri> uris = new ArrayList<>();
    private final List<String> itemSelected = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getInfoVideo();
    }

    private void initView(View view) {
        setHasOptionsMenu(true);
        rvVideo = view.findViewById(R.id.rv_video);
        rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void getInfoVideo() {
        VideoPresenter mVideoPresenter = new VideoPresenter(this, getContext());
        mVideoPresenter.getVideo();
    }

    private void openVideo(int position) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri data = Uri.parse(mVideoAdapter.getmData().get(position).getFileVideo());
        intent.setDataAndType(data, "video/*");
        Objects.requireNonNull(getContext()).startActivity(intent);

    }

    @Override
    public void showVideo(List<ITemVideo> mItemVideos) {
        mVideoAdapter = new VideoAdapter(getContext());
        mVideoAdapter.setData(mItemVideos);
        rvVideo.setAdapter(mVideoAdapter);
        rvVideo.addOnItemTouchListener(new RecyclerItemClickListener(getContext()
                , rvVideo, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (itemSelected.size() > 0) {
                    multiSelect(position);
                } else {
                    openVideo(position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                multiSelect(position);
            }
        }));
    }

    private void multiSelect(int position) {
        ITemVideo data = mVideoAdapter.getData(position);
        if (data != null) {
            if (itemSelected.contains(data.getFileVideo()))
                itemSelected.remove(data.getFileVideo());
            else
                itemSelected.add(data.getFileVideo());
            mVideoAdapter.setmItemSelected(itemSelected);
        }
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
