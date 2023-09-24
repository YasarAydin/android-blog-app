package com.example.myblogapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myblogapp.R
import com.example.myblogapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, navHost.navController)

        //NavigationUI.setupWithNavController(binding.bottomNavigationView, navHost.navController)
    }

}