package gst.trainingcourse.final_mock.presenter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.models.ItemMusic;

public class MusicPresenter {

    private MusicUi mMusicUi;


    public MusicPresenter( MusicUi musicUi){
        mMusicUi = musicUi;

    }

    public void parseAllMusic(Context context) {

        ArrayList<ItemMusic> mItemMusics = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor mCursor = context.getContentResolver().query(uri,
                null, null, null,
                MediaStore.MediaColumns.DATE_ADDED + " DESC");
        if (mCursor != null) {

            int indexName = mCursor.getColumnIndex(
                    MediaStore.Audio.Media.TITLE
            );
            int indexAuthor = mCursor.getColumnIndex(
                    MediaStore.Audio.Media.ARTIST
            );
            int duration = mCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            Cursor cursorAlbum;
            int albumId = mCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            mCursor.moveToFirst();
            while (!mCursor.isAfterLast()) {
                String author = mCursor.getString(indexAuthor);
                String title = mCursor.getString(indexName);
                int duration1 = mCursor.getInt(duration);
                String idAlbum = mCursor.getString(albumId);
                cursorAlbum = context.getContentResolver()
                        .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                                new String[]{MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                                MediaStore.Audio.Albums._ID + "=" + idAlbum,
                                null, null);

                if (cursorAlbum != null) {
                    int album = cursorAlbum.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
                    cursorAlbum.moveToFirst();
                    while (!cursorAlbum.isAfterLast()) {
                        String albumCoverPath = cursorAlbum.getString(album);
                        mItemMusics.add(new ItemMusic(author, title, albumCoverPath, duration1));
                        cursorAlbum.moveToNext();
                    }
                }

                mCursor.moveToNext();
                mMusicUi.musicData(mItemMusics);
                if (cursorAlbum != null) {
                    cursorAlbum.close();
                }
            }

            mCursor.close();
        }
    }


    public interface MusicUi {
        void musicData(ArrayList<ItemMusic> musics);

    }
}
