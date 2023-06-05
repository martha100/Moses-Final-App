package com.example.residenthomes

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewAmenitiesActivity : AppCompatActivity() {
    lateinit var listAmenities: ListView
    lateinit var amenities:ArrayList<Amenity>
    lateinit var adapter: AmenitiesAdapter
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_amenities)

        listAmenities = findViewById(R.id.mListAmenities)
        amenities = ArrayList()
        adapter = AmenitiesAdapter(this, amenities)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        // Create a reference to load data from the database
        var ref = FirebaseDatabase.getInstance().getReference().child("Amenities")
        // Start showing the progress as you load data
        progressDialog.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                amenities.clear()
                for (data in snapshot.children) {
                    var amenity = data.getValue(Amenity::class.java)
                    amenities.add(amenity!!)
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@ViewAmenitiesActivity,
                    "Database server is inaccessible",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        listAmenities.adapter = adapter
    }
}