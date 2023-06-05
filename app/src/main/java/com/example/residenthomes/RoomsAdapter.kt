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

class RoomsAdapter(var context: Context, var data:ArrayList<House>): BaseAdapter()  {
    private class ViewHolder(row: View?){
        var ImgDeposit: ImageView
        var ImgOnesharing: ImageView
        var ImgTwosharing: ImageView
        var ImgThreesharing: ImageView
        var ImgFoursharing: ImageView
        var ImgSixsharing: ImageView
        var ImgEightsharing: ImageView
        var ImgTensharing: ImageView
        var mTxtDeposit: TextView
        var mTxtOneSharing: TextView
        var mTxtTwoSharing: TextView
        var mTxtThreeSharing: TextView
        var mTxtFourSharing: TextView
        var mTxtSixSharing: TextView
        var mTxtEightSharing: TextView
        var mTxtTenSharing: TextView
        var btnDelete: Button
        var storageRef: StorageReference

        init {
            this.ImgDeposit = row?.findViewById(R.id.ImgDepositView) as ImageView
            this.ImgOnesharing = row?.findViewById(R.id.ImgOneSharingView) as ImageView
            this.ImgTwosharing = row?.findViewById(R.id.ImgTwoSharingView) as ImageView
            this.ImgThreesharing = row?.findViewById(R.id.ImgThreeSharingView) as ImageView
            this.ImgFoursharing = row?.findViewById(R.id.ImgFourSharingView) as ImageView
            this.ImgSixsharing = row?.findViewById(R.id.ImgSixSharingView) as ImageView
            this.ImgEightsharing = row?.findViewById(R.id.ImgEightSharingView) as ImageView
            this.ImgTensharing = row?.findViewById(R.id.ImgTenSharingView) as ImageView
            this.mTxtDeposit = row?.findViewById(R.id.mTvDeposit) as TextView
            this.mTxtOneSharing = row?.findViewById(R.id.mTvOneSharing) as TextView
            this.mTxtTwoSharing = row?.findViewById(R.id.mTvTwoSharing) as TextView
            this.mTxtThreeSharing = row?.findViewById(R.id.mTvThreeSharing) as TextView
            this.mTxtFourSharing = row?.findViewById(R.id.mTvFourSharing) as TextView
            this.mTxtSixSharing = row?.findViewById(R.id.mTvSixSharing) as TextView
            this.mTxtEightSharing = row?.findViewById(R.id.mTvEightSharing) as TextView
            this.mTxtTenSharing = row?.findViewById(R.id.mTvTenSharing) as TextView
            this.btnDelete = row?.findViewById(R.id.BtnDelete) as Button
            this.storageRef = FirebaseStorage.getInstance().reference
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.view_rooms,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item: House = getItem(position) as House
        viewHolder.mTxtDeposit.text = item.deposit
        viewHolder.mTxtOneSharing.text = item.Onesharing
        viewHolder.mTxtTwoSharing.text = item.Twosharing
        viewHolder.mTxtThreeSharing.text = item.Threesharing
        viewHolder.mTxtFourSharing.text = item.Foursharing
        viewHolder.mTxtSixSharing.text = item.Sixsharing
        viewHolder.mTxtEightSharing.text = item.Eightsharing
        viewHolder.mTxtTenSharing.text = item.Tensharing

        // Load image from Firebase Storage
        Glide.with(context).load(item.Depositimage).into(viewHolder.ImgDeposit)
        Glide.with(context).load(item.Oneimage).into(viewHolder.ImgOnesharing)
        Glide.with(context).load(item.Twoimage).into(viewHolder.ImgTwosharing)
        Glide.with(context).load(item.Threeimage).into(viewHolder.ImgThreesharing)
        Glide.with(context).load(item.Fourimage).into(viewHolder.ImgFoursharing)
        Glide.with(context).load(item.Siximage).into(viewHolder.ImgSixsharing)
        Glide.with(context).load(item.Eightimage).into(viewHolder.ImgEightsharing)
        Glide.with(context).load(item.Tenimage).into(viewHolder.ImgTensharing)

        viewHolder.btnDelete.setOnClickListener {
            var ref = FirebaseDatabase.getInstance().getReference().child("Houses/"+item.id)
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