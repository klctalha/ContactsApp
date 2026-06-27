package com.talhakilic.contactsapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contacts (
    @PrimaryKey(autoGenerate = true) val personId : Int,
    val personName :String,
    val personPhoneNumber: String,
    val personEmail: String,
    val personDateOfBirth: String,
    val personImage: String
){

}