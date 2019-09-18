package gst.trainingcourse.final_mock.adapter;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gst.trainingcourse.final_mock.ITemVideo;
import gst.trainingcourse.final_mock.R;

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
        videoHolder.tvNameVideo.setText(Html.fromHtml(model.getFileName()));
        Bitmap bitmap = ThumbnailUtils.
                extractThumbnail(ThumbnailUtils.createVideoThumbnail(model.getFilePath(),
                        MediaStore.Video.Thumbnails.MINI_KIND), 80, 50);
        if (bitmap != null) {
            videoHolder.imvVideo.setImageBitmap(bitmap);
        }
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
