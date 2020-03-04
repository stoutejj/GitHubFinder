package com.example.githubfinder.model

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
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
    suspend fun getUserNameSearch(
        @Query("q") userName: String
        //@Query("order") order: String = "asc"
    ): Response<ItemList>
    //-----------------------------------------------------------------------

    //Base URL:  https://api.github.com/users/USERNAME
    //EXAMPLE:   https://api.github.com/users/tom

    @GET("/users/{username}")
    suspend fun getUserInfo(
        @Path("username") userName: String
    ): UserInfo
    //-----------------------------------------------------------------------

    //Base URL:  https://api.github.com/users/USERNAME
    //EXAMPLE:   https://api.github.com/users/tom

    @GET("users/{username}/repos")
    suspend fun getUserRepo(
        @Path("username") userName: String
    ): List<RepoInfo>

    //Base URL: https://api.github.com/users/USERNAME/repos
    //EXAMPLE: https://api.github.com/users/tom/repos
}


class Network(var url: String) {
    fun initRetrofit(): NetworkConnection {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit by lazy {

            Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkConnection::class.java)
        }

        return retrofit
    }
}