package com.example.baka57r.ezpy;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by nina on 02/07/2019.
 */

public interface NotifLimitApi {
    String BASE_URL = "http://192.168.43.220:3040/api/v1/";
//    String BASE_URL = "http://192.168.18.189:3040/api/v1/";

    //@FormUrlEncoded
    @GET("notif")
    Call<ResponseBody> getNotifLimit(@Query("role") String role, @Query("email") String email, @Header("Authorization") String auths);
}





















