package gst.trainingcourse.final_mock.presenter;

import android.content.Context;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.worker.LoadPhoto;


public class PhotoPresenter {


    public void parseAllImages(Context context, PhotoUi photoUi) {
       new LoadPhoto(context,photoUi).execute();
    }


    public interface PhotoUi {
        void photoData(ArrayList<ItemPhoto> itemPhotos);

    }
}
