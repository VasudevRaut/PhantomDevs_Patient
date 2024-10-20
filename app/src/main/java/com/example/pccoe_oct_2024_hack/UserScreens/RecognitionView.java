package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pccoe_oct_2024_hack.DTO.Symptom;
import com.example.pccoe_oct_2024_hack.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RecognitionView extends AppCompatActivity {
    private SearchView searchView;
    private ListView listView;
    private TextView responseTextView;
    private ArrayAdapter<Symptom> adapter;
    private List<Symptom> symptomList;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition_view);


        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);
        responseTextView = findViewById(R.id.responseTextView);

        // Initialize Volley Request Queue
        requestQueue = Volley.newRequestQueue(this);

        // Initialize symptom data
        initializeData();

        // Set up adapter and ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, symptomList);
        listView.setAdapter(adapter);

        // Search filter logic
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        // Handle ListView item clicks
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Symptom selectedSymptom = (Symptom) parent.getItemAtPosition(position);
            sendSymptomsToApi(selectedSymptom.getSymptoms());
        });
    }

    private void initializeData() {
        symptomList = new ArrayList<>();
        symptomList.add(new Symptom("Fungal infection", List.of("itching", "skin_rash", "nodal_skin_eruptions")));
        symptomList.add(new Symptom("Allergy", List.of("continuous_sneezing", "shivering", "chills", "watering_from_eyes")));
        symptomList.add(new Symptom("GERD", List.of("stomach_pain", "acidity", "ulcers_on_tongue", "vomiting")));
        symptomList.add(new Symptom("Chronic cholestasis", List.of("itching", "vomiting", "yellowish_skin", "nausea")));
        symptomList.add(new Symptom("Drug Reaction", List.of("itching", "skin_rash", "stomach_pain", "burning_micturition")));
        // Add more symptoms as needed...
    }

    // Method to send the selected symptoms to the API using Volley
    private void sendSymptomsToApi(List<String> symptoms) {
        String url = "https://predict-symptom.onrender.com/predict";

        // Create the JSON object with symptoms
        JSONObject jsonBody = new JSONObject();
        try {
            JSONArray symptomsArray = new JSONArray();
            symptomsArray.put(symptoms.get(0));
            symptomsArray.put(symptoms.get(1));
            symptomsArray.put(symptoms.get(2));
            jsonBody.put("symptoms", symptomsArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create the JSON object request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the server
                        System.out.println("Response: " + response.toString());
                        JSONObject jsonObject = response;

                        // Extract fields from the JSON response
                        try{


                        String description = jsonObject.getString("description");
                        String disease = jsonObject.getString("disease");

                        // Diets
                        JSONArray dietsArray = jsonObject.getJSONArray("diets");
                        String diets = dietsArray.getString(0).replace("[", "").replace("]", "").replace("'", "");

                        // Medications
                        JSONArray medicationsArray = jsonObject.getJSONArray("medications");
                        String medications = medicationsArray.getString(0).replace("[", "").replace("]", "").replace("'", "");

                        // Precautions
                        JSONArray precautionsArray = jsonObject.getJSONArray("precautions");
                        StringBuilder precautions = new StringBuilder();
                        for (int i = 0; i < precautionsArray.length(); i++) {
                            precautions.append("- ").append(precautionsArray.getString(i)).append("\n");
                        }

                        // Workout (if any)
                        JSONArray workoutArray = jsonObject.getJSONArray("workout");
                        String workout = workoutArray.length() == 0 ? "No specific workouts recommended" : workoutArray.toString();
                            String formattedResponse = "Disease: " + disease + "\n\n"
                                    + "Description: " + description + "\n\n"
                                    + "Diets: " + diets + "\n\n"
                                    + "Medications: " + medications + "\n\n"
                                    + "Precautions: \n" + precautions.toString() + "\n"
                                    + "Workout: " + workout;

                            responseTextView.setText(formattedResponse);
                        }
                        catch (JSONException j){

                        }
                        // Formatting the text to display in TextView


                        // Set the formatted string to the TextView

//                        Toast.makeText(RecognitionView.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle any error that occurred
                        System.out.println("Error: " + error.toString());
                        if (error.networkResponse != null) {
                            String body = new String(error.networkResponse.data);
                            Toast.makeText(RecognitionView.this, ""+body, Toast.LENGTH_SHORT).show();

                            System.out.println("Error Response: " + body);
                        } else {
                            Toast.makeText(RecognitionView.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

                            System.out.println("Error: " + error.toString());
                        }
                    }
                });

        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);
    }
}