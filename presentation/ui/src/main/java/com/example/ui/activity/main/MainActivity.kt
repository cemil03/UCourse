package com.example.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUiSaveStateControl
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.ui.R
import com.example.ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private val navController: NavController
        get() = (supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).navController


    @OptIn(NavigationUiSaveStateControl::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController, false)
    }
}
