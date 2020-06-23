package com.appogeodigital.ccare.base

import android.app.Application
import com.appogeodigital.ccare.data.source.repository.AppRepository
import com.appogeodigital.ccare.di.component.ApplicationComponent
import com.appogeodigital.ccare.di.component.DaggerApplicationComponent
import com.appogeodigital.ccare.di.module.ApplicationModule
import com.appogeodigital.ccare.di.module.DataModule
import com.appogeodigital.ccare.di.module.NetworkModule
import com.appogeodigital.ccare.utils.AppLogger
import javax.inject.Inject

class MainApplication : Application() {

    @Inject
    lateinit var repository: AppRepository

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .dataModule(DataModule())
                .networkModule(NetworkModule())
                .build()

        component.inject(this)

        instance = this

        (instance as MainApplication).initializeInstance()

    }

    private fun initializeInstance() {
        AppLogger.init()
    }

    companion object {
        lateinit var instance: Application
    }

}
