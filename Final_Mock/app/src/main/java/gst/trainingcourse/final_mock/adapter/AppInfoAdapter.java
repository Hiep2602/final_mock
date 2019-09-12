package gst.trainingcourse.final_mock.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gst.trainingcourse.final_mock.AppInfo;
import gst.trainingcourse.final_mock.R;

public class AppInfoAdapter extends BaseRecycleAdapter<AppInfo> {

    @Override
    protected int getViewId() {
        return R.layout.item_app;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new AppInfoHoder(view);
    }

    @Override
    protected void onBindView(BaseViewHolder holder, AppInfo model) {
        AppInfoHoder hoder = (AppInfoHoder) holder;
        hoder.mTvNameApp.setText(model.getLabel());
        hoder.mImvApp.setImageDrawable(model.getIcon());
    }

    private static class AppInfoHoder extends BaseViewHolder {
        private ImageView mImvApp;
        private TextView mTvNameApp;

        public AppInfoHoder(@NonNull View view) {
            super(view);
            mImvApp = view.findViewById(R.id.iv_item_icon);
            mTvNameApp = view.findViewById(R.id.tv_item_name);
        }
    }
}
