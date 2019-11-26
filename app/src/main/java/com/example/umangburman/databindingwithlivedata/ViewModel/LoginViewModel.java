package com.example.umangburman.databindingwithlivedata.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.umangburman.databindingwithlivedata.Model.LoginUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {

    private boolean _auth;
    private FirebaseAuth firebaseAuth;

    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<LoginUser> userMutableLiveData;

    public void initFirebase(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public MutableLiveData<LoginUser> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }

    public void onClick(View view) {

        LoginUser loginuser = new LoginUser(EmailAddress.getValue(),Password.getValue());
        userMutableLiveData.setValue(loginuser);
        boolean is_valid = !loginuser.validate();
        if (is_valid){
            firebaseAuth.signInWithEmailAndPassword(loginuser.getStrEmailAddress(),loginuser.getStrPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    set_auth(true);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    set_auth(false);
                }
            });
        }
    }
    public void signUpClick(View view) {

        LoginUser loginuser = new LoginUser(EmailAddress.getValue(),Password.getValue());
        userMutableLiveData.setValue(loginuser);
        boolean is_valid = !loginuser.validate();
        if (is_valid){
            firebaseAuth.createUserWithEmailAndPassword(loginuser.getStrEmailAddress(),loginuser.getStrPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    set_auth(true);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    set_auth(false);
                }
            });
        }
    }

    public boolean is_auth() {
        return _auth;
    }

    public void set_auth(boolean _auth) {
        this._auth = _auth;
    }
}
