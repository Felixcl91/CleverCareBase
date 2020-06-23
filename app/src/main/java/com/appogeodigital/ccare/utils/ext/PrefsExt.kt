package com.appogeodigital.ccare.utils.ext

import android.content.SharedPreferences

//clears all the shared prefs
fun SharedPreferences.clearSharedPrefs() {
    edit().clear().apply()
}

fun SharedPreferences.getLongSharedPref(key: String): Long {
    return getLong(key, 0)
}

fun SharedPreferences.setLongSharedPref(key: String, value: Long) {
    edit().putLong(key, value).apply()
}

fun SharedPreferences.getIntSharedPref(key: String): Int {
    return getInt(key, 0)
}

fun SharedPreferences.setIntSharedPref(key: String, value: Int) {
    edit().putInt(key, value).apply()
}

fun SharedPreferences.getBooleanSharedPref(key: String): Boolean {
    return getBoolean(key, false)
}

fun SharedPreferences.setBooleanSharedPref(key: String, value: Boolean) {
    edit().putBoolean(key, value).apply()
}

fun SharedPreferences.getStringSharedPref(key: String): String {
    return getString(key, "").toString()
}

fun SharedPreferences.setStringSharedPref(key: String, value: String) {
    edit().putString(key, value).apply()
}
