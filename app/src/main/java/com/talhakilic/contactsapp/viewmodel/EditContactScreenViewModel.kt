package com.talhakilic.contactsapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talhakilic.contactsapp.entity.Contacts
import com.talhakilic.contactsapp.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditContactScreenViewModel(private val repository :ContactRepository) : ViewModel(){
    fun updateContact(contact: Contacts){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateContact(contact)
        }
    }

}