package gst.trainingcourse.final_mock.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

@Entity(tableName = "photo")
public class ItemPhoto {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "photoID") private int photoId;

    @ColumnInfo(name = "path") private String pathImage;

    @Ignore
    public ItemPhoto(String pathImage) {
        this.pathImage = pathImage;
    }

    public ItemPhoto() {}

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
       if (obj instanceof ItemPhoto) {
           ItemPhoto item = (ItemPhoto) obj;
           return this.photoId == item.photoId && this.pathImage.equals(item.pathImage);
       }
       return false;
    }
}
