package com.example.pccoe_oct_2024_hack.Adapters;

import android.content.Context;
import android.opengl.Visibility;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.UserScreens.UserMedicalHistoryPresenter;

import java.util.List;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import org.checkerframework.checker.index.qual.GTENegativeOne;


public class UserMedicalHistoryAdapter extends BaseAdapter<UserMedicalHistoryDTO, UserMedicalHistoryAdapter.UserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(UserMedicalHistoryDTO user);
    }
    public interface OnCheckedChangeListener {
        void onCheckedChanged(UserMedicalHistoryDTO userMedicalHistoryDTO, boolean isChecked);
    }

    private OnItemClickListener listener;
    private List<UserMedicalHistoryDTO> userMedicalHistoryList;

    private OnCheckedChangeListener onCheckedChangeListener;
    Context context;
    public UserMedicalHistoryAdapter(Context context, List<UserMedicalHistoryDTO> userMedicalHistoryList, OnItemClickListener listener, OnCheckedChangeListener onCheckedChangeListener) {
        super(userMedicalHistoryList);
        this.userMedicalHistoryList = userMedicalHistoryList;
        this.context = context;
        this.listener = listener;
        this.onCheckedChangeListener = onCheckedChangeListener;
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
        holder.checkBox.setSelected(userMedicalHistory.isIscheckd());
        holder.checkBox.setVisibility(View.GONE);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(userMedicalHistory, isChecked);  // Trigger the listener
            }
        });

    }

    public void selectAllItems(boolean isSelected) {
        Toast.makeText(context, "come in adapter", Toast.LENGTH_SHORT).show();
        for (UserMedicalHistoryDTO item : userMedicalHistoryList) {

            item.setSelected(isSelected);  // Update the selection state for all items
        }
        notifyDataSetChanged();  // Refresh the RecyclerView to reflect changes
    }

    // ViewHolder for User items
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView emailTextView;
        CheckBox checkBox;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox =itemView.findViewById(R.id.checkBox);
//            firstNameTextView = itemView.findViewById(R.id.firstNameTextView);
//            lastNameTextView = itemView.findViewById(R.id.lastNameTextView);
//            emailTextView = itemView.findViewById(R.id.emailTextView);
        }
    }
}
