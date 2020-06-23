package com.appogeodigital.ccare.data.source.db

import androidx.room.*
import com.appogeodigital.ccare.data.models.local.Familiar
import io.reactivex.Flowable

@Dao
interface FamilyItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleItem(familiarData: Familiar)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultipleItem(itemList: List<Familiar>)

    @Query("SELECT * FROM FamiliarData WHERE id = :itemId")
    fun fetchItemByItemId(itemId: Int): Flowable<Familiar>

    @Query("SELECT * FROM FamiliarData")
    fun fetchItems(): Flowable<List<Familiar>>

    @Update
    fun updateItem(familiarData: Familiar)

    @Delete
    fun deleteItem(familiarData: Familiar)

}