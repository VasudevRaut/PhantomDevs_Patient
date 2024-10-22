package com.example.pccoe_oct_2024_hack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.DTO.SheduleAppointmentDTO;
import com.example.pccoe_oct_2024_hack.R;

import java.util.List;

public class SheduleAppointmentAdapter extends BaseAdapter<SheduleAppointmentDTO, SheduleAppointmentAdapter.UserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(SheduleAppointmentDTO user);
    }
    private OnItemClickListener listener;
    Context context;

    public SheduleAppointmentAdapter(Context context,List<SheduleAppointmentDTO> userList, OnItemClickListener listener) {
        super(userList);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.shedule_appointment_card;  // Your layout for the user item
    }

    @Override
    protected UserViewHolder createViewHolder(View view) {
        return new UserViewHolder(view);
    }

    @Override
    protected void bindData(UserViewHolder holder, SheduleAppointmentDTO user) {
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(user);  // Pass the clicked user back to the activity
            }
        });
        //set here value to lebel
        holder.name.setText(user.getDoctorName());
        holder.address.setText(user.getAppointmentStartTime());
        holder.Time.setText(user.getAppointmentEndTime()+"");
        holder.charges.setText(user.getAppointmentDate()); // Convert to string if it's a number
        holder.type.setText(user.getAppointmentStatus());
        holder.videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "7823859849"; // Replace this with the actual phone number you want to call
                startWhatsAppVideoCall(phoneNumber);
            }
        });
    }

    private void startWhatsAppVideoCall(String phoneNumber) {
        // Remove any non-digit characters, except the '+' for international format
        phoneNumber = phoneNumber.replace("+", "");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://wa.me/" + phoneNumber + "?video=true"));
        intent.setPackage("com.whatsapp");

        // Check if WhatsApp is installed on the device
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "WhatsApp is not installed", Toast.LENGTH_SHORT).show();
        }
    }

    // ViewHolder for User items
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;
        TextView charges,Time,type;
        ImageView videocall;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            Time = itemView.findViewById(R.id.Time);
            charges = itemView.findViewById(R.id.charges);
            type = itemView.findViewById(R.id.type);
            videocall = itemView.findViewById(R.id.vidoccall);
        }
    }
}
