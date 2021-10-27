package com.example.api_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Spinner for signs
        val signSpinner: Spinner = findViewById(R.id.signSpinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.signs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            signSpinner.adapter = adapter
        }

        //Spinner for days
        val daySpinner: Spinner = findViewById(R.id.daySpinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.days,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            daySpinner.adapter = adapter
        }

        //Button for starting new activity
        val start : Button = findViewById(R.id.startButton)

        //onClick event for getting selected items from the spinners and starting a new activity
        start.setOnClickListener {
            val selectedSign : String = signSpinner.selectedItem.toString()
            val selectedDay : String = daySpinner.selectedItem.toString()
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