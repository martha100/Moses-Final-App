package com.example.residenthomes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class DashBoardActivity : AppCompatActivity() {
    lateinit var admin:ImageView
    lateinit var student:ImageView
    lateinit var hostel:ImageView
    lateinit var rooms:ImageView
    lateinit var room:ImageView
    lateinit var location:ImageView
    lateinit var amenity:ImageView
    lateinit var logout:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_boardactivity)
        admin = findViewById(R.id.ImgAdmin)
        student = findViewById(R.id.ImgStudentProfile)
        hostel = findViewById(R.id.ImgHostel)
        rooms= findViewById(R.id.ImgRooms)
        room = findViewById(R.id.ImgRoom)
        location= findViewById(R.id.ImgLocation)
        amenity = findViewById(R.id.ImgAmenities)
        logout = findViewById(R.id.ImgLogout)

        admin.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
        student.setOnClickListener {
            val intent = Intent(this, StudentProfileActivity::class.java)
            startActivity(intent)
        }
        hostel.setOnClickListener {
            val intent = Intent(this, HostelActivity::class.java)
            startActivity(intent)
        }
        rooms.setOnClickListener {
            val intent = Intent(this,RoomActivity::class.java)
            startActivity(intent)
        }
        room.setOnClickListener {
            val intent = Intent(this,RoomBookingActivity::class.java)
            startActivity(intent)
        }
        location.setOnClickListener {
            val intent = Intent(this,LocationActivity::class.java)
            startActivity(intent)
        }
        amenity.setOnClickListener {
            val intent = Intent(this,AmenityActivity::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}