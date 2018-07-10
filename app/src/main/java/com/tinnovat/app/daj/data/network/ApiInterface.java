package com.tinnovat.app.daj.data.network;


/**
 * Created by Rahul on 9/1/2017.
 */

import com.tinnovat.app.daj.data.network.model.EventListModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    //Login
    @POST("/Dar_alJewar/api/loginUser")
    Call<LoginResponseModel> login(@Body RequestParams.LoginReequest params);

    //Get Events and News
    @GET("/Dar_alJewar/api/eventNews")
    Call<EventListModel> getEventsAndNews( @Query("language") String language);

}
