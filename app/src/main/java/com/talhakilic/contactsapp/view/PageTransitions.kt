package com.talhakilic.contactsapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.talhakilic.contactsapp.entity.Contacts
import com.talhakilic.contactsapp.repository.ContactRepository
import com.talhakilic.contactsapp.viewmodel.ContactsScreenViewModel

@Composable
fun PageTransitions(repo: ContactRepository){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "display"){
        composable("display"){
            ContactScreen(navController,repo)
        }
        composable("addContact"){
            AddContactScreen(navController,repo)
        }
        composable("detailContact/{data}",
            arguments=listOf(
                navArgument("data"){type = NavType.StringType}
            )
        ){
            val encodedJson = it.arguments?.getString("data")
            val json = java.net.URLDecoder.decode(encodedJson,"UTF-8")
            val contact = Gson().fromJson(json, Contacts::class.java)
            DetailContactScreen(navController,repo,contact)
        }
        composable("editContact/{data}",
            arguments =listOf(
                navArgument("data"){type = NavType.StringType})
            ){
            val encodedJson =it.arguments?.getString("data")
            val json = java.net.URLDecoder.decode(encodedJson,"UTF-8")
            val contact = Gson().fromJson(json, Contacts::class.java)
            EditContactScreen(navController, repo, contact)
        }
    }
}