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
    private SparseBooleanArray selected_items;
    private int current_selected_idx = -1;

    public BaseRecycleAdapter() {
        selected_items = new SparseBooleanArray();
    }

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
        holder.itemView.setActivated(selected_items.get(postion, false));
        holder.itemView.setOnClickListener(v -> {
            if (onItemClick != null) {
                onItemClick.onItemClick(holder.getAdapterPosition());
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            onItemClick.onITemOnLongClick(v, postion);
            return true;
        });
        toggleCheckedIcon(holder, postion);
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selected_items.size());
        for (int i = 0; i < selected_items.size(); i++) {
            items.add(selected_items.keyAt(i));
        }
        return items;
    }

    public void removeData(int position) {
        mData.remove(position);
        resetCurrentIndex();
    }

    protected void toggleCheckedIcon(BaseViewHolder holder, int position) {
        if (selected_items.get(position, false)) {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            if (current_selected_idx == position) resetCurrentIndex();
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
            if (current_selected_idx == position) resetCurrentIndex();
        }
    }

    public void clearSelections() {
        selected_items.clear();
        notifyDataSetChanged();
    }

    private void resetCurrentIndex() {
        current_selected_idx = -1;
    }

    public void toggleSelection(int pos) {
        current_selected_idx = pos;
        if (selected_items.get(pos, false)) {
            selected_items.delete(pos);
        } else {
            selected_items.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public int getSelectedItemCount() {
        return selected_items.size();
    }


    @Override
    public int getItemCount() {
        return getDataSize();
    }

    abstract protected int getViewId();

    abstract protected BaseViewHolder getViewHolder(View view);

    abstract protected void onBindView(BaseViewHolder holder, T model, int position);
}
