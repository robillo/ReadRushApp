package com.robillo.readrush.data.network.retrofit;

import com.robillo.readrush.data.network.retrofit.model.CollectionListItemSuper;
import com.robillo.readrush.data.network.retrofit.model.CollectionsSuper;
import com.robillo.readrush.data.network.retrofit.model.Content;
import com.robillo.readrush.data.network.retrofit.model.CoverSuper;
import com.robillo.readrush.data.network.retrofit.model.FeaturedSuper;
import com.robillo.readrush.data.network.retrofit.model.LibraryItem;
import com.robillo.readrush.data.network.retrofit.model.ProfileNumbersSuper;
import com.robillo.readrush.data.network.retrofit.model.Review;
import com.robillo.readrush.data.network.retrofit.model.RushAudioContent;
import com.robillo.readrush.data.network.retrofit.model.RushInfo;
import com.robillo.readrush.data.network.retrofit.model.SearchResultSuper;
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

    @FormUrlEncoded
    @POST("new_api/create_review")
    Call<ResponseBody> createReview(
            @Field("user_id") String user_id,
            @Field("rush_id") String rush_id,
            @Field("rating") String rating,
            @Field("review") String review
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
    Call<CollectionListItemSuper> getCollectionFromCid(
            @Path("collection_id") String collection_id
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
    Call<List<Content>> getRushContent(
            @Path("rush_id") String rush_id
    );

    @GET("new_api/add_to_library/{user_id}/{rush_id}")
    Call<ResponseBody> addToUserLibrary(
            @Path("user_id") String user_id,
            @Path("rush_id") String rush_id
    );

    @GET("new_api/search/{search_tag}")
    Call<SearchResultSuper> searchResultsFetch(
            @Path("search_tag") String search_tag
    );

    @GET("new_api/delete_rush/{user_id}/{rush_id}")
    Call<ResponseBody> deleteRushFromLibrary(
            @Path("user_id") String user_id,
            @Path("rush_id") String rush_id
    );

    @GET("new_api/fetch_user_review/{user_id}/{rush_id}")
    Call<ResponseBody> fetchUserReview(
            @Path("user_id") String user_id,
            @Path("rush_id") String rush_id
    );

    @GET("new_api/move_to_read/{user_id}/{rush_id}")
    Call<ResponseBody> moveToRead(
            @Path("user_id") String user_id,
            @Path("rush_id") String rush_id
    );

    @FormUrlEncoded
    @POST("new_api/update_preferences/{user_id}")
    Call<ResponseBody> updatePreferences(
            @Path("user_id") String user_id,
            @Field("new_preference") String updated_preferences
    );

    @GET("new_api/fetch_profile_numbers/{user_id}")
    Call<ProfileNumbersSuper> fetchProfileNumbers(
            @Path("user_id") String user_id
    );

    @GET("new_api/rush_audio_fetch/{rush_id}")
    Call<List<RushAudioContent>> fetchRushAudioContent(
            @Path("rush_id") String rush_id
    );
}
