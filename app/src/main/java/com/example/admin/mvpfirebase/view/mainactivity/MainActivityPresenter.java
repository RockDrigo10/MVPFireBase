package com.example.admin.mvpfirebase.view.mainactivity;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Admin on 8/22/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{
    private static final String TAG = "MainActivityPresenter";
    MainActivityContract.View view;
    DatabaseReference myRef;
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void dettacheView() {
        this.view = null;
    }

    // TODO: 8/22/2017 Implement firebase logic to save data
    @Override
    public void saveDataToCloud(String s) {
        //save data
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        myRef.setValue(s);
        Log.d(TAG, "saveDataToCloud: " + s);
        //if saved to cloud
        view.onDataSaved(true);
    }

    @Override
    public void getDataToCloud() {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value );
                view.sendToView(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
