package com.appogeodigital.ccare.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.appogeodigital.ccare.di.component.ActivityComponent
import com.appogeodigital.ccare.di.component.DaggerActivityComponent
import com.appogeodigital.ccare.di.module.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as MainApplication).component)
                .build()
    }

    public override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        this.intent = intent
    }

}
