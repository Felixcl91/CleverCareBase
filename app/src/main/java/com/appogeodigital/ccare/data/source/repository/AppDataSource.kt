package com.appogeodigital.ccare.data.source.repository

import com.appogeodigital.ccare.data.models.local.Familiar

import io.reactivex.Flowable

interface AppDataSource {

    fun getItemList() : Flowable<List<Familiar>>

    fun updateItemList(items: List<Familiar>)

}
