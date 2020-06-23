package com.appogeodigital.ccare.data.source.prefs

import android.content.Context
import android.content.SharedPreferences

import com.appogeodigital.ccare.di.ApplicationContext
import com.appogeodigital.ccare.di.PreferenceInfo

import com.appogeodigital.ccare.utils.ext.getBooleanSharedPref
import com.appogeodigital.ccare.utils.ext.getIntSharedPref
import com.appogeodigital.ccare.utils.ext.getStringSharedPref
import com.appogeodigital.ccare.utils.ext.getLongSharedPref
import com.appogeodigital.ccare.utils.ext.setBooleanSharedPref
import com.appogeodigital.ccare.utils.ext.setIntSharedPref
import com.appogeodigital.ccare.utils.ext.setStringSharedPref
import com.appogeodigital.ccare.utils.ext.setLongSharedPref

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppPreferences @Inject
constructor(@ApplicationContext context: Context,
            @PreferenceInfo prefFileName: String) : Preferences {

    private val spPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getBoolean(key: String): Boolean {
        return spPrefs.getBooleanSharedPref(key)
    }

    override fun getLong(key: String): Long {
        return spPrefs.getLongSharedPref(key)
    }

    override fun getInt(key: String): Int {
        return spPrefs.getIntSharedPref(key)
    }

    override fun getString(key: String): String {
        return spPrefs.getStringSharedPref(key)
    }

    override fun setBoolean(key: String, value: Boolean) {
        spPrefs.setBooleanSharedPref(key, value)
    }

    override fun setLong(key: String, value: Long) {
        spPrefs.setLongSharedPref(key, value)
    }

    override fun setInt(key: String, value: Int) {
        spPrefs.setIntSharedPref(key, value)
    }

    override fun getString(key: String, value: String) {
        spPrefs.setStringSharedPref(key, value)
    }

}
