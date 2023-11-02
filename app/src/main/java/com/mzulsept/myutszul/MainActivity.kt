package com.mzulsept.myutszul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mzulsept.myutszul.fragment.MahasiswaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView  // Mengganti import ini
import com.mzulsept.myutszul.fragment.TekomFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mahasiswaFragment = MahasiswaFragment()
        val tekomFragment = TekomFragment()

        makeCurrentFragment(mahasiswaFragment)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)  // Mengganti ini
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuMahasiswa -> makeCurrentFragment(mahasiswaFragment)
                R.id.menuTekom -> makeCurrentFragment(tekomFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment, fragment)
            commit()
        }
    }
}