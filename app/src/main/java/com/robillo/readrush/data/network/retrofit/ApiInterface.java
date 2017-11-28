package com.robillo.readrush.data.network.retrofit;

import com.robillo.readrush.data.network.retrofit.model.CollectionsSuper;
import com.robillo.readrush.data.network.retrofit.model.CoverSuper;
import com.robillo.readrush.data.network.retrofit.model.FeaturedSuper;
import com.robillo.readrush.data.network.retrofit.model.LibraryItem;
import com.robillo.readrush.data.network.retrofit.model.Review;
import com.robillo.readrush.data.network.retrofit.model.RushInfo;
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
    Call<CollectionsSuper> getCollections(
            @Path("user_id") String user_id
    );

    @GET("new_api/fetch_cover/{user_id}")
    Call<CoverSuper> getTopCovers(
            @Path("user_id") String user_id
    );

    @GET("new_api/fetch_featured/{user_id}")
    Call<FeaturedSuper> getFeaturedBooks(
            @Path("user_id") String user_id
    );

    @GET("new_api/fetch_collection/{collection_id}")
    Call<ResponseBody> getCollectionFromCid(
            @Path("collection_id") int collection_id
    );

    @GET("new_api/library_fetch/{user_id}")
    Call<List<LibraryItem>> fetchLibrary(
            @Path("user_id") String user_id
    );

    @GET("new_api/rush_fetch/{rush_id}")
    Call<List<RushInfo>> fetchRush(
            @Path("rush_id") String rush_id
    );

    @GET("new_api/fetch_rush_review/{rush_id}")
    Call<List<Review>> fetchRushReview(
            @Path("rush_id") String rush_id
    );

    @GET("new_api/rush_content/{rush_id}")
    Call<ResponseBody> getRushContent(
            @Path("rush_id") String rush_id
    );

    @GET("new_api/add_to_library/{user_id}/{rush_id}")
    Call<ResponseBody> addToUserLibrary(
            @Path("user_id") String user_id,
            @Path("rush_id") String rush_id
    );
}
