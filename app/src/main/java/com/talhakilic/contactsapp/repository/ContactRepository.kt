package com.talhakilic.contactsapp.repository

import com.talhakilic.contactsapp.entity.Contacts
import com.talhakilic.contactsapp.room.ContactsDao
class ContactRepository (private val contactDao: ContactsDao){

    suspend fun getAllContacts():List<Contacts> {
        return contactDao.getAllContacts()
    }
    suspend fun addContact(contact : Contacts){
        contactDao.personAdd(contact)
    }
    suspend fun updateContact(contact: Contacts){
        contactDao.personUpdate(contact)
    }
    suspend fun deleteContact(contact:Contacts) {
        contactDao.personDelete(contact)
    }
    suspend fun searchContact(word:String):List<Contacts> {
       return contactDao.personSearch(word)
    }
}