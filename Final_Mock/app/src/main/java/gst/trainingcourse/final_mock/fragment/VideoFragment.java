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
import java.util.Objects;

import gst.trainingcourse.final_mock.ITemVideo;
import gst.trainingcourse.final_mock.MainActivity;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.VideoAdapter;
import gst.trainingcourse.final_mock.presenter.VideoPresenter;

public class VideoFragment extends BaseFragment {
    private RecyclerView mRvVideo;

    private List<ITemVideo> mListVideos;

    private VideoAdapter mVideoAdapter;

    private VideoPresenter mVideoPresenter;

    private VideoPresenter.VideoUi mVideoUi = new VideoPresenter.VideoUi() {
        @Override
        public void videoData(ArrayList<ITemVideo> itemPhotos) {
            if (mListVideos!=null){
                mListVideos.clear();
                mListVideos.addAll(itemPhotos);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.video_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvVideo = view.findViewById(R.id.rv_video);
        MainActivity mainActivity = (MainActivity) getActivity();
        if (Objects.requireNonNull(mainActivity).checkPermision(getContext())) {
            mVideoPresenter = new VideoPresenter(mVideoUi);
            mVideoPresenter.parseAllVideo(getActivity());
            mVideoAdapter = new VideoAdapter();
            mVideoAdapter.setData(mListVideos);
        }
        mRvVideo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRvVideo.setAdapter(mVideoAdapter);
    }


}
