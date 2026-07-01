package com.talhakilic.contactsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talhakilic.contactsapp.entity.Contacts
import com.talhakilic.contactsapp.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddContactScreenViewModel(private val repository : ContactRepository): ViewModel(){

    fun addContact(contact: Contacts){
        viewModelScope.launch(Dispatchers.IO){
            repository.addContact(contact)
        }
    }

}