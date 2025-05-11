package com.studio.beeradvisor

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.find_beer).setOnClickListener {
            val beers = getBeers(findViewById<Spinner>(R.id.beer_colour).selectedItem.toString())
            findViewById<TextView>(R.id.brands).text = beers.reduce{str, item -> str + '\n' + item}
        }
    }

    private fun getBeers(colour: String): List<String> {
        return when (colour) {
            "Light" -> listOf("Jail Pale Ale", "Lager Lite")
            "Amber" -> listOf("Jack Amber", "Red Moose")
            "Brown" -> listOf("Brown Bear Beer", "Bock Brownie")
            "Dark" -> listOf("Gout Stout", "Dark Daniel")
            else -> listOf("Unknown beer colour $colour selected; please contact your system administrator.")
        }
    }
}