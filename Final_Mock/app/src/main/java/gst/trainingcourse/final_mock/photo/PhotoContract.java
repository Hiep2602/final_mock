package gst.trainingcourse.final_mock.photo;

import java.util.List;

import gst.trainingcourse.final_mock.models.ItemPhoto;

public interface PhotoContract {
    interface View {
        void showPhoto(List<ItemPhoto> itemPhotos);
    }

    interface Presenter {
        void getItemPhoto();
    }
}
