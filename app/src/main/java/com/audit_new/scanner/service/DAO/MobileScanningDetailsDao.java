package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.MobileScanningDetails;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MobileScanningDetailsDao {

    @POST("audit/auditAsset")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<MobileScanningDetailsDao> insertDetails(@Header("Authorization") String authKey, @Body MobileScanningDetails mobileScanningDetails);
}
