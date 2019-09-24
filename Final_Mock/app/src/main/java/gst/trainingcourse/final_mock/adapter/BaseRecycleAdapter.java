package gst.trainingcourse.final_mock.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.utils.OnItemClick;

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> mData = new ArrayList<>();
    private OnItemClick onItemClick;


    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<T> getmData() {
        return mData;
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
        onBindView(holder, mData.get(postion), postion);
//        holder.itemView.setOnClickListener(v -> {
//            if (onItemClick != null) {
//                onItemClick.onItemClick(v, holder.getAdapterPosition());
//            }
//        });
//        holder.itemView.setOnLongClickListener(v -> {
//            onItemClick.onITemOnLongClick(v, holder.getAdapterPosition());
//            return true;
//        });
    }

    public T getData(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return getDataSize();
    }

    abstract protected int getViewId();

    abstract protected BaseViewHolder getViewHolder(View view);

    abstract protected void onBindView(BaseViewHolder holder, T model, int position);
}
