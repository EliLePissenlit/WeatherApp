package com.test.wheatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(private val items: List<ForecastDay>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast_day, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = items[position]
        val context = holder.itemView.context

        // Date formatée
        val date = Date(item.dt * 1000)
        val sdf = SimpleDateFormat("dd MMM", Locale.FRENCH)
        holder.textDate.text = sdf.format(date)

        // Température
        holder.textTemp.text = "${item.temp.day.toInt()}°C"

        // Icône météo
        val icon = item.weather.firstOrNull()?.icon ?: "01d"
        val iconUrl = "https://openweathermap.org/img/wn/${icon}@2x.png"
        Glide.with(context).load(iconUrl).into(holder.imgWeather)
    }

    override fun getItemCount(): Int = items.size

    class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textDate: TextView = view.findViewById(R.id.text_date)
        val imgWeather: ImageView = view.findViewById(R.id.img_weather)
        val textTemp: TextView = view.findViewById(R.id.text_temp)
    }
} 