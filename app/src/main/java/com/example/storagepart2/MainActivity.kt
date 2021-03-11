package com.example.storagepart2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

fun Context.showToast(msg: String){
    Toast.makeText(this ,msg , Toast.LENGTH_SHORT).show()
}

class MainActivity : AppCompatActivity(),CustomAdapter.OnItemClickListener {

    private var recyclerView: RecyclerView? = null
    private var adapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CustomAdapter()
        adapter?.setListener(this)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

        addDataButton.setOnClickListener {
         /*   nameInput.text.clear()
            addressInput.text.clear()
            contactNoInput.text.clear()*/
            saveData()
            this.showToast("Data Successfully Saved")
        }
    }


    private fun saveData() {
        Executors.newSingleThreadExecutor().execute{
            val database: AppRoomDatabase = RoomDatabaseBuilder.getInstance(context = this)
            database.employeeDao().insertEmployee(
                    Employee(name = nameInput.text.toString(),
                            address = addressInput.text.toString(),
                            contactNo = contactNoInput.text.toString()))

            val employeeList: List<Employee> = database.employeeDao().getAllEmployees()
            runOnUiThread{adapter?.setTasks(employeeList)}
        }
    }


    override fun onItemClickDelete(id: Int) {

        val database: AppRoomDatabase = RoomDatabaseBuilder.getInstance(context = this)
        Executors.newSingleThreadExecutor().execute{
            database.employeeDao().deleteEmployee(Employee(emp_id = id))

            val employeeList: List<Employee> = database.employeeDao().getAllEmployees()
            runOnUiThread { adapter?.deleteTasks(id,employeeList) }
        }
    }


    override fun onItemClickEdit(position: Int, id: Int) {
        val database: AppRoomDatabase = RoomDatabaseBuilder.getInstance(context = this)
        Executors.newSingleThreadExecutor().execute{
            database.employeeDao().updateEmployee(Employee(emp_id = 100 , name = "rahul kumawat",address = "jaipur",contactNo = "00000"))

            val employeeList: List<Employee> = database.employeeDao().getAllEmployees()
            runOnUiThread { adapter?.updateTasks(position,employeeList) }
        }
    }
}
