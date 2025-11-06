package edu.charlotte.assignment13.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.charlotte.assignment13.ForecastAdapter;
import edu.charlotte.assignment13.MainActivity;
import edu.charlotte.assignment13.R;
import edu.charlotte.assignment13.databinding.FragmentWeatherForecastBinding;
import edu.charlotte.assignment13.models.DataService;
import edu.charlotte.assignment13.models.ForecastResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherForecastFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private DataService.City mCity;

    OkHttpClient client = new OkHttpClient();

    ForecastResponse forecastResponse;

    ListView listView;
    ForecastAdapter adapter;

    ArrayList<ForecastResponse.Forecast> forecasts = new ArrayList<>();

    public WeatherForecastFragment() {
        // Required empty public constructor
    }

    public static WeatherForecastFragment newInstance(DataService.City city) {
        WeatherForecastFragment fragment = new WeatherForecastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = (DataService.City) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentWeatherForecastBinding binding = FragmentWeatherForecastBinding.bind(view);

        binding.textViewForecastCity.setText(mCity.getCity());

        String forecastUrl = "";
        forecastUrl += "https://api.openweathermap.org/data/2.5/forecast?lat=" + mCity.getLatitude();
        forecastUrl += "&lon=" + mCity.getLongitude();
        forecastUrl += "&appid=" + MainActivity.API_KEY;
        forecastUrl += "&units=imperial";

        Request request = new Request.Builder()
                .url(forecastUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if(response.isSuccessful()){
                    Gson gson = new Gson();
                    forecastResponse = gson.fromJson(response.body().charStream(), ForecastResponse.class);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            forecasts.addAll(Arrays.asList(forecastResponse.list));
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

            }
        });

        adapter = new ForecastAdapter(getActivity(), forecasts);
        binding.listViewForecasts.setAdapter(adapter);

    }
}