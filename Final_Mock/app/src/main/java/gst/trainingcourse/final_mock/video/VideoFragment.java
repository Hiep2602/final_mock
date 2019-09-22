package gst.trainingcourse.final_mock.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
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
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.VideoAdapter;
import gst.trainingcourse.final_mock.models.ITemVideo;
import gst.trainingcourse.final_mock.utils.Constants;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class VideoFragment extends Fragment implements VideoContract.View {
    private RecyclerView rvVideo;
    private VideoAdapter mVideoAdapter;
    private List<Uri> uris;

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

    private OnItemClick mOnclickItem = new OnItemClick() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(getContext(), "asfaf" + position, Toast.LENGTH_SHORT).show();
            openVideo(position);
        }

        @Override
        public void onITemOnLongClick(View v,int position) {
            Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            toggleSelection(position);
        }
    };

    private void toggleSelection(int position) {
        mVideoAdapter.toggleSelection(position);
        int count = mVideoAdapter.getSelectedItemCount();

        if (count == 0) {

        } else {
            uris = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Uri u = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider", new File(mVideoAdapter.getmData()
                                .get(position).getFileVideo()));
                uris.add(u);
            }
            Log.d("hsize", "toggleSelection: " + uris.size());
        }
    }

    private void openVideo(int position) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri data = Uri.parse(mVideoAdapter.getmData().get(position).getFileVideo());
        intent.setDataAndType(data, "video/*");
        getContext().startActivity(intent);

    }

    @Override
    public void showVideo(List<ITemVideo> mItemVideos) {
        mVideoAdapter = new VideoAdapter(getContext());
        mVideoAdapter.setData(mItemVideos);
        mVideoAdapter.setOnItemClick(mOnclickItem);
        rvVideo.setAdapter(mVideoAdapter);
    }
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
}
