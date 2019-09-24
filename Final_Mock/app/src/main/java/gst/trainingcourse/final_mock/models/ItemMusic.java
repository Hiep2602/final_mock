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
    private String pathMusic;

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

    public String getPathMusic() {
        return pathMusic;
    }

    public void setPathMusic(String pathMusic) {
        this.pathMusic = pathMusic;
    }

}
