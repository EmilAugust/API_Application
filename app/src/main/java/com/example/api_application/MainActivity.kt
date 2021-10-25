package com.example.api_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Spinner for signs
        val sign_spinner: Spinner = findViewById(R.id.signSpinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.signs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            sign_spinner.adapter = adapter
        }

        //Spinner for days
        val day_spinner: Spinner = findViewById(R.id.daySpinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.days,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            day_spinner.adapter = adapter
        }

        //Button for starting new activity
        val start : Button = findViewById(R.id.startButton)

        start.setOnClickListener {
            val selectedSign = sign_spinner.selectedItem.toString()
            val selectedDay = day_spinner.selectedItem.toString()
            val url = createURL(selectedSign, selectedDay)

            val intent = Intent(this@MainActivity,HoroskopActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        }

    }

    //Creates URL
    private fun createURL(sign : String, date : String) : String {
        return "https://aztro.sameerkumar.website/?sign=$sign&day=$date"
    }
}