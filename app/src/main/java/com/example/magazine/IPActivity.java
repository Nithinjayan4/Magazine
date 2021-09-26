package com.example.magazine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.magazine.utils.GlobalPreference;

public class IPActivity extends AppCompatActivity {

    final Context c = this;
    GlobalPreference mGlobalPreference;
    public static final String TAG=IPActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        mGlobalPreference= new GlobalPreference(getApplicationContext());
        getIP();
    }
    public void getIP(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(c);
        alertDialogBuilderUserInput.setView(mView);
        final EditText userInputDialogEditText = mView.findViewById(R.id.userInputDialog);
        userInputDialogEditText.setText(mGlobalPreference.RetrieveIp());

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        mGlobalPreference.addIp(userInputDialogEditText.getText().toString());
                        userInputDialogEditText.setText(userInputDialogEditText.getText().toString());

                        if(mGlobalPreference.getLoginStatus()){
                            startActivity(new Intent(IPActivity.this, HomeActivity.class));
                        }
                        else{
                            startActivity(new Intent(IPActivity.this, WelcomeActivity.class));
                        }
                         finish();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }
}
