package com.talhakilic.contactsapp.room

import androidx.core.app.Person
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.talhakilic.contactsapp.entity.Contacts

@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun personAdd(contact : Contacts)
    @Update
    suspend fun personUpdate(contact: Contacts)
    @Delete
    suspend fun personDelete(contact: Contacts)

    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts():List<Contacts>

    @Query("SELECT * FROM contacts WHERE personName LIKE '%' || :searchPerson || '%'")
    suspend fun personSearch(searchPerson: String): List<Contacts>
}