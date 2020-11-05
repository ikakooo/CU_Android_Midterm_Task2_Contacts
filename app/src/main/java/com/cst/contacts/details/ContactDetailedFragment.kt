package com.cst.contacts.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cst.contacts.Extensions.changeColorDrawable
import com.cst.contacts.R
import com.cst.contacts.databinding.FragmentDetailedContactBinding
import com.cst.contacts.details.reciclerview_halper.ContactsInfoAdapter
import com.cst.contacts.donottouch.*
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
        binding.PhoneImageViewID.isEnabled = false
        binding.MessageImageViewID.isEnabled = false
        binding.videoImageViewID.isEnabled = false
        binding.EmailImageViewID.isEnabled = false


        val bundle = this.arguments
        val contactID = bundle?.getLong("Contact_ID")
        val contactColor = bundle?.getInt("Contact_Color", 1)
        val contactById = contactID?.let { getContactById(it) }

        /////////// შესაბამისი ფერის დასეტვა რაც წინა ფრაგმენტის რესაიქლერვიუს ამ კონკრეტულ აითემს ჰქონდა/////////
        binding.profilePhotoTextViewID.changeColorDrawable(contactColor,R.drawable.circle)



        if (contactById != null) {
            binding.profilePhotoTextViewID.text = contactById.name[0].toString()
            binding.contactNameTextViewID.text = contactById.name


/////////////////// თუ გვაქვს შესაბამისი საკონტაქტო ინფორმაცია მაშინ შესაბამისი კომპონენტები აქტიურდება//////////////////
            if (contactById.phoneNumbers.isNotEmpty()) {
                binding.PhoneImageViewID.isEnabled = true
                binding.MessageImageViewID.isEnabled = true
                binding.videoImageViewID.isEnabled = true
                binding.PhoneImageViewID.changeColorDrawable(R.color.purple_200,R.drawable.ic_phone)
                binding.MessageImageViewID.changeColorDrawable(R.color.purple_200,R.drawable.ic_message)
                binding.videoImageViewID.changeColorDrawable(R.color.purple_200,R.drawable.ic_video)
            }
            if (contactById.emails.isNotEmpty()) {
                binding.EmailImageViewID.isEnabled = true
                binding.EmailImageViewID.changeColorDrawable(R.color.purple_200,R.drawable.ic_email)
            }
        }



        binding.PhoneImageViewID.setOnClickListener {
            Toast.makeText(context, "Call", Toast.LENGTH_SHORT).show()
        }
        binding.MessageImageViewID.setOnClickListener {
            Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show()
        }
        binding.videoImageViewID.setOnClickListener {
            Toast.makeText(context, "Set Up", Toast.LENGTH_SHORT).show()
        }
        binding.EmailImageViewID.setOnClickListener {
            Toast.makeText(context, "Email", Toast.LENGTH_SHORT).show()
        }


////////////////რესაიქლერვიუზე ადაპტერის მიბმა და დეითას დასეტვა //////////////////////////////
        binding.contactInfoRecyclerViewID.apply {
            contactsInfoAdapter = ContactsInfoAdapter(phoneNumbersInfoList, emailsInfoList)
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