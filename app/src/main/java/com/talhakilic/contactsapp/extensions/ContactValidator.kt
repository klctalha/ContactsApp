package com.talhakilic.contactsapp.extensions

object ContactValidator {
    fun isValidName(name: String): Boolean {
        return name.isNotBlank() && name.all{it.isLetter() || it.isWhitespace()}
    }
    fun isValidPhone(phone:String): Boolean {
        return phone.length==10
    }
    fun isValidEmail(email :String):Boolean {
        return Regex(
            "^[A-Za-z0-9._%+-]+@gmail\\.com$"
        ).matches(email)
    }
    fun isValidBirthDate(date: String):Boolean {
        return Regex("\\d{8}").matches(date)

    }
}