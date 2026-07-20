package com.talhakilic.contactsapp.view


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.talhakilic.contactsapp.R
import com.talhakilic.contactsapp.entity.Contacts
import com.talhakilic.contactsapp.repository.ContactRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContactScreen(navController: NavController, repo: ContactRepository, inComingData : Contacts) {

    Scaffold(
        Modifier.safeDrawingPadding(),
        topBar = {
            TopAppBar(

                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(R.drawable.detail_contact), contentDescription = "")
                        Text(text= "Detail Screen")
                    } },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.firstColor)
                )
            )
        },
        content ={paddingValues ->
            Column(
                modifier =Modifier.fillMaxSize().padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                OutlinedCard(
                    modifier = Modifier.size(700.dp,500.dp)
                        .padding(all =10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    shape= RoundedCornerShape(corner = CornerSize(16.dp)),
                    border = BorderStroke(1.dp,Color.Black),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(150.dp).padding(all = 20.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            model = inComingData.personImage,
                            contentDescription = ""
                        )

                        OutlinedCard(
                            modifier = Modifier.fillMaxWidth().padding(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(CornerSize(16.dp)),
                            border = BorderStroke(1.dp,Color.Black),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ){
                            Text(text = "Name: ${inComingData.personName}", fontSize = 20.sp,
                                modifier = Modifier.padding(10.dp),  )
                        }
                        OutlinedCard(
                            modifier = Modifier.fillMaxWidth().padding(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(CornerSize(16.dp)),
                            border = BorderStroke(1.dp,Color.Black),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ){
                            Text(text = "Phone: ${inComingData.personPhoneNumber}", fontSize = 20.sp,
                                modifier = Modifier.padding(10.dp),  )
                        }
                        OutlinedCard(
                            modifier = Modifier.fillMaxWidth().padding(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(CornerSize(16.dp)),
                            border = BorderStroke(1.dp,Color.Black),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ){
                            Text(text = "Email: ${inComingData.personEmail}", fontSize = 20.sp,
                                modifier = Modifier.padding(10.dp),  )
                        }
                        OutlinedCard(
                            modifier = Modifier.fillMaxWidth().padding(15.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(CornerSize(16.dp)),
                            border = BorderStroke(1.dp,Color.Black),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ){
                            Text(text = "Birth of Day: ${inComingData.personDateOfBirth}", fontSize = 20.sp,
                                modifier = Modifier.padding(10.dp),  )
                        }


                    }

                }
            }
        }
    )
}