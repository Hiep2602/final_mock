package gst.trainingcourse.final_mock.presenter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.models.ItemPhoto;

public class PhotoPresenter {
    private PhotoUi photoUi;

    public PhotoPresenter(PhotoUi photoUi) {
        this.photoUi = photoUi;
    }

    public void parseAllImages(Context context) {
        ArrayList<ItemPhoto> photos = new ArrayList<>();
        try {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, // Which columns to return
                    null,       // Return all rows
                    null,
                    null);


            assert cursor != null;
            int size = cursor.getCount();

            if (size == 0) {

            } else {

//                int thumbID = 0;
                while (cursor.moveToNext()) {

                    int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);



                    /*  Used to show image on view in LoadImagesFromSDCard class */
                    String path = cursor.getString(file_ColumnIndex);


                    ItemPhoto photoInfo = new ItemPhoto();
                    photoInfo.setPathImage(path);
                    photos.add(photoInfo);
                }
                photoUi.photoData(photos);
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface PhotoUi {
        void photoData(ArrayList<ItemPhoto> itemPhotos);

    }
}
