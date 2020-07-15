package com.audit_new.scanner.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.audit_new.scanner.R;
import com.audit_new.scanner.service.DAO.DepartmentDAO;
import com.audit_new.scanner.service.DAO.EmployeeDao;
import com.audit_new.scanner.service.DAO.LocationDAO;
import com.audit_new.scanner.service.DAO.SublocDAO;
import com.audit_new.scanner.service.pojo.DepartmentDetails;
import com.audit_new.scanner.service.pojo.EmployeeDetails;
import com.audit_new.scanner.service.pojo.LocationDetails;
import com.audit_new.scanner.service.pojo.SublocDetails;
import com.audit_new.scanner.service.remote.ApiUtils;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PRFragment extends Fragment {
    Spinner spinnerPr1,spinnerPr2,spinnerPr3,spinnerPr4,spinnerPr6,spinnerPr7=null;

    EditText spinnerPr5, editText;
    Button button;
    DepartmentDAO departmentDao;
    LocationDAO locationDao;
    SublocDAO sublocDao;
    EmployeeDao employeeDao;
    String resObj;
    String reason;
    ArrayList<LocationDetails> locObj;
    ArrayList<SublocDetails> subObj;
    ArrayList<DepartmentDetails> depObj;
    SharedPreferences preferences;
    int employee_id=0;
    String authKey="", requestedBy="";
    List<String> values = new ArrayList<String>();
    List<String> values1 = new ArrayList<String>();
    List<String> values2 = new ArrayList<String>();

    String[] values6 = { "Type of Request","Capex","Opex", "Services"};
    String[] values7 = {"Asset Type","IT","Non IT","Consumables","Service"};

    String selectedValueStr="",selectedValueStr1="";
    int selectedValue1=0,selectedValue2=0,selectedValue3=0;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pr, container, false);

        /*spinner*/

        spinnerPr2 = (Spinner) v.findViewById(R.id.spinnerPr2);
        spinnerPr3 = (Spinner) v.findViewById(R.id.spinnerPr3);
        spinnerPr4 = (Spinner) v.findViewById(R.id.spinnerPr4);
        /**/
        /*Edit Text*/
        editText = (EditText) v.findViewById(R.id.spinnerPr1);
        spinnerPr5 = (EditText) v.findViewById(R.id.spinnerPr5);
        /**/
        /*spinner*/
        spinnerPr6 = (Spinner) v.findViewById(R.id.spinnerPr6);
        spinnerPr7 = (Spinner) v.findViewById(R.id.spinnerPr7);
        /**/
        /*button*/
        button = (Button) v.findViewById(R.id.prButton1);
        /**/
        /*Calling required objects*/
        employeeDao = ApiUtils.getEmpCode();
        departmentDao = ApiUtils.getAllDep();
        locationDao = ApiUtils.getAllLocation();
        sublocDao = ApiUtils.getAllSubloc();
        /**/
        /*calling shared preferences*/
        preferences = this.getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        authKey=preferences.getString("auth_token",null);
        employee_id = Integer.parseInt(preferences.getString("emp_id","0"));
        /**/
        reason = spinnerPr5.getText().toString();
        /*Requested By*/
        Call<EmployeeDetails> call = employeeDao.getEmpCode(authKey,employee_id);
        try{
            call.enqueue(new Callback<EmployeeDetails>() {
                @Override
                public void onResponse(Call<EmployeeDetails> call, Response<EmployeeDetails> response) {

                    resObj = response.body().getEmp_name();

                    requestedBy=resObj.toString();

                    editText.setText(requestedBy);
                }

                @Override
                public void onFailure(Call<EmployeeDetails> call, Throwable t) {
                    requestedBy="Connection Failed";
                }
            });
        }catch(Exception e){
            System.out.println("Err Spinner 1-->"+e);
        }



        /*City*/
        Call<ArrayList<LocationDetails>> call1 = locationDao.getAllLoc(authKey);
        try{
            call1.enqueue(new Callback<ArrayList<LocationDetails>>() {
                @Override
                public void onResponse(Call<ArrayList<LocationDetails>> call, Response<ArrayList<LocationDetails>> response) {
                    locObj=response.body();
                    for (int i = 0; i < locObj.size(); i++) {
                        values.add(locObj.get(i).getLoc_name());
                    }
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, values);
                        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        spinnerPr2.setAdapter(adapter2);

                        spinnerPr2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                               // Toast.makeText(adapterView.getContext(),selectedValue1,Toast.LENGTH_SHORT).show();
                                selectedValue1=(locObj.get(spinnerPr2.getSelectedItemPosition()).getLoc_id());
                                System.out.println("1-->"+selectedValue1);

                                System.out.println("4");
                                getSubloc(authKey, selectedValue1);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });


                }

                @Override
                public void onFailure(Call<ArrayList<LocationDetails>> call, Throwable t) {

                }
            });
        }catch (Exception e){
            System.out.println(e);
        }



        /*Department*/
        Call<ArrayList<DepartmentDetails>> call3 = departmentDao.getAllDep(authKey);
        call3.enqueue(new Callback<ArrayList<DepartmentDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<DepartmentDetails>> call, Response<ArrayList<DepartmentDetails>> response) {
                depObj=response.body();
                for (int i = 0; i < depObj.size(); i++) {
                    values2.add(depObj.get(i).getDep_name());
                }

                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, values2);
                adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerPr4.setAdapter(adapter4);

                spinnerPr4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        Toast.makeText(adapterView.getContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                        selectedValue2=(depObj.get(spinnerPr2.getSelectedItemPosition()).getDep_id());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<DepartmentDetails>> call, Throwable t) {

            }
        });



        /*Type of Request*/
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, values6);
        adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerPr6.setAdapter(adapter5);

        spinnerPr6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                selectedValueStr=(spinnerPr6.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*Asset Type*/
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, values7);
        adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerPr7.setAdapter(adapter6);

        spinnerPr7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(adapterView.getContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                selectedValueStr1=(spinnerPr7.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new PrValuesHandler();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putInt("loc_id",selectedValue1);
                args.putInt("sloc_id",selectedValue2);
                args.putInt("dep_id",selectedValue3);
                args.putInt("emp_id",employee_id);
                args.putString("req_type",selectedValueStr);
                args.putString("asset_type",selectedValueStr1);
                args.putString("reason",reason);
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });

        return v;
    }
    public void getSubloc(String authKey, int selectedValue1){
        System.out.println("authKey-->"+authKey);
        System.out.println("selectedValue1-->"+selectedValue1);
        int loc_id=0;
        if(selectedValue1!=0) {
            loc_id = selectedValue1;
        }
        System.out.println(loc_id);
        values1.clear();
        /*Property*/
        Call<ArrayList<SublocDetails>> call2 = sublocDao.getMySub(authKey,loc_id);
        try{
            call2.enqueue(new Callback<ArrayList<SublocDetails>>() {
                @Override
                public void onResponse(Call<ArrayList<SublocDetails>> call, Response<ArrayList<SublocDetails>> response) {
                    subObj=response.body();
                    if(subObj.size()>0){
                        for (int i = 0; i < subObj.size(); i++) {
                            values1.add(subObj.get(i).getSubloc_name());
                        }
                    }else{
                        values1.add("");
                    }
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, values1);
                    adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    spinnerPr3.setAdapter(adapter3);

                    spinnerPr3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedValue3=0;
                            Toast.makeText(adapterView.getContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                            selectedValue3=(subObj.get(spinnerPr2.getSelectedItemPosition()).getSubloc_id());
                            System.out.println("2--->"+selectedValue3);
                            System.out.println("3");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<ArrayList<SublocDetails>> call, Throwable t) {

                }
            });
        }catch(Exception e){
            System.out.println("Err spinner3-->"+e);
        }

    }
}
