package com.appogeodigital.ccare.di.module

import com.appogeodigital.ccare.data.source.db.AppDatabase
import com.appogeodigital.ccare.data.source.db.AppDbOpenHelper
import com.appogeodigital.ccare.data.source.repository.local.AppLocalDataSource
import com.appogeodigital.ccare.data.source.prefs.AppPreferences
import com.appogeodigital.ccare.data.source.prefs.Preferences
import com.appogeodigital.ccare.data.source.repository.remote.AppRemoteDataSource
import com.appogeodigital.ccare.data.source.repository.AppDataRepository
import com.appogeodigital.ccare.data.source.repository.AppDataSource
import com.appogeodigital.ccare.data.source.repository.AppRepository
import com.appogeodigital.ccare.di.DatabaseInfo
import com.appogeodigital.ccare.di.Local
import com.appogeodigital.ccare.di.PreferenceInfo
import com.appogeodigital.ccare.di.Remote
import com.appogeodigital.ccare.utils.AppConstants

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @Singleton
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @Singleton
    @PreferenceInfo
    fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    @Local
    fun provideAppLocalDataSource(appLocalDataSource: AppLocalDataSource): AppDataSource {
        return appLocalDataSource
    }

    @Provides
    @Singleton
    @Remote
    fun provideAppRemoteDataSource(appRemoteDataSource: AppRemoteDataSource): AppDataSource {
        return appRemoteDataSource
    }

    @Provides
    @Singleton
    fun provideAppRepository(dataRepository: AppDataRepository): AppRepository {
        return dataRepository
    }

    @Provides
    @Singleton
    fun provideAppDb(appDbOpenHelper: AppDbOpenHelper): AppDatabase {
        return appDbOpenHelper.database
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferences: AppPreferences): Preferences {
        return appPreferences
    }

}
