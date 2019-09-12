package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.models.ItemMusic;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> {
    private ArrayList<ItemMusic> mMusics;

    private Context mContext;

    public MusicAdapter(Context context, ArrayList<ItemMusic> musics) {
        mContext = context;
        mMusics = musics;
    }


    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder musicHolder, int i) {

    }

    @Override
    public int getItemCount() {
        if (mMusics!=null){
            return mMusics.size();
        }else{
            return 0;
        }
    }

    static class MusicHolder extends RecyclerView.ViewHolder {
        public MusicHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
