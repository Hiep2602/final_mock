package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemMusic;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> implements Filterable {
    private ArrayList<ItemMusic> mMusics;

    private ArrayList<ItemMusic> mMusicsFull;

    private Context mContext;

    private Filter mMusicFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ItemMusic> filterItemMusics = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterItemMusics.addAll(mMusicsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ItemMusic item : mMusicsFull) {
                    if (item.getNameSong().toLowerCase().trim().contains(filterPattern)) {
                        filterItemMusics.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterItemMusics;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mMusics.clear();
            mMusics.addAll((ArrayList<ItemMusic>) results.values);
            notifyDataSetChanged();
        }
    };

    public MusicAdapter(Context context, ArrayList<ItemMusic> itemMusics) {
        mContext = context;
        mMusics = itemMusics;
        mMusicsFull = new ArrayList<>(mMusics);
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

    @Override
    public Filter getFilter() {
        return mMusicFilter;
    }

    static class MusicHolder extends RecyclerView.ViewHolder {
        private ImageView mImageMusic;

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
