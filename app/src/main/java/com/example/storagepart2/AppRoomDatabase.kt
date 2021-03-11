package com.example.storagepart2

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Employee::class],version = 8)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
}