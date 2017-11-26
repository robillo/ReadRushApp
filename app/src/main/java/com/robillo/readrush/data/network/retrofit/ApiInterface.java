package com.robillo.readrush.data.network.retrofit;

import com.robillo.readrush.data.network.retrofit.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("new_api/login/{email}/{password}")
    Call<List<User>> validateUser(
            @Path("email") String email,
            @Path("password") String password
    );

    @GET("new_api/fetch_collection_cover/{user_id}")
    Call<ResponseBody> getCollections(
            @Path("user_id") int user_id
    );
}
