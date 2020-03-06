package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.MobileScanning;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MobileScanningDAO {
    @FormUrlEncoded
    @POST("audit/findFac")
    Call<MobileScanning> scan(@Header("Authorization") String authKey, @Field("facility_code") String facility_code);
}
