package com.example.sirizetu.activities


import android.R.menu
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.sirizetu.R
import com.example.sirizetu.adapters.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout


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
        inflater.inflate(com.example.sirizetu.R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
        return true
    }


}