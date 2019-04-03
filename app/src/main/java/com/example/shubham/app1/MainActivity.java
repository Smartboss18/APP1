package com.example.shubham.app1;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static java.util.Arrays.asList;


public class MainActivity extends AppCompatActivity {

    String currentUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userSignInType;

    public static final String ANONYMOUS = "anonymous";

    public static final int RC_SIGN_IN = 1;

    private String mUsername;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public void learn(View view){
    }

    public void quiz(View view){
        Intent learn = new Intent(this, Animal_QuizActivity.class);
        startActivity(learn);
    }

    public  void progress(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        firstTimeCheck();
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(getApplicationContext());

        mFirebaseAuth = FirebaseAuth.getInstance();
        mUsername = ANONYMOUS;

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment,new HomeFragment());
        ft.commit();

        final ArrayList<AuthUI.IdpConfig> providers = new ArrayList<>(asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()));

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user  = firebaseAuth.getCurrentUser();
                if (user!=null){
                    getExistingUserDetails();

                }else{
                    onSignedOutInitialise();
                    Log.i("DDDD", "hhhhhhhh");
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                            .build(),
                            RC_SIGN_IN
                    );
                } 
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            if(resultCode == RESULT_OK){
                //Toast.makeText(this, "SIGNED IN!", Toast.LENGTH_SHORT).show();
                //getExistingUserDetails();

            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "SIGN IN CANCELED", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public void  onSignedInInitialise(String username){
        mUsername = username;
    }

    public void  onSignedOutInitialise(){
        mUsername = ANONYMOUS;
        SharedPreferenceUtils.deleteSharedPrefence(getApplicationContext());
    }

    public void getExistingUserDetails(){
        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        if (user!=null) {
            Toast.makeText(this, "sn", Toast.LENGTH_SHORT).show();
            onSignedInInitialise(user.getDisplayName());
            String userID = user.getUid();
            String userEmail = user.getEmail();
            String phoneNumber = user.getPhoneNumber();
            if (phoneNumber == null) {
                Toast.makeText(MainActivity.this, userEmail, Toast.LENGTH_SHORT).show();
                SharedPreferenceUtils.checkUserExistance(getApplicationContext(), userEmail, "email");
                SharedPreferenceUtils.updateProgress("CurrentUser", userEmail, getApplicationContext());
                userSignInType =  userEmail;

            } else if (userEmail == null) {
                Toast.makeText(MainActivity.this, phoneNumber, Toast.LENGTH_SHORT).show();
                SharedPreferenceUtils.checkUserExistance(getApplicationContext(), phoneNumber, "phoneNumber");
                SharedPreferenceUtils.updateProgress("CurrentUser", phoneNumber, getApplicationContext());
                userSignInType = phoneNumber;
            }
        }
    }
}