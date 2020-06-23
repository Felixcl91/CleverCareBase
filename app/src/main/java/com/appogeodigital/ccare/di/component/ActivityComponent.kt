package com.appogeodigital.ccare.di.component

import com.appogeodigital.ccare.di.PerActivity
import com.appogeodigital.ccare.di.module.ActivityModule
import com.appogeodigital.ccare.ui.family.FamilyFragment

import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    // fun inject(activity: MainActivity)

    // fun inject(fragment: MainFragment)

    fun inject(fragment: FamilyFragment)

}
