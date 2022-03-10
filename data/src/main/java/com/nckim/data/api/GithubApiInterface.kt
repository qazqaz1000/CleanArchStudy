package com.nckim.data.api

import com.nckim.data.api.ApiClient.GITHUB_BASE_URL
import com.nckim.data.model.github.GithubResponse
import com.nckim.data.utils.CLIENT_ID
import com.nckim.data.utils.CLIENT_SECRET
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiInterface {
    @GET("search/repositories")
    fun getGithubRepository(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10,
    ) : Single<GithubResponse>

    companion object{
        fun createGithubApi(): GithubApiInterface{
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

//            val interceptor = Interceptor{ chain ->
//                with(chain){
//                    val newReqest = request().newBuilder()
//                        .addHeader("X-Naver-Client-Id", CLIENT_ID)
//                        .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
//                        .build()
//                    proceed(newReqest)
//                }
//            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(GITHUB_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApiInterface::class.java)
        }
    }
}