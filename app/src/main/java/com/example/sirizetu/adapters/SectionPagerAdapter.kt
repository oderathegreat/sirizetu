package com.example.sirizetu.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sirizetu.fragments.ChatFragment
import com.example.sirizetu.fragments.UsersFragment

class SectionPagerAdapter(fragm : FragmentManager )  : FragmentPagerAdapter(fragm){


    override fun getItem(position: Int): Fragment {

        when(position) {
            0 -> {
                return UsersFragment()
            }
            1->{
                return ChatFragment()
            }

        }
        return null!!
    }

    override fun getCount(): Int {

        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when(position) {
            0 -> return "USERS"
            1 -> return "CHATS"
        }
        return null!!

    }
}