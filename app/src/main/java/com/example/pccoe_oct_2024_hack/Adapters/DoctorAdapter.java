package com.example.pccoe_oct_2024_hack.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.R;

import java.util.List;

public class DoctorAdapter extends BaseAdapter<DoctorDTO, DoctorAdapter.UserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(DoctorDTO user);
    }
    private OnItemClickListener listener;

    public DoctorAdapter(List<DoctorDTO> userList, OnItemClickListener listener) {
        super(userList);
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.doctor_list_card;  // Your layout for the user item
    }

    @Override
    protected UserViewHolder createViewHolder(View view) {
        return new UserViewHolder(view);
    }

    @Override
    protected void bindData(UserViewHolder holder, DoctorDTO user) {
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(user);  // Pass the clicked user back to the activity
            }
        });
        //set here value to lebel
    }

    // ViewHolder for User items
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView emailTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
//            firstNameTextView = itemView.findViewById(R.id.firstNameTextView);
//            lastNameTextView = itemView.findViewById(R.id.lastNameTextView);
//            emailTextView = itemView.findViewById(R.id.emailTextView);
        }
    }
}
