package com.appogeodigital.ccare.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import com.appogeodigital.ccare.data.source.repository.AppRepository
import com.appogeodigital.ccare.di.ActivityContext
import com.appogeodigital.ccare.ui.family.FamilyContract
import com.appogeodigital.ccare.ui.family.FamilyPresenter
import com.appogeodigital.ccare.utils.rx.AppSchedulerProvider
import com.appogeodigital.ccare.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    fun provideMainPresenter(appRepository: AppRepository,
                                      schedulerProvider: SchedulerProvider,
                                      compositeDisposable: CompositeDisposable): FamilyContract.Presenter {
        return FamilyPresenter(appRepository, schedulerProvider, compositeDisposable)
    }

}
