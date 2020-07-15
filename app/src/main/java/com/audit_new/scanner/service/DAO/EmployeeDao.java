package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.EmployeeDetails;
import retrofit2.Call;
import retrofit2.http.Field;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface EmployeeDao {
    @FormUrlEncoded
    @POST("findEmployee")
    Call<EmployeeDetails> getEmpCode(@Header("Authorization") String authKey,@Field("emp_id") int emp_id);
}
