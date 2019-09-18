package gst.trainingcourse.final_mock.worker;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.presenter.PhotoPresenter;

public class LoadPhoto extends AsyncTask<Void, Integer, ArrayList<ItemPhoto>> {



    private WeakReference<Context> mWeakReference;
    private ArrayList<ItemPhoto> mItemPhotos;
    private PhotoPresenter.PhotoUi mPhotoUi;

    public LoadPhoto(Context context, PhotoPresenter.PhotoUi photoUi) {
        mWeakReference = new WeakReference<>(context);
        mPhotoUi = photoUi;
    }

    @Override
    protected ArrayList<ItemPhoto> doInBackground(Void... voids) {

        Context context = mWeakReference.get();
        if (context == null) {
            throw new IllegalArgumentException("context must not null");
        }
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
                throw new  Exception();
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
//                    photoUi.photoData(photos);
            }

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return photos;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemPhoto> itemPhotos) {
        super.onPostExecute(itemPhotos);
        mItemPhotos = new ArrayList<>();
        mItemPhotos.clear();
        mItemPhotos.addAll(itemPhotos);
        Log.d("size", "onPostExecute: "+mItemPhotos.size());
        mPhotoUi.photoData(mItemPhotos);
    }
}
