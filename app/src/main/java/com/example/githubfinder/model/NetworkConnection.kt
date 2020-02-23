package com.example.githubfinder.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.nio.ByteOrder

interface NetworkConnection {

    //Base URL:  https://api.github.com
    //EXAMPLE:   https://api.github.com/users


    @GET("users")
    fun getUserList(): Call<List<User>>

    //-----------------------------------------------------------------------

    //Base URL:  https://api.github.com
    //EXAMPLE:   https://api.github.com/search/users?q=tom&order=asc

    @GET("search/users")
    fun getUserNameSearch(
        @Query("q") userName : String
        //@Query("order") order: String = "asc"
    ): Call<ItemList>
    //-----------------------------------------------------------------------

    //Base URL:  https://api.github.com/users/USERNAME
    //EXAMPLE:   https://api.github.com/users/tom

    @GET("/users/{username}")
    fun getUserInfo(
        @Path("username") userName : String
    ): Call<UserInfo>
    //-----------------------------------------------------------------------

    //Base URL:  https://api.github.com/users/USERNAME
    //EXAMPLE:   https://api.github.com/users/tom

    @GET("users/{username}/repos")
    fun getPublicRepo(
        @Path("username") userName : String
    ): Call<List<RepoInfo>>

    //Base URL: https://api.github.com/users/USERNAME/repos
    //EXAMPLE: https://api.github.com/users/tom/repos
}


class Network (var url: String){
    fun initRetrofit(): NetworkConnection {
        var retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(NetworkConnection::class.java)
    }
}