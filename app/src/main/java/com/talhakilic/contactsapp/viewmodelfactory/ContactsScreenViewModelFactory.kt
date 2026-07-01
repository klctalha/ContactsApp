package com.talhakilic.contactsapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talhakilic.contactsapp.repository.ContactRepository
import com.talhakilic.contactsapp.viewmodel.ContactsScreenViewModel

class ContactsScreenViewModelFactory(private var repository : ContactRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsScreenViewModel(repository) as T
    }
}