package com.example.pccoe_oct_2024_hack.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.R;

import java.util.List;

public class UserMedicalHistoryAdapter extends BaseAdapter<UserMedicalHistoryDTO, UserMedicalHistoryAdapter.UserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(UserMedicalHistoryDTO user);
    }
    private OnItemClickListener listener;

    public UserMedicalHistoryAdapter(List<UserMedicalHistoryDTO> userMedicalHistoryList, OnItemClickListener listener) {
        super(userMedicalHistoryList);
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_medical_history_card;  // Your layout for the user item
    }

    @Override
    protected UserViewHolder createViewHolder(View view) {
        return new UserViewHolder(view);
    }

    @Override
    protected void bindData(UserViewHolder holder, UserMedicalHistoryDTO userMedicalHistory) {
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(userMedicalHistory);  // Pass the clicked user back to the activity
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
