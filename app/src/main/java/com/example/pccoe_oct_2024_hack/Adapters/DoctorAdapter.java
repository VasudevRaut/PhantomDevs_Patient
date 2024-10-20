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
        holder.name.setText(user.getDoctorName());
        holder.address.setText(user.getDoctorAddress());
        holder.education.setText(user.getDoctorAge()+"");
        holder.charges.setText(String.valueOf(user.getDoctorFee())); // Convert to string if it's a number
        holder.profession.setText(user.getDoctorType());

        //set here value to lebel
    }

    // ViewHolder for User items
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;
        TextView education,charges,profession;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            education = itemView.findViewById(R.id.education);
            charges = itemView.findViewById(R.id.charges);
            profession = itemView.findViewById(R.id.profession);

        }
    }
}
