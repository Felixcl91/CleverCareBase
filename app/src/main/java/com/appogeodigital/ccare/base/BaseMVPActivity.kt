package com.appogeodigital.ccare.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.appogeodigital.ccare.di.component.ActivityComponent
import com.appogeodigital.ccare.di.component.DaggerActivityComponent
import com.appogeodigital.ccare.di.module.ActivityModule
import com.appogeodigital.ccare.utils.ext.createProgressDialog
import com.google.android.material.snackbar.Snackbar

abstract class BaseMVPActivity<T> : AppCompatActivity(), BaseContract.View<T> {

    private var progressDialog: Dialog? = null
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

    /**
     * Custom Progress Dialog
     */
    override fun showProgressDialog() {
        progressDialog = createProgressDialog()
    }

    override fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

    override fun showToastMessage(message: String?) {
        if (!message.isNullOrEmpty())
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showToastMessage(@StringRes stringResourceId: Int) {
        showToastMessage(getString(stringResourceId))
    }

    override fun showSnackBarMessage(message: String?) {
        if (!message.isNullOrEmpty())
            showSnackBar(message)
    }

    override fun showSnackBarMessage(@StringRes stringResourceId: Int) {
        showSnackBarMessage(getString(stringResourceId))
    }

    /**
     * Creates a SnackBar for message display
     */
    private fun showSnackBar(message: String?) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content),
                message as CharSequence, Snackbar.LENGTH_SHORT)
        val sbView = snackBar.view
        // val textView = sbView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        //val textView = sbView.findViewById<View>(android.support.constraint.R) as TextView
        snackBar.show()
    }

    override fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar("Message Error")
        }
    }

    override fun onError(@StringRes resId: Int) {
        onError(getString(resId))
    }

    override fun onStop() {
        super.onStop()
        dismissProgressDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgressDialog()
    }

}
