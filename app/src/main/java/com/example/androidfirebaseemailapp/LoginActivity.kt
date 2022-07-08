package com.example.androidfirebaseemailapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailName : EditText
    private lateinit var passwordName : TextInputEditText
    private lateinit var btnMasuk : Button
    private lateinit var btnDaftar : Button
    private lateinit var lupaPass : TextView
    var email : String = ""
    var password : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        emailName = findViewById(R.id.idEdtEmailLogin);
        passwordName = findViewById(R.id.idIDTPasswordLogin)
        btnMasuk = findViewById(R.id.idButtonMasukLogin)
        btnDaftar = findViewById(R.id.idButtonDaftarLogin)
        lupaPass = findViewById(R.id.idTextLupaPassword)
        auth = Firebase.auth

        btnMasuk.setOnClickListener {
            email = emailName.text.toString()
            password = passwordName.text.toString()
            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"Email harus di isi",Toast.LENGTH_SHORT).show()
            }else if (password.length <6)
            {
                Toast.makeText(applicationContext,"Password harus di isi minimal 6 karakter",Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(emailName.text.toString(), passwordName.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        } else {
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        btnDaftar.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }

        lupaPass.setOnClickListener {
            startActivity(Intent(applicationContext,ForgetPasswordActivity::class.java))
        }
    }
}