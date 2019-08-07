package com.example.imedical.core

import android.text.TextUtils
import com.example.imedical.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    fun <S> createService(serviceClass: Class<S>, authToken: String?): S {
        return createService(BuildConfig.SERVER_URL, serviceClass, authToken)
    }

    private fun <S> createService(
        baseURL: String,
        serviceClass: Class<S>, authToken: String?
    ): S {

        var httpClient = OkHttpClient.Builder()
        var builder = Retrofit.Builder()

        httpClient.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            httpClient.addInterceptor(logging)
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        // todo  addCallAdapterFactory
        builder.baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        var interceptor: Interceptor

        var response: Response?
        if (!TextUtils.isEmpty(authToken)) {

            interceptor = Interceptor { chain ->
                val request = chain?.request()?.newBuilder()
                    ?.addHeader("Authorization", authToken?.trim())
                    ?.addHeader("Content-Type", "application/json")
                    ?.build()


                response = chain?.proceed(request)
                response

            }
        } else {
            interceptor = Interceptor { chain ->
                val request = chain?.request()?.newBuilder()
                    ?.addHeader("User-Agent", "Tooli")
                    ?.addHeader("Content-Type", "application/json")
                    ?.addHeader("Charset", "UTF-8")
                    ?.build()

                val response = chain?.proceed(request)
                response

            }
        }

        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor)

            builder.client(httpClient.build())
        }

        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }


}