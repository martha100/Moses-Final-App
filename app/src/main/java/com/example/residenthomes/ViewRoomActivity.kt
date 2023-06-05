package com.example.residenthomes

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ViewRoomActivity : AppCompatActivity() {
    lateinit var listRoom: ListView
    lateinit var houses:ArrayList<House>
    lateinit var adapter: RoomsAdapter
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_room)

        // Initialize Firebase Storage
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference

        listRoom = findViewById(R.id.mListRooms)
        houses = ArrayList()
        adapter = RoomsAdapter(this,houses)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        // Create a reference to load data from the database
        var ref = FirebaseDatabase.getInstance().getReference().
        child("Houses")
        // Start showing the progress as you load data
        progressDialog.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                houses.clear()
                for (data in snapshot.children){
                    var houseRoom = data.getValue(House::class.java)
                    houses.add(houseRoom!!)
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewRoomActivity,
                    "Database server is inaccessible",
                    Toast.LENGTH_LONG).show()
            }
        })
        listRoom.adapter = adapter
    }
}