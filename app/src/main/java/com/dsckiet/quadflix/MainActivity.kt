package com.dsckiet.quadflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.gauravk.bubblenavigation.BubbleNavigationLinearView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_QuadFlix)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        val bottomNavigationView : BubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setNavigationChangeListener{view, position ->
            var pos = position
            when(pos){
                0 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.homeFragment)
                    pos = 0
                }
                1->{
                    navController.popBackStack()
                    navController.navigate(R.id.searchFragment)
                    pos = 1
                }
            }
        }
    }

}