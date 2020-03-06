package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.AuditDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AuditDetailsDao {

    @GET("find_details/{bm_code}")
    Call<AuditDetails> getDetails(@Header("Authorization") String authKey,@Path("bm_code") String bm_code);
}
