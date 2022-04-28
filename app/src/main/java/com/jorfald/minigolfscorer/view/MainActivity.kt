package com.jorfald.minigolfscorer.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jorfald.minigolfscorer.R


class MainActivity : AppCompatActivity() {
    var backPressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount

        if (backStackEntryCount != null && backStackEntryCount > 0) {
            super.onBackPressed()
        } else {
            val backMessage =
                Toast.makeText(this, "Trykk tilbake igjen for å lukke appen", Toast.LENGTH_LONG)

            if (backPressTime + 2000 > System.currentTimeMillis()) {
                backMessage.cancel()
                super.onBackPressed()
                return
            } else {
                backMessage.show()
            }

            backPressTime = System.currentTimeMillis()
        }
    }
}