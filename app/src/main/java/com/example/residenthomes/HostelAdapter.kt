package com.example.residenthomes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class HostelAdapter(var context: Context, var data:ArrayList<Hostel>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mImgview:ImageView
        var mTxtHostel:TextView
        var mTxtGender:TextView
        var btnDelete:Button
        var storageRef: StorageReference

        init {
            this.mImgview = row?.findViewById(R.id.mImgHostel) as ImageView
            this.mTxtHostel = row?.findViewById(R.id.TvHostelName) as TextView
            this.mTxtGender = row?.findViewById(R.id.TvGender) as TextView
            this.btnDelete = row?.findViewById(R.id.mBtnDelete) as Button
            this.storageRef = FirebaseStorage.getInstance().reference
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.view_hostel,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item: Hostel = getItem(position) as Hostel
        viewHolder.mTxtHostel.text = item.hostel
        viewHolder.mTxtGender.text = item.gender

        // Load image from Firebase Storage
        Glide.with(context).load(item.image).into(viewHolder.mImgview)

        viewHolder.btnDelete.setOnClickListener {
            var ref = FirebaseDatabase.getInstance().getReference().child("Hostels/"+item.id)
            ref.removeValue().addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(context, "Image deleted successfully",
                        Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Image deletion failed",
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