package gst.trainingcourse.final_mock.models;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class ItemMusic {
    private String author;

    private String nameSong;

    private String pathImage;

    private int duration;

    public ItemMusic() {
    }

    public ItemMusic(String author, String nameSong, String pathImage, int duration) {
        this.author = author;
        this.nameSong = nameSong;
        this.pathImage = pathImage;
        this.duration = duration;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDuration() {
        return duration;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }



    //    public void parseAllMusic(Context context) {
//
//        ArrayList<ItemMusic> mItemMusics = new ArrayList<>();
//        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Cursor mCursor = context.getContentResolver().query(uri,
//                null, null, null,
//                MediaStore.MediaColumns.DATE_ADDED + " DESC");
//        if (mCursor != null) {
//
//            int indexName = mCursor.getColumnIndex(
//                    MediaStore.Audio.Media.TITLE
//            );
//            int indexAuthor = mCursor.getColumnIndex(
//                    MediaStore.Audio.Media.ARTIST
//            );
//            int duration = mCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
//            Cursor cursorAlbum;
//            int albumId = mCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
//            mCursor.moveToFirst();
//            while (!mCursor.isAfterLast()) {
//                String author = mCursor.getString(indexAuthor);
//                String title = mCursor.getString(indexName);
//                int duration1 = mCursor.getInt(duration);
//                String idAlbum = mCursor.getString(albumId);
//                cursorAlbum = context.getContentResolver()
//                        .query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
//                                new String[]{MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
//                                MediaStore.Audio.Albums._ID + "=" + idAlbum,
//                                null, null);
//
//                if (cursorAlbum != null) {
//                    int album = cursorAlbum.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
//                    cursorAlbum.moveToFirst();
//                    while (!cursorAlbum.isAfterLast()) {
//                        String albumCoverPath = cursorAlbum.getString(album);
//                        mItemMusics.add(new ItemMusic(author, title, albumCoverPath, duration1));
//                        cursorAlbum.moveToNext();
//                    }
//                }
//                mCursor.moveToNext();
//
//            }
//            mCursor.close();
//        }
//    }
}
