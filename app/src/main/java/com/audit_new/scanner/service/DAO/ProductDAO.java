package com.audit_new.scanner.service.DAO;

import com.audit_new.scanner.service.pojo.ProductDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ProductDAO {
    @GET("getAll")
    Call<ArrayList<ProductDetails>> getMyProducts(@Header("Authorization") String authKey);
}
