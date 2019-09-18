package gst.trainingcourse.final_mock.presenter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.models.ItemMusic;

public class MusicPresenter {

    private MusicUi mMusicUi;


    public MusicPresenter(MusicUi musicUi) {
        mMusicUi = musicUi;

    }

    public void parseAllAudio(Context context) {
        try {
            ArrayList<ItemMusic> mItemMusics = new ArrayList<>();
            Cursor cur = context.getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                    null);

            if (cur == null) {
            } else if (!cur.moveToFirst()) {
            } else {
                do {
                    int artistColumn = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int filePathIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    ItemMusic music = new ItemMusic();
                    music.setNameSong(cur.getString(titleColumn));
                    music.setPathImage(cur.getString(filePathIndex));
                    music.setAuthor(cur.getString(artistColumn));
                    mItemMusics.add(music);
                    mMusicUi.musicData(mItemMusics);

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface MusicUi {
        void musicData(ArrayList<ItemMusic> musics);

    }
}
