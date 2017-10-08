package com.example.jevil.gsontest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonApi {

    @GET("/posts")
    Call<List<JsonModel>> getPosts(@Query("id") int id);

    @GET("/comments")
    Call<List<JsonModel>> getComments(@Query("id") int id);

    @GET("/users")
    Call<List<JsonModel>> getUsers();

    @GET("/photos")
    Call<List<JsonModel>> getPhotos(@Query("id") int id);

    @GET("/todos")
    Call<List<JsonModel>> getTodos(@Query("id") int id);

}