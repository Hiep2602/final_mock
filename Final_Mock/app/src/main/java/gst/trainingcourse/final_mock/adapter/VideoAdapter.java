package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import gst.trainingcourse.final_mock.models.ITemVideo;
import gst.trainingcourse.final_mock.R;

public class VideoAdapter extends BaseRecycleAdapter<ITemVideo> {
    private Context mContext;

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected int getViewId() {
        return R.layout.item_video;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new VideoHolder(view);
    }

    @Override
    protected void onBindView(BaseViewHolder holder, ITemVideo model,int position) {
        VideoHolder videoHolder = (VideoHolder) holder;
        videoHolder.tvNameVideo.setText(model.getFileName());
        Glide.with(mContext).load(model.getFileVideo())
                .skipMemoryCache(false)
                .into(videoHolder.imvVideo);


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
