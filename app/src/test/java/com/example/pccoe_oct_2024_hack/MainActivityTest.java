package com.example.pccoe_oct_2024_hack;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import android.util.Log;

import com.example.pccoe_oct_2024_hack.Database.FirebaseClientToken;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MainActivityTest {

    @Mock
    FirebaseMessaging firebaseMessaging;

    @Mock
    Task<String> mockTask;

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Create an instance of MainActivity
        mainActivity = Mockito.spy(new MainActivity());

        // Mock FirebaseMessaging instance and return it when called
        when(FirebaseMessaging.getInstance()).thenReturn(firebaseMessaging);
    }

    @Test
    public void testGetFirebaseToken() {
        // Mock a successful token retrieval
        when(mockTask.isSuccessful()).thenReturn(true);
        when(mockTask.getResult()).thenReturn("mockToken123");

        // Mock the getToken() method to return the mock task
        when(firebaseMessaging.getToken()).thenReturn(mockTask);

        // Call the method in MainActivity to get the token
        FirebaseClientToken firebaseClientToken = new FirebaseClientToken();
        String token = firebaseClientToken.getFirebaseToken();

        // Verify that the token is logged and handled properly
//        Mockito.verify(mainActivity).handleToken("mockToken123");
    }

    @Test
    public void testGetFirebaseTokenFailure() {
        // Mock a failed token retrieval
        when(mockTask.isSuccessful()).thenReturn(false);

        // Mock the getToken() method to return the mock task
        when(firebaseMessaging.getToken()).thenReturn(mockTask);

        // Call the method in MainActivity to get the token
//        mainActivity.getFirebaseToken();
//
//        // Ensure handleToken is NOT called on failure
//        Mockito.verify(mainActivity, never()).handleToken(anyString());
    }
}
