package com.example.residenthomes

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class RoomBookingActivity : AppCompatActivity() {
    lateinit var HostelName:EditText
    lateinit var StudentName:EditText
    lateinit var PhoneNumber:EditText
    lateinit var EntryDate:EditText
    lateinit var Room:AutoCompleteTextView
    lateinit var btnBook:Button
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_booking)

        HostelName = findViewById(R.id.mEditHostelName)
        StudentName = findViewById(R.id.mEditStudentName)
        PhoneNumber = findViewById(R.id.mEditPhoneNumber)
        Room = findViewById(R.id.auto_complete)
        EntryDate = findViewById(R.id.mEditEntryDate)
        btnBook = findViewById(R.id.mBtnBook)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")


        // Set OnClickListener to Book Now Button
        btnBook.setOnClickListener {
            val hostelname = HostelName.text.toString().trim()
            val studentname = StudentName.text.toString().trim()
            val phonenumber = PhoneNumber.text.toString().trim()
            val room = Room.text.toString().trim()
            val entrydate = EntryDate.text.toString().trim()

            var id = System.currentTimeMillis().toString()
            // Check if the admin is submitting empty fields
            if (hostelname.isEmpty()){
                HostelName.setError("Please fill this field")
                HostelName.requestFocus()
            }else if (studentname.isEmpty()){
                StudentName.setError("Please fill this field")
                StudentName.requestFocus()
            }else if (phonenumber.isEmpty()){
                PhoneNumber.setError("Please fill this field")
                PhoneNumber.requestFocus()
            }else if (room.isEmpty()){
                Room.setError("Please fill this field")
                Room.requestFocus()
            }else if (entrydate.isEmpty()){
                EntryDate.setError("Please fill this field")
                EntryDate.requestFocus()
            }else{
                // Proceed to save data
                var room = Room(hostelname, studentname, phonenumber, room, entrydate, id)
                // Create a reference to FirebaseDatabase
                var ref = FirebaseDatabase.getInstance().
                getReference().child("Rooms/"+id)
                //Start saving
                progressDialog.show()
                ref.setValue(room).addOnCompleteListener {
                    progressDialog.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this, "Hostel booked successfully",
                            Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Hostel booking failed",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        val rooms = listOf("1","2","3","4","6","8","10")
        val autocomplete : AutoCompleteTextView = findViewById(R.id.auto_complete)
        val adapter = ArrayAdapter(this, R.layout.rooms_item,rooms)

        autocomplete.setAdapter(adapter)

        autocomplete.onItemClickListener = AdapterView.OnItemClickListener {
                adaperView, view, i, l ->

            val itemSelected = adaperView.getItemAtPosition(i)
            Toast.makeText(this, "$itemSelected:Sharing",Toast.LENGTH_SHORT).show()
        }
    }
}