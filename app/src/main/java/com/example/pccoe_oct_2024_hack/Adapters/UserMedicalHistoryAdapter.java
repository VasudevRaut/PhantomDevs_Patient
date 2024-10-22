package com.example.pccoe_oct_2024_hack.Adapters;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pccoe_oct_2024_hack.Adapters.Security.HelpUtil;
import com.example.pccoe_oct_2024_hack.Adapters.Security.MedicalDataEncryption;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.UserScreens.DisplayPDFView;
import com.example.pccoe_oct_2024_hack.UserScreens.ImageViewerView;
import com.example.pccoe_oct_2024_hack.UserScreens.MedicalListSharePresenter;
import com.example.pccoe_oct_2024_hack.UserScreens.UserMedicalHistoryPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import org.checkerframework.checker.index.qual.GTENegativeOne;
import org.json.JSONObject;


public class UserMedicalHistoryAdapter extends BaseAdapter<UserMedicalHistoryDTO, UserMedicalHistoryAdapter.UserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(UserMedicalHistoryDTO user);
    }
    public interface OnCheckedChangeListener {
        void onCheckedChanged(UserMedicalHistoryDTO userMedicalHistoryDTO, boolean isChecked);
    }
//    UserViewHolder holder;
    public String responseText;

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
//        this.holder = holder;
        //set here value to lebel
        holder.person_name.setText(userMedicalHistory.getDocName());
        try {
            holder.date_time.setText(getDate(userMedicalHistory.getReportDate()));

        }
        catch (Exception e){}
//        fetchRecord(context,userMedicalHistory.getBlockID());







        String url = "https://blockchain-records.onrender.com/api/records/" + userMedicalHistory.getBlockID();

        // Create a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Create a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the API
                        try {
                            // Parse the response as JSON
                            JSONObject jsonObject = new JSONObject(response);
                            // Extract the value of the "record" key
                            String recordText = jsonObject.getString("record");

                            // Use the extracted text
//                            Toast.makeText(context, "Record: " + recordText, Toast.LENGTH_SHORT).show();

                            // Further processing, like passing it to your MedicalDataEncryption
                            responseText = MedicalDataEncryption.fromString(recordText).getDescription();
                            holder.text_users.setText(responseText);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "JSON parsing error", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        System.out.println("Error: " + error.toString());
                    }
                });

        // Set a custom retry policy to increase timeout duration
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000, // 30 seconds timeout
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // Number of retries
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT // Backoff multiplier
        ));

        // Add the request to the RequestQueue
        requestQueue.add(stringRequest);

//        return responseText!=null ?responseText:"---";








//        holder.text_users.setText(fetchRecord(context,userMedicalHistory.getBlockID()));

        holder.checkBox.setSelected(false);
        holder.checkBox.setVisibility(View.GONE);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(userMedicalHistory, isChecked);  // Trigger the listener
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, MedicalListSharePresenter.class);
                context.startActivity(intent);
            }
        });

        holder.imageviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageViewerView.class);
                HelpUtil helpUtil = new HelpUtil();
                intent.putExtra("byteArrayKey", helpUtil.convertStringToByteArray(userMedicalHistory.getImage()));
                context.startActivity(intent);
            }
        });

        holder.pdfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayPDFView.class);
                HelpUtil helpUtil = new HelpUtil();
                Log.w("PDFData",userMedicalHistory.getPDF());
                intent.putExtra("byteArrayKey", helpUtil.convertStringToByteArray(userMedicalHistory.getPDF()));
                context.startActivity(intent);
            }
        });



    }

    public String fetchRecord(Context context, String id) {
        String url = "https://blockchain-records.onrender.com/api/records/" + id;

        // Create a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Create a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response from the API
                        try {
                            // Parse the response as JSON
                            JSONObject jsonObject = new JSONObject(response);
                            // Extract the value of the "record" key
                            String recordText = jsonObject.getString("record");

                            // Use the extracted text
//                            Toast.makeText(context, "Record: " + recordText, Toast.LENGTH_SHORT).show();

                            // Further processing, like passing it to your MedicalDataEncryption
                            responseText = MedicalDataEncryption.fromString(recordText).getDescription();
//                            holder.text_users.setText(responseText);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "JSON parsing error", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        System.out.println("Error: " + error.toString());
                    }
                });

        // Set a custom retry policy to increase timeout duration
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000, // 30 seconds timeout
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // Number of retries
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT // Backoff multiplier
        ));

        // Add the request to the RequestQueue
        requestQueue.add(stringRequest);

        return responseText!=null ?responseText:"Need to rest";
    }

    public static String getRecordText(String jsonResponse) {
        try {
            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(jsonResponse);
            // Get the value of the "records" key
            Log.w("Someeeeeeeeeeeeeee",jsonObject.toString());
            return jsonObject.getString("records");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String getDate(String currentTimeMillisString) {
        String timeString = "";

        try {
            // Parse the input string to a long representing milliseconds
            long currentTimeMillis = Long.parseLong(currentTimeMillisString);

            // Create a Date object from the milliseconds
            Date date = new Date(currentTimeMillis);

            // Create a format for the date (yyyy-MM-dd)
            SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Return the date part of the Date object
            timeString = dateOnlyFormat.format(date);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Invalid milliseconds format";
        }

        return timeString;
    }




    public void selectAllItems(boolean isSelected) {
//        Toast.makeText(context, "come in adapter", Toast.LENGTH_SHORT).show();
        for (UserMedicalHistoryDTO item : userMedicalHistoryList) {

//            item.setSelected(isSelected);  // Update the selection state for all items
        }
        notifyDataSetChanged();  // Refresh the RecyclerView to reflect changes
    }

    // ViewHolder for User items
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView text_users;
        TextView person_name;
        TextView date_time;
        CheckBox checkBox;
        ImageView imageView;

        LinearLayout imageviews,pdfView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox =itemView.findViewById(R.id.checkBox);
            imageView = itemView.findViewById(R.id.share_icon);
            imageviews = itemView.findViewById(R.id.card_image);
            pdfView = itemView.findViewById(R.id.card_pdf);
            text_users = itemView.findViewById(R.id.text_users);
            person_name = itemView.findViewById(R.id.person_name);
            date_time = itemView.findViewById(R.id.date_time);


        }
    }
}
