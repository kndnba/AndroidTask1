package com.bignerdranch.android.androidtask1

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bignerdranch.android.androidtask1.task1.Task1Fragment
import com.bignerdranch.android.androidtask1.databinding.ActivityMainBinding
import com.bignerdranch.android.androidtask1.task2.view.Task2Fragment
import com.bignerdranch.android.androidtask1.task3.view.Task3Fragment
import com.bignerdranch.android.androidtask1.task4.view.Task4Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.let { toolbar ->
            drawerLayout = binding.container
            navigationView = binding.navigationView
            navigationView.setNavigationItemSelectedListener(this)
            fragment = Task4Fragment()
            commitFragment()
            toolbar.setOnClickListener {
                drawerLayout.open()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.task1 -> {
            fragment = Task1Fragment()
            commitFragment()
            }
            R.id.task2 -> {
                fragment = Task2Fragment()
                commitFragment()
            }
            R.id.task3 -> {
                fragment = Task3Fragment()
                commitFragment()
            }
            R.id.task4 -> {
                fragment = Task4Fragment()
                commitFragment()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun commitFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}