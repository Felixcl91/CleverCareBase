package com.appogeodigital.ccare.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appogeodigital.ccare.data.models.local.Familiar

@Database(entities = [Familiar::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): FamilyItemDao

}