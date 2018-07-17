package com.tinnovat.app.daj.data.network;


/**
 * Created by Rahul on 9/1/2017.
 */

import com.tinnovat.app.daj.data.network.model.ComplaintListResponseModel;
import com.tinnovat.app.daj.data.network.model.ContactResponseModel;
import com.tinnovat.app.daj.data.network.model.EventListModel;
import com.tinnovat.app.daj.data.network.model.FoodResponseModel;
import com.tinnovat.app.daj.data.network.model.FuturePhasesResponseModel;
import com.tinnovat.app.daj.data.network.model.RequestParams;
import com.tinnovat.app.daj.data.network.model.LoginResponseModel;
import com.tinnovat.app.daj.data.network.model.Service;
import com.tinnovat.app.daj.data.network.model.ServiceSlots;
import com.tinnovat.app.daj.data.network.model.ServicesResponseModel;

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

    //Get Service List
    @GET("/Dar_alJewar/api/serviceList")
    Call<ServicesResponseModel> getServiceList(@Query("language") String language);

    //Get Service Available Time Slots
    @GET("/Dar_alJewar/api/availableTimeslots")
    Call<ServiceSlots> getAvailableTimeSlots(@Query("category") String category, @Query("service_type") String service_type, @Query("date")String date);

    //Get Future Phases Info
    @GET("/Dar_alJewar/api/futurePhases")
    Call<FuturePhasesResponseModel> getFuturePhasesInfo(@Query("language") String language);

    //Get Food Apps
    @GET("/Dar_alJewar/api/appFood")
    Call<FoodResponseModel> getFoodApps(@Query("language") String language, @Query("device") String device);

    //Get Taxi Apps
    @GET("/Dar_alJewar/api/appTaxi")
    Call<FoodResponseModel> getTaxiApps(@Query("language") String language, @Query("device") String device);

    //Get ContactList
    @GET("/Dar_alJewar/api/contactList")
    Call<ContactResponseModel> getContactList(@Query("language") String language);

    //Get My Complaint list
    @GET("/Dar_alJewar/api/complaintList")
    Call<ComplaintListResponseModel> getComplaintList(@Query("language") String language);
}
