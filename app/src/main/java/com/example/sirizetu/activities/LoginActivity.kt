package com.example.sirizetu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sirizetu.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class LoginActivity : AppCompatActivity() {

    private lateinit var email_inp:EditText
    private lateinit var pass_inp:EditText
    private lateinit var _btnlogin:Button

    private  var mAuth: FirebaseAuth? = null
    var uDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email_inp = findViewById(R.id.edtemail)
        pass_inp = findViewById(R.id.edt_password)
        _btnlogin = findViewById(R.id.btnLoginuser)
        mAuth = FirebaseAuth.getInstance()

        _btnlogin.setOnClickListener {

            var user_email = email_inp.text.toString().trim()
            var user_password = pass_inp.text.toString().trim()

            _loginUser(user_email, user_password)
        }

    }

    private fun _loginUser(user_email:String, user_password:String) {
          mAuth!!.signInWithEmailAndPassword(user_email,user_password).addOnCompleteListener {
              task: Task<AuthResult> ->
              if (task.isSuccessful) {
                  //get the username from splitting users' email
                  var username = user_email.split("@")[0]
                  var dash_intent = Intent(this, DashboardActivity::class.java)
                  dash_intent.putExtra("username", username)
                  Log.d("Username ==> ", username.toString())
                  startActivity(dash_intent)
                  finish()

              } else {

             Toast.makeText(this, "Failed to login please try again", Toast.LENGTH_SHORT).show()
                  Log.d("Error ==> ", task.exception.toString())

              }
          }
    }
}