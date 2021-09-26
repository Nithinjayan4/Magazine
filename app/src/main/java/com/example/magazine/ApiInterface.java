package com.example.magazine;


import com.example.magazine.model.category.CategoryBase;
import com.example.magazine.model.login.LoginBase;
import com.example.magazine.model.magazine.MagazineBase;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("login.php")
    Call<LoginBase> userLogin(@Query("id") String id);

    @GET("getCategory.php")
    Call<CategoryBase> getCategory();


    @GET("getData.php")
    Call<MagazineBase> getData(@Query("category") String category);


}


