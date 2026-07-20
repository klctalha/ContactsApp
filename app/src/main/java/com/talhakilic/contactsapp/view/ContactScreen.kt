package com.talhakilic.contactsapp.view

import android.R.attr.bitmap
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.talhakilic.contactsapp.R
import com.talhakilic.contactsapp.entity.Contacts
import com.talhakilic.contactsapp.repository.ContactRepository
import com.talhakilic.contactsapp.viewmodel.ContactsScreenViewModel
import com.talhakilic.contactsapp.viewmodelfactory.ContactsScreenViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(navController: NavController,repository: ContactRepository){
    val hasSearched = remember{ mutableStateOf(false)}
    val tf = remember{ mutableStateOf("") }
    val viewModel : ContactsScreenViewModel = viewModel(
        factory = ContactsScreenViewModelFactory(repository)
    )
    val contactList = viewModel.contactList.observeAsState(listOf())
    val activity = (LocalActivity.current as Activity)
    val dropdownList = listOf("Detail", "Delete")
    val dropdownMenuStatus = remember{mutableStateOf<Int?>(null)}
    val selectedIndex = remember {mutableStateOf(0)}

    LaunchedEffect(key1=true) {
        viewModel.getAllContacts()
    }
    Scaffold(
        Modifier.safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = {
                    if(hasSearched.value){
                        OutlinedTextField(
                            value = tf.value,
                            onValueChange ={
                                tf.value = it
                                viewModel.searchContact(it)},
                            label ={ Text(text="Search")},
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                focusedContainerColor = Color.Transparent
                            )
                        )
                    }else{
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(modifier = Modifier.padding(end=10.dp),painter= painterResource(R.drawable.account_image), contentDescription = "")
                            Text("Contacts")
                        }

                    }
                        },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.firstColor),
                    titleContentColor = Color.Black
                ),
                actions = {
                    if(hasSearched.value){
                        IconButton(onClick = {hasSearched.value = false
                            tf.value ="" }) {
                            Icon(painter = painterResource(R.drawable.close), contentDescription = "")
                        }
                    }else{
                        IconButton(onClick = {hasSearched.value=true}) {
                            Icon(painter = painterResource(R.drawable.search_image), contentDescription = "")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick={
                    navController.navigate("addContact")
                },
                containerColor = colorResource(R.color.firstColor),
                content ={
                    Icon(painter = painterResource(R.drawable.add), contentDescription = "")
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ){
                items(
                    count = contactList.value!!.count(),
                    itemContent = {
                        val contact = contactList.value!![it]
                        OutlinedCard(
                            modifier = Modifier.fillMaxWidth().padding(all= 8.dp),
                            colors= CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                            border = BorderStroke(1.dp,Color.Gray),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ){
                            Row(
                                modifier = Modifier.fillMaxSize().pointerInput(Unit){ detectTapGestures(
                                    onTap ={
                                        val contactJson = Gson().toJson(contact)
                                        val encodedJson = java.net.URLEncoder.encode(contactJson,"UTF-8")
                                        navController.navigate("editContact/$encodedJson")
                                    },
                                    onLongPress={
                                        dropdownMenuStatus.value = contact.personId
                                    })
                                }
                            ){
                                Row(
                                    modifier= Modifier.fillMaxSize().padding(all=5.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                  AsyncImage(
                                      model =contact.personImage,
                                      contentDescription = "",
                                      contentScale = ContentScale.Crop,
                                      modifier = Modifier.size(60.dp).clip(CircleShape)
                                      )

                                    Row(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text =" ${contact.personName}",fontSize =20.sp)

                                        DropdownMenu(
                                            modifier = Modifier.padding(end =10.dp),
                                            expanded= dropdownMenuStatus.value==contact.personId,
                                            onDismissRequest= {dropdownMenuStatus.value = null}
                                        ) {
                                            dropdownList.forEachIndexed { index, options ->
                                                DropdownMenuItem(
                                                    text = {Text(text = options)},
                                                    onClick ={
                                                        dropdownMenuStatus.value =null
                                                        selectedIndex.value=index
                                                        if(selectedIndex.value ==0){
                                                            val contactJson = Gson().toJson(contact)
                                                            val encodedJson = java.net.URLEncoder.encode(contactJson,"UTF-8")
                                                            navController.navigate("detailContact/$encodedJson")
                                                        }else if(selectedIndex.value ==1){
                                                            dropdownMenuStatus.value = null
                                                            viewModel.deleteContacts(contact)
                                                        }
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}