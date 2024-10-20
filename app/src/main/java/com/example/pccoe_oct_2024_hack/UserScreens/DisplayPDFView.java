package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

import com.example.pccoe_oct_2024_hack.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DisplayPDFView extends AppCompatActivity {


    private ImageView pdfImageView;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private ParcelFileDescriptor parcelFileDescriptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pdfview);
        pdfImageView = findViewById(R.id.pdfImageView);

        // Example byte array representing the PDF (replace with your actual byte array)
//        byte[] receivedByteArray = getIntent().getByteArrayExtra("byteArrayKey");

        byte[] pdfByteArray = getIntent().getByteArrayExtra("byteArrayKey");

        try {
            File pdfFile = convertByteArrayToPdfFile(pdfByteArray);
            openRenderer(pdfFile);
            showPage(0);  // Display the first page
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Convert byte array to temporary file
    private File convertByteArrayToPdfFile(byte[] pdfByteArray) throws IOException {
        File tempPdf = File.createTempFile("tempPdf", ".pdf", getCacheDir());
        FileOutputStream fos = new FileOutputStream(tempPdf);
        fos.write(pdfByteArray);
        fos.flush();
        fos.close();
        return tempPdf;
    }

    private void openRenderer(File file) throws IOException {
        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
        pdfRenderer = new PdfRenderer(parcelFileDescriptor);
    }

    private void showPage(int index) {
        if (pdfRenderer.getPageCount() <= index) {
            return;
        }

        if (currentPage != null) {
            currentPage.close();
        }

        currentPage = pdfRenderer.openPage(index);

        Bitmap bitmap = Bitmap.createBitmap(currentPage.getWidth(), currentPage.getHeight(),
                Bitmap.Config.ARGB_8888);
        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

        pdfImageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        try {
            if (currentPage != null) {
                currentPage.close();
            }
            pdfRenderer.close();
            parcelFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    // Mock function to provide byte array (replace with actual PDF byte array)
    private byte[] getPdfByteArray() {
        return new byte[]{/* Your PDF byte array here */};
    }

}