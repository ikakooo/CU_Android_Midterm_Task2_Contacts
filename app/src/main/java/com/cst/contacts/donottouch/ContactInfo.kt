package com.cst.contacts.donottouch

import java.io.Serializable

/** ===== არ შეეხოთ! ===== **/
data class ContactInfo(
    val id: Long,
    val name: String?,
    val phoneNumbers: List<PhoneNumber>,
    val emails: List<Email>
): Serializable