package com.team23.socialprofile

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.team23.home.ui.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: FragmentActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        /*if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true).add(com.team23.home.R.id.fragment_home, HomeFragment())
            }
        }*/
    }
}