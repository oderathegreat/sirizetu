package com.example.sirizetu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
      private lateinit var email_Input:EditText
      private lateinit var password_Input:EditText
      private lateinit var username_Input:EditText
      private lateinit var btn_CreateUser:Button
      private  var mAuth:FirebaseAuth? = null
      var uDatabase:DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Declare our variables
        email_Input = findViewById(R.id.edtEmail)
        password_Input = findViewById(R.id.edtPassword)
        btn_CreateUser = findViewById(R.id.btnCreateUser)
        username_Input = findViewById(R.id.edtUsername)

        //get our firebase Instance
        mAuth = FirebaseAuth.getInstance()

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseRef = firebaseDatabase.getReference("messages").push()


       //on click when user presses button
        btn_CreateUser.setOnClickListener {

            val email = email_Input.text.toString().trim()
            val password = password_Input.text.toString().trim()
            val _username = username_Input.text.toString().trim()

            //validate fields
            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(_username)) {

                //check password length

                if(password.length < 6 ) {
                    Toast.makeText(this, "Password should be atleast be six characters", Toast.LENGTH_SHORT).show()
                } else {
                    //call create user function
                    createUser(email,password, _username)

                }


            } else {
                Toast.makeText(this, "One of the fields is empty", Toast.LENGTH_SHORT).show()
            }

        }



    }

    private fun createUser(email: String, password: String, _username: String) {
            //proceed to create account
            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    task: Task<AuthResult> ->
                if (task.isSuccessful) {

                      //create a user object
                    var currUser = mAuth!!.currentUser
                    var user_id = currUser!!.uid

                    var userObject = HashMap<String, String>()
                    userObject.put("user_name", _username)
                    userObject.put("status","Welcome back")
                    userObject.put("image", "default photo")



                    Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()

                    //navigate user to login
                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {

                    Toast.makeText(this, "Error creating account", Toast.LENGTH_SHORT).show()
                    Log.d("Error is ==> ", task.exception.toString())
                }
            }





    }
    //saving objects in db
    //data class House(var location:String, var address:String, var price:String, var owner:String)
}