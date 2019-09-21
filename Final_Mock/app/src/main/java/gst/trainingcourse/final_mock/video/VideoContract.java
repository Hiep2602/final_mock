package gst.trainingcourse.final_mock.video;

import java.util.List;

import gst.trainingcourse.final_mock.models.ITemVideo;

public interface VideoContract {
    interface View {
        void showVideo(List<ITemVideo> mItemVideos);
    }

    interface Presenter {
        void getVideo();
    }
}
