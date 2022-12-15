package com.bignerdranch.android.androidtask1

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bignerdranch.android.androidtask1.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.topAppBar!!
        drawerLayout = binding.container
        navigationView = binding.navigationView
        navigationView.setNavigationItemSelectedListener(this)

        toolbar.setOnClickListener{
            drawerLayout.open()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        fragment = Fragment()

        when (item.itemId){
            R.id.task1 -> {
            fragment = Task1Fragment()
            commitFragment()
            }
            R.id.task2 -> Toast.makeText(this, "Task 2", Toast.LENGTH_SHORT).show()
            R.id.task3 -> Toast.makeText(this, "Task 3", Toast.LENGTH_SHORT).show()
            R.id.task4 -> Toast.makeText(this, "Task 4", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun commitFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .replace(R.id.container, fragment)
            .commit()
    }

}