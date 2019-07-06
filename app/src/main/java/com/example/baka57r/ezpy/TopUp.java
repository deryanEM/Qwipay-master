package com.example.baka57r.ezpy;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TopUp {
    //String BASE_URL = "http://ezpy.advlop.com/api/v1/charge/midtrans/";
//    String BASE_URL = "http://192.168.18.189:3040/api/v1/saldo/";
    String BASE_URL = "http://192.168.43.220:3040/api/v1/saldo/";

    //@FormUrlEncoded
    @PUT("{email}/{nominal}")
    Call<ResponseBody> getTopup(@Path("email") String email,
                                @Path("nominal") String nominal,
                                @Header("Authorization") String auths);
}
