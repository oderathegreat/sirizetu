package com.example.sirizetu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sirizetu.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserStatusActivity : AppCompatActivity() {

    private lateinit var statusEdt:EditText
    private lateinit var statusBtn:Button
    var mDatabase: DatabaseReference? = null
    var mCurrentuser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_status)

        //get our components
        statusEdt = findViewById(R.id.edtUpdateStatus)
        statusBtn = findViewById(R.id.btnStatusUpdate)


        supportActionBar!!.title = "Status"

        if (intent.extras != null) {
            var oldStatus = intent!!.extras!!.get("status")
            statusEdt.setText(oldStatus.toString())
        }

        if (intent.extras!!.equals(null)) {
            statusEdt.setText("Enter your new status")
        }

        statusBtn.setOnClickListener {


             //Toast.makeText(this, "Status", Toast.LENGTH_SHORT).show()
            //get current user
            mCurrentuser = FirebaseAuth.getInstance().currentUser
            var user_Id = mCurrentuser!!.uid
            //Do to the database after getting the uid
           //Toast.makeText(this, "User id is $user_Id", Toast.LENGTH_SHORT).show()
            mDatabase = FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(user_Id)

            var _status = statusEdt.text.toString().trim()
           mDatabase!!.child("status")
               .setValue(_status).addOnCompleteListener {
                   task: Task<Void> ->
                   if (task.isSuccessful) {

                       Toast.makeText(this, "Status Update Successfully", Toast.LENGTH_SHORT).show()
                       //Once done navigate back to settings page
                       var i = Intent(this, SettingsActivity::class.java)
                       startActivity(i)
                       finish()

                   } else {
                       Toast.makeText(this, "Failed to update status", Toast.LENGTH_SHORT).show()
                   }
               }





        }
    }
}