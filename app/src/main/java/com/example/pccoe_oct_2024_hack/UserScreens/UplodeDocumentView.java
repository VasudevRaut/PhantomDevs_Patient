package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pccoe_oct_2024_hack.Adapters.Security.AESUtils;
import com.example.pccoe_oct_2024_hack.Adapters.Security.HelpUtil;
import com.example.pccoe_oct_2024_hack.Adapters.Security.MedicalDataEncryption;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.Database.ReportManager;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.ShatedPreferences.SharedPrefsHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UplodeDocumentView extends AppCompatActivity {


    private static final int REQUEST_CODE_IMAGE = 1;
    private static final int REQUEST_CODE_PDF = 2;

    private EditText editPrescription,doctorName,doctorEmail;
    private Button buttonUploadImage, buttonUploadPDF;
    private ImageView uploadedImageView;
    private TextView pdfFileName;
    private Uri selectedPDFUri;
    String image="";
    String pdf="";
    String responses= "";
    String blockID;

    private List<String> symptomsList = new ArrayList<>();
    private List<String> medicinesList = new ArrayList<>();
    private List<String> measuresList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uplode_document_view);
        // Initialize views
//        sendReport("Vasudev");
        editPrescription = findViewById(R.id.editPrescription);
        buttonUploadImage = findViewById(R.id.buttonUploadImage);
        uploadedImageView = findViewById(R.id.uploadedImageView);
        buttonUploadPDF = findViewById(R.id.buttonUploadPDF);
        pdfFileName = findViewById(R.id.pdfFileName);
        doctorName = findViewById(R.id.doctorName);
        doctorEmail = findViewById(R.id.doctorEmail);
        // Upload Image
        buttonUploadImage.setOnClickListener(v -> openImagePicker());
        // Upload PDF
        buttonUploadPDF.setOnClickListener(v -> openPDFPicker());

        Button share  = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                showOrderConfirmationDialog();
            }
        });

        Button button = findViewById(R.id.generateBlock);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateBlock();
            }
        });



        findViewById(R.id.ivAddSymptom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymptoms();
            }
        });

        findViewById(R.id.ivAddMeasure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeasures();
            }
        });

        findViewById(R.id.ivAddMedicine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedicines();
            }
        });


    }

    // Method to open image picker
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_IMAGE);
    }

    // Method to open PDF picker
    private void openPDFPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_CODE_PDF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_IMAGE) {
                Uri imageUri = data.getData();
                if (imageUri != null) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        uploadedImageView.setImageBitmap(bitmap);
                        uploadedImageView.setVisibility(View.VISIBLE);

                        // Step 2: Convert Bitmap to byte array
                        byte[] imageByteArray = convertBitmapToByteArray(bitmap);
                        HelpUtil helpUtil = new HelpUtil();
                        image = helpUtil.convertInputStreamToByteArrayImage(imageByteArray);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (requestCode == REQUEST_CODE_PDF) {
                selectedPDFUri = data.getData();
                if (selectedPDFUri != null) {
                    String fileName = getFileName(selectedPDFUri);
                    pdfFileName.setText(fileName);  // Display the PDF file name in TextView
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(data.getData());
                        HelpUtil helpUtil = new HelpUtil();
                        pdf = helpUtil.convertInputStreamToByteArrayPDF(inputStream);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            }
        }
    }

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);  // You can choose JPEG/PNG format
        return byteArrayOutputStream.toByteArray();
    }



    // Method to get file name from URI
    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String fileName = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        } else {
            fileName = new File(uri.getPath()).getName();
        }
        return fileName;
    }


    private void showOrderConfirmationDialog() {
        // Create an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set dialog title and message
        builder.setTitle("Order Confirmation");
        builder.setMessage("Are you sure you want to confirm your order?");

        // Set positive button (Yes) action
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "Yes", proceed with the order confirmation
                confirmOrder();
            }
        });

        // Set negative button (No) action
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "No", dismiss the dialog and cancel the order
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Function to handle order confirmation (when the user clicks "Yes")
    private void confirmOrder() {
        showOrderPlacedAnimation();
    }

    // Example function to show order placed animation (can be customized)
    private void showOrderPlacedAnimation() {


//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(false)
//                .build();
//        FirebaseFirestore.getInstance().setFirestoreSettings(settings);

        String description = "Allergic reaction with skin rash and swelling.";
        String dataString = "Patient had a reaction after consuming shellfish.";

        String reportDate = String.valueOf(System.currentTimeMillis());
        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(this);

        User savedUser = sharedPrefsHelper.getObject("user", User.class);
//        String blockID = generateBlock();

        UserMedicalHistoryDTO userMedicalHistoryDTO = new UserMedicalHistoryDTO(reportDate,doctorEmail.getText().toString(),doctorName.getText().toString(),savedUser.getPatientEmail(),savedUser.getPatientName(),blockID,image,pdf);
        Log.w("USerData",pdf);




        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore
                .collection("patients")
                .document(savedUser.getPatientEmail())
                .collection("reports")
                .document(reportDate)
                .set(userMedicalHistoryDTO)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UplodeDocumentView.this, "Sucessssss", Toast.LENGTH_SHORT).show();
//                            Log.d("TAG", "onSuccess: " + doctorModel.getDocEmail());
                                        Intent intent = new Intent(UplodeDocumentView.this, OrderesPlacedView.class);
                startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UplodeDocumentView.this, "Fail"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



//        new ReportManager().addData(userMedicalHistoryDTO, "vasudevraut156@gmail1.com", new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//
//
//
//
//
//
//
//
//
//
//
//
//
//                Intent intent = new Intent(UplodeDocumentView.this, OrderesPlacedView.class);
//                startActivity(intent);
//            }
//        }, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

    }





    public String sendReport(String reportText) {
        // API URL
//        String url = "https://blockchain-records.onrender.com/api/records";
//        Toast.makeText(this, "Data "+reportText, Toast.LENGTH_SHORT).show();
//        // Create a request queue
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        // Create the JSON object to send in the request body
//        JSONObject requestBody = new JSONObject();
//        try {
//            requestBody.put("record", reportText);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Create a POST request with a JSON body
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // Handle the response
//
//                       responses = response.toString();
//
//                        Toast.makeText(UplodeDocumentView.this, "Response "+ responses.toString(), Toast.LENGTH_SHORT).show();
//                        System.out.println("Response: " + response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle the error
//                        System.out.println("Error: " + error.toString());
//                        Toast.makeText(UplodeDocumentView.this, "Error"+ error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//        // Add the request to the request queue
//        queue.add(jsonObjectRequest);
//        return responses;


        sendReportAndGetSavedId(reportText, new VolleyCallback() {
            @Override
            public void onSuccess(String savedId) {
                // Use the savedId here
                responses = savedId;
                blockID  = savedId;
//                Toast.makeText(UplodeDocumentView.this, "saveId : "+savedId, Toast.LENGTH_SHORT).show();
            }
        });

        return responses;
    }



    public void sendReportAndGetSavedId(String reportText, final VolleyCallback callback) {
        // API URL
        String url = "https://blockchain-records.onrender.com/api/records";

        // Create a request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Create the JSON object to send in the request body
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("record", reportText);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a POST request with a JSON body
        // Create a POST request with a JSON body
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String savedId = response.getString("savedId");
//                            Toast.makeText(UplodeDocumentView.this, "Success "+savedId, Toast.LENGTH_SHORT).show();
                            callback.onSuccess(savedId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            String responseBody = new String(error.networkResponse.data);
                            System.out.println("Error Status Code: " + statusCode);
                            System.out.println("Error Response Body: " + responseBody);
                            Toast.makeText(UplodeDocumentView.this, "Error " + statusCode + ": " + responseBody, Toast.LENGTH_LONG).show();
                        } else {
//                            Toast.makeText(UplodeDocumentView.this, "Timeout or Network Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

// Set a retry policy: (timeout, max retries, backoff multiplier)
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000, // 30 seconds timeout
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // Default retry count
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT // Default backoff multiplier
        ));

// Add the request to the request queue
        queue.add(jsonObjectRequest);

    }

    // Callback interface to return the savedId
    public interface VolleyCallback {
        void onSuccess(String savedId);
    }









    private void addSymptoms() {
        String specialization = ((EditText)findViewById(R.id.etSymptoms)).getText().toString().trim();


        if (!specialization.isEmpty()) {
            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout specializationLayout = (LinearLayout) inflater.inflate(R.layout.specialization_layout, null);

// Set layout parameters with margin
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

// Set the start margin to 10
            params.setMargins(10, 0, 0, 0);  // Left, Top, Right, Bottom margins
            specializationLayout.setLayoutParams(params);


            TextView tvSpecialization = specializationLayout.findViewById(R.id.tvSpecialization);
            ImageView tvDelete = specializationLayout.findViewById(R.id.tvDelete);

            tvSpecialization.setText(specialization);

            symptomsList.add(specialization);

            ((LinearLayout)findViewById(R.id.llSymptoms)).addView(specializationLayout);

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeSymptom(specializationLayout, specialization);
                }
            });

            ((EditText)findViewById(R.id.etSymptoms)).setText("");
        }
    }

    private void removeSymptom(View symptomLayout, String symptom) {
        ((LinearLayout)findViewById(R.id.llSymptoms)).removeView(symptomLayout);
        symptomsList.remove(symptom);
    }

    public List<String> getAllSymptoms() {
        return new ArrayList<>(symptomsList);
    }


    private void addMedicines() {
        String specialization = ((EditText)findViewById(R.id.etMedicines)).getText().toString().trim();

        if (!specialization.isEmpty()) {
            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout specializationLayout = (LinearLayout) inflater.inflate(R.layout.specialization_layout, null);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(10, 0, 0, 0);  // Left, Top, Right, Bottom margins
            specializationLayout.setLayoutParams(params);

            TextView tvSpecialization = specializationLayout.findViewById(R.id.tvSpecialization);
            ImageView tvDelete = specializationLayout.findViewById(R.id.tvDelete);

            tvSpecialization.setText(specialization);

            medicinesList.add(specialization);

            ((LinearLayout)findViewById(R.id.llMedicines)).addView(specializationLayout);

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeMedicine(specializationLayout, specialization);
                }
            });

            ((EditText)findViewById(R.id.etMedicines)).setText("");
        }
    }

    private void removeMedicine(View medicineLayout, String medicine) {
        ((LinearLayout)findViewById(R.id.llMedicines)).removeView(medicineLayout);
        symptomsList.remove(medicine);
    }

    public List<String> getAllMedicine() {
        return new ArrayList<>(symptomsList);
    }


    private void addMeasures() {
        String measure = ((EditText)findViewById(R.id.etMeasures)).getText().toString().trim();

        if (!measure.isEmpty()) {
            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout specializationLayout = (LinearLayout) inflater.inflate(R.layout.specialization_layout, null);

// Set layout parameters with margin
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

// Set the start margin to 10
            params.setMargins(10, 0, 0, 0);  // Left, Top, Right, Bottom margins
            specializationLayout.setLayoutParams(params);

            TextView tvSpecialization = specializationLayout.findViewById(R.id.tvSpecialization);
            ImageView tvDelete = specializationLayout.findViewById(R.id.tvDelete);

            tvSpecialization.setText(measure);

            measuresList.add(measure);

            ((LinearLayout)findViewById(R.id.llMeasures)).addView(specializationLayout);

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeMeasure(specializationLayout, measure);
                }
            });

            ((EditText)findViewById(R.id.etMeasures)).setText("");
        }
    }

    private void removeMeasure(View measureLayout, String measure) {
        ((LinearLayout)findViewById(R.id.llMeasures)).removeView(measureLayout);
        measuresList.remove(measure);
    }

    public List<String> getAllMeasures() {
        return new ArrayList<>(measuresList);
    }


    private String generateBlock() {
        MedicalDataEncryption medicalData = new MedicalDataEncryption(editPrescription.getText().toString(), symptomsList, medicinesList, measuresList);

        return getBlockID(medicalData.convertToString());
    }

    private String getBlockID(String data) {
        String blockID = "";
        return sendReport(data);


    }




}