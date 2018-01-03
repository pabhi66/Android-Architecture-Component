package com.abhi.androidarchitecturecomponents.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.abhi.androidarchitecturecomponents.BuildConfig
import com.abhi.androidarchitecturecomponents.data.local.dao.FakePostDao
import com.abhi.androidarchitecturecomponents.data.local.database.FakeDatabase
import com.abhi.androidarchitecturecomponents.data.remote.FakeApiService
import com.abhi.androidarchitecturecomponents.util.Constants
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 */

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.apply {
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(StethoInterceptor())
                addInterceptor(httpLoggingInterceptor)
            }
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): FakeApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return retrofit.create(FakeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFakeDatabase(application: Application): FakeDatabase =
            Room.databaseBuilder(application, FakeDatabase::class.java, "posts.db").build()

    @Provides
    @Singleton
    fun provideFakeDao(fakeDatabase: FakeDatabase): FakePostDao = fakeDatabase.fakePostDao()
}