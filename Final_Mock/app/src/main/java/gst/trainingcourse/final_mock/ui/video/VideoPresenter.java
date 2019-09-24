package gst.trainingcourse.final_mock.ui.video;

import android.content.Context;

import java.util.List;

import gst.trainingcourse.final_mock.models.ITemVideo;

public class VideoPresenter implements VideoContract.Presenter {
    private VideoContract.View view;
    private GetInfoVideo getInfo;
    private Context context;

    public VideoPresenter(VideoContract.View view, Context context) {
        this.view = view;
        getInfo = new GetInfoVideo();
        this.context = context;
    }

    @Override
    public void getVideo() {
        List<ITemVideo> mItemVideos = getInfo.getmItemVideos(context);
        view.showVideo(mItemVideos);
    }
}
