package gst.trainingcourse.final_mock.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemMusic;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class MusicsAdapter extends BaseRecycleAdapter<ItemMusic> {
    private OnItemClick onItemClick;

    public MusicsAdapter(OnItemClick onItemClick) {
        super();
        this.onItemClick = onItemClick;
    }

    @Override
    protected int getViewId() {
        return R.layout.item_music;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new MusicHolder(view);
    }

    @Override
    protected void onBindView(BaseViewHolder holder, ItemMusic model, int position) {
        MusicHolder musicHolder = (MusicHolder) holder;
        musicHolder.mTvName.setText(model.getNameSong());
        musicHolder.mTvAuthor.setText(model.getAuthor());
        displayImage(musicHolder, model);
        musicHolder.lyt_parent.setOnLongClickListener(v -> {
            onItemClick.onITemOnLongClick(position);
            return true;
        });
        musicHolder.lyt_parent.setOnClickListener(v -> {
            onItemClick.onItemClick(position);
        });
        toggleCheckedIcon(musicHolder, position);
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

    private static class MusicHolder extends BaseViewHolder {
        private CircularImageView mImageMusic;
        private RelativeLayout lyt_checked, lyt_image;
        private View lyt_parent;
        private TextView mTvName, mTvAuthor;

        public MusicHolder(@NonNull View view) {
            super(view);
            mImageMusic = view.findViewById(R.id.imv_arrtist);
            mTvName = view.findViewById(R.id.tvnamesong);
            mTvAuthor = view.findViewById(R.id.tvnamearrtist);
            lyt_parent = view.findViewById(R.id.lyt_parent);
        }
    }
}
