package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemMusic;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class MusicsAdapter extends BaseRecycleAdapter<ItemMusic> {
    private List<String> itemSelected;
    private Context context;

    public MusicsAdapter(Context context) {
        super();
        this.context = context;
        itemSelected = new ArrayList<>();
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

        if (itemSelected.contains(getmData().get(position).getPathMusic())) {
            musicHolder.itemView.setBackgroundColor(Color.BLUE);
        } else {
            musicHolder.itemView.setForeground(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
        }

    }

    public ItemMusic getItem(int position) {
        return getmData().get(position);
    }

    public void setItemSelected(List<String> itemSelected) {
        this.itemSelected = itemSelected;
        notifyDataSetChanged();
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
        private TextView mTvName, mTvAuthor;

        public MusicHolder(@NonNull View view) {
            super(view);
            mImageMusic = view.findViewById(R.id.imv_arrtist);
            mTvName = view.findViewById(R.id.tvnamesong);
            mTvAuthor = view.findViewById(R.id.tvnamearrtist);
        }
    }
}
