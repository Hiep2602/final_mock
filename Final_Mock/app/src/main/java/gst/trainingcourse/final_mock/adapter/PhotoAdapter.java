package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.models.ItemPhoto;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private ArrayList<ItemPhoto> mPhotos;

    private Context mContext;

    public PhotoAdapter(Context context, ArrayList<ItemPhoto> photos){
        mContext = context;
        mPhotos = photos;

    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder photoHolder, int i) {

    }

    @Override
    public int getItemCount() {
        if (mPhotos!=null){
            return mPhotos.size();
        }else {
            return 0;
        }
    }

    static class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView mImagePhoto;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
