package com.cst.contacts.details

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cst.contacts.databinding.FragmentContactsBinding
import com.cst.contacts.databinding.FragmentDetailedContactBinding
import com.cst.contacts.donottouch.ContactInfo
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
        val contactById = contactID?.let { getContactById(it) }
//        if (contactID != null) {
////            d("dgfdgdfgdg", contactID.toLong().toString())
////            d("dsfsdfd", getContactById(contactID.toLong()).toString())
//
//        }
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