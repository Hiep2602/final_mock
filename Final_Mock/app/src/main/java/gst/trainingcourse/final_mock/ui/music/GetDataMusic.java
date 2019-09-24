package gst.trainingcourse.final_mock.ui.music;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.models.ItemMusic;

public class GetDataMusic {
    private List<ItemMusic> mItemMusics;

    public List<ItemMusic> getmItemMusic(Context context) {
        try {
            mItemMusics = new ArrayList<>();
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
                    String pathMusic = cur.getString(cur.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    music.setPathMusic(pathMusic);
                    mItemMusics.add(music);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mItemMusics;
    }

}
