package com.example.magazine;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.magazine.base.BaseActivity;
import com.example.magazine.model.login.LoginBase;
import com.example.magazine.utils.GlobalPreference;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class QrCodeScanActivity extends BaseActivity {

    private static final int REQUEST_CODE_QR_SCAN = 101;
    private ApiInterface mApiInterface;
    private GlobalPreference mGlobalPreference;
    private Gson gson;
    private static final String TAG = "QrCodeScanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scan);
        init();
        Intent i = new Intent(QrCodeScanActivity.this, QrCodeActivity.class);
        startActivityForResult(i, REQUEST_CODE_QR_SCAN);

    }


    private void init() {
        mGlobalPreference = new GlobalPreference(this);
        gson = new Gson();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(QrCodeScanActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");

            if (result.equals("1")) {
                Toast.makeText(QrCodeScanActivity.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                mGlobalPreference.setLoginStatus(true);
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            } else {
                Toast.makeText(QrCodeScanActivity.this, "Invalid user", Toast.LENGTH_SHORT).show();
                finish();
            }

//            try {
//                JSONObject jsonObject = new JSONObject(result);
//                String status = jsonObject.getString("status");
//                if (status.equals("true")) {
//                    callLoginApi(jsonObject.getString("id"));
//                } else {
//                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

        }
    }

    private void callLoginApi(String id) {
        mApiInterface = ApiClient.getRetrofit(mGlobalPreference.RetrieveIp()).create(ApiInterface.class);
        Call<LoginBase> mLoginBaseCall = mApiInterface.userLogin(id);
        mLoginBaseCall.enqueue(new Callback<LoginBase>() {
            @Override
            public void onResponse(Call<LoginBase> call, Response<LoginBase> response) {
                if (response.body().isSuccess()) {
                    Log.d(TAG, "onResponse: "+response.body().isSuccess());
                    Toast.makeText(QrCodeScanActivity.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                    mGlobalPreference.setLoginStatus(true);
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                } else {
                    Toast.makeText(QrCodeScanActivity.this, "Invalid user", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginBase> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
