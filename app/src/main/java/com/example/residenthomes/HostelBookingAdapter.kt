package com.example.residenthomes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase


class HostelBookingAdapter(var context: Context, var data:ArrayList<Room>): BaseAdapter() {
    private class ViewHolder(row: View?){
        var mTvHosteltext: TextView
        var mTvStudent: TextView
        var mTvPhone: TextView
        var mTvRoom: TextView
        var mTvEntrydate: TextView
        var btnDelete:Button
        init {
            this.mTvHosteltext = row?.findViewById(R.id.mTxtHosteltext) as TextView
            this.mTvStudent = row?.findViewById(R.id.mTxtStudenttext) as TextView
            this.mTvPhone = row?.findViewById(R.id.mTxtPhonetext) as TextView
            this.mTvRoom = row?.findViewById(R.id.mTxtRoomtext) as TextView
            this.mTvEntrydate = row?.findViewById(R.id.mTxtEntrytext) as TextView
            this.btnDelete = row?.findViewById(R.id.BtnDelete) as Button
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.hostel_booking,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:Room = getItem(position) as Room
        viewHolder.mTvHosteltext.text = item.hostelnames
        viewHolder.mTvStudent.text = item.studentname
        viewHolder.mTvPhone.text = item.phonenumber
        viewHolder.mTvRoom.text = item.room
        viewHolder.mTvEntrydate.text = item.entrydate

        viewHolder.btnDelete.setOnClickListener {
            var ref = FirebaseDatabase.getInstance().getReference().child("Rooms/"+item.id)
            ref.removeValue().addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(context, "Room deleted successfully",
                        Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Room deletion failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}