package com.example.phonepayscroll

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private var videoPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.mainnot)

        videoView = findViewById(R.id.videoView)
        val videoPath = "android.resource://" + packageName + "/" + R.raw.insh
        val uri: Uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        // Set the OnCompletionListener to loop the video
        videoView.setOnCompletionListener {
            videoView.start() // Restart the video when it finishes
        }

        videoView.start()

        val clickImg: ImageView = findViewById(R.id.clickimgg)
        clickImg.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
        // Save the current video position
        videoPosition = videoView.currentPosition
        videoView.pause()
    }

    override fun onResume() {
        super.onResume()
        // Restore the video position and start playback
        videoView.seekTo(videoPosition)
        videoView.start()
    }
}
