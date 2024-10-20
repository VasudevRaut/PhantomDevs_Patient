package com.example.pccoe_oct_2024_hack.Adapters.Security;

import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HelpUtil {

    public static byte[] convertStringToByteArray(String input) {
        byte[] pdfData = Base64.decode(input, Base64.DEFAULT);
        try {
            return   AESUtils.decrypt(pdfData,"MySecretKey12345");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return pdfData;
    }

    public String convertInputStreamToByteArrayPDF(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        try {
            String keyString = "MySecretKey12345";
            byte[] encryptedPdf = AESUtils.encrypt(outputStream.toByteArray(), keyString);
             return Base64.encodeToString(encryptedPdf, Base64.DEFAULT);
        }
        catch (Exception e){}
        return "";

    }



    public String convertInputStreamToByteArrayImage(byte[] buffer) {
        try {
            String keyString = "MySecretKey12345";
            byte[] encryptedPdf = AESUtils.encrypt(buffer, keyString);
            return Base64.encodeToString(encryptedPdf, Base64.DEFAULT);
        }
        catch (Exception e){}
        return "";

    }

    public String convertStringToEncryptedString(String s){
        byte[] str = s.getBytes();
        String keyString = "MySecretKey12345";
        byte[] encryptedPdf = new byte[0];
        try {
            encryptedPdf = AESUtils.encrypt(str, keyString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Base64.encodeToString(encryptedPdf, Base64.DEFAULT);
    }


    public String convertEncryptedStringToOriginalString(String encryptedString) {
        // Decode the encrypted string from Base64
        byte[] encryptedPdf = Base64.decode(encryptedString, Base64.DEFAULT);

        String keyString = "MySecretKey12345"; // Same secret key used for encryption
        byte[] decryptedPdf = new byte[0];

        try {
            // Decrypt the byte array using the same AES decryption logic
            decryptedPdf = AESUtils.decrypt(encryptedPdf, keyString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Convert the decrypted byte array back to the original string
        return new String(decryptedPdf);
    }







//    private String processPdfByteArray(byte[] pdfByteArray) {
//
//
//        try {
//            String keyString = "MySecretKey12345";
//            byte[] encryptedPdf = AESUtils.encrypt(pdfByteArray, keyString);
////        byte[] after = AESUtils.decrypt(encryptedPdf,keyString);
////            Toast.makeText(this, ""+encryptedPdf.length, Toast.LENGTH_SHORT).show();
//            return Base64.encodeToString(encryptedPdf, Base64.DEFAULT);
//
//        }
//        catch (Exception e){
//
//        }
//        return "";
//    }
}
