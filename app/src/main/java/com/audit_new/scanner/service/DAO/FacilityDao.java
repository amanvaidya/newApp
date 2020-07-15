package com.audit_new.scanner.service.DAO;


import com.audit_new.scanner.service.pojo.FacilityResult;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FacilityDao {


    @POST("facCode")
    Call<ArrayList<FacilityResult>> facility(@Header("Authorization") String authKey);

}
