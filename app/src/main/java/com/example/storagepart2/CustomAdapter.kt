package com.example.storagepart2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){


    var myArrayList: List<Employee> ?=null
    var myListener: OnItemClickListener? = null

    class CustomViewHolder(view: View):RecyclerView.ViewHolder(view){

        val tv_name = view.findViewById<TextView>(R.id.nameTextView)
        val tv_address = view.findViewById<TextView>(R.id.addressTextView)
        val tv_contact = view.findViewById<TextView>(R.id.contactNoTextView)

        val btnEdit = view.findViewById<ImageButton>(R.id.editButton)
        val btnDelete = view.findViewById<ImageButton>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
     return CustomViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)))
    }

    override fun getItemCount(): Int {
        if (myArrayList == null) {
            return 0
        } else {
            return myArrayList?.size!!
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = myArrayList?.get(position)
        holder.tv_name.text = currentItem?.name!!
        holder.tv_address.text = myArrayList?.get(position)?.address
        holder.tv_contact.text = myArrayList?.get(position)?.contactNo

        holder.btnDelete.setOnClickListener {
            myListener?.onItemClickDelete(currentItem.emp_id)
        }

        holder.btnEdit.setOnClickListener {
            myListener?.onItemClickEdit(currentItem.emp_id, position)
        }
    }

    fun setTasks(employeeList: List<Employee>) {
        myArrayList = employeeList
        notifyDataSetChanged()
    }

    fun deleteTasks(position: Int, employeeList: List<Employee>) {
        myArrayList = employeeList
        notifyDataSetChanged()
    }

    fun updateTasks(position : Int, employeeList: List<Employee>){
        myArrayList = employeeList
        notifyItemChanged(position)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemClickListener) {
        myListener = listener
    }


    interface OnItemClickListener{
        fun onItemClickDelete(id: Int)
        fun onItemClickEdit(position: Int,id: Int)
    }
}