package com.appogeodigital.ccare.ui.family

import com.appogeodigital.ccare.base.BasePresenter
import com.appogeodigital.ccare.data.models.local.Familiar
import com.appogeodigital.ccare.data.source.repository.AppRepository
import com.appogeodigital.ccare.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class FamilyPresenter @Inject
constructor(appRepository: AppRepository,
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable
) :
    BasePresenter<FamilyContract.View>(appRepository, schedulerProvider, compositeDisposable),
    FamilyContract.Presenter {

    private var disposable: Disposable? = null

    override fun loadItems(refresh: Boolean) {
        view?.showProgressDialog()

        if (refresh)
            dataSource.refreshItems()

        if (disposable != null)
            compositeDisposable.delete(disposable!!)

        disposable = dataSource.getItemList()
            .subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .subscribe({ items: List<Familiar> ->

                if (!isViewAttached)
                    return@subscribe

                view?.dismissProgressDialog()
                if (items.isNotEmpty())
                    view?.refreshItemList(items)
                else
                    view?.showEmptyListUI()
            }, { throwable: Throwable? ->
                if (!isViewAttached)
                    return@subscribe

                view?.dismissProgressDialog()
                handleApiError(throwable)
            })

        compositeDisposable.add(disposable!!)
    }

}