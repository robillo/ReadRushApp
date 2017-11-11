package com.robillo.readrush.data.network.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by robinkamboj on 12/11/17.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("new_api/create_user")
    Call<ResponseBody> createUser(
            @Field("name") String name,
            @Field("email_id") String email,
            @Field("password") String password,
            @Field("preference") String preference
    );

}
