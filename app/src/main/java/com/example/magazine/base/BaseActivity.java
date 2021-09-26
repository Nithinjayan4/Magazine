package com.example.magazine.base;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.example.magazine.utils.CommonUtils;

import java.util.ArrayList;



public class BaseActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, PermissionResultCallback {
    ArrayList<String> permissions = new ArrayList<>();
    PermissionUtils permissionUtils;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionUtils = new PermissionUtils(this);
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissionUtils.check_permission(permissions, "App Need to allow all permission for the proper functioning.", 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }


    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    private boolean isCameraPermissionAllowed() {

        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }


    @Override
    public void PermissionGranted(int request_code) {
        Log.e("PERMISSION", "GRANTED");
    }

    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {
        Log.e("PERMISSION PARTIALLY", "GRANTED");
        permissionUtils.check_permission(permissions, "App Need to all permission for the proper functioning.", 1);
    }

    @Override
    public void PermissionDenied(int request_code) {
        Log.e("PERMISSION", "DENIED");
        permissionUtils.check_permission(permissions, "App Need to allow camera and location permission for the proper functioning.", 1);
    }

    @Override
    public void NeverAskAgain(int request_code) {
        Log.e("PERMISSION", "NEVER ASK AGAIN");
    }
}
