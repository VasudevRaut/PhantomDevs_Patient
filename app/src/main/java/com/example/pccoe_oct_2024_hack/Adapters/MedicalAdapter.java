package com.example.pccoe_oct_2024_hack.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pccoe_oct_2024_hack.DTO.MedicalDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.R;

import java.util.List;

public class MedicalAdapter extends BaseAdapter<MedicalDTO, MedicalAdapter.UserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(MedicalDTO user);
    }
    private OnItemClickListener listener;

    public MedicalAdapter(List<MedicalDTO> userList, OnItemClickListener listener) {
        super(userList);
        this.listener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.single_medicine_store_card;  // Your layout for the user item
    }

    @Override
    protected UserViewHolder createViewHolder(View view) {
        return new UserViewHolder(view);
    }

    @Override
    protected void bindData(UserViewHolder holder, MedicalDTO user) {
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(user);  // Pass the clicked user back to the activity
            }
        });

        holder.med_location.setText(user.getAddress());
        holder.med_distance.setText(user.getDistance()+"");
        holder.med_name.setText(user.getName());

        //set here value to lebel
    }

    // ViewHolder for User items
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView med_name;
        TextView med_location;
        TextView med_distance;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            med_name = itemView.findViewById(R.id.med_name);
            med_location = itemView.findViewById(R.id.med_location);
            med_distance = itemView.findViewById(R.id.med_distance);
        }
    }
}
