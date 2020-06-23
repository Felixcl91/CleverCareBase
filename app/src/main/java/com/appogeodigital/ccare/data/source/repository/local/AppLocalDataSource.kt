package com.appogeodigital.ccare.data.source.repository.local

import com.appogeodigital.ccare.data.models.local.Familiar
import com.appogeodigital.ccare.data.source.db.AppDatabase
import com.appogeodigital.ccare.data.source.db.FamilyItemDao
import com.appogeodigital.ccare.data.source.repository.AppDataSource

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Flowable

@Singleton
class AppLocalDataSource @Inject
constructor(database: AppDatabase) : AppDataSource {

    private val itemDao: FamilyItemDao = database.itemDao()

    override fun getItemList(): Flowable<List<Familiar>> {
        return itemDao.fetchItems()
    }

    override fun updateItemList(items: List<Familiar>) {
        itemDao.insertMultipleItem(items)
    }
}