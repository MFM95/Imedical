package com.example.imedical.core.di.module

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.example.imedical.AndroidApplication
import com.example.imedical.BuildConfig
import com.example.imedical.login.data.repository.LoginRepository
import com.example.imedical.login.domain.repository.ILoginRepository
import com.example.imedical.core.UserPreferences
import com.example.imedical.core.db.ImedicalDatabase
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 8/13/2019.
 */

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): AndroidApplication = application

    @Provides
    @Singleton
    fun provideSharedPreferences(application: AndroidApplication): SharedPreferences {
        return application.getSharedPreferences(BuildConfig.PREF_USER, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideRetrofit(userPreferences: UserPreferences): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(createClient(userPreferences))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(userPreferences: UserPreferences): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val interceptor = Interceptor { chain ->
            val builder = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
            //Adds authorization header to request
            val token = userPreferences.getAccessToken()
            if (!token.isNullOrEmpty())
                builder.addHeader("Authorization", "Bearer " + userPreferences.getAccessToken())

            val request = builder.build()
            chain.proceed(request)
        }

        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        okHttpClientBuilder.addInterceptor(interceptor)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideImedicalDatabase(context: AndroidApplication): ImedicalDatabase {
        return Room.databaseBuilder(context, ImedicalDatabase::class.java, "ImedicalDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

}