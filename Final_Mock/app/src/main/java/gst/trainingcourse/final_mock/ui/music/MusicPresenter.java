package gst.trainingcourse.final_mock.ui.music;

import android.content.Context;

import java.util.List;

import gst.trainingcourse.final_mock.models.ItemMusic;

public class MusicPresenter implements MusicContract.Presenter {
    private Context context;
    private MusicContract.View view;
    private GetDataMusic mGetData;

    public MusicPresenter(Context context, MusicContract.View view) {
        this.context = context;
        this.view = view;
        mGetData = new GetDataMusic();
    }

    @Override
    public void getDataMusic() {
        List<ItemMusic> mItemMusic = mGetData.getmItemMusic(context);
        view.showInfoMusic(mItemMusic);
    }
}
