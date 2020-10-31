package com.cst.contacts.donottouch

/** ===== არ შეეხოთ! ===== **/
data class ContactInfo(
    val id: Long,
    val name: String,
    val phoneNumbers: List<PhoneNumber>,
    val emails: List<Email>
)