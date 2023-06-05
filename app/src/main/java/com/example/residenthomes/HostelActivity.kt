package com.example.residenthomes

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class HostelActivity : AppCompatActivity() {
//    lateinit var hostelImageView:ImageView
    lateinit var search: SearchView
    lateinit var listHostels: ListView
    lateinit var hostels:ArrayList<Hostel>
    lateinit var adapter: HostelAdapter
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hostel)
        search = findViewById(R.id.Searchview)
//        hostelImageView = findViewById(R.id.imageView)
//        var receivedImage = intent.getStringExtra("image")
//        Glide.with(this).load(receivedImage).into(hostelImageView)

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference

        listHostels = findViewById(R.id.mListHostel)
        hostels = ArrayList()
        adapter = HostelAdapter(this,hostels)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        // Create a reference to load data from the database
        var ref = FirebaseDatabase.getInstance().getReference().
        child("Hostels")
        // Start showing the progress as you load data
        progressDialog.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                hostels.clear()
                for (data in snapshot.children){
                    val hostel = data.getValue(Hostel::class.java)
                    hostels.add(hostel!!)
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HostelActivity,
                    "Database server is inaccessible",
                    Toast.LENGTH_LONG).show()
            }
        })
        listHostels.adapter = adapter
    }
}