package com.appogeodigital.ccare.data.models.remote

import com.appogeodigital.ccare.data.models.local.Familiar
import java.io.Serializable
import java.util.ArrayList

data class ResponseItemHolder(val itemList: ArrayList<Familiar>) : Serializable