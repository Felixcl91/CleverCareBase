package com.appogeodigital.ccare.di.module

import com.appogeodigital.ccare.BuildConfig
import com.appogeodigital.ccare.data.source.network.APIService
import com.appogeodigital.ccare.data.source.network.NetworkAPIs
import com.appogeodigital.ccare.data.source.network.NetworkUtils
import com.appogeodigital.ccare.data.source.network.APIHelper
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideCall(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(NetworkUtils.httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofitHelper(apiHelper: APIHelper): NetworkAPIs {
        return apiHelper
    }

}
