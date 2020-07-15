package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.PRDetails;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PRDAO {
    @POST("savePR")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<PRDAO> createPr(@Header("Authorization") String authKey, @Body PRDetails pRDetails);

    @FormUrlEncoded
    @POST("findPrId")
    Call<PRDetails> findPr(@Header("Authorization") String authKey, @Field("req_no") String prNo);
}
