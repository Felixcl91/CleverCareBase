package com.appogeodigital.ccare.utils.ext

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.Fragment
import com.appogeodigital.ccare.R
import kotlinx.android.synthetic.main.component_input.*

/**
 * Creates a Progress Dialog
 * @return
 */
fun Fragment.createProgressDialog(): Dialog {
    val progressDialog = Dialog(context!!)
    progressDialog.show()
    if (progressDialog.window != null) {
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    progressDialog.setContentView(R.layout.progress_dialog)
    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    return progressDialog
}