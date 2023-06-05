package com.example.residenthomes

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AddAmenitiesActivity : AppCompatActivity() {
    lateinit var breakfast:RadioButton
    lateinit var lunch:RadioButton
    lateinit var supper:RadioButton
    lateinit var serviceOne:EditText
    lateinit var serviceTwo:EditText
    lateinit var btnSubmit:Button
    lateinit var editbreakfast:EditText
    lateinit var editlunch:EditText
    lateinit var editsupper:EditText
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_amenities)
        breakfast = findViewById(R.id.rBtnBreak)
        lunch = findViewById(R.id.rBtnLunch)
        supper = findViewById(R.id.rBtnSupper)
        serviceOne = findViewById(R.id.mEditSer1)
        serviceTwo = findViewById(R.id.mEditSer2)
        btnSubmit = findViewById(R.id.mBtnSubmit)
        editbreakfast = findViewById(R.id.mEditBreakfast)
        editlunch = findViewById(R.id.mEditLunch)
        editsupper = findViewById(R.id.mEditSupper)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Saving")
        progressDialog.setMessage("Please wait...")


        btnSubmit.setOnClickListener {
            // Receive data from the admin
            var breakFast = breakfast.text.toString().trim()
            var Lunch = lunch.text.toString().trim()
            var Supper = supper.text.toString().trim()
            var Service = serviceOne.text.toString().trim()
            var Services = serviceTwo.text.toString().trim()
            var breakfastTime = editbreakfast.text.toString().trim()
            var lunchTime = editlunch.text.toString().trim()
            var supperTime = editsupper.text.toString().trim()

            var id = System.currentTimeMillis().toString()
            // Check if the admin is submitting empty fields
            if (breakFast.isEmpty()){
                breakfast.setError("Please fill this field")
                breakfast.requestFocus()
            }else if (Lunch.isEmpty()){
                lunch.setError("Please fill this field")
                lunch.requestFocus()
            }else if (Supper.isEmpty()){
                supper.setError("Please fill this field")
                supper.requestFocus()
            }else if (Service.isEmpty()){
                serviceOne.setError("Please fill this field")
                serviceOne.requestFocus()
            }else if (Services.isEmpty()){
                serviceTwo.setError("Please fill this field")
                serviceTwo.requestFocus()
            }else if (breakfastTime.isEmpty()){
                editbreakfast.setError("Please fill this field")
                editbreakfast.requestFocus()
            }else if (lunchTime.isEmpty()){
                editlunch.setError("Please fill this field")
                editlunch.requestFocus()
            }else if (supperTime.isEmpty()){
                editsupper.setError("Please fill this field")
                editsupper.requestFocus()
            }else{
                // Proceed to save data
                var amenity = Amenity(breakFast, Lunch, Supper, Service, Services, breakfastTime, lunchTime, supperTime, id)
                // Create a reference to FirebaseDatabase
                var ref = FirebaseDatabase.getInstance().
                getReference().child("Amenities/"+id)
                //Start saving
                progressDialog.show()
                ref.setValue(amenity).addOnCompleteListener {
                    progressDialog.dismiss()
                    if (it.isSuccessful){
                        Toast.makeText(this, "Amenities saved successfully",
                            Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Amenities saving failed",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }
}