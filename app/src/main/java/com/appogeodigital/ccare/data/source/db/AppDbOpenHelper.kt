package com.appogeodigital.ccare.data.source.db

import android.content.Context
import androidx.room.Room

import com.appogeodigital.ccare.di.ApplicationContext
import com.appogeodigital.ccare.di.DatabaseInfo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbOpenHelper @Inject
constructor(@ApplicationContext context: Context, @DatabaseInfo name: String) {

    val database: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, name)
            .build()

}
