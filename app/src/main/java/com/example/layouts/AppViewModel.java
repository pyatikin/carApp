package com.example.layouts;

import android.app.Activity;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


import com.example.layouts.Repository.AuthRepository;

import com.example.layouts.Models.AuthUser;
import com.example.layouts.R;

/*
public class AppViewModel extends BaseObservable {

    // creating object of Model class
    private AuthUser model;
    private Activity activity;
    private AuthRepository repo = new AuthRepository();
    private MutableLiveData<AuthUser> mAuthUser = new MutableLiveData<>();

    // string variables for
    // toast messages
    private String successMessage = "Login successful";
    private String errorMessage = "Email or Password is not valid";

    @Bindable
    // string variable for
    // toast message
    private String toastMessage = null;

    // getter and setter methods
    // for toast message
    public String getToastMessage() {
        return toastMessage;
    }



    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    // getter and setter methods
    // for email variable
    @Bindable
    public String getUserEmail() {
        return model.getEmail();
    }

    public void setUserEmail(String email) {
        model.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    // getter and setter methods
    // for password variable
    @Bindable
    public String getUserPassword() {
        return model.getPassword();
    }

    public void setUserPassword(String password) {
        model.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    // constructor of com.example.layouts.ViewModel class
    public AppViewModel(Activity a) {
        this.activity = a;
        // instantiating object of
        // model class
    }

    // actions to be performed
    // when user clicks
    // the LOGIN button
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onButtonClicked() {
        if (isValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    // method to keep a check
    // that variable fields must
    // not be kept empty by user
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isValid() {
        TextView mailTextView = this.activity.findViewById(R.id.editEmailAddress);
        String mailOnScreen = mailTextView.getText().toString();
        TextView passTextView = this.activity.findViewById(R.id.ediPassword);
        String passOnScreen = passTextView.getText().toString();
        AuthUser user = repo.getUserByEmail(mailOnScreen);

        //System.out.println(mailOnScreen + passOnScreen);
        System.out.println("mail = " + mailOnScreen + "\nusermail = " + getUserEmail() + "\npass = " + passOnScreen + "\nuserpass = " + getUserPassword());
        return user.getEmail().equals(mailOnScreen) && user.getPassword().equals(passOnScreen);
        //return !TextUtils.isEmpty(getUserEmail()) && Patterns.EMAIL_ADDRESS.matcher(getUserEmail()).matches()
        //&& getUserPassword().length() > 5;
    }
}*/
