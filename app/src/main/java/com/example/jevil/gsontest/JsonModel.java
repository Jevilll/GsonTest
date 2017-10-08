package com.example.jevil.gsontest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("completed")
    @Expose
    private Boolean completed;
    private String category;
    private int index;

    //users
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("website")
    @Expose
    private String website;

    private List<JsonModel> usersList;

    public void setUsersList(List<JsonModel> usersList){this.usersList = usersList;}
    public List<JsonModel> getUsersList() {return usersList;}

    public String getTitle() {return title;}

    public String getBody() {
        return body;
    }

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public boolean getCompleted() {return completed;}

    public String getUrl() {return url;}

    public String getEmail() {return "email: " + email;}

    public String getName() {
        return name;
    }

    //users
    public String getUsername() {
        return username;
    }
    public String getPhone() {
        return phone;
    }
    public String getWebsite() {
        return website;
    }

}