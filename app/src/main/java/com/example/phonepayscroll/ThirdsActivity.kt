package com.example.phonepayscroll

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.*

class ThirdsActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_thirds)

        window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.gollottie)
        lottieAnimationView.repeatCount = 0  // Play once
        lottieAnimationView.playAnimation()

        preloadDataAndStartNextActivity()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun preloadDataAndStartNextActivity() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                // Simulate data loading
                loadData()
            }
            startNextActivity()
        }
    }

    private fun loadData() {
        // Simulate data loading, e.g., network request, database query, etc.
        Thread.sleep(2000) // Simulate a delay
    }

    private fun startNextActivity() {
        val intent = Intent(this, FourthActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}
