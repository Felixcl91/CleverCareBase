package com.appogeodigital.ccare.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "familiarData")
data class Familiar(
    @field:PrimaryKey
    val id: Int,
    val imageSrc: Int?,
    val email: String?,
    val Password: String?,
    val name: String?,
    val lastName: String?,
    val phone: Int?,
    val phone2: Int?,
    val relation: String?,
    val rol: String
)
