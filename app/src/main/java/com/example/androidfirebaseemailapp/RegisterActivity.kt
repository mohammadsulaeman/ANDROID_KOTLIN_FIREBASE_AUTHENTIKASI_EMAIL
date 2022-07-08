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

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailName : EditText
    private lateinit var passwordName : TextInputEditText
    private lateinit var btnMasuk : Button
    private lateinit var btnDaftar : Button
    var email : String = ""
    var password : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        emailName = findViewById(R.id.idEdtEmailRegister)
        passwordName = findViewById(R.id.idIDTPasswordRegister)
        btnMasuk = findViewById(R.id.idButtonMasukRegister)
        btnDaftar = findViewById(R.id.idButtonDaftarRegister)

        btnMasuk.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
        btnDaftar.setOnClickListener {
            email = emailName.text.toString()
            password = passwordName.text.toString()

            if (TextUtils.isEmpty(email))
            {
                Toast.makeText(applicationContext,"Email Wajib Di isi",Toast.LENGTH_SHORT).show()
            }else if (password.length <6)
            {
                Toast.makeText(applicationContext,"Password harus di isi minimal 6 karakter",Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(emailName.text.toString(),passwordName.text.toString())
                    .addOnCompleteListener(this){task->
                        if (task.isSuccessful){
                            startActivity(Intent(applicationContext,LoginActivity::class.java))
                        }else
                        {
                            Toast.makeText(applicationContext,"Error : "+task.exception, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}