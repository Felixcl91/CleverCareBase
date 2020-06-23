package com.appogeodigital.ccare.base

import android.app.Dialog
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.appogeodigital.ccare.utils.ext.createProgressDialog
import com.google.android.material.snackbar.Snackbar

abstract class BaseMVPDialog<T> : DialogFragment(), BaseContract.View<T> {

    private var progressDialog: Dialog? = null

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
            // val textView = sbView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
            snackBar.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgressDialog()
    }

}
