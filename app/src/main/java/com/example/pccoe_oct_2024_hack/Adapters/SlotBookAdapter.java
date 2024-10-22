package com.example.pccoe_oct_2024_hack.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.R;

import java.util.List;

public class SlotBookAdapter extends RecyclerView.Adapter<SlotBookAdapter.SlotViewHolder> {

    private List<SlotDTO> slotList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(SlotDTO slot, int position);
    }

    public SlotBookAdapter(List<SlotDTO> slotList, OnItemClickListener listener) {
        this.slotList = slotList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public SlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slot_item_card, parent, false);
        return new SlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotViewHolder holder, int position) {
        SlotDTO slot = slotList.get(position);

        holder.slotTime.setText(slot.getTimeRange());

        if (slot.isBooked()) {
            holder.bookButton.setText("Booked");
            holder.bookButton.setEnabled(false);
        } else {
            holder.bookButton.setText("Book");
            holder.bookButton.setEnabled(true);
        }

        holder.bookButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(slot, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return slotList.size();
    }

    public static class SlotViewHolder extends RecyclerView.ViewHolder {

        TextView slotTime;
        Button bookButton;

        public SlotViewHolder(@NonNull View itemView) {
            super(itemView);
            slotTime = itemView.findViewById(R.id.tv_slot_time);
            bookButton = itemView.findViewById(R.id.btn_book_slot);
        }
    }
}
