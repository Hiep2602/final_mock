package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemMusic;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> {
    private ArrayList<ItemMusic> mMusics = new ArrayList<>();

    private ArrayList<ItemMusic> mMusicsFull;

    private Context mContext;

    public MusicAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<ItemMusic> data) {

        mMusics.addAll(data);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.music_row_item, viewGroup, false);
        return new MusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder musicHolder, int i) {
        ItemMusic music = mMusics.get(i);
        musicHolder.mTvName.setText(music.getNameSong());
        musicHolder.mTvAuthor.setText(music.getAuthor());
        musicHolder.mTvSize.setText(String.valueOf(music.getDuration()));
        Glide.with(mContext).load(music.getPathImage()).into(musicHolder.mImageMusic);

    }

    @Override
    public int getItemCount() {
        if (mMusics != null) {
            return mMusics.size();
        } else {
            return 0;
        }
    }

    static class MusicHolder extends RecyclerView.ViewHolder {
        private ImageButton mImageMusic;

        private TextView mTvName, mTvAuthor, mTvSize;

        MusicHolder(@NonNull View itemView) {
            super(itemView);
            mImageMusic = itemView.findViewById(R.id.img_music);
            mTvName = itemView.findViewById(R.id.tv_music_name);
            mTvAuthor = itemView.findViewById(R.id.tv_music_author);
            mTvSize = itemView.findViewById(R.id.tv_music_size);
        }
    }
}
