package com.example.residenthomes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AmenitiesAdapter(var context: Context, var data:ArrayList<Amenity>):BaseAdapter() {
        private class ViewHolder(row:View?){
            var mTvBreakfast:TextView
            var mTvLunch:TextView
            var mTvSupper:TextView
            var mTvService:TextView
            var mTvServices:TextView
            var mTvbreakfasttime:TextView
            var mTvlunchtime:TextView
            var mTvsuppertime:TextView
            var btnDelete:Button
            init {
                this.mTvBreakfast = row?.findViewById(R.id.mTxtBreakfast) as TextView
                this.mTvLunch = row?.findViewById(R.id.mTxtLunch) as TextView
                this.mTvSupper = row?.findViewById(R.id.mTxtSupper) as TextView
                this.mTvService = row?.findViewById(R.id.mTxtService) as TextView
                this.mTvServices = row?.findViewById(R.id.mTxtServices) as TextView
                this.mTvbreakfasttime = row?.findViewById(R.id.mTxtbreakfastTime) as TextView
                this.mTvlunchtime = row?.findViewById(R.id.mTxtlunchTime) as TextView
                this.mTvsuppertime = row?.findViewById(R.id.mTxtsupperTime) as TextView
                this.btnDelete = row?.findViewById(R.id.mbtnDelete) as Button

            }
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view:View?
            var viewHolder:ViewHolder
            if (convertView == null){
                var layout = LayoutInflater.from(context)
                view = layout.inflate(R.layout.view_amenity,parent,false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            }else{
                view = convertView
                viewHolder = view.tag as ViewHolder
            }
            var item:Amenity = getItem(position) as Amenity
            viewHolder.mTvBreakfast.text = item.breakfast
            viewHolder.mTvLunch.text = item.lunch
            viewHolder.mTvSupper.text = item.supper
            viewHolder.mTvService.text = item.service
            viewHolder.mTvServices.text = item.services
            viewHolder.mTvbreakfasttime.text = item.breakfasttime
            viewHolder.mTvlunchtime.text = item.lunchtime
            viewHolder.mTvsuppertime.text = item.suppertime

            viewHolder.btnDelete.setOnClickListener {
                var ref = FirebaseDatabase.getInstance().getReference().child("Amenities/"+item.id)
                ref.removeValue().addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(context, "Amenities deleted successfully",
                            Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "Amenities deletion failed",
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