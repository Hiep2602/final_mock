package gst.trainingcourse.final_mock.fragment;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.models.ITemVideo;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.VideoAdapter;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class VideoFragment extends BaseFragment implements OnItemClick {
    private RecyclerView rvVideo;
    private List<ITemVideo> mListVideos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ITemVideo mItemVideo = new ITemVideo();
        mListVideos = mItemVideo.parseAllVideo(getContext());
        VideoAdapter adapter = new VideoAdapter();
        adapter.setData(mListVideos);
        adapter.setOnItemClick(this);
        rvVideo = view.findViewById(R.id.rv_video);
        rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));
        rvVideo.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
//        SharePK(position);
        openVideo(position);
    }

    @Override
    public void onITemOnLongClick(View view, Object T, int position) {

    }

    public void SharePK(int position) {
        try {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("video/*");
            Log.d("mp4", "SharePK: " + mListVideos.get(position).getFileVideo());
            share.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getContext(),
                    BuildConfig.APPLICATION_ID + ".provider", new File(mListVideos.get(position).getFileVideo())));
//            share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(mListVideos.get(position).getFileName())));
            getContext().startActivity(share);
        } catch (Exception e) {
            Log.e("ShareApp", e.getMessage());
        }

    }

    private void openVideo(int position) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri data = Uri.parse(mListVideos.get(position).getFileVideo());
        intent.setDataAndType(data, "video/*");
        getContext().startActivity(intent);

    }
}
