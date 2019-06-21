package com.example.singleactivitynavigation

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("ApplicationConstant", Context.MODE_PRIVATE)

        val isFirstLogin = prefs.getBoolean("isFirstLogin", true)

        object :CountDownTimer(3000,1000){
            override fun onFinish() {
                if (isFirstLogin) {
                    val navController = findNavController(this@MainActivity, R.id.navHostFragment)
                    navController.navigate(R.id.action_splashScreenFragment_to_loginFragment)

                    val editor = prefs.edit()
                    editor.putBoolean("isFirstLogin", false)
                    editor.apply()
                }else{
                    val navController = findNavController(this@MainActivity, R.id.navHostFragment)
                    navController.navigate(R.id.action_splashScreenFragment_to_dashboardFragment)

                    val editor = prefs.edit()
                    editor.putBoolean("isFirstLogin", false)
                    editor.apply()
                }
            }

            override fun onTick(p0: Long) {
            }
        }.start()




    }
}
