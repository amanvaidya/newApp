package com.audit_new.scanner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.audit_new.scanner.service.DAO.MobileScanningDAO;
import com.audit_new.scanner.service.DAO.MobileScanningDetailsDao;
import com.audit_new.scanner.service.DAO.SaveAuditDetails;
import com.audit_new.scanner.service.pojo.MobileScanning;
import com.audit_new.scanner.service.pojo.MobileScanningDetails;
import com.audit_new.scanner.service.remote.ApiUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanScreen extends AppCompatActivity {
    Button buttonScan;
    String authKey="";
    String facility_code, given_assetid, given_serialno, given_remarks;
    int location;
    int slocation;
    int facility;
    int cubicle;
    int audit_id;
    String working_status="working";
    Date c = Calendar.getInstance().getTime();

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
    String formattedDate = df.format(c);
    MobileScanningDAO auditDao;
    MobileScanningDetailsDao mobileScanningDAO;
    EditText editText, editText1, editText2;
    Button given_save;
    String employee_id;
    int emp_id,group_id,subgroup_id,comp_id;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_scanning_details);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setLogo(R.drawable.logo_toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        auditDao =  ApiUtils.getMobileDetails();
        group_id=0;
        subgroup_id=0;
        comp_id=1;

        mobileScanningDAO = ApiUtils.saveMobileDetails();

        SharedPreferences sharedPreferences = ScanScreen.this.getSharedPreferences("facCode", Context.MODE_PRIVATE);
        facility_code = sharedPreferences.getString("FacCode",null);

        SharedPreferences preferences = ScanScreen.this.getSharedPreferences("user_details", Context.MODE_PRIVATE);
        employee_id = preferences.getString("emp_id",null);
        authKey = preferences.getString("auth_token",null);

        emp_id= Integer.parseInt(employee_id);
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        /*API call to etch details*/
        System.out.println("Am I here");
        Call<MobileScanning> call = auditDao.scan(authKey,facility_code);
        call.enqueue(new Callback<MobileScanning>() {
            @Override
            public void onResponse(Call<MobileScanning> call, Response<MobileScanning> response) {
                MobileScanning auditDetails = response.body();
                location = auditDetails.getLoc_id();
                slocation = auditDetails.getSubloc_id();
                facility = auditDetails.getFacility_id();
                cubicle = auditDetails.getCubicle_id();
                audit_id = auditDetails.getId();
            }

            @Override
            public void onFailure(Call<MobileScanning> call, Throwable t) {

            }
        });
        editText = (EditText) findViewById(R.id.remarks1) ;
        editText1= (EditText) findViewById(R.id.serial);
        editText2= (EditText) findViewById(R.id.remarks);
        given_save= (Button) findViewById(R.id.button3);

        given_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                given_assetid = editText.getText().toString();
                given_serialno= editText1.getText().toString();
                given_remarks = editText2.getText().toString();

                MobileScanningDetails mobileScanningDetails = new MobileScanningDetails(given_assetid,location,slocation,cubicle,facility,emp_id,formattedDate,working_status,audit_id);
                mobileScanningDetails.asset_id=given_assetid;
                mobileScanningDetails.scan_loc_id=location;
                mobileScanningDetails.scan_subloc_id=slocation;
                mobileScanningDetails.scan_cubicle_id=cubicle;
                mobileScanningDetails.scan_facility_id=facility;
                mobileScanningDetails.scan_date=formattedDate;
               mobileScanningDetails.working_status=working_status;
               mobileScanningDetails.audit_id=audit_id;
                Call<MobileScanningDetailsDao> call2 = mobileScanningDAO.insertDetails(authKey, mobileScanningDetails);
                call2.enqueue(new Callback<MobileScanningDetailsDao>() {
                    @Override
                    public void onResponse(Call<MobileScanningDetailsDao> call, Response<MobileScanningDetailsDao> response) {
                        MobileScanningDetailsDao mobileScanningDetailsDao = response.body();

                    }

                    @Override
                    public void onFailure(Call<MobileScanningDetailsDao> call, Throwable t) {
                        Toast.makeText(ScanScreen.this,"Success",Toast.LENGTH_SHORT).show();
                        editText.setText("");
                        editText1.setText("");
                        editText2.setText("");
                    }
                });
            }
        });
        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(ScanScreen.this).setCaptureActivity(ScannerActivity.class).initiateScan();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_item_one) {
            Intent intent = new Intent(ScanScreen.this, home.class);
            startActivity(intent);
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(ScanScreen.this,home.class);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_LONG).show();
            } else {
                showResultDialogue(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void showResultDialogue(final String result) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Scan Result")
                .setMessage("Scanned result is " + result)
                .setPositiveButton("Scan Next", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        given_assetid = result;
                        given_serialno="";
                        given_remarks="";

                        MobileScanningDetails mobileScanningDetails = new MobileScanningDetails(given_assetid,location,slocation,cubicle,facility,emp_id,formattedDate,working_status,audit_id);
                        mobileScanningDetails.asset_id=given_assetid;
                        mobileScanningDetails.scan_loc_id=location;
                        mobileScanningDetails.scan_subloc_id=slocation;
                        mobileScanningDetails.scan_cubicle_id=cubicle;
                        mobileScanningDetails.scan_facility_id=facility;
                        mobileScanningDetails.scan_date=formattedDate;
                        mobileScanningDetails.working_status=working_status;
                        mobileScanningDetails.audit_id=audit_id;
                        Call<MobileScanningDetailsDao> call2 = mobileScanningDAO.insertDetails(authKey, mobileScanningDetails);
                        call2.enqueue(new Callback<MobileScanningDetailsDao>() {
                            @Override
                            public void onResponse(Call<MobileScanningDetailsDao> call, Response<MobileScanningDetailsDao> response) {
                                MobileScanningDetailsDao mobileScanningDetailsDao = response.body();

                            }

                            @Override
                            public void onFailure(Call<MobileScanningDetailsDao> call, Throwable t) {
                                Toast.makeText(ScanScreen.this,"Success",Toast.LENGTH_SHORT).show();
                                editText.setText("");
                                editText1.setText("");
                                editText2.setText("");
                            }
                        });

                        new IntentIntegrator(ScanScreen.this).setCaptureActivity(ScannerActivity.class).initiateScan();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNeutralButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                given_assetid = result;
                given_serialno="";
                given_remarks="";

                MobileScanningDetails mobileScanningDetails = new MobileScanningDetails(given_assetid,location,slocation,cubicle,facility,emp_id,formattedDate,working_status,audit_id);
                mobileScanningDetails.asset_id=given_assetid;
                mobileScanningDetails.scan_loc_id=location;
                mobileScanningDetails.scan_subloc_id=slocation;
                mobileScanningDetails.scan_cubicle_id=cubicle;
                mobileScanningDetails.scan_facility_id=facility;
                mobileScanningDetails.scan_date=formattedDate;
                mobileScanningDetails.working_status=working_status;
                mobileScanningDetails.audit_id=audit_id;
                Call<MobileScanningDetailsDao> call2 = mobileScanningDAO.insertDetails(authKey, mobileScanningDetails);
                call2.enqueue(new Callback<MobileScanningDetailsDao>() {
                    @Override
                    public void onResponse(Call<MobileScanningDetailsDao> call, Response<MobileScanningDetailsDao> response) {
                        MobileScanningDetailsDao mobileScanningDetailsDao = response.body();

                    }

                    @Override
                    public void onFailure(Call<MobileScanningDetailsDao> call, Throwable t) {
                        Toast.makeText(ScanScreen.this,"Success",Toast.LENGTH_SHORT).show();
                        editText.setText("");
                        editText1.setText("");
                        editText2.setText("");
                    }
                });


            }
        })
                .show();
    }
}
