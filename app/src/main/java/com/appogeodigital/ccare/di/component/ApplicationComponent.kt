package com.appogeodigital.ccare.di.component

import android.app.Application
import android.content.Context

import com.appogeodigital.ccare.base.MainApplication
import com.appogeodigital.ccare.data.source.repository.AppRepository
import com.appogeodigital.ccare.di.ApplicationContext
import com.appogeodigital.ccare.di.module.ApplicationModule
import com.appogeodigital.ccare.di.module.DataModule
import com.appogeodigital.ccare.di.module.NetworkModule

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [ApplicationModule::class, DataModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun getAppRepository(): AppRepository

    fun inject(app: MainApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

}
