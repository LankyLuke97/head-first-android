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

    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        if (savedInstanceState != null) {
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)

            if (running) {
                stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                stopwatch.start()
            } else setBaseTime()
        }

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

    override fun onResume() {
        super.onResume()
        if (running) {
            setBaseTime()
            stopwatch.start()
            offset = 0
        }
    }

    override fun onPause() {
        super.onPause()
        if (running) {
            saveOffset()
            stopwatch.stop()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(OFFSET_KEY, offset)
        outState.putBoolean(RUNNING_KEY, running)
        outState.putLong(BASE_KEY, stopwatch.base)
        super.onSaveInstanceState(outState)
    }

    fun setBaseTime() { stopwatch.base = SystemClock.elapsedRealtime() - offset }

    fun saveOffset() { offset = SystemClock.elapsedRealtime() - stopwatch.base }
}