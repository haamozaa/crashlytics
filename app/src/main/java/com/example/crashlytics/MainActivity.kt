package com.example.crashlytics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crashlytics.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.SignIn.setOnClickListener {
            signInUser( binding.Email.text.toString(), binding.Pass.text.toString())
        }
        binding.SignUp.setOnClickListener {
            startActivity(Intent(applicationContext,SignUp::class.java))
        }
    }
    fun signInUser(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {task->
                if (task.isSuccessful){

                    val user = auth.currentUser
                    Toast.makeText(applicationContext,"${user?.email} >> signIn success", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext,"signIn fail", Toast.LENGTH_SHORT).show()
                }
            }
    }
}