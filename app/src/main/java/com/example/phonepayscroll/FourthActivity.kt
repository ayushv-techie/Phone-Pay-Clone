package com.example.phonepayscroll

import android.animation.Animator
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ScrollView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

class FourthActivity : AppCompatActivity() {

    private lateinit var videoView4: VideoView
    private var videoPosition4: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fourth)

        window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar4)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        val scrollView = findViewById<ScrollView>(R.id.scrollView)
        scrollView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Scroll to the top
                scrollView.scrollTo(0, 0)
                // Remove the listener to avoid multiple calls
                scrollView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.lottieone)
        lottieAnimationView.repeatCount = 0  // Play lottieone once
        lottieAnimationView.playAnimation()

        lottieAnimationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                lottieAnimationView.setAnimation(R.raw.lottietwo)
                lottieAnimationView.repeatCount = LottieDrawable.INFINITE  // Loop lottietwo
                lottieAnimationView.playAnimation()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })

        val clickm4 = findViewById<Button>(R.id.button4)
        clickm4.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        videoView4 = findViewById(R.id.videoView4)
        val videoPath4 = "android.resource://" + packageName + "/" + R.raw.babitav
        val uri4: Uri = Uri.parse(videoPath4)
        videoView4.setVideoURI(uri4)

        videoView4.setOnCompletionListener {
            videoView4.start()  // Restart the video when it finishes
        }

        videoView4.start()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
        videoPosition4 = videoView4.currentPosition
        videoView4.pause()
    }

    override fun onResume() {
        super.onResume()
        videoView4.seekTo(videoPosition4)
        videoView4.start()
    }

    private fun calculateTotalDuration(animationDrawable: AnimationDrawable): Long {
        var totalDuration = 0L
        for (i in 0 until animationDrawable.numberOfFrames) {
            totalDuration += animationDrawable.getDuration(i)
        }
        return totalDuration
    }
}
