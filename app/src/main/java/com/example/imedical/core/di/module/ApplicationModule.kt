package com.example.imedical.core.di.module

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.example.imedical.AndroidApplication
import com.example.imedical.BuildConfig
import com.example.imedical.core.db.ImedicalDatabase
import com.example.imedical.login.data.repository.LoginRepository
import com.example.imedical.login.domain.repository.ILoginRepository
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
    fun provideSharedPreferences(application: AndroidApplication): SharedPreferences{
        return application.getSharedPreferences(BuildConfig.PREF_USER, Context.MODE_PRIVATE)
    }

    @Provides @Singleton fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val interceptor = Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }

            okHttpClientBuilder.addInterceptor(loggingInterceptor)
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideImedicalDatabase(dbName: String, context: Context): ImedicalDatabase {
        return Room.databaseBuilder(context, ImedicalDatabase::class.java, dbName).fallbackToDestructiveMigration()
            .build()
    }

}