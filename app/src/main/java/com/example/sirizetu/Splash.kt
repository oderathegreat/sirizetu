package com.example.sirizetu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

class Splash : AppCompatActivity() {

    private lateinit var _btnLogin:Button
    private lateinit var _btnSignup:Button

    private  var mAuth: FirebaseAuth? = null
    var uDatabase: DatabaseReference? = null
    var user:FirebaseUser? = null
    var authlistener:FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //initialise firebase properties
        mAuth = FirebaseAuth.getInstance()
        authlistener = FirebaseAuth.AuthStateListener {
            firebaseAuth: FirebaseAuth ->

            user = firebaseAuth.currentUser

            //check if user is signed in
            if (user !=null) {
                   //proceed to dashboard
               startActivity(Intent(this, DashboardActivity::class.java))
               finish()

            } else {
       Toast.makeText(this, "User not signed in", Toast.LENGTH_SHORT).show()
            }
        }

        _btnLogin = findViewById(R.id.btn_goLogin)
        _btnSignup = findViewById(R.id.btn_goRegister)

        _btnLogin.setOnClickListener {

        }

        _btnSignup.setOnClickListener {

        }

        //


    }

    override fun onStart() {
        super.onStart()
        authlistener?.let { mAuth!!.addAuthStateListener(it) }
    }

    override fun onStop() {
        super.onStop()

        if(authlistener != null) {
            mAuth!!.removeAuthStateListener(authlistener!!)
        }
    }
}
