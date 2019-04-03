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
        Log.i("checkUserExistance", user +" " + type);


            db.collection("PROGRESS")
                    .document(user)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()){
                                DocumentSnapshot documentSnapshot = task.getResult();
                                if(documentSnapshot.exists()){
                                    Log.i("Userrr", "Old");
                                    documentSnapshot.getData();

                                    Log.i("DATAAAA",  documentSnapshot.getString("Animal"));
                                    String animal =  documentSnapshot.getString("Animal");
                                    String color = documentSnapshot.getString("Color");
                                    String fruit = documentSnapshot.getString("Fruit");

                                    SharedPreferenceUtils.updateProgress("Animal", animal, context);
                                    SharedPreferenceUtils.updateProgress("Color", color, context);
                                    SharedPreferenceUtils.updateProgress("Fruit", fruit, context);

                                }else{

                                    SharedPreferenceUtils.getDetail("Animal", context);
                                    Log.i("DATAAA", "New");
                                    Map<String, Object> progress = new HashMap<>();
                                    progress.put("Animal: ", "0");
                                    progress.put("Color: ", "0");
                                    progress.put("Fruit", "0");

//                                    SharedPreferenceUtils.deleteSharedPrefence(context);

                                    SharedPreferenceUtils.updateProgress("Animal", "0", context);
                                    SharedPreferenceUtils.updateProgress("Color", "0", context);
                                    SharedPreferenceUtils.updateProgress("Fruit", "0", context);

                                    db.collection("PROGRESS").document(user)
                                            .set(progress)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(context, "New User", Toast.LENGTH_SHORT).show();
                                                    SharedPreferenceUtils.updateProgress("Animal", "0", context);
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(context, "Couldn't add user.", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }else {
                                Toast.makeText(context, "Failed To Get Data!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

//        db.collection("PROGRESS")
//                .whereEqualTo(type, user)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        Log.i(" checkUserExistance", "USER FOUND SS");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.i(" checkUserExistance", "USER not FOUND ff");
//                    }
//                })
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    String animal = document.get("Animal").toString();
//                                    String color = document.get("Color").toString();
//                                    String fruit = document.get("Fruit").toString();
//
//                                    SharedPreferenceUtils.updateProgress("Animal", animal, context);
//                                    SharedPreferenceUtils.updateProgress("Color", color, context);
//                                    SharedPreferenceUtils.updateProgress("Fruit", fruit, context);
//
//                                    Log.i(" checkUserExistance", "USER FOUND");
//                                }
//                            } else {
//                                SharedPreferenceUtils.updateProgress("Animal", String.valueOf(0), context);
//                                SharedPreferenceUtils.updateProgress("Color", String.valueOf(0), context);
//                                SharedPreferenceUtils.updateProgress("Fruit", String.valueOf(0), context);
//                                Log.i(" checkUserExistance", "USER not FOUND");
//                            }
//                        }
//                    });
    }
}


//    public static void updateProgressInCloud(final Context context){
//
//        final String emailID = SharedPreferenceUtils.getDetail("email" , context);
//        final String phoneNumber = SharedPreferenceUtils.getDetail("phoneNumber", context);
//
//
//        }
//    }


