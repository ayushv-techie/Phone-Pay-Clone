package com.example.phonepayscroll

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ThirdActivity : AppCompatActivity() {

    private lateinit var pinDigits: Array<ImageView>
    private lateinit var underlines: Array<View>
    private lateinit var buttons: Array<Button>
    private var enteredPin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        val  clickme = findViewById<Button>(R.id.btn_confirm)
        clickme.setOnClickListener {
            val intent = Intent(this, ThirdsActivity::class.java)
            startActivity(intent)
            finish()
        }

        pinDigits = arrayOf(
            findViewById(R.id.pin_digit_1),
            findViewById(R.id.pin_digit_2),
            findViewById(R.id.pin_digit_3),
            findViewById(R.id.pin_digit_4),
            findViewById(R.id.pin_digit_5),
            findViewById(R.id.pin_digit_6)
        )

        underlines = arrayOf(
            findViewById(R.id.underline_1),
            findViewById(R.id.underline_2),
            findViewById(R.id.underline_3),
            findViewById(R.id.underline_4),
            findViewById(R.id.underline_5),
            findViewById(R.id.underline_6)
        )

        buttons = arrayOf(
            findViewById(R.id.btn_0),
            findViewById(R.id.btn_1),
            findViewById(R.id.btn_2),
            findViewById(R.id.btn_3),
            findViewById(R.id.btn_4),
            findViewById(R.id.btn_5),
            findViewById(R.id.btn_6),
            findViewById(R.id.btn_7),
            findViewById(R.id.btn_8),
            findViewById(R.id.btn_9)
        )

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (enteredPin.length < 6) {
                    enteredPin += index.toString()
                    updatePinDisplay()
                }
            }
        }

        findViewById<Button>(R.id.btn_delete).setOnClickListener {
            if (enteredPin.isNotEmpty()) {
                enteredPin = enteredPin.dropLast(1)
                updatePinDisplay()
            }
        }


        updatePinDisplay()

    }



    private fun updatePinDisplay() {
        pinDigits.forEachIndexed { index, imageView ->
            if (index < enteredPin.length) {
                imageView.visibility = View.VISIBLE
                underlines[index].visibility = View.INVISIBLE
            } else {
                imageView.visibility = View.INVISIBLE
                underlines[index].visibility = View.VISIBLE
                underlines[index].setBackgroundColor(
                    if (index == enteredPin.length) Color.DKGRAY else Color.LTGRAY
                )
            }
        }
    }
}
