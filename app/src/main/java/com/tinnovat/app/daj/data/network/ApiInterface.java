package com.tinnovat.app.daj.data.network;


/**
 * Created by Rahul on 9/1/2017.
 */

import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    //Login
    @POST("/abc/loginservice/login")
    Call<ResponseModel> login(@Body RequestParams.LoginReequest params);
    

    //Get Something
    @POST("/abc/loginservice/login")
    Call<ResponseModel> getSomething(@Header("Authorization") String authKey, @Body RequestParams.LoginReequest params);

}
