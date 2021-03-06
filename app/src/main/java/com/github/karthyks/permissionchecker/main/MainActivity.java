package com.github.karthyks.permissionchecker.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.karthyks.permissionchecker.R;
import com.github.karthyks.runtimepermissions.Permission;
import com.github.karthyks.runtimepermissions.PermissionUtil;
import com.github.karthyks.runtimepermissions.googleapi.LocationSettingsActivity;
import com.github.karthyks.runtimepermissions.googleapi.LocationSettingsHelper;
import com.google.android.gms.location.LocationRequest;

import static com.github.karthyks.runtimepermissions.PermissionActivity.PERMISSION_DENIED;
import static com.github.karthyks.runtimepermissions.PermissionActivity.PERMISSION_GRANTED;
import static com.github.karthyks.runtimepermissions.PermissionActivity.PERMISSION_PERMANENTLY_DENIED;
import static com.github.karthyks.runtimepermissions.PermissionActivity.REQUEST_PERMISSION_CODE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button btnLocation = (Button) findViewById(R.id.btn_location);
    btnLocation.setOnClickListener(this);

    Button btnCamera = (Button) findViewById(R.id.btn_camera);
    btnCamera.setOnClickListener(this);

    Button btnMic = (Button) findViewById(R.id.btn_mic);
    btnMic.setOnClickListener(this);

    Button btnSms = (Button) findViewById(R.id.btn_sms);
    btnSms.setOnClickListener(this);

    Button btnPhone = (Button) findViewById(R.id.btn_phone);
    btnPhone.setOnClickListener(this);

    Button btnStorage = (Button) findViewById(R.id.btn_storage);
    btnStorage.setOnClickListener(this);

    Button btnSensors = (Button) findViewById(R.id.btn_sensors);
    btnSensors.setOnClickListener(this);

    Button btnCalendar = (Button) findViewById(R.id.btn_calendar);
    btnCalendar.setOnClickListener(this);

    Button btnContacts = (Button) findViewById(R.id.btn_contacts);
    btnContacts.setOnClickListener(this);

    Button btnLocationSettings = (Button) findViewById(R.id.btn_location_settings);
    btnLocationSettings.setOnClickListener(this);
  }

  private void checkLocationPermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_LOCATION)
        .usingActivity(this).withRationale("This app requires your location for no reason!")
        .build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  private void checkCameraPermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_CAMERA)
        .usingActivity(this).withRationale("This app uses your camera for no reason!").build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_location:
        checkLocationPermission();
        break;
      case R.id.btn_camera:
        checkCameraPermission();
        break;
      case R.id.btn_contacts:
        checkContactsPermission();
        break;
      case R.id.btn_calendar:
        checkCalendarPermission();
        break;
      case R.id.btn_mic:
        checkMicPermission();
        break;
      case R.id.btn_phone:
        checkPhonePermission();
        break;
      case R.id.btn_sensors:
        checkSensorPermission();
        break;
      case R.id.btn_sms:
        checkSmsPermission();
        break;
      case R.id.btn_storage:
        checkStoragePermission();
        break;
      case R.id.btn_location_settings:
        checkLocationSettings();
      default:
    }
  }

  private void checkStoragePermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_STORAGE)
        .usingActivity(this).withRationale("This app uses your storage for no reason!").build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  private void checkSmsPermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_SMS)
        .usingActivity(this).withRationale("This app uses your sms for no reason!").build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  private void checkSensorPermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_SENSORS)
        .usingActivity(this).withRationale("This app uses your sensors for no reason!").build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  private void checkPhonePermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_PHONE)
        .usingActivity(this).withRationale("This app uses your call facility for no reason!")
        .build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  private void checkMicPermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_MICROPHONE)
        .usingActivity(this).withRationale("This app uses your mic for no reason!").build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  private void checkCalendarPermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_CALENDAR)
        .usingActivity(this).withRationale("This app uses your calendar for no reason!").build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  private void checkContactsPermission() {
    Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_CONTACTS)
        .usingActivity(this).withRationale("This app uses your contacts for no reason!").build();
    permission.requestPermission(REQUEST_PERMISSION_CODE);
  }

  private void checkLocationSettings() {
    LocationRequest locationRequest = new LocationRequest()
        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    LocationSettingsHelper settingsApi = new LocationSettingsHelper(MainActivity.this,
        locationRequest, true, false);
    settingsApi.checkLocationRequest();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_PERMISSION_CODE) {
      switch (resultCode) {
        case PERMISSION_GRANTED:
          Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
          break;
        case PERMISSION_DENIED:
          Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
          break;
        case PERMISSION_PERMANENTLY_DENIED:
          Toast.makeText(this, "Permanently denied", Toast.LENGTH_SHORT).show();
          PermissionUtil.openAppSettings(this);
          break;
        default:
      }
    } else if (requestCode == LocationSettingsActivity.REQUEST_LOCATION_SETTINGS) {
      switch (resultCode) {
        case Activity.RESULT_OK:
          Toast.makeText(this, "Allowed Location Settings", Toast.LENGTH_SHORT).show();
          break;
        case Activity.RESULT_CANCELED:
          Toast.makeText(this, "Location Settings canceled", Toast.LENGTH_SHORT).show();
          break;
        default:
      }
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }
}
