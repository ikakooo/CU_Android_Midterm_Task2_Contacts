package com.cst.contacts.details.reciclerview_halper

import android.annotation.SuppressLint
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.R
import com.cst.contacts.databinding.ContactsLayoutBinding
import com.cst.contacts.databinding.DetailedInfoLayoutBinding
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.ContactsApplication
import com.cst.contacts.donottouch.Email
import com.cst.contacts.donottouch.PhoneNumber


class ContactsInfoAdapter(
    private val phoneNumbersInfoList:MutableList<PhoneNumber>,
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


        @SuppressLint("UseCompatLoadingForDrawables")
        fun onBind() {
            if (adapterPosition < phoneNumbersInfoList.size){
                phoneModel = phoneNumbersInfoList[adapterPosition]
                itemView.findViewById<TextView>(R.id.InfoTextViewID).text =phoneModel.number
                itemView.findViewById<TextView>(R.id.typeTextViewID).text = phoneModel.type.toString()


            }else{
                emailModel = emailsInfoList[adapterPosition-phoneNumbersInfoList.size]
                itemView.findViewById<TextView>(R.id.InfoTextViewID).text =emailModel.address
                itemView.findViewById<TextView>(R.id.typeTextViewID).text = emailModel.type.toString()
            }


            //  binding.numberTextViewID.text = "${model.id +1})"
            // binding.TextViewID.text = model.name

            //Glide.with(itemView.context).load(BASE_IMG_URL + model.path).into(itemView.moviesImageViewID)
//            val nameFirsCharTextViewID = itemView.findViewById<TextView>(R.id.nameFirsCharTextViewID)
//           val  profilePhotoTextViewID = itemView.findViewById<TextView>(R.id.profilePhotoTextViewID)
//            when {
//                adapterPosition == 0 -> nameFirsCharTextViewID.text = model.name[0].toString()
//                Contacts[adapterPosition - 1].name[0] == model.name[0] -> nameFirsCharTextViewID.text = ""
//                else -> nameFirsCharTextViewID.text = model.name[0].toString()
//
//            }
////////////////////რანდომ ფერის აღება/////////////////////////////////////
//            val randomColor = (Math.random() * 16777215).toInt() or (0xFF shl 24)
//
//            val myIcon: Drawable? = ContactsApplication.instance.getContext().getDrawable(R.drawable.circle)
//            /////////ფერის შეცვლა////////
//            val filter: ColorFilter = LightingColorFilter(randomColor, randomColor)
//            myIcon?.colorFilter = filter
//            //////////////შესაბამისი რანდომ ფერის მქონე რესურსის დასეტვა/////////
//            profilePhotoTextViewID.background = myIcon
//            profilePhotoTextViewID.text = model.name[0].toString()
//
//            itemView.findViewById<TextView>(R.id.TextViewID).text = model.name

        }
    }
}