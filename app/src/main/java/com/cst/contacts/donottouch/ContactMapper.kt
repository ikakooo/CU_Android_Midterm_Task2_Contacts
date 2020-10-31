package com.cst.contacts.donottouch

import com.github.tamir7.contacts.Contact

/** ===== არ შეეხოთ! ===== **/

fun Contact.mapToContactInfo() =
    ContactInfo(
        id,
        displayName,
        phoneNumbers.toList().map { phoneNumber ->
            PhoneNumber(
                phoneNumber.number,
                phoneNumber.type.mapToInfoType()
            )
        },
        emails.toList().map { email ->
            Email(
                email.address,
                email.type.mapToInfoType()
            )
        }
    )

private fun com.github.tamir7.contacts.PhoneNumber.Type.mapToInfoType() =
    when (this) {
        com.github.tamir7.contacts.PhoneNumber.Type.HOME -> InfoType.HOME
        com.github.tamir7.contacts.PhoneNumber.Type.MOBILE -> InfoType.MOBILE
        com.github.tamir7.contacts.PhoneNumber.Type.WORK -> InfoType.WORK
        else -> InfoType.OTHER
    }

private fun com.github.tamir7.contacts.Email.Type.mapToInfoType() =
    when (this) {
        com.github.tamir7.contacts.Email.Type.HOME -> InfoType.HOME
        com.github.tamir7.contacts.Email.Type.MOBILE -> InfoType.MOBILE
        com.github.tamir7.contacts.Email.Type.WORK -> InfoType.WORK
        else -> InfoType.OTHER
    }