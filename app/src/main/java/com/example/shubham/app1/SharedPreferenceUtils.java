package com.example.shubham.app1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferenceUtils {

    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void updateProgress(String type, String value, final Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(type, value);
        editor.apply();
    }

    public static String getDetail(String key, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    public static void updateProgressInCloud(final Context context, String user){
        final Map<String, Object> progressHashMap = new HashMap<>();
        progressHashMap.put("Animal", SharedPreferenceUtils.getDetail("Animal", context));
        progressHashMap.put("Color", SharedPreferenceUtils.getDetail("Color", context));
        progressHashMap.put("Fruit", SharedPreferenceUtils.getDetail("Fruit", context));
        progressHashMap.put("Flag", SharedPreferenceUtils.getDetail("Flag", context));
        progressHashMap.put("RoadSign", SharedPreferenceUtils.getDetail("RoadSign", context));

        db.collection("PROGRESS").document(user)
                .set(progressHashMap, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i(" CompleteListener", "Success");
                }else {
                    Log.i(" CompleteListener", "Failed");
                }
            }
        });
    }

    public static void deleteSharedPrefence(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void checkUserExistance(final Context context, final String user, String type){

            db.collection("PROGRESS")
                    .document(user)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot documentSnapshot = task.getResult();
                                if(documentSnapshot.exists()){
                                    documentSnapshot.getData();

                                    String animal =  documentSnapshot.getString("Animal");
                                    String color = documentSnapshot.getString("Color");
                                    String fruit = documentSnapshot.getString("Fruit");
                                    String flags = documentSnapshot.getString("Flag");
                                    String road_sign = documentSnapshot.getString("RoadSign");

                                    SharedPreferenceUtils.updateProgress("Animal", animal, context);
                                    SharedPreferenceUtils.updateProgress("Color", color, context);
                                    SharedPreferenceUtils.updateProgress("Fruit", fruit, context);
                                    SharedPreferenceUtils.updateProgress("Flag", flags, context);
                                    SharedPreferenceUtils.updateProgress("RoadSign", road_sign, context);

                                }else{
                                    
                                    Map<String, Object> progress = new HashMap<>();
                                    progress.put("Animal: ", "0");
                                    progress.put("Color: ", "0");
                                    progress.put("Fruit: ", "0");
                                    progress.put("Flag: ", "0");
                                    progress.put("RoadSign: ", "0");

                                    SharedPreferenceUtils.updateProgress("Animal", "0", context);
                                    SharedPreferenceUtils.updateProgress("Color", "0", context);
                                    SharedPreferenceUtils.updateProgress("Fruit", "0", context);
                                    SharedPreferenceUtils.updateProgress("Flag", "0", context);
                                    SharedPreferenceUtils.updateProgress("RoadSign", "0", context);

                                    db.collection("PROGRESS").document(user)
                                            .set(progress)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                }
                                            });
                                }
                            }else {
                                Toast.makeText(context, "Failed To Get Data!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    }
}