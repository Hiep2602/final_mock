package gst.trainingcourse.final_mock.ui.photo;

import android.content.Context;

import java.util.List;

import gst.trainingcourse.final_mock.models.ItemPhoto;

public class PhotoPresenter implements PhotoContract.Presenter {
    private PhotoContract.View view;
    private Context mContext;
    private GetPhoto mGetPhoto;

    public PhotoPresenter(PhotoContract.View view, Context mContext) {
        this.view = view;
        this.mContext = mContext;
        mGetPhoto = new GetPhoto();
    }

    @Override
    public void getItemPhoto() {
        List<ItemPhoto> mItemPhotos = mGetPhoto.getmItemPhoto(mContext);
        view.showPhoto(mItemPhotos);
    }
}
