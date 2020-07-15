package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.SaveGivenData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SaveAuditDetails {


    @POST("insertAudit")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call <SaveAuditDetails> insertDetails(@Header("Authorization") String authKey,@Body SaveGivenData saveGivenData);
}
