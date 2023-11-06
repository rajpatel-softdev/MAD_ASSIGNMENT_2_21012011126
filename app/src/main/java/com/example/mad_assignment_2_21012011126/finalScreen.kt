package com.example.mad_assignment_2_21012011126

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//API KEY 34131efdd780479ba306516e60484923
class finalScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)

        val searchBtn: Button = findViewById(R.id.button2)
        val searchText:EditText=findViewById(R.id.editText1)

        searchBtn.setOnClickListener{
            val search = searchText.text.toString()
            if(search==""){
                Toast.makeText(this,"Please enter city or country name!",Toast.LENGTH_LONG).show()
            }
            else{
                fetchData(search)
            }
        }
    }
    private fun fetchData(name:String){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(ApiInterface::class.java)
        val response = retrofit.getWeatherData(name,"34131efdd780479ba306516e60484923","metric")
        response.enqueue(object : Callback<WeatherDetails>
        {
            override fun onResponse(call: Call<WeatherDetails>, response: Response<WeatherDetails>)
            {
                val responseBody = response.body()
                if(response.isSuccessful && responseBody!=null)
                {
                    val temperature = responseBody.main.temp.toString()
                    val humidity = responseBody.main.humidity.toString()
                    val wind = responseBody.wind.speed.toString()
                    val feelsLike = responseBody.main.feels_like.toString()
                    val visibility = (responseBody.visibility/1000).toString()
                    val country = responseBody.sys.country
                    val description = responseBody.weather[0].description

                    val mainTemperature: TextView = findViewById(R.id.textView6)
                    mainTemperature.text= "$temperature°C"

                    val mainName:TextView = findViewById(R.id.textView5)
                    mainName.text="$name, $country"

                    val mainVisibility:TextView = findViewById(R.id.textView8)
                    mainVisibility.text="Visibility $visibility Km"

                    val mainFeelsLike:TextView = findViewById(R.id.textView9)
                    mainFeelsLike.text="Feels like $feelsLike°C"

                    val mainHumidity:TextView = findViewById(R.id.textView10)
                    mainHumidity.text="Humidity $humidity %"

                    val mainWind:TextView = findViewById(R.id.textView11)
                    mainWind.text="Wind $wind m/s"

                    val mainDescription:TextView = findViewById(R.id.textView7)
                    mainDescription.text="$description"

                    val weatherIcons = mapOf(
                        "Clear" to R.drawable.clear,
                        "Clouds" to R.drawable.cloudy,
                        "Rain" to R.drawable.rainy,
                        "Snow" to R.drawable.snowy,
                        "Thunderstorm" to R.drawable.thunderstorm,
                        "Haze" to R.drawable.winds,
                        "Drizzle" to R.drawable.drizzle
                    )
                    val weatherIcon: ImageView = findViewById(R.id.imageView4)
                    val icon = weatherIcons[responseBody.weather[0].main]
                    if (icon != null) {
                        weatherIcon.setImageResource(icon)
                    } else {
                        weatherIcon.setImageResource(R.drawable.clear)
                    }
                }
            }
            override fun onFailure(call: Call<WeatherDetails>, t: Throwable) {
                TODO("HAVEN'T IMPLEMENTED")
            }
        })
    }
}