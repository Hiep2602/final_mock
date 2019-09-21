package gst.trainingcourse.final_mock.photo;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.models.ItemPhoto;

public class GetPhoto {

    private List<ItemPhoto> mItemPhoto;

    public List<ItemPhoto> getmItemPhoto(Context context) {
        mItemPhoto = new ArrayList<>();
        try {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);


            assert cursor != null;
            int size = cursor.getCount();

            if (size == 0) {
                throw new  Exception();
            } else {
                while (cursor.moveToNext()) {
                    int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    String path = cursor.getString(file_ColumnIndex);
                    ItemPhoto photoInfo = new ItemPhoto();
                    photoInfo.setPathImage(path);
                    mItemPhoto.add(photoInfo);
                }
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mItemPhoto;
    }
}
