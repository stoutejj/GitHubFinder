package com.example.githubfinder.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiService {

    //Base URL:  https://api.github.com
    //EXAMPLE:   https://api.github.com/search/users?q=tom&order=asc

    @GET("/search/users")
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
    ): Response<UserInfo>
    //-----------------------------------------------------------------------

    //Base URL: https://api.github.com/users/USERNAME/repos
    //EXAMPLE: https://api.github.com/users/tom/repos

    @GET("users/{username}/repos")
    suspend fun getUserRepo(
        @Path("username") userName: String
    ): Response<List<RepoInfo>>

    companion object {

        fun create(): GitApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit =
                Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(GitApiService::class.java)
        }

    }
}



