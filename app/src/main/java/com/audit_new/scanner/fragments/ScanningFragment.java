package com.audit_new.scanner.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.audit_new.scanner.R;
import com.audit_new.scanner.ScanScreen;
import com.audit_new.scanner.service.DAO.FacilityDao;
import com.audit_new.scanner.service.pojo.FacilityResult;
import com.audit_new.scanner.service.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScanningFragment extends Fragment {
 FacilityDao facilityDao;
 ArrayList<FacilityResult> resObj;
 ArrayList<String> CountryName;
 List<String> values = new ArrayList<String>();
 String array[];
 String selectedValue;
 SharedPreferences preferences;
 String authKey="";

 @Nullable
 @Override
 public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



  View v = inflater.inflate(R.layout.fragment_scanning, container, false);
  facilityDao = ApiUtils.getFacilityService();

  final Spinner spinner = (Spinner) v.findViewById(R.id.spinner1);

  preferences = this.getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
  authKey=preferences.getString("auth_token",null);

  Call<ArrayList<FacilityResult>> call = facilityDao.facility(authKey);
  try {
   call.enqueue(new Callback<ArrayList<FacilityResult>>() {
    @Override
    public void onResponse(Call<ArrayList<FacilityResult>> call, Response<ArrayList<FacilityResult>> response) {
     resObj = response.body();
     for (int i = 0; i < resObj.size(); i++) {
      values.add(resObj.get(i).getFacility_code());

     }

     ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, values);
     adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
     spinner.setAdapter(adapter);

     spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

       Toast.makeText(adapterView.getContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
       selectedValue=(spinner.getItemAtPosition(i).toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
     });
    }

    @Override
    public void onFailure(Call<ArrayList<FacilityResult>> call, Throwable t) {

    }
   });
  } catch (Exception e) {
   System.out.println(e);
  }


  Button btn = (Button) v.findViewById(R.id.spinner_btn);

  btn.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    preferences = getActivity().getSharedPreferences("facCode", Context.MODE_PRIVATE);
    SharedPreferences.Editor sharedPrefEdit = preferences.edit();

    sharedPrefEdit.putString("FacCode",selectedValue);
    sharedPrefEdit.commit();
    Intent intent = new Intent(getActivity(), ScanScreen.class);
    startActivity(intent);
   }
  });

  return v;
 }
}