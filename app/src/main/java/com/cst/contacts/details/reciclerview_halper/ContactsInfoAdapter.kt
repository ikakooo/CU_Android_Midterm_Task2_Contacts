package com.cst.contacts.details.reciclerview_halper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.databinding.DetailedInfoLayoutBinding
import com.cst.contacts.donottouch.ContactsApplication
import com.cst.contacts.donottouch.Email
import com.cst.contacts.donottouch.PhoneNumber


class ContactsInfoAdapter(
    private val phoneNumbersInfoList: MutableList<PhoneNumber>,
    private val emailsInfoList: MutableList<Email>
) :
    RecyclerView.Adapter<ContactsInfoAdapter.ViewHolder>() {

    companion object {
        lateinit var binding: DetailedInfoLayoutBinding
    }

    override fun getItemCount(): Int = emailsInfoList.size + phoneNumbersInfoList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DetailedInfoLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var phoneModel: PhoneNumber
        private lateinit var emailModel: Email


        fun onBind() {
            /////// ადაპტერში ვიუ ბაინდინგი სწორად როგორ მუშაობს გასარჩევი მაქვს /////////////
            if (adapterPosition < phoneNumbersInfoList.size) {
                phoneModel = phoneNumbersInfoList[adapterPosition]
                itemView.findViewById<TextView>(R.id.InfoTextViewID).text = phoneModel.number
                itemView.findViewById<TextView>(R.id.typeTextViewID).text =
                    phoneModel.type.toString()

                itemView.findViewById<ImageView>(R.id.leftImageViewID).apply {
                    setOnClickListener {
                        Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show()
                    }
                    background = ContextCompat.getDrawable(
                        ContactsApplication.instance.getContext(),
                        R.drawable.ic_message
                    )
                }


                when (adapterPosition) {
                    0 -> itemView.findViewById<ImageView>(R.id.RightImageViewID).apply {
                        setOnClickListener {
                            Toast.makeText(context, "Call", Toast.LENGTH_SHORT).show()
                        }
                        background =
                            ContextCompat.getDrawable(
                                ContactsApplication.instance.getContext(),
                                R.drawable.ic_phone
                            )
                    }
                }
            } else {
                emailModel = emailsInfoList[adapterPosition - phoneNumbersInfoList.size]
                itemView.findViewById<TextView>(R.id.InfoTextViewID).text = emailModel.address
                itemView.findViewById<TextView>(R.id.typeTextViewID).text = emailModel.type.toString()

                if (adapterPosition == phoneNumbersInfoList.size) {
                    itemView.findViewById<ImageView>(R.id.RightImageViewID).apply {
                        setOnClickListener {
                            Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show()
                        }
                        background = ContextCompat.getDrawable(
                            ContactsApplication.instance.getContext(),
                            R.drawable.ic_email
                        )
                    }
                    itemView.findViewById<ImageView>(R.id.linearLayoutTopID).isVisible = true
                }
            }
        }
    }
}