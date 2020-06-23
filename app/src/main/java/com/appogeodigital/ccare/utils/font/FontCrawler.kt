package com.appogeodigital.ccare.utils.font

import android.content.res.AssetManager
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FontCrawler {
    private var typeface: Typeface? = null

    constructor(typeface: Typeface) {
        this.typeface = typeface
    }

    constructor(assets: AssetManager, assetsFontFileName: String) {
        typeface = CustomTypeface[assets, assetsFontFileName]
    }

    fun replaceFonts(viewTree: ViewGroup) {
        var child: View
        for (i in 0 until viewTree.childCount) {
            child = viewTree.getChildAt(i)
            if (child is ViewGroup) {
                // recursive call
                replaceFonts(child)
            } else if (child is TextView) {
                // base case
                child.typeface = typeface
            }
        }
    }
}