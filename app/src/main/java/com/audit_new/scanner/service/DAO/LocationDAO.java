package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.LocationDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LocationDAO {

    @POST("allLoc")
    Call<ArrayList<LocationDetails>> getAllLoc(@Header("Authorization") String authKey);
}
