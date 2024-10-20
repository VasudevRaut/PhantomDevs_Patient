package com.example.pccoe_oct_2024_hack.Adapters.Security;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

public class ImageHelper {

    // Function to convert byte array to Bitmap and display in an ImageView
    public void showImageFromByteArray(byte[] imageByteArray, ImageView imageView) {
        // Convert byte array to Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

        // Display the Bitmap in the provided ImageView
        imageView.setImageBitmap(bitmap);
        imageView.setVisibility(View.VISIBLE);  // Ensure the ImageView is visible
    }
}
