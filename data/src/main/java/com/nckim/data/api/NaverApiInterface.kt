package com.nckim.data.api

import com.nckim.data.api.ApiClient.NAVER_BASE_URL
import com.nckim.data.model.movie.MovieResponse
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.nckim.data.utils.CLIENT_ID
import com.nckim.data.utils.CLIENT_SECRET


interface NaverApiInterface {
    @GET("v1/search/movie.json")
    fun getSearchMovie(
        @Query("query") query: String,
        @Query("start") start: Int = 1,
        @Query("display") display: Int = 15,
    ): Single<MovieResponse>

    companion object{
        fun createNaverApi(): NaverApiInterface{
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val interceptor = Interceptor{ chain ->
                with(chain){
                    val newReqest = request().newBuilder()
                        .addHeader("X-Naver-Client-Id", CLIENT_ID)
                        .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                        .build()
                    proceed(newReqest)
                }
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(NAVER_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverApiInterface::class.java)
        }
    }
}