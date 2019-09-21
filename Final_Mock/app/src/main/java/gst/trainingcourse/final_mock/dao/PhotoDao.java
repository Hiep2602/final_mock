package gst.trainingcourse.final_mock.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import gst.trainingcourse.final_mock.models.ItemPhoto;

@Dao
public interface PhotoDao {
    @Insert
    Long[] insertPhoto(List<ItemPhoto> items);

    @Query("SELECT * FROM photo")
    List<ItemPhoto> getListPhoto();

    @Query("DELETE FROM photo")
    int deleteAllPhoto();

}
