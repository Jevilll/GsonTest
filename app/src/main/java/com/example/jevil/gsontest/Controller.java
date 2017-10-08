package com.example.jevil.gsontest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Controller {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static JsonApi getApi() {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))//Конвертер, необходимый для преобразования JSON'а в объекты
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);//Создаем объект, при помощи которого будем выполнять запросы
        return jsonApi;
    }
}