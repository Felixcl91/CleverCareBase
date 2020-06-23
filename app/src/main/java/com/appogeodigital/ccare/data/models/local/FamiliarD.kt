package com.appogeodigital.ccare.data.models.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FamiliarD (
    val id: Int,
    val imageSrc: Int,
    val email: String,
    val Password: String,
    val name: String,
    val lastName: String,
    val phone: Int,
    val phone2: Int,
    val relation: String,
    val rol: String
) : Parcelable


