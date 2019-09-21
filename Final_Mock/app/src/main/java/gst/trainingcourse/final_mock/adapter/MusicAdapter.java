package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemMusic;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> implements Filterable  {
    private ArrayList<ItemMusic> mMusics;

    private ArrayList<ItemMusic> mMusicsFull;

    private Context mContext;

    private ClickItemMusic mClickItemMusic;

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

    public MusicAdapter(Context context, ArrayList<ItemMusic> itemMusics,ClickItemMusic clickItemMusic) {
        mContext = context;
        mMusics = itemMusics;
        mClickItemMusic = clickItemMusic;
        mMusicsFull = new ArrayList<>(mMusics);
    }


    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_music, viewGroup, false);
        return new MusicHolder(view,mClickItemMusic);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder musicHolder, int position) {
        ItemMusic music = mMusics.get(position);
        musicHolder.mTvName.setText(music.getNameSong());
        musicHolder.mTvAuthor.setText(music.getAuthor());
        Log.d("music", "onBindViewHolder: " + music.getNameSong());
//        toggleCheckedIcon(musicHolder, i);
        displayImage(musicHolder, music);

    }



    private void displayImage(MusicHolder holder, ItemMusic music) {

        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        if (music.getPathImage() != null) {
            mmr.setDataSource(music.getPathImage());
            try {
                if (mmr != null) {
                    byte[] art = mmr.getEmbeddedPicture();
                    Bitmap bmp = BitmapFactory.decodeByteArray(art, 0, art.length);
                    if (bmp != null) {
                        bmp = ThumbnailUtils.extractThumbnail(bmp, 80, 50);
                        holder.mImageMusic.setImageBitmap(bmp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        private CircularImageView mImageMusic;
        public RelativeLayout lyt_checked, lyt_image;

        private ClickItemMusic mClickItemMusic;

        private TextView mTvName, mTvAuthor;

        MusicHolder(@NonNull View view, ClickItemMusic clickItemMusic) {
            super(view);
            this.mClickItemMusic = clickItemMusic;
            mImageMusic = view.findViewById(R.id.imv_arrtist);
            mTvName = view.findViewById(R.id.tvnamesong);
            mTvAuthor = view.findViewById(R.id.tvnamearrtist);
            view.setOnClickListener(v -> mClickItemMusic.clickItemMusic(getAdapterPosition()));
        }
    }

    public interface ClickItemMusic{
        void clickItemMusic(int position);
    }
}
