package com.example.residenthomes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AdminActivity : AppCompatActivity() {
    lateinit var adminProfile:ImageView
    lateinit var hostelBooked:ImageView
    lateinit var hostel:ImageView
    lateinit var viewHostel:ImageView
    lateinit var room:ImageView
    lateinit var viewRoom:ImageView
    lateinit var amenities:ImageView
    lateinit var viewAmenities:ImageView
    lateinit var location:ImageView
    lateinit var viewLocation:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        adminProfile = findViewById(R.id.ImgAdminProfile)
        hostelBooked = findViewById(R.id.ImgHostelBooked)
        hostel = findViewById(R.id.ImgAddhostel)
        viewHostel = findViewById(R.id.ImgViewHostel)
        room = findViewById(R.id.ImgAddRoom)
        viewRoom = findViewById(R.id.ImgViewRoom)
        amenities = findViewById(R.id.ImgAddAmenities)
        viewAmenities = findViewById(R.id.ImgViewAmenities)
        location = findViewById(R.id.ImgAddLocation)
        viewLocation = findViewById(R.id.ImgViewLocation)

        adminProfile.setOnClickListener {
            val intent = Intent(this,AdminProfileActivity::class.java)
            startActivity(intent)
        }
        hostelBooked.setOnClickListener {
            val intent = Intent(this,HostelBookingActivity::class.java)
            startActivity(intent)
        }
        hostel.setOnClickListener {
            val intent = Intent(this,AddHostelActivity::class.java)
            startActivity(intent)
        }
        viewHostel.setOnClickListener {
            val intent = Intent(this,ViewHostelActivity::class.java)
            startActivity(intent)
        }
        room.setOnClickListener {
            val intent = Intent(this,AddRoomActivity::class.java)
            startActivity(intent)
        }
        viewRoom.setOnClickListener {
            val intent = Intent(this,ViewRoomActivity::class.java)
            startActivity(intent)
        }
        amenities.setOnClickListener {
            val intent = Intent(this,AddAmenitiesActivity::class.java)
            startActivity(intent)
        }
        viewAmenities.setOnClickListener {
            val intent = Intent(this,ViewAmenitiesActivity::class.java)
            startActivity(intent)
        }
        location.setOnClickListener {
            val intent = Intent(this,AddLocationActivity::class.java)
            startActivity(intent)
        }
        viewLocation.setOnClickListener {
            val intent = Intent(this,ViewLocationActivity::class.java)
            startActivity(intent)
        }
    }
}