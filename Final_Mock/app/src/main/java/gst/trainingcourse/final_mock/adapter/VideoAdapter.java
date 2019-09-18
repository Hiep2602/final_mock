package gst.trainingcourse.final_mock.adapter;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gst.trainingcourse.final_mock.models.ITemVideo;
import gst.trainingcourse.final_mock.R;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class VideoAdapter extends BaseRecycleAdapter<ITemVideo> {
    @Override
    protected int getViewId() {
        return R.layout.item_video;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new VideoHolder(view);
    }

    @Override
    protected void onBindView(BaseViewHolder holder, ITemVideo model) {
        VideoHolder videoHolder = (VideoHolder) holder;
        videoHolder.tvNameVideo.setText(model.getFileName());
        Bitmap bitmap = ThumbnailUtils.
                extractThumbnail(ThumbnailUtils.createVideoThumbnail(model.getFilePath(),
                        MediaStore.Video.Thumbnails.MINI_KIND), 80, 50);
        if (bitmap != null) {
            videoHolder.imvVideo.setImageBitmap(bitmap);
        }
        FFmpegMediaMetadataRetriever mFFmpegMediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
        mFFmpegMediaMetadataRetriever.setDataSource(model.getFileVideo());
        String mVideoDuration = mFFmpegMediaMetadataRetriever.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_DURATION);
        long mTimeInMilliseconds = Long.parseLong(mVideoDuration);
        int sec = (int) (mTimeInMilliseconds / 1000) % 60;
        int min = (int) ((mTimeInMilliseconds / (1000 * 60)) % 60);
        int hr = (int) ((mTimeInMilliseconds / (1000 * 60 * 60)) % 24);
        Log.d("video", "onBindView: " + sec + ":" + min + ":" + hr);

    }

    private static class VideoHolder extends BaseViewHolder {
        private ImageView imvVideo;
        private TextView tvNameVideo;

        public VideoHolder(@NonNull View view) {
            super(view);
            imvVideo = view.findViewById(R.id.imv_video);
            tvNameVideo = view.findViewById(R.id.tvnamevideo);
        }
    }
}
