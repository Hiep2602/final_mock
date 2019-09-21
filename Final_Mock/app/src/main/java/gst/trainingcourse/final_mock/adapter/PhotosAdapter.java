package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class PhotosAdapter extends BaseRecycleAdapter<ItemPhoto> {
    private Context mContext;


    public PhotosAdapter(Context mContext) {
        super();
        this.mContext = mContext;

    }

    @Override
    protected int getViewId() {
        return R.layout.photo_row_item;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new PhotoHolder(view);
    }

    @Override
    protected void onBindView(BaseViewHolder holder, ItemPhoto model, int position) {
        PhotoHolder mPhotoHolder = (PhotoHolder) holder;
        Glide.with(mContext).load("file://" + model.getPathImage()).skipMemoryCache(false).into(mPhotoHolder.mImagePhoto);
        Log.d("pho", "onBindView: " + model.getPathImage());

    }


    private static class PhotoHolder extends BaseViewHolder {
        private ImageView mImagePhoto;

        PhotoHolder(@NonNull View itemView) {
            super(itemView);
            mImagePhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
