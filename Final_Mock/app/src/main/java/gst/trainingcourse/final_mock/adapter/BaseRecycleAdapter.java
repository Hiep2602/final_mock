package gst.trainingcourse.final_mock.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> mData = new ArrayList<>();

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    private int getDataSize() {
        return (mData == null ? 0 : mData.size());
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getViewId(), parent, false);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int postion) {
        onBindView(holder, mData.get(postion));
    }

    @Override
    public int getItemCount() {
        return getDataSize();
    }

    abstract protected int getViewId();

    abstract protected BaseViewHolder getViewHolder(View view);

    abstract protected void onBindView(BaseViewHolder holder, T model);
}
