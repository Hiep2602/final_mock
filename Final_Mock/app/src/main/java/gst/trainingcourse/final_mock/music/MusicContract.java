package gst.trainingcourse.final_mock.music;

import java.util.List;

import gst.trainingcourse.final_mock.models.ItemMusic;

public interface MusicContract {
    interface View {
        void showInfoMusic(List<ItemMusic> itemMusics);
    }

    interface Presenter {
        void getDataMusic();
    }
}
