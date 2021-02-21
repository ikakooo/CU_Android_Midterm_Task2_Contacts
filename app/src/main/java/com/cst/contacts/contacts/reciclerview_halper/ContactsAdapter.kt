package com.cst.contacts.contacts.reciclerview_halper


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.contacts.Extensions.changeColorDrawable
import com.cst.contacts.R
import com.cst.contacts.databinding.ContactsLayoutBinding
import com.cst.contacts.donottouch.ContactInfo


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


        fun onBind() {
            model = Contacts[adapterPosition]


            val nameFirsCharTextViewID =
                itemView.findViewById<TextView>(R.id.nameFirsCharTextViewID)
            val profilePhotoTextViewID =
                itemView.findViewById<TextView>(R.id.profilePhotoTextViewID)
            when {
                adapterPosition == 0 -> nameFirsCharTextViewID.text = model.name?.get(0)?.toString()
                Contacts[adapterPosition - 1].name?.get(0) == model.name?.get(0) -> nameFirsCharTextViewID.text =
                    ""
                else -> nameFirsCharTextViewID.text = model.name?.get(0)?.toString()

            }
//////////////////რანდომ ფერის აღება და რესაიქლერვიუს აითემებზე დასეტვა/////////////////////////////////////
            val randomColor = (Math.random() * 16777215).toInt() or (0xFF shl 24)
            profilePhotoTextViewID.changeColorDrawable(randomColor, R.drawable.circle)

            profilePhotoTextViewID.text = model.name?.get(0)?.toString()

            itemView.findViewById<TextView>(R.id.TextViewID).text = model.name

            ////////ადაპტერის პოზიციის და რანდომ არჩეული ფერის გადაწოდება ქოლბექით///////
            itemView.setOnClickListener {
                clickingListener.viewClicked(adapterPosition, randomColor)
            }
        }
    }
}