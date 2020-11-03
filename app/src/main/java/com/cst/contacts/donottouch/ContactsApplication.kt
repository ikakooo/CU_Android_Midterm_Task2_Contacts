package com.cst.contacts.donottouch

import android.app.Application
import android.content.Context
import com.github.tamir7.contacts.Contacts

/** ===== არ შეეხოთ! ===== **/

class ContactsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
        Contacts.initialize(this);
    }
    companion object {
        lateinit var instance: ContactsApplication
        private lateinit var context: Context
    }

    fun getContext() = context

}