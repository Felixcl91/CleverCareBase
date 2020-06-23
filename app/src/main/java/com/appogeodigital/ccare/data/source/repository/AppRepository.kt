package com.appogeodigital.ccare.data.source.repository

import com.appogeodigital.ccare.data.models.local.Familiar

import io.reactivex.Flowable

interface AppRepository : AppDataSource {

    fun getItemList(forceRemote: Boolean): Flowable<List<Familiar>>

    fun refreshItems()
}
