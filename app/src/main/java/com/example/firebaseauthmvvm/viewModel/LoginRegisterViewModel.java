package com.example.firebaseauthmvvm.viewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firebaseauthmvvm.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegisterViewModel extends AndroidViewModel {
    private MutableLiveData<User> mutableLiveData;

    FirebaseAuth firebaseAuth;
    public LoginRegisterViewModel(@NonNull Application application) {
        super(application);
        mutableLiveData = new MutableLiveData<>();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public FirebaseUser getFirebaseUser() {
        return firebaseAuth.getCurrentUser();
    }

    public MutableLiveData<User> getMutableLiveData() {
        return mutableLiveData;
    }


    public void loggedIn(){
        if(getFirebaseUser() != null){
            mutableLiveData.setValue(new User(getFirebaseUser().getEmail(),getFirebaseUser().getUid(),true));
        }


    }

    public void signUp(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(authResult -> {
            mutableLiveData.setValue(new User(email,pass));
        }).addOnFailureListener(e -> {
            Toast.makeText(getApplication().getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void signIn(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(authResult -> {
            mutableLiveData.setValue(new User(email,pass));
        }).addOnFailureListener(e -> {
            Toast.makeText(getApplication().getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        });
    }
    public void signOut(){
        mutableLiveData.setValue(new User());
        firebaseAuth.signOut();
    }


}