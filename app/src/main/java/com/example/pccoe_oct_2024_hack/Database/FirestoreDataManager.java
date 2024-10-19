package com.example.pccoe_oct_2024_hack.Database;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import android.util.Log;

public abstract class FirestoreDataManager<T> {

    private FirebaseFirestore db;
    private String collectionName;

    public FirestoreDataManager(String collectionName) {
        this.db = FirebaseFirestore.getInstance();
        this.collectionName = collectionName;
    }

    // Method to add data using a document ID
    public void addData(T dto, String documentId, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        db.collection(collectionName)
                .document(documentId)
                .set(dto)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }

    // Method to update existing data
    public void updateData(T dto, String documentId, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        db.collection(collectionName)
                .document(documentId)
                .set(dto)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }

    // Optional: Method to delete data
    public void deleteData(String documentId, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        db.collection(collectionName)
                .document(documentId)
                .delete()
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }

    // Optional: Method to get data (this is an example for fetching by document ID)
    public void getData(String documentId, OnSuccessListener<DocumentSnapshot> onSuccess, OnFailureListener onFailure) {
        db.collection(collectionName)
                .document(documentId)
                .get()
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
    public void getAllData(OnSuccessListener<QuerySnapshot> onSuccess, OnFailureListener onFailure) {
        db.collection(collectionName)
                .get()
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure);
    }
}
