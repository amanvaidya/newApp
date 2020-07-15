package com.audit_new.scanner.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.audit_new.scanner.R;

import com.audit_new.scanner.service.DAO.PRDAO;
import com.audit_new.scanner.service.pojo.PRDetails;
import com.audit_new.scanner.service.remote.ApiUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PrValuesHandler extends Fragment {

    int loc_id=0;
    int sloc_id=0;
    int dep_id=0;
    int emp_id=0;
    String req_type="";
    String asset_type="";
    String reason="";
    long currDtTm;
    String prNo;
    String prDt;
    String resObj;
    String costcenter_id="0";
    String req_level="Super";
    String budget_status="1";
    String entity_id="1";
    String fin_year="1004";
    PRDAO prDao;
    String authKey="";
    String req_id="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v      = inflater.inflate(R.layout.fragment_pr_values_handler, container, false);
        Bundle bundle = this.getArguments();
        loc_id      = bundle.getInt("loc_id");
        sloc_id     = bundle.getInt("sloc_id");
        dep_id      = bundle.getInt("dep_id");
        req_type    = bundle.getString("req_type");
        asset_type  = bundle.getString("asset_type");
        emp_id      = bundle.getInt("emp_id");
        reason      = bundle.getString("reason");
        currDtTm    = System.currentTimeMillis();

        if(req_type.equals("Opex")){
            req_type="PO";
        }else if(req_type.equals("Services")){
            req_type="SO";
        }
        prNo        = "PR/"+req_type+"/"+currDtTm;
        prDao       = ApiUtils.insertPr();
        SharedPreferences preferences = getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        authKey = preferences.getString("auth_token",null);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        prDt = formatter.format(calendar.getTime());
        PRDetails prDetails = new PRDetails(prNo,prDt,Integer.toString(emp_id),costcenter_id,reason,entity_id,Integer.toString(loc_id),Integer.toString(sloc_id),req_type,asset_type,req_level,budget_status,fin_year);
        prDetails.req_no = prNo;
        prDetails.req_date=prDt;
        prDetails.req_by=Integer.toString(emp_id);
        prDetails.costcenter_id=costcenter_id;
        prDetails.remarks=reason;
        prDetails.entity_id=entity_id;
        prDetails.loc_id=Integer.toString(loc_id);
        prDetails.subloc_id=Integer.toString(sloc_id);
        prDetails.req_type=req_type;
        prDetails.asset_type=asset_type;
        prDetails.req_level=req_level;
        prDetails.budget_status=budget_status;
        prDetails.fin_year=fin_year;
        Call<PRDAO> call = prDao.createPr(authKey, prDetails);
        call.enqueue(new Callback<PRDAO>() {
            @Override
            public void onResponse(Call<PRDAO> call, Response<PRDAO> response) {
                System.out.println("here response.body()-->"+response.body());
                PRDAO prdao = response.body();
            }

            @Override
            public void onFailure(Call<PRDAO> call, Throwable t) {
                System.out.println("Failed");
            }
        });

        Call<PRDetails> call1 = prDao.findPr(authKey,prNo);
        call1.enqueue(new Callback<PRDetails>() {
            @Override
            public void onResponse(Call<PRDetails> call, Response<PRDetails> response) {
                resObj = response.body().getReq_id();
                req_id=resObj;
                System.out.println("req_id-->"+req_id);
                Fragment fragment = new ProductSelectionFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putString("req_id",req_id);
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            @Override
            public void onFailure(Call<PRDetails> call, Throwable t) {

                Fragment fragment = new ProductSelectionFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Bundle args = new Bundle();
                args.putString("req_id",req_id);
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        System.out.println("loc_id-->"+loc_id);
        System.out.println("sloc_id-->"+sloc_id);
        System.out.println("dep_id-->"+dep_id);
        System.out.println("req_type-->"+req_type);
        System.out.println("asset_type-->"+asset_type);
        System.out.println("emp_id-->"+emp_id);
        System.out.println("reason-->"+reason);



        return v;
    }
}
