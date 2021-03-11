package com.example.storagepart2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Employee_Table")
data class Employee(
        @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name= "Employee_Id")
        var emp_id: Int = 0,

        @ColumnInfo(name = "Employee_Name")
        var name: String?= null,

        @ColumnInfo(name = "Employee_Address")
        var address: String?= null,

        @ColumnInfo(name = "Employee_ContactNo")
        var contactNo: String? = null
)