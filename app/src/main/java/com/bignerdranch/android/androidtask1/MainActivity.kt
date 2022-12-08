package com.bignerdranch.android.androidtask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bignerdranch.android.androidtask1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.button2.setOnClickListener {
            mBinding.button2.visibility = View.GONE
            mBinding.textView.visibility = View.GONE
            mBinding.textView2.visibility = View.GONE
            fragment = BlankFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.container, fragment)
            fragmentTransaction.commit()
        }
    }
}