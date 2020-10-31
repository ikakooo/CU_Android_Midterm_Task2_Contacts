package com.cst.contacts.details

import androidx.fragment.app.Fragment
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts

class ContactDetailedFragment : Fragment() {


    /** ==== ქვედა კოდს არ ეხებით, მხოლოდ სწორ ადგილას ახდენთ გამოძახებას ==== **/

    private fun getContactById(id: Long): ContactInfo? {
        return Contacts.getQuery().whereEqualTo(Contact.Field.ContactId, 803)
            .find().firstOrNull()?.mapToContactInfo()
    }

}