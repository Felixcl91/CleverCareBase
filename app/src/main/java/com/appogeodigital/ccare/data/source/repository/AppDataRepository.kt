package com.appogeodigital.ccare.data.source.repository

import androidx.annotation.VisibleForTesting
import com.appogeodigital.ccare.data.models.local.Familiar
import com.appogeodigital.ccare.data.source.prefs.Preferences
import com.appogeodigital.ccare.di.Local
import com.appogeodigital.ccare.di.Remote
import javax.inject.Inject
import javax.inject.Singleton
import io.reactivex.Flowable

@Singleton
class AppDataRepository @Inject
constructor(@param:Remote private val remoteAppDataSource: AppDataSource,
            @param:Local private val localAppDataSource: AppDataSource,
            private val preference: Preferences) : AppRepository {

    @VisibleForTesting
    internal var cachedItemList: List<Familiar>? = null

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    @VisibleForTesting
    internal var cacheIsDirty = false

    //get the items from the server
    private fun getItemFromServerDB(): Flowable<List<Familiar>> {
        return remoteAppDataSource
                .getItemList()
                .doOnNext { items ->
                    localAppDataSource.updateItemList(items)
                    cachedItemList = items
                    cacheIsDirty = false
                }
    }

    //get the elements from local db, and if empty get it from sever
    private fun getItemFromLocalDB(): Flowable<List<Familiar>> {
        return localAppDataSource
                .getItemList()
                .switchIfEmpty(getItemFromServerDB())
                .flatMap { items ->
                    val tempItems: Flowable<List<Familiar>>
                    if (items.isNotEmpty()) {
                        cachedItemList = items
                        cacheIsDirty = false
                        tempItems = Flowable.just(items)
                    } else {
                        cacheIsDirty = true
                        tempItems = getItemFromServerDB()
                    }
                    return@flatMap tempItems
                }
    }

    override fun getItemList(forceRemote: Boolean): Flowable<List<Familiar>> {
        return if (forceRemote) getItemFromServerDB() else getItemList()
    }

    override fun getItemList(): Flowable<List<Familiar>> {
        // Respond immediately with cache if available and not dirty
        if (cachedItemList != null && !cacheIsDirty) {
            return Flowable.just(cachedItemList)
        }

        //if cache is dirty, get the data from server
        return if (cacheIsDirty) getItemFromServerDB() else getItemFromLocalDB()
    }

    override fun updateItemList(items: List<Familiar>) {
        localAppDataSource.updateItemList(items)
    }

    override fun refreshItems() {
        cacheIsDirty = true
    }
}
