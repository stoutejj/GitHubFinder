package com.example.githubfinder.model

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkConnection {

    //Base URL:  https://api.github.com
    //EXAMPLE:   https://api.github.com/search/users?q=tom&order=asc

    @GET("search/users")
    fun getUserNameSearch(
        @Query("q") userName : String
        //@Query("order") order: String = "asc"
    ): Observable<ItemList>
    //-----------------------------------------------------------------------

    //Base URL:  https://api.github.com/users/USERNAME
    //EXAMPLE:   https://api.github.com/users/tom

    @GET("/users/{username}")
    fun getUserInfo(
        @Path("username") userName : String
    ): Observable<UserInfo>
    //-----------------------------------------------------------------------

    //Base URL:  https://api.github.com/users/USERNAME
    //EXAMPLE:   https://api.github.com/users/tom

    @GET("users/{username}/repos")
    fun getUserRepo(
        @Path("username") userName : String
    ): Observable<List<RepoInfo>>

    //Base URL: https://api.github.com/users/USERNAME/repos
    //EXAMPLE: https://api.github.com/users/tom/repos
}


class Network (var url: String){
    fun initRetrofit(): NetworkConnection {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        var retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(NetworkConnection::class.java)
    }
}