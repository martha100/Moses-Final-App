package com.example.residenthomes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var editname:EditText
    lateinit var editemail: EditText
    lateinit var editpassword: EditText
    lateinit var editconfirmpassword: EditText
    lateinit var btnRegister:Button
    lateinit var tvLoginUser: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        editname = findViewById(R.id.mEditName)
        editemail = findViewById(R.id.mEditEmail)
        editpassword = findViewById(R.id.mEditPassword)
        editconfirmpassword = findViewById(R.id.mEditCfmPassword)
        btnRegister = findViewById(R.id.mBtnRegister)
        tvLoginUser = findViewById(R.id.mTvLoginUser)

        auth = FirebaseAuth.getInstance()

        // Set OnClick listener to the button and textview
        btnRegister.setOnClickListener {
            val username = editname.text.toString().trim()
            val email = editemail.text.toString().trim()
            val password = editpassword.text.toString().trim()
            val confirmpass = editconfirmpassword.text.toString().trim()

            if (username.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || confirmpass.isNotEmpty()){
                if (password == confirmpass){

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(),
                            Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Please check your passsword again",
                    Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please fill all the fields",
                Toast.LENGTH_SHORT).show()
            }
        }
        tvLoginUser.setOnClickListener {
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}