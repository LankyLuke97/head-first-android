package com.studio.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var stopwatch: Chronometer
    var running = false
    var offset: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        findViewById<Button>(R.id.start_button).setOnClickListener {
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }

        findViewById<Button>(R.id.pause_button).setOnClickListener {
            if (running) {
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }

        findViewById<Button>(R.id.reset_button).setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    fun setBaseTime() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
}