package com.msd117.cryptocompose.presentation.splash.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.msd117.cryptocompose.presentation.main.ui.MainActivity
import com.msd117.cryptocompose.theme.setUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUi {
            //SplashView()
        }

        CoroutineScope(Job()).launch {
            withContext(Dispatchers.IO) {
                delay(3000)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}