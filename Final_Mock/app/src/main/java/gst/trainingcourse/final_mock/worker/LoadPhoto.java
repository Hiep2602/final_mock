package gst.trainingcourse.final_mock.worker;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.database.BluetoothDatabase;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.presenter.PhotoPresenter;

public class LoadPhoto extends AsyncTask<Void, Integer, List<ItemPhoto>> {
    private WeakReference<Context> mWeakReference;

    private PhotoPresenter.PhotoUi mPhotoUi;

    public LoadPhoto(Context context, PhotoPresenter.PhotoUi photoUi) {
        mWeakReference = new WeakReference<>(context);
        mPhotoUi = photoUi;
    }

    @Override
    protected List<ItemPhoto> doInBackground(Void... strings) {

        Context context = mWeakReference.get();
        if (context == null) {
            throw new IllegalArgumentException("context must not null");
        }

        ArrayList<ItemPhoto> photos = new ArrayList<>();

        BluetoothDatabase database = BluetoothDatabase.getInstance(context);
        List<ItemPhoto> databaseList = database.getDaoPhoto().getListPhoto();
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null);
        assert cursor != null;
        int size = cursor.getCount();

        if (size != 0) {

            while (cursor.moveToNext()) {

                int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                String path = cursor.getString(file_ColumnIndex);

                ItemPhoto photoInfo = new ItemPhoto();
                photoInfo.setPathImage(path);
                photos.add(photoInfo);

            }
        }
        cursor.close();

        if (databaseList == null) {
            database.getDaoPhoto().insertPhoto(photos);
            return photos;
        } else {
            int databaseSize = databaseList.size();
            int photoSize = photos.size();

            if (databaseSize != photoSize) {
                database.getDaoPhoto().deleteAllPhoto();
                database.getDaoPhoto().insertPhoto(photos);
                return database.getDaoPhoto().getListPhoto();
            } else {
                for (int i = 0; i < photoSize; i++) {
                    if (photos.get(i) != databaseList.get(i)) {
                        database.getDaoPhoto().deleteAllPhoto();
                        database.getDaoPhoto().insertPhoto(photos);
                        return database.getDaoPhoto().getListPhoto();
                    }
                }
            }
            return databaseList;
        }
    }

    @Override
    protected void onPostExecute(List<ItemPhoto> itemPhotos) {
        super.onPostExecute(itemPhotos);
        mPhotoUi.photoData(itemPhotos);
    }
}
