package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.pccoe_oct_2024_hack.R;

public class ImageViewerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer_view);
        ImageView imageView = findViewById(R.id.imageview);
        byte[] imageByteArray = getIntent().getByteArrayExtra("byteArrayKey");

        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

        // Display the Bitmap in the provided ImageView
        imageView.setImageBitmap(bitmap);
        imageView.setVisibility(View.VISIBLE);  // Ensure the ImageView is visible



    }
}