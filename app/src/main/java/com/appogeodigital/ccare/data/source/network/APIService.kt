
package com.appogeodigital.ccare.data.source.network

import com.appogeodigital.ccare.data.models.remote.ResponseItemHolder

import io.reactivex.Flowable
import retrofit2.http.GET

interface APIService {

    @GET("itemList")
    fun getItemList(): Flowable<ResponseItemHolder>

}
