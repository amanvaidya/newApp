package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.SublocDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SublocDAO {
    @FormUrlEncoded
    @POST(value="findSubloc")
    Call<ArrayList<SublocDetails>> getMySub(@Header("Authorization") String authKey, @Field("loc_id") int loc_id);
}
