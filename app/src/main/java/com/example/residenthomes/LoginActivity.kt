package com.example.residenthomes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var editEmail:EditText
    lateinit var editPassword:EditText
    lateinit var btnLogin:Button
    lateinit var tvSkip:TextView
    lateinit var tvRegUser:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editEmail = findViewById(R.id.mEditEmail)
        editPassword = findViewById(R.id.mEditPassword)
        btnLogin = findViewById(R.id.mBtnLogin)
        tvSkip = findViewById(R.id.mTvSkip)
        tvRegUser = findViewById(R.id.mTvRegUser)

        auth = FirebaseAuth.getInstance()

        // Set OnClick listener to the button and textview
        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val result = task.result
                        if (result != null && result.signInMethods != null && result.signInMethods!!.contains(
                                EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {
                            // The email address is already registered, so prompt the user to sign in instead.
                            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    val intent = Intent(this,HomeActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            // The email address is not registered, so show an error message to the user.
                            Toast.makeText(this, "Email address is not registered.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
        tvRegUser.setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        tvSkip.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}