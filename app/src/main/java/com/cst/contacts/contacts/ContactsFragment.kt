package com.cst.contacts.contacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cst.contacts.R
import com.cst.contacts.contacts.reciclerview_halper.ContactsAdapter
import com.cst.contacts.contacts.reciclerview_halper.ItemClickListener
import com.cst.contacts.databinding.FragmentContactsBinding
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.mapToContactInfo
import com.github.tamir7.contacts.Contacts
import java.util.*

class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding

    private var contactsList = mutableListOf<ContactInfo>()
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissionsAndGetContacts()
    }

    private fun displayContacts(contacts: List<ContactInfo>) {
        /** ==== თქვენი კოდი ==== **/
        contactsList.clear()
        binding.contactsRecyclerViewID.apply {
            contactsAdapter = ContactsAdapter(contactsList,
                object : ItemClickListener {
                    override fun viewClicked(position: Int, randomColor: Int) {
                        val contactID = contactsList[position].id

                        val bundle = Bundle()
                        bundle.putLong("Contact_ID", contactID)
                        bundle.putInt("Contact_Color", randomColor)
                        view?.findNavController()?.navigate(R.id.contactDetailedFragmentID, bundle)
                    }
                })
            layoutManager = LinearLayoutManager(context)
            adapter = contactsAdapter

        }
        /////////// პირველადი სრული საკონტაქტო მონაცემების ჩატვირთვა ///////////////////////////
        contactsList.addAll(contacts)
        contactsAdapter.notifyDataSetChanged()

////////////// კონტაქტის ძებნისთვის ტექსტვოჩერი /////////////////////////
        binding.SearchEditTextID.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                contactsList.clear()
                contactsList.addAll(contacts.filter(p0.toString()))
                contactsAdapter.notifyDataSetChanged()
            }

        })
    }
    //////////  ფილტრი სერჩისთვის  /////////////////////////
    fun List<ContactInfo>.filter(nameChars: String): List<ContactInfo> {
        val list = mutableListOf<ContactInfo>()
        forEach {
            if (it.name.toLowerCase(Locale.ROOT).contains(nameChars.toLowerCase(Locale.ROOT))) list.add(it)
        }
        return list
    }

    /** ======== ამის ქვევით კოდს არ შეეხოთ ============= **/

    private fun checkPermissionsAndGetContacts() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                123
            )
        } else {
            displayContacts(getContacts())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            displayContacts(getContacts())
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun getContacts() =
        Contacts.getQuery().find().map {
            it.mapToContactInfo()
        }

}