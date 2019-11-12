package com.willlockwood.lockwoodtakehome.data.api

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LockwoodApiService {

    companion object {

        const val API_KEY = "12995248-024645e7fdef25f159d1c27fa"
        const val photos_per_page = 10
        const val BASE_URL = "https://pixabay.com/api/"


        private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()


        private val gson = GsonBuilder().create()
        fun create(): LockwoodApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
            return retrofit.create(LockwoodApiService::class.java)
        }

        fun createRx(): LockwoodApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            return retrofit.create(LockwoodApiService::class.java)
        }
    }

    @GET("http://pixabay.com/api/?image_type=photo")
    fun getPhotos(@Query("per_page") per_page: Int,
                  @Query("key") key: String):
            Call<LockwoodResponse>

    @GET("http://pixabay.com/api/?image_type=photo")
    fun getRxPhotos(@Query("per_page") per_page: Int = photos_per_page,
                    @Query("key") key: String = API_KEY
    ):
            Observable<LockwoodResponse>
//            Observable<List<Lockwood>>



    @GET("http://pixabay.com/api/videos/?")
    fun getVideos(@Query("per_page") per_page: Int, @Query("key") key: String): Call<LockwoodResponse>

}

// @GET annotation receives the template and parameters for the