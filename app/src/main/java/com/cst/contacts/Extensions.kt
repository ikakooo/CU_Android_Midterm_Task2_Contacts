package com.cst.contacts

import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.cst.contacts.donottouch.ContactsApplication
import org.intellij.lang.annotations.Identifier

object Extensions {
    fun View.changeColorDrawable(changeColor:Int?, @Identifier drawableAddress:Int){
        val changeColorIcon: Drawable? =
            ContextCompat.getDrawable(ContactsApplication.instance.getContext(),drawableAddress )
        val sFilter: LightingColorFilter? = changeColor?.let { LightingColorFilter(it, it) }
        changeColorIcon?.colorFilter = sFilter
        background=changeColorIcon
    }

}