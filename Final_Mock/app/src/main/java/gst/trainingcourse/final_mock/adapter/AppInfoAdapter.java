package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.models.AppInfo;
import gst.trainingcourse.final_mock.R;

public class AppInfoAdapter extends BaseRecycleAdapter<AppInfo> {
    private List<String> mSelectItem = new ArrayList<>();
    private Context mContext;

    public AppInfoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected int getViewId() {
        return R.layout.item_app;
    }

    @Override
    protected BaseViewHolder getViewHolder(View view) {
        return new AppInfoHoder(view);
    }

    @Override
    protected void onBindView(BaseViewHolder holder, AppInfo model, int position) {
        AppInfoHoder hoder = (AppInfoHoder) holder;
        hoder.mTvNameApp.setText(model.getLabel());
        hoder.mImvApp.setImageDrawable(model.getIcon());
        hoder.mTvPackgeName.setText(model.getPackageName());

        if (mSelectItem.contains(getData(position).getFilePathApk())) {
            hoder.itemView.setBackgroundColor(Color.BLUE);
        } else {
            hoder.itemView.setForeground(new ColorDrawable(ContextCompat.getColor(mContext, android.R.color.transparent)));
        }
    }

    public void setmSelectItem(List<String> mSelectItem) {
        this.mSelectItem = mSelectItem;
        notifyDataSetChanged();
    }

    private static class AppInfoHoder extends BaseViewHolder {
        private ImageView mImvApp;
        private TextView mTvNameApp, mTvPackgeName;

        public AppInfoHoder(@NonNull View view) {
            super(view);
            mImvApp = view.findViewById(R.id.iv_item_icon);
            mTvNameApp = view.findViewById(R.id.tv_item_name);
            mTvPackgeName = view.findViewById(R.id.tv_packagename);
        }
    }
}
