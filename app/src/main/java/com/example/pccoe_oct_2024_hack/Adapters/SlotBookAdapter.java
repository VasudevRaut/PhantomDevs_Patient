package com.example.pccoe_oct_2024_hack.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.R;

import java.util.List;

public class SlotBookAdapter extends BaseAdapter<SlotDTO, SlotBookAdapter.UserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(SlotDTO user);
    }
    private OnItemClickListener listener;

    public SlotBookAdapter(List<SlotDTO> userList, OnItemClickListener listener) {
        super(userList);
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.slot_item_card;  // Your layout for the user item
    }

    @Override
    protected UserViewHolder createViewHolder(View view) {
        return new UserViewHolder(view);
    }

    @Override
    protected void bindData(UserViewHolder holder, SlotDTO user) {
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(user);  // Pass the clicked user back to the activity
            }
        });
        //set here value to lebel
        holder.slotTime.setText(user.getTimeRange());

        // Check if the slot is booked or not
        if (user.isBooked()) {
            holder.bookButton.setText("Booked");
            holder.bookButton.setEnabled(false);  // Disable the button if booked
        } else {
            holder.bookButton.setText("Book");
            holder.bookButton.setEnabled(true);
        }
    }

    // ViewHolder for User items
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView slotTime;
        Button bookButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            slotTime = itemView.findViewById(R.id.tv_slot_time);
            bookButton = itemView.findViewById(R.id.btn_book_slot);
//            firstNameTextView = itemView.findViewById(R.id.firstNameTextView);
//            lastNameTextView = itemView.findViewById(R.id.lastNameTextView);
//            emailTextView = itemView.findViewById(R.id.emailTextView);
        }
    }
}
