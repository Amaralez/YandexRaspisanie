package com.bignerdranch.android.yandexraspisanie

import com.bignerdranch.android.yandexraspisanie.responsedata.YandexResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface QueryInterface {

    companion object{

        private const val BASE_URL = "https://api.rasp.yandex.net"
        fun create(): QueryInterface{

            val loInterceptor = HttpLoggingInterceptor()
            loInterceptor.level = HttpLoggingInterceptor.Level.BASIC

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loInterceptor)
                .build()


            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(QueryInterface::class.java)
        }
    }

    //@GET("./vacancies?text=Developer&area=1")
    //@GET("./v3.0/schedule/?&format=json&station=s9600731&lang=ru_RU&page=1&date=2021-12-22&apikey=f54df5ce-f50a-4c02-a23f-25b7cb34a527")
    @GET("./v3.0/schedule/?&format=json")
    fun getResp(
        @Query("station") station: String,
        //@Query("date") date: String,
        @Query("apikey") apikey: String
    ) : Call<YandexResponse>;




}