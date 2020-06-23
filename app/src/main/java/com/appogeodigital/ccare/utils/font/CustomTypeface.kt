package com.appogeodigital.ccare.utils.font

import android.content.res.AssetManager
import android.graphics.Typeface

import com.appogeodigital.ccare.utils.AppLogger

import java.util.Hashtable


object CustomTypeface {

    private const val TAG = "TypefaceHelper"

    private val cache = Hashtable<String, Typeface>()

    operator fun get(am: AssetManager, assetPath: String): Typeface? {
        if (!cache.containsKey(assetPath)) {
            try {
                val t = Typeface.createFromAsset(am,
                        assetPath)
                cache[assetPath] = t
            } catch (e: Exception) {
                AppLogger.e(TAG, "Could not get typeface '" + assetPath
                        + "' because " + e.message)
                return null
            }

        }
        return cache[assetPath]
    }
}