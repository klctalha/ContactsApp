package com.talhakilic.contactsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.talhakilic.contactsapp.entity.Contacts

@Database(entities = [Contacts::class],version=1,exportSchema =false)
abstract class ContatcDataBase : RoomDatabase(){
    abstract fun contactDao() : ContactsDao

    companion object{
        @Volatile
        var INSTANCE: ContatcDataBase?=null

        fun databaseAccess(context: Context): ContatcDataBase?{
            if(INSTANCE==null){
                synchronized(ContatcDataBase::class){
                    INSTANCE=Room.databaseBuilder(
                        context.applicationContext,
                        ContatcDataBase::class.java,
                        "contacts_db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}