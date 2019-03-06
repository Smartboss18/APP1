//package com.example.shubham.app1;
//
//import android.app.Application;
//import android.content.Intent;
//import android.util.Log;
//
//import com.firebase.ui.auth.AuthUI;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//
//public class MyApplication extends Application {
//    FirebaseAuth firebaseAuth;
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        Log.i("DDDD", "FFFFFFF");
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser user  = firebaseAuth.getCurrentUser();
//        if (user!=null){
//            Log.i("DDDD", "AAAAAAAA");
//            startActivity(new Intent(this, MainActivity.class));
//        }else{
//            Log.i("DDDD", "6666666");
//            startActivity(
//                    AuthUI.getInstance()
//                            .createSignInIntentBuilder()
//                            .setIsSmartLockEnabled(false)
//                            .setProviders(
//                                    AuthUI.EMAIL_PROVIDER,
//                                    AuthUI.GOOGLE_PROVIDER,
//                                    AuthUI.FACEBOOK_PROVIDER)
//                            .build()
//                    );
//        }
//    }
//}
