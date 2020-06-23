package com.appogeodigital.ccare.base

import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.appogeodigital.ccare.di.component.ActivityComponent
import com.appogeodigital.ccare.utils.ext.createProgressDialog
import com.google.android.material.snackbar.Snackbar


abstract class BaseMVPFragment<T> : Fragment(), BaseContract.View<T> {

    private var progressDialog: Dialog? = null

    protected lateinit var activityComponent: ActivityComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity) {
            this.activityComponent = context.activityComponent
        }
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
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
        if (activity != null) {
            val snackBar = Snackbar.make(requireActivity().findViewById(android.R.id.content),
                    message as CharSequence, Snackbar.LENGTH_SHORT)
            val sbView = snackBar.view
            //val textView = sbView.findViewById<View>(androidx.core.d.R.id.snackbar_text) as TextView
            snackBar.show()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        dismissProgressDialog()
    }

}
