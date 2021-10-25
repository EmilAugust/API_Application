package com.example.api_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class HoroskopActivity : AppCompatActivity() {

    var requestQueue : RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoskop)

        //Defines all of the TextViews
        val compability : TextView = findViewById(R.id.compability)
        val number : TextView = findViewById(R.id.number)
        val time : TextView = findViewById(R.id.time)
        val color : TextView = findViewById(R.id.color)
        val currentDate : TextView = findViewById(R.id.currentDate)
        val dateRange : TextView = findViewById(R.id.dateRange)
        val mood : TextView = findViewById(R.id.mood)
        val description : TextView = findViewById(R.id.description)

        //Makes a list out of all of the variables with the TextViews
        val textViewList = listOf(
            compability,
            number,
            time,
            color,
            currentDate,
            dateRange,
            mood,
            description)

        //Gets intent
        val getURL = intent.getStringExtra("url")
        val url = getURL.toString()

        //Calls the horoscope function
        APICall(textViewList, url)

    }
    private fun APICall(views: List<TextView>, url: String) {

        requestQueue = Volley.newRequestQueue(this)

        //Makes a request
        val request = StringRequest(
            Request.Method.POST, url,
            { response ->
                //Outputs the content
                val listOfContent = formatResponse(response)
                for (i in views) {
                    val index = views.indexOf(i)
                    i.text = listOfContent[index]

                }
            },
            { error ->
                //Handles errors
                views[0].text = "Kunne ikke laste horoskop!"
            }
        )
        //Gives the request a tag
        request.tag = "horoskop"

        //Adds request to queue
        requestQueue?.add(request)

    }

    override fun onStop() {
        super.onStop()
        requestQueue?.cancelAll("horoskop")
    }

    //Formats response
    private fun formatResponse(resp : String) : List<String> {
        try {
            val list = resp.split("\"")

            //Returns a list of where it splits to get the content
            return listOf(
                list[3],
                list[7],
                list[11],
                list[15],
                list[19],
                list[23],
                list[27],
                list[31])
        } catch (expection: ArrayIndexOutOfBoundsException) {
            return listOf("Kunne ikke hente horoskop!")
        }
    }
}