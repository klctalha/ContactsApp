package com.talhakilic.contactsapp.view

import android.R.attr.onClick
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.RoundedCorner
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.talhakilic.contactsapp.R
import com.talhakilic.contactsapp.entity.Contacts
import com.talhakilic.contactsapp.extensions.BirthDateVisualTransformation
import com.talhakilic.contactsapp.extensions.ContactValidator
import com.talhakilic.contactsapp.extensions.PhoneVisualTransformation
import com.talhakilic.contactsapp.repository.ContactRepository
import com.talhakilic.contactsapp.viewmodel.EditContactScreenViewModel
import com.talhakilic.contactsapp.viewmodelfactory.EditContactScreenViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditContactScreen(navController: NavController, repository : ContactRepository,inComingData: Contacts){
    val viewModel : EditContactScreenViewModel = viewModel(
        factory = EditContactScreenViewModelFactory(repository)
    )
    val tfName = remember{ mutableStateOf("") }
    val tfPhoneNumber = remember{mutableStateOf("")}
    val tfEmail = remember{mutableStateOf("")}
    val tfBirthDay = remember {mutableStateOf("")}
    val isNameError = remember{mutableStateOf(false)}
    val isPhoneError = remember { mutableStateOf(false) }
    val isEmailError = remember { mutableStateOf(false) }
    val isBirthDateError = remember { mutableStateOf(false) }

    val isFormValid = ContactValidator.isValidName(tfName.value) && ContactValidator.isValidPhone(tfPhoneNumber.value) &&
            ContactValidator.isValidEmail(tfEmail.value) && ContactValidator.isValidBirthDate(tfBirthDay.value)
    val imageUri = remember {mutableStateOf<Uri?>(null)}
    val activity = LocalActivity.current as Activity
    val launcher = rememberLauncherForActivityResult(
        contract= ActivityResultContracts.GetContent()
    ){
        uri :Uri? ->
        uri?.let{
            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
            activity.contentResolver.takePersistableUriPermission(it,flag)
            imageUri.value = uri
        }
    }

    LaunchedEffect(key1= true) {
        tfName.value = inComingData.personName
        tfPhoneNumber.value = inComingData.personPhoneNumber
        tfEmail.value= inComingData.personEmail
        tfBirthDay.value = inComingData.personDateOfBirth
    }
    Scaffold(
        Modifier.safeDrawingPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(all =5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(painter = painterResource(R.drawable.edit), contentDescription = "",modifier = Modifier.padding(end= 10.dp))
                        Text(text ="Edit Contact")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.firstColor),
                )
            )
        },
        content = {paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Column(
                    modifier = Modifier.fillMaxWidth().padding(all = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    AsyncImage(
                        modifier = Modifier.size(150.dp).padding(all = 10.dp).clip(CircleShape)
                        .border(1.dp,Color.Gray),
                        contentScale = ContentScale.Crop,
                        model = inComingData.personImage,
                        contentDescription = ""
                    )
                    Spacer(modifier =Modifier.padding(all =20.dp))
                    OutlinedButton(
                        onClick={
                            launcher.launch("image/*")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.firstColor),
                            contentColor = Color.White
                        )
                    ){
                        Text(text = "Update Contact")
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize().padding(all =5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().padding(3.dp),
                        shape = RoundedCornerShape(16.dp),
                        value = tfName.value,
                        onValueChange= {
                            if(it.all {c ->
                                    c.isLetter() || c.isWhitespace()
                                }){
                                tfName.value = it
                                isNameError.value = !ContactValidator.isValidName(it)
                            } },
                        isError = isNameError.value,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            focusedContainerColor = Color.Transparent
                        ),
                        label = {Text(text="Name")},
                        placeholder = {Text(text = "Örnek İsim")},
                        maxLines = 1,

                        )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().padding(3.dp),
                        shape = RoundedCornerShape(16.dp),
                        value = tfPhoneNumber.value,
                        onValueChange = {
                            val number = it.filter { c -> c.isDigit() }.take(10)
                            tfPhoneNumber.value = number
                            isPhoneError.value =
                                !ContactValidator.isValidPhone(number)

                        },
                        visualTransformation = PhoneVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            focusedContainerColor = Color.Transparent
                        ),
                        placeholder = {Text(text = "555 555 55 55")},
                        keyboardOptions= KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        label = {Text(text="PhoneNumber")},
                        isError = isPhoneError.value,
                        maxLines=1

                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().padding(3.dp),
                        shape = RoundedCornerShape(16.dp),
                        value = tfEmail.value,
                        onValueChange= {
                            tfEmail.value = it
                            isEmailError.value=!ContactValidator.isValidEmail(it)
                        },
                        isError= isEmailError.value,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            focusedContainerColor = Color.Transparent
                        ),
                        label = {Text(text="Email")},
                        placeholder = {Text("xxxx@gmail.com")},
                        maxLines =1

                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().padding(3.dp),
                        shape = RoundedCornerShape(16.dp),
                        value = tfBirthDay.value,
                        onValueChange = {
                            val date = it.filter { c -> c.isDigit() }.take(8)
                            tfBirthDay.value = date
                            isBirthDateError.value =
                                !ContactValidator.isValidBirthDate(date)
                        },
                        isError = isBirthDateError.value,
                        visualTransformation = BirthDateVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            focusedContainerColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        placeholder = {
                            Text("../../....")
                        },
                        label = {Text(text="BirthOfDay")},
                        maxLines = 1

                    )
                    Spacer(modifier =Modifier.padding(all=10.dp))
                    OutlinedButton(
                        enabled=isFormValid,
                        onClick={
                            val name = tfName.value
                            val phoneNumber=tfPhoneNumber.value
                            val email = tfEmail.value
                            val birthDay= tfBirthDay.value
                            val image = imageUri.value?.toString()?:inComingData.personImage
                            val updatedContact = Contacts(inComingData.personId,name,phoneNumber,email,birthDay,image)
                            viewModel.updateContact(updatedContact)
                            navController.popBackStack()
                        },
                        colors =ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.firstColor),
                            contentColor = Color.White
                        )
                    ){
                        Text(text="Update Contact")
                    }
                }
            }
        }
    )
}