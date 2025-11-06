package edu.charlotte.assignment13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.charlotte.assignment13.models.ForecastResponse;

public class ForecastAdapter extends ArrayAdapter<ForecastResponse.Forecast> {

    ArrayList<ForecastResponse.Forecast> mForecasts;

    public ForecastAdapter(@NonNull Context context, @NonNull List<ForecastResponse.Forecast> objects) {
        super(context, 0, objects);
        mForecasts = (ArrayList<ForecastResponse.Forecast>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_forecast, parent, false);
        }

        ForecastResponse.Forecast forecast = mForecasts.get(position);

        ForecastViewHolder holder = new ForecastViewHolder(convertView);

        // TODO: use ForecastResponse class to fill in fields;

        holder.temp.setText(forecast.main.temp + " F");
        holder.maxTemp.setText("Max: " + forecast.main.temp_max + " F");
        holder.minTemp.setText("Min: " + forecast.main.temp_min + " F");
        holder.humidity.setText(forecast.main.humidity + " %");
        holder.description.setText(forecast.weather[0].description);
        holder.datetime.setText(forecast.dt_txt);

        Picasso.get()
                .load("https://openweathermap.org/img/wn/" + forecast.weather[0].icon + ".png")
                .into(holder.icon);

        return convertView;
    }

    public static class ForecastViewHolder {

        TextView temp, maxTemp, minTemp, datetime, humidity, description;
        ImageView icon;

        public ForecastViewHolder(View view){

            this.temp = view.findViewById(R.id.textViewForecastTemp);
            this.maxTemp = view.findViewById(R.id.textViewForecastMaxTemp);
            this.minTemp = view.findViewById(R.id.textViewForecastMinTemp);
            this.humidity = view.findViewById(R.id.textViewForecastHumidity);
            this.datetime = view.findViewById(R.id.textViewForecastDatetime);
            this.description = view.findViewById(R.id.textViewForecastDesc);

            this.icon = view.findViewById(R.id.imageViewForecastIcon);

        }

    }

}
