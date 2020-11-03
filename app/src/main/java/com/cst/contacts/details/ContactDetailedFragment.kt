package com.cst.contacts.details

import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cst.contacts.R
import com.cst.contacts.databinding.FragmentContactsBinding
import com.cst.contacts.databinding.FragmentDetailedContactBinding
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.ContactsApplication
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts

class ContactDetailedFragment : Fragment() {
    // R.id.contactDetailedFragmentID
    private lateinit var binding: FragmentDetailedContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        val contactID = bundle?.getLong("Contact_ID")
        val contactColor = bundle?.getInt("Contact_Color",1)
        val contactById = contactID?.let { getContactById(it) }
//        if (contactID != null) {
////            d("dgfdgdfgdg", contactID.toLong().toString())
////            d("dsfsdfd", getContactById(contactID.toLong()).toString())
//
//        }
        val myIcon: Drawable? = context?.let { ContextCompat.getDrawable(it,R.drawable.circle) }
        /////////ფერის შეცვლა////////
        val filter: LightingColorFilter? = contactColor?.let { LightingColorFilter(it, it) }
        myIcon?.colorFilter = filter
        //////////////შესაბამისი რანდომ ფერის მქონე რესურსის დასეტვა/////////
        binding.profilePhotoTextViewID.background = myIcon
        if (contactById != null) {
            binding.profilePhotoTextViewID.text = contactById.name[0].toString()
            binding.contactNameTextViewID.text = contactById.name
        }

    }

    /** ==== ქვედა კოდს არ ეხებით, მხოლოდ სწორ ადგილას ახდენთ გამოძახებას ==== **/
    private fun getContactById(id: Long): ContactInfo? {
        return Contacts.getQuery().whereEqualTo(Contact.Field.ContactId, id)
            .find().firstOrNull()?.mapToContactInfo()
    }

}