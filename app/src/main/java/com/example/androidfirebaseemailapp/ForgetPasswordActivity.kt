package com.example.androidfirebaseemailapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailAnda: EditText
    private lateinit var btnSubmit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        auth = Firebase.auth
        emailAnda = findViewById(R.id.idEDTEmailForget)
        btnSubmit = findViewById(R.id.idButtonForgetPass)

        btnSubmit.setOnClickListener {
           if (TextUtils.isEmpty(emailAnda.text.toString()))
           {
               Toast.makeText(applicationContext,"Masukkan Email Anda Yang Terdaftar", Toast.LENGTH_SHORT).show()
           }else{
               auth.sendPasswordResetEmail(emailAnda.text.toString())
                   .addOnCompleteListener(this){ task ->
                       if (task.isSuccessful)
                       {
                           startActivity(Intent(this@ForgetPasswordActivity, LoginActivity::class.java))
                       }else{
                           Toast.makeText(applicationContext,"Authentikasi Failed",Toast.LENGTH_SHORT).show()
                       }

                   }
           }
        }
    }
}