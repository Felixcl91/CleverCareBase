package com.appogeodigital.ccare.base

import com.appogeodigital.ccare.data.source.repository.AppRepository
import com.appogeodigital.ccare.data.source.network.NetworkError
import com.appogeodigital.ccare.utils.rx.SchedulerProvider

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View<*>>(
        protected val dataSource: AppRepository,
        protected val schedulerProvider: SchedulerProvider,
        protected val compositeDisposable: CompositeDisposable) :
        BaseContract.Presenter<V> {

    protected var view: V? = null

    protected val isViewAttached: Boolean
        get() = view != null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        compositeDisposable.clear()
        view = null
    }

    override fun handleApiError(throwable: Throwable?) {

        if (throwable == null) {
            view?.onError("Message Error")
            return
        }

        val networkError = NetworkError(throwable)
        val errorMsg = networkError.appErrorMessage
        if (!errorMsg.isEmpty())
            view?.onError(errorMsg)
        else
            view?.onError("Message Error")

    }

}
