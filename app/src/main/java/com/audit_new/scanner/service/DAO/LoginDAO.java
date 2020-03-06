package com.audit_new.scanner.service.DAO;


import com.audit_new.scanner.service.pojo.LoginResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface LoginDAO {

    @FormUrlEncoded
    @POST("Login/LoginToApp")
    Call<LoginResult> login(@Field("login_name") String login_name, @Field("password") String password);
}