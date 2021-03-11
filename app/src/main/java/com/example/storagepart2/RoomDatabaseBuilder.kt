package com.example.storagepart2

import android.content.Context
import androidx.room.Room

object RoomDatabaseBuilder {


    //Creating Instance
    private var INSTANCE: AppRoomDatabase? = null

    fun getInstance(context: Context): AppRoomDatabase {

        //Checking if database exists or not
        if (INSTANCE == null) {
            synchronized(AppRoomDatabase::class) {
                INSTANCE = createRoomDb(context)
            }
        }
        return INSTANCE!!
    }

    //Creating Database
    private fun createRoomDb(context: Context): AppRoomDatabase? {
        return Room.databaseBuilder(context, AppRoomDatabase::class.java, "ROOM DATABASE")
            .fallbackToDestructiveMigration()
            .build()
    }
}