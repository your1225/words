package com.yourstar.words

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        Navigation.findNavController(findViewById(R.id.fragment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//
//        navController.navigateUp()
//    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()

        return super.onSupportNavigateUp()
    }

}
