package com.example.sirizetu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.sirizetu.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

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

        }
    }
}