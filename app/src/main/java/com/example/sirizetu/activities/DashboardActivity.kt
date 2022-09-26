package com.example.sirizetu.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.sirizetu.R
import com.example.sirizetu.adapters.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth


class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var sectionadapter : SectionPagerAdapter? = null
        var dashviewPager:ViewPager? = null
        var main_tabs: TabLayout? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        dashviewPager = findViewById(R.id.dash_viewPager_id)
        main_tabs = findViewById(R.id.main_Tabs)

        //set tab colors
        main_tabs.setTabTextColors(Color.WHITE, Color.GREEN)

        supportActionBar!!.title = "Dashboard"

        sectionadapter = SectionPagerAdapter(supportFragmentManager)
        dashviewPager.adapter = sectionadapter
        main_tabs.setupWithViewPager(dashviewPager)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId) {
           R.id.settings_id -> {
               //Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
               //Take User to settings page

             if (item != null) {
                 var intent = Intent(this, SettingsActivity::class.java)
                 startActivity(intent)
             }
               else {
                   Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
             }

           }
           R.id.logout_id -> {
               FirebaseAuth.getInstance().signOut()
               //Quick fix Sign Out A user from the app
               var i = Intent(this, MainActivity::class.java)
               startActivity(i)
               finish()

           }
       }

        return true

    }


}