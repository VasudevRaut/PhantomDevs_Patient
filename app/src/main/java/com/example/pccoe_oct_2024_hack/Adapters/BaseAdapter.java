package com.example.pccoe_oct_2024_hack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;

import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> dataList;

    public BaseAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        bindData(holder, dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    // Abstract method to get the layout resource ID
    protected abstract int getLayoutId();

    // Abstract method to create the ViewHolder
    protected abstract VH createViewHolder(View view);

    // Abstract method to bind data to ViewHolder
    protected abstract void bindData(VH holder, T data);

    // Optional: Method to update the dataset
    public void updateData(List<T> newData) {
        this.dataList = newData;
        notifyDataSetChanged();
    }

}
