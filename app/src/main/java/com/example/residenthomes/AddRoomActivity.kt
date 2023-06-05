package com.example.residenthomes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddRoomActivity : AppCompatActivity() {
    lateinit var deposit:ImageView
    lateinit var oneSharing:ImageView
    lateinit var twoSharing:ImageView
    lateinit var threeSharing:ImageView
    lateinit var fourSharing:ImageView
    lateinit var sixSharing:ImageView
    lateinit var eightSharing:ImageView
    lateinit var tenSharing:ImageView
    lateinit var btnupload:Button
    lateinit var imageReference: FirebaseStorage
    private var currentFile: Uri? = null
    lateinit var editDeposit:EditText
    lateinit var editOnesharing:EditText
    lateinit var editTwosharing:EditText
    lateinit var editThreesharing:EditText
    lateinit var editFoursharing:EditText
    lateinit var editSixsharing:EditText
    lateinit var editEightSharing:EditText
    lateinit var editTensharing:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)
        deposit = findViewById(R.id.ImgDeposit)
        oneSharing = findViewById(R.id.ImgOneSharing)
        twoSharing = findViewById(R.id.ImgTwoSharing)
        threeSharing = findViewById(R.id.ImgThreeSharing)
        fourSharing = findViewById(R.id.ImgFourSharing)
        sixSharing = findViewById(R.id.ImgSixSharing)
        eightSharing = findViewById(R.id.ImgEightSharing)
        tenSharing = findViewById(R.id.ImgTenSharing)
        btnupload = findViewById(R.id.BtnUpload)
        editDeposit = findViewById(R.id.mEditDeposit)
        editOnesharing = findViewById(R.id.mEditOneSharing)
        editTwosharing = findViewById(R.id.mEditTwoSharing)
        editThreesharing = findViewById(R.id.mEditThreeSharing)
        editFoursharing = findViewById(R.id.mEditFourSharing)
        editSixsharing = findViewById(R.id.mEditSixSharing)
        editEightSharing = findViewById(R.id.mEditEightSharing)
        editTensharing = findViewById(R.id.mEditTenSharing)
        imageReference = FirebaseStorage.getInstance()

        deposit.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type ="image/*"
                imageLauncher.launch(it)
                Toast.makeText(this,"Select a hostel", Toast.LENGTH_SHORT).show()
            }
        }
        oneSharing.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type ="image/*"
                imageLauncher.launch(it)
                Toast.makeText(this,"Select one sharing room",Toast.LENGTH_SHORT).show()
            }
        }
        twoSharing.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type ="image/*"
                imageLauncher.launch(it)
                Toast.makeText(this,"Select two sharing room",Toast.LENGTH_SHORT).show()
            }
        }
        threeSharing.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type ="image/*"
                imageLauncher.launch(it)
                Toast.makeText(this,"Select three sharing room",Toast.LENGTH_SHORT).show()
            }
        }
        fourSharing.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type ="image/*"
                imageLauncher.launch(it)
                Toast.makeText(this,"Select four sharing room",Toast.LENGTH_SHORT).show()
            }
        }
        sixSharing.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type ="image/*"
                imageLauncher.launch(it)
                Toast.makeText(this,"Select six sharing room",Toast.LENGTH_SHORT).show()
            }
        }
        eightSharing.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type ="image/*"
                imageLauncher.launch(it)
                Toast.makeText(this,"Select eight sharing room",Toast.LENGTH_SHORT).show()
            }
        }
        tenSharing.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type ="image/*"
                imageLauncher.launch(it)
                Toast.makeText(this,"Select ten sharing room",Toast.LENGTH_SHORT).show()
            }
        }
        btnupload.setOnClickListener {
            val filename = generateFilename()
            uploadImageToStorage(filename)
        }

    }
    private val imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
            result?.data?.data?.let {
                currentFile = it
                deposit.setImageURI(it)
                oneSharing.setImageURI(it)
                twoSharing.setImageURI(it)
                threeSharing.setImageURI(it)
                fourSharing.setImageURI(it)
                sixSharing.setImageURI(it)
                eightSharing.setImageURI(it)
                tenSharing.setImageURI(it)
            }
        }else{
            Toast.makeText(this, "Canceled",Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateFilename(): String {
        val currentTime = Calendar.getInstance().timeInMillis
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    private fun uploadImageToStorage(filename:String){
        var depositrent = editDeposit.text.toString().trim()
        var onesharing = editOnesharing.text.toString().trim()
        var twosharing = editTwosharing.text.toString().trim()
        var threesharing = editThreesharing.text.toString().trim()
        var foursharing = editFoursharing.text.toString().trim()
        var sixsharing = editSixsharing.text.toString().trim()
        var eightsharing = editEightSharing.text.toString().trim()
        var tensharing = editTensharing.text.toString().trim()

        if (depositrent.isEmpty()){
            editDeposit.setError("Please enter deposit amount")
            editDeposit.requestFocus()
        }
        if (onesharing.isEmpty()){
            editOnesharing.setError("Please enter rent for one sharing")
            editOnesharing.requestFocus()
        }
        if (twosharing.isEmpty()){
            editTwosharing.setError("Please enter rent for two sharing")
            editTwosharing.requestFocus()
        }
        if (threesharing.isEmpty()){
            editThreesharing.setError("Please enter rent for three sharing")
            editThreesharing.requestFocus()
        }
        if (foursharing.isEmpty()){
            editFoursharing.setError("Please enter rent for four sharing")
            editFoursharing.requestFocus()
        }
        if (sixsharing.isEmpty()){
            editSixsharing.setError("Please enter rent for six sharing")
            editSixsharing.requestFocus()
        }
        if (eightsharing.isEmpty()){
            editEightSharing.setError("Please enter rent for eight sharing")
            editEightSharing.requestFocus()
        }
        if (tensharing.isEmpty()){
            editTensharing.setError("Please enter rent for ten sharing")
            editTensharing.requestFocus()
        }else{
            try {
                currentFile?.let {
                    imageReference.getReference().child("houses/$filename").putFile(it).
                    addOnCompleteListener {
                        var url = it.result.storage.downloadUrl.addOnSuccessListener {
                            Toast.makeText(this, "Upload success!!", Toast.LENGTH_SHORT).show()
                            var id = System.currentTimeMillis().toString()
                            var roomData = House(it.toString(),it.toString(),it.toString(),it.toString(),it.toString(),
                                it.toString(),it.toString(),it.toString(),depositrent,onesharing,twosharing,
                                threesharing,foursharing,sixsharing,eightsharing,tensharing,id)
                            var ref = FirebaseDatabase.getInstance().getReference().child("Houses/$id")
                            ref.setValue(roomData)
                        }
                    }.addOnFailureListener{
                        Toast.makeText(this, "Upload failed",Toast.LENGTH_SHORT).show()
                    }
                }
            }catch (e:Exception){
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }
}