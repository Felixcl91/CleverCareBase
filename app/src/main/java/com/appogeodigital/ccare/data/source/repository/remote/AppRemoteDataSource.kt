package com.appogeodigital.ccare.data.source.repository.remote

import com.appogeodigital.ccare.data.models.local.Familiar
import com.appogeodigital.ccare.data.models.remote.ResponseItemHolder
import com.appogeodigital.ccare.data.source.repository.AppDataSource
import com.appogeodigital.ccare.data.source.network.NetworkAPIs

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.Flowable

@Singleton
class AppRemoteDataSource @Inject
constructor(private val networkAPIs: NetworkAPIs) : AppDataSource {

    override fun getItemList(): Flowable<List<Familiar>> {
        return networkAPIs.getAPIService()
                .getItemList()
                .map { t: ResponseItemHolder -> t.itemList}
    }

    override fun updateItemList(items: List<Familiar>) {
        //do nothing
    }
}