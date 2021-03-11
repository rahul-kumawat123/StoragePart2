package com.example.storagepart2

import androidx.room.*


@Dao
interface EmployeeDao {

    @Query("Select * from Employee_Table")
    fun getAllEmployees(): List<Employee>

    @Insert
    fun insertEmployee(employee: Employee)

    @Update
    fun updateEmployee(employee: Employee)

    @Delete
    fun deleteEmployee(employee: Employee)
}