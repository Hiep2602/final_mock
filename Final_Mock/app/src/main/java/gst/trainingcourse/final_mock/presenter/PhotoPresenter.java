package gst.trainingcourse.final_mock.presenter;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.worker.LoadPhoto;


public class PhotoPresenter {


    public void parseAllImages(Context context, PhotoUi photoUi) {

       new LoadPhoto(context,photoUi).execute();
    }


    public interface PhotoUi {
        void photoData(List<ItemPhoto> itemPhotos);

    }
}
