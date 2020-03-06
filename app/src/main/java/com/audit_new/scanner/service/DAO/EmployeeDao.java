package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.EmployeeDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface EmployeeDao {


    @GET("find_employee/{emp_id}")
    Call<EmployeeDetails> getEmpCode(@Header("Authorization") String authKey,@Path("emp_id") int emp_id);
}
