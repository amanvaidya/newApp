package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.DepartmentDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DepartmentDAO {

    @POST("getDepList")
    Call<ArrayList<DepartmentDetails>> getAllDep(@Header("Authorization") String authKey);
}
