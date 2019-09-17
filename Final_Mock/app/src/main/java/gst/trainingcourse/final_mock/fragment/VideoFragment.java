package gst.trainingcourse.final_mock.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.ITemVideo;
import gst.trainingcourse.final_mock.MainActivity;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.VideoAdapter;

public class VideoFragment extends BaseFragment {
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
        try {


            if (checkPermision(getContext())) {
                ITemVideo mItemVideo = new ITemVideo();
                mItemVideo.parseAllVideo(getContext());
                mListVideos = new ArrayList<>();
                mListVideos.add(mItemVideo);
                Toast.makeText(getContext(), checkPermision(getContext()) + " " + mListVideos.size(), Toast.LENGTH_SHORT).show();
                VideoAdapter adapter = new VideoAdapter();
                adapter.setData(mListVideos);
                rvVideo = view.findViewById(R.id.rv_video);
                rvVideo.setLayoutManager(new LinearLayoutManager(getContext()));
                rvVideo.setAdapter(adapter);
            }
        } catch (NullPointerException e) {
            Log.d("null", "onViewCreated: ");
        }
    }
}
