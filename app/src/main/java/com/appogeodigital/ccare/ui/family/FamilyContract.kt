package com.appogeodigital.ccare.ui.family

import com.appogeodigital.ccare.base.BaseContract
import com.appogeodigital.ccare.data.models.local.Familiar

interface FamilyContract {

    interface View : BaseContract.View<Presenter> {

        fun refreshItemList(itemList: List<Familiar>)

        fun showEmptyListUI()

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun loadItems(refresh: Boolean)

    }
}