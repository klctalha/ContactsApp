package com.talhakilic.contactsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.talhakilic.contactsapp.repository.ContactRepository
import com.talhakilic.contactsapp.room.ContatcDataBase
import com.talhakilic.contactsapp.ui.theme.ContactsAppTheme
import com.talhakilic.contactsapp.view.PageTransitions
import com.talhakilic.contactsapp.viewmodel.AddContactScreenViewModel
import com.talhakilic.contactsapp.viewmodel.ContactsScreenViewModel
import com.talhakilic.contactsapp.viewmodelfactory.ContactsScreenViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = ContatcDataBase.databaseAccess(this)!!
        val repo = ContactRepository(db.contactDao())
        setContent {
            ContactsAppTheme {
              PageTransitions(repo)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactsAppTheme {
    }
}