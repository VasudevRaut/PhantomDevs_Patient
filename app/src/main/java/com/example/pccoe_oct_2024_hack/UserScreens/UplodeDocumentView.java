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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.Security.AESUtils;
import com.example.pccoe_oct_2024_hack.Adapters.Security.HelpUtil;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.Database.ReportManager;
import com.example.pccoe_oct_2024_hack.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UplodeDocumentView extends AppCompatActivity {


    private static final int REQUEST_CODE_IMAGE = 1;
    private static final int REQUEST_CODE_PDF = 2;

    private EditText editPrescription;
    private Button buttonUploadImage, buttonUploadPDF;
    private ImageView uploadedImageView;
    private TextView pdfFileName;
    private Uri selectedPDFUri;
    String image="";
    String pdf="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uplode_document_view);
        // Initialize views
        editPrescription = findViewById(R.id.editPrescription);
        buttonUploadImage = findViewById(R.id.buttonUploadImage);
        uploadedImageView = findViewById(R.id.uploadedImageView);
        buttonUploadPDF = findViewById(R.id.buttonUploadPDF);
        pdfFileName = findViewById(R.id.pdfFileName);
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
        LocalDateTime current = LocalDateTime.now();

        // Format the date and time if needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = current.format(formatter);
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build();
        FirebaseFirestore.getInstance().setFirestoreSettings(settings);

        String description = "Allergic reaction with skin rash and swelling.";
        String dataString = "Patient had a reaction after consuming shellfish.";
        UserMedicalHistoryDTO userMedicalHistoryDTO = new UserMedicalHistoryDTO(formatted,"vasudevraut156@gmail.com","vasudevraut156@gmail.com",description,dataString,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),image,pdf);
        Log.w("USerData",pdf);

        new ReportManager().addData(userMedicalHistoryDTO, "vasudevraut156@gmail1.com", new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Intent intent = new Intent(UplodeDocumentView.this, OrderesPlacedView.class);
                startActivity(intent);
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

}