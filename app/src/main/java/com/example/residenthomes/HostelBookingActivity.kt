package com.example.residenthomes

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HostelBookingActivity : AppCompatActivity() {
    lateinit var listRooms: ListView
    lateinit var rooms:ArrayList<Room>
    lateinit var adapter: HostelBookingAdapter
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hostel_booking)

        listRooms = findViewById(R.id.mListRoom)
        rooms = ArrayList()
        adapter = HostelBookingAdapter(this, rooms)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        // Create a reference to load data from the database
        var ref = FirebaseDatabase.getInstance().getReference().child("Rooms")
        // Start showing the progress as you load data
        progressDialog.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                rooms.clear()
                for (data in snapshot.children) {
                    var room = data.getValue(Room::class.java)
                    rooms.add(room!!)
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@HostelBookingActivity,
                    "Database server is inaccessible",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        listRooms.adapter = adapter
    }
}