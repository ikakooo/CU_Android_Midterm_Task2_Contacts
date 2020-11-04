package com.cst.contacts.details

import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cst.contacts.R
import com.cst.contacts.databinding.FragmentDetailedContactBinding
import com.cst.contacts.details.reciclerview_halper.ContactsInfoAdapter
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.Email
import com.cst.contacts.donottouch.PhoneNumber
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts

class ContactDetailedFragment : Fragment() {

    private lateinit var binding: FragmentDetailedContactBinding
    private val phoneNumbersInfoList = mutableListOf<PhoneNumber>()
    private val emailsInfoList = mutableListOf<Email>()
    private lateinit var contactsInfoAdapter: ContactsInfoAdapter

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
        if (contactID != null) {
            d("dgfdgdfgdg", contactID.toLong().toString())
            d("dsfsdfd", getContactById(contactID.toLong()).toString())
           // contactById?.emails?.get(0)?.type?.name?.let { d("dsfsdfd", it) }
        }
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

        binding.PhoneImageViewID.setOnClickListener {
         Toast.makeText(context,"Call",Toast.LENGTH_SHORT).show()
        }
        binding.MessageImageViewID.setOnClickListener {
            Toast.makeText(context,"Message",Toast.LENGTH_SHORT).show()
        }
        binding.videoImageViewID.setOnClickListener {
            Toast.makeText(context,"Set Up",Toast.LENGTH_SHORT).show()
        }
        binding.EmailImageViewID.setOnClickListener {
            Toast.makeText(context,"Email",Toast.LENGTH_SHORT).show()
        }



        binding.contactInfoRecyclerViewID.apply {
            contactsInfoAdapter = ContactsInfoAdapter(phoneNumbersInfoList,emailsInfoList)
            layoutManager = LinearLayoutManager(context)
            adapter = contactsInfoAdapter
        }

        contactById?.phoneNumbers?.let { phoneNumbersInfoList.addAll(it) }
        contactById?.emails?.let { emailsInfoList.addAll(it) }
        contactsInfoAdapter.notifyDataSetChanged()
    }

    /** ==== ქვედა კოდს არ ეხებით, მხოლოდ სწორ ადგილას ახდენთ გამოძახებას ==== **/
    private fun getContactById(id: Long): ContactInfo? {
        return Contacts.getQuery().whereEqualTo(Contact.Field.ContactId, id)
            .find().firstOrNull()?.mapToContactInfo()
    }

}