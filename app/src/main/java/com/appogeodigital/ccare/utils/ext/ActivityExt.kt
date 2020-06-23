package com.appogeodigital.ccare.utils.ext

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.appogeodigital.ccare.R
import com.appogeodigital.ccare.ui.family.FamilyFragment

@Suppress("UNCHECKED_CAST")
fun <T> FragmentActivity.findFragmentById(@IdRes id: Int): T = supportFragmentManager.findFragmentById(id) as T

fun FragmentActivity.setFragment(fragment: FamilyFragment, frameId: Int) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}

/**
 * Get Device Screen width in pixels
 */
fun Activity.getScreenWidth(): Int {
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

/**
 * Get Device Screen height in pixels
 */
fun Activity.getScreenHeight(): Int {
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.y
}

/**
 * Extension function to hide the keyboard
 */
fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Convenience method for getting into Immersive mode.
 */
fun Activity.goFullscreen() {
    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
            or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
}

/**
 * Creates a Progress Dialog from Activity
 * @return
 */
fun Activity.createProgressDialog(): Dialog {
    val progressDialog = Dialog(this)
    progressDialog.show()
    if (progressDialog.window != null) {
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    progressDialog.setContentView(R.layout.progress_dialog)
    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    return progressDialog
}