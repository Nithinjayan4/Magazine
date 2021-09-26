package com.example.magazine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.magazine.base.BaseActivity;

import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "WelcomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

    }

    public void scan(View view){
      startActivity(new Intent(getApplicationContext(),QrCodeScanActivity.class));
    }



}
