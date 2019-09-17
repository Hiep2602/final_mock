package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemPhoto;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder>{
    private ArrayList<ItemPhoto> mPhotos;

    private Context mContext;



    public PhotoAdapter(Context context, ArrayList<ItemPhoto> photos) {
        mContext = context;
        mPhotos = photos;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.photo_row_item, viewGroup, false);
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder photoHolder, int i) {
        ItemPhoto photo=mPhotos.get(i);
        Glide.with(mContext).load(photo.getPathImage()).into(photoHolder.mImagePhoto);
    }

    @Override
    public int getItemCount() {
        return (mPhotos != null ? mPhotos.size() : 0);
    }



    static class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView mImagePhoto;

        PhotoHolder(@NonNull View itemView) {
            super(itemView);
            mImagePhoto = itemView.findViewById(R.id.img_photo);
        }
    }

}
