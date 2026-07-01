package com.talhakilic.contactsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talhakilic.contactsapp.entity.Contacts
import com.talhakilic.contactsapp.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsScreenViewModel(private val repository : ContactRepository) : ViewModel() {

    var contactList = MutableLiveData<List<Contacts>>()

    fun getAllContacts(){
        viewModelScope.launch(Dispatchers.IO){
            val liste = repository.getAllContacts()
            contactList.value = liste
        }
    }
    fun deleteContacts(contact : Contacts){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contact)
            getAllContacts()
        }
    }
    fun searchContact(searchWord : String){
        viewModelScope.launch(Dispatchers.IO) {
            val liste = repository.searchContact(searchWord)
            contactList.value = liste
        }
    }

}