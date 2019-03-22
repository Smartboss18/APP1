package com.example.shubham.app1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Progress extends AppCompatActivity {

    TextView animalPercent;
    TextView fruitPercent;
    TextView colorPercent;

    private DatabaseReference mFirebaseDatabase;
    FirebaseFirestore db;

    private static final String ANIMAL_KEY = "Animal";
    private static final String FRUIT_KEY = "Fruit";
    private static final String COLOR_KEY = "Color";

    String animalPoint;
    String fruitPoint;
    String colorPoint;
    String emailID;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        db = FirebaseFirestore.getInstance();


        animalPercent = findViewById(R.id.animalPercent);
        fruitPercent = findViewById(R.id.fruitPercent);
        colorPercent = findViewById(R.id.colorPercent);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        animalPoint = sharedPreferences.getString("animalPercentage", "");
        fruitPoint = sharedPreferences.getString("fruitPercentage", "");
        colorPoint = sharedPreferences.getString("colorPercentage", "");


        emailID = sharedPreferences.getString("email", "");
        phoneNumber = sharedPreferences.getString("phoneNumber", "");

        SharedPreferences sharedPreferenceForID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userID = sharedPreferenceForID.getString("userID", "");

        Map<String, Object> progressHashMap = new HashMap<>();
        progressHashMap.put("UserID", userID);
        progressHashMap.put("Animals", animalPoint);
        progressHashMap.put("Fruits", fruitPoint);
        progressHashMap.put("Color", colorPoint);

        animalPercent.setText("Animals: " + animalPoint);
        fruitPercent.setText("Fruits: " + fruitPoint);
        colorPercent.setText("Color: " + colorPoint);

        if (emailID.isEmpty()){
            progressHashMap.put("Phone", phoneNumber);

            db.collection("PROGRESS").document(phoneNumber)
                    .set(progressHashMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Progress.this, "Saved Phone", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

            db.collection("PROGRESS")
                    .whereEqualTo("Phone", phoneNumber)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()){

                                }
                            } else {
                                
                            }
                        }
                    });

            db.collection("PROGRESS").document(phoneNumber)
                    .set(progressHashMap, SetOptions.merge());
        }
        else
            {
                progressHashMap.put("Email", emailID);
                db.collection("PROGRESS").document()
                        .set(progressHashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Progress.this, "Saved Email", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                db.collection("PROGRESS")
                        .whereEqualTo("Email", emailID)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()){
                                        Log.i( " EMAILL", document.get("Color").toString());
                                    }
                                } else {
                                    Log.i( " EMAILL", "FAILED");
                                }
                            }
                        });

                db.collection("PROGRESS").document(emailID)
                        .set(progressHashMap, SetOptions.merge());
        }
    }
}