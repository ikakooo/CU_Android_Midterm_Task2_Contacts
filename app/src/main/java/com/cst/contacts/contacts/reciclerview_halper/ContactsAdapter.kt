package com.cst.contacts.contacts.reciclerview_halper

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
import com.cst.contacts.donottouch.ContactInfo
import com.cst.contacts.donottouch.ContactsApplication


class ContactsAdapter(
    private val Contacts: MutableList<ContactInfo>,
    val clickingListener: ItemClickListener
) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    companion object {
        lateinit var binding: ContactsLayoutBinding
    }

    override fun getItemCount(): Int = Contacts.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ContactsLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var model: ContactInfo


        @SuppressLint("UseCompatLoadingForDrawables")
        fun onBind() {
            model = Contacts[adapterPosition]
            //  binding.numberTextViewID.text = "${model.id +1})"
            // binding.TextViewID.text = model.name

            //Glide.with(itemView.context).load(BASE_IMG_URL + model.path).into(itemView.moviesImageViewID)
            val nameFirsCharTextViewID = itemView.findViewById<TextView>(R.id.nameFirsCharTextViewID)
           val  profilePhotoTextViewID = itemView.findViewById<TextView>(R.id.profilePhotoTextViewID)
            when {
                adapterPosition == 0 -> nameFirsCharTextViewID.text = model.name[0].toString()
                Contacts[adapterPosition - 1].name[0] == model.name[0] -> nameFirsCharTextViewID.text = ""
                else -> nameFirsCharTextViewID.text = model.name[0].toString()

            }
//////////////////რანდომ ფერის აღება/////////////////////////////////////
            val randomColor = (Math.random() * 16777215).toInt() or (0xFF shl 24)

            val myIcon: Drawable? = ContactsApplication.instance.getContext().getDrawable(R.drawable.circle)
            /////////ფერის შეცვლა////////
            val filter: ColorFilter = LightingColorFilter(randomColor, randomColor)
            myIcon?.colorFilter = filter
            //////////////შესაბამისი რანდომ ფერის მქონე რესურსის დასეტვა/////////
            profilePhotoTextViewID.background = myIcon
            profilePhotoTextViewID.text = model.name[0].toString()

            itemView.findViewById<TextView>(R.id.TextViewID).text = model.name

            itemView.setOnClickListener {
                clickingListener.viewClicked(adapterPosition,randomColor)
            }
        }
    }
}