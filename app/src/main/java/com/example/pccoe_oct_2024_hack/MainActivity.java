package com.example.pccoe_oct_2024_hack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.UserAdapter;
import com.example.pccoe_oct_2024_hack.DTO.Admin;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.Database.AdminManager;
import com.example.pccoe_oct_2024_hack.Database.FirebaseClientToken;
import com.example.pccoe_oct_2024_hack.Database.UserManager;
import com.example.pccoe_oct_2024_hack.Norification.UserNotification;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        createAbstractNotification();
//        createAbstractRecyclerViewAdapter();
//
//        getAllDataFromCollection();
//        deleteDocument();
//        addDocument();
//        updateDocument();

        listenForNotifications();

//        Intent serviceIntent = new Intent(this, NotificationService.class);
//        startService(serviceIntent);
//        getFireaseClientToken();

//        startActivity(new Intent(this,SplashScreen.class));






















    }

    private void listenForNotifications() {
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();

        db.collection("patients")
                .document("vasudevraut9912@gmail.com")
                .collection("appointments")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                        Log.w("FirebaseToken", "Chanegeddddddddddddddd");

                        if (e != null) {
                            return;
                        }

                        for (DocumentChange doc : snapshots.getDocumentChanges()) {
                            if (doc.getDocument().getId().equals("1234")) {  // Check for the specific document ID "1234"
                                String message = doc.getDocument().getString("message");

                                createAbstractNotification();

                            }
                        }
                    }
                });

    }


    private void getFireaseClientToken() {
        FirebaseClientToken firebaseClientToken = new FirebaseClientToken();
        String token = firebaseClientToken.getFirebaseToken();

    }

//    private void updateDocument() {
        //         To update a user
//        User user = new User("John", "Doe", "john.doe@example.com");

//        user.setEmail("new.email@example.com");
//        user.setFirstName("Chetan");
//        new UserManager().updateData(user, user.getEmail(),
//                new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d("Firestore", "User successfully updated!");
//                    }
//                },
//                new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("Firestore", "Error updating user", e);
//                    }
//                }
//        );
//    }

//    private void addDocument() {
//         //Insert user into Firestore with email as document ID
//        User user = new User("John", "Doe", "john.doe@example.com");
//
//        new UserManager().addData(user, user.getEmail(),
//                new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(MainActivity.this, "supported", Toast.LENGTH_SHORT).show();
//
//                        Log.d("Firestore", "User successfully added!");
//                    }
//                },
//                new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this, "not supported", Toast.LENGTH_SHORT).show();
//
//                        Log.w("Firestore", "Error adding user", e);
//                    }
//                }
//        );
//    }
//
//    private void deleteDocument() {
//        User user = new User("John", "Doe", "john.doe@example.com");
//
//        new AdminManager().deleteData(user.getEmail(), new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//
//            }
//        }, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
//    }

    private void getAllDataFromCollection() {
        // Create a new User DTO

        new AdminManager().getAllData(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Admin> allAdmins = queryDocumentSnapshots.toObjects(Admin.class);
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void createAbstractRecyclerViewAdapter() {
        RecyclerView recyclerView = findViewById(R.id.abstrat_adapter_test);
        List<User> userList = new ArrayList<>();
        // Add data to the userList
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());

        UserAdapter userAdapter = new UserAdapter(userList, new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {

            }
        });
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Suppose if we want to add diffrent type of action listener
        /*

            public interface OnItemClickListener {
                void onItemClick(User user);
                void onLongItemClick(User user);
                void onEmailButtonClick(User user);
            }

            // Set listener for long click
            holder.itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    listener.onLongItemClick(user);
                }
                return true;
            });

            // Set listener for email button click
            holder.emailTextView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEmailButtonClick(user);
                }
            });


            UserAdapter userAdapter = new UserAdapter(userList, new UserAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(User user) {
                    Log.d("MainActivity", "Clicked user: " + user.getFirstName());
                }

                @Override
                public void onLongItemClick(User user) {
                    Log.d("MainActivity", "Long clicked user: " + user.getFirstName());
                }

                @Override
                public void onEmailButtonClick(User user) {
                    Log.d("MainActivity", "Email button clicked for: " + user.getFirstName());
                }
            });

         */

    }

    private void createAbstractNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        // Send a notification to the user
        UserNotification userNotification = new UserNotification(this, "user_channel_id", "John Doe");
        userNotification.sendNotification(1, intent);  // Pass notification ID and intent
    }
}