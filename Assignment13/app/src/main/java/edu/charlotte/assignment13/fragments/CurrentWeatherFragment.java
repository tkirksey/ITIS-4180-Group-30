package edu.charlotte.assignment13.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import edu.charlotte.assignment13.MainActivity;
import edu.charlotte.assignment13.databinding.FragmentCurrentWeatherBinding;
import edu.charlotte.assignment13.models.CurrentWeatherResponse;
import edu.charlotte.assignment13.models.DataService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrentWeatherFragment extends Fragment {
    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private DataService.City mCity;
    FragmentCurrentWeatherBinding binding;

    CurrentWeatherResponse weatherResponse;

    private final OkHttpClient client = new OkHttpClient();

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    public static CurrentWeatherFragment newInstance(DataService.City city) {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = (DataService.City) getArguments().getSerializable(ARG_PARAM_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Current Weather");

        binding.textViewCity.setText(mCity.getCity());

        String currentWeatherUrl = "";
        currentWeatherUrl += "https://api.openweathermap.org/data/2.5/weather?lat=" + mCity.getLatitude();
        currentWeatherUrl += "&lon=" + mCity.getLongitude();
        currentWeatherUrl += "&appid=" + MainActivity.API_KEY;
        currentWeatherUrl += "&units=imperial";

        Request request = new Request.Builder()
                .url(currentWeatherUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    weatherResponse = gson.fromJson(response.body().charStream(), CurrentWeatherResponse.class);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.textViewTemp.setText(String.valueOf(weatherResponse.main.temp) + " F");
                            binding.textViewTempMax.setText(String.valueOf(weatherResponse.main.temp_max) + " F");
                            binding.textViewTempMin.setText(String.valueOf(weatherResponse.main.temp_min) + " F");
                            binding.textViewHumidity.setText(String.valueOf(weatherResponse.main.humidity) + "%");
                            binding.textViewDesc.setText(weatherResponse.weather[0].description);
                            binding.textViewCloudy.setText(String.valueOf(weatherResponse.clouds.all) + "%");
                            binding.textViewWindSpeed.setText(String.valueOf(weatherResponse.wind.speed) + " miles/hour");
                            binding.textViewWindDegree.setText(String.valueOf(weatherResponse.wind.deg) + " degrees");

                            Picasso.get()
                                    .load("https://openweathermap.org/img/wn/" + weatherResponse.weather[0].icon + ".png")
                                    .into(binding.imageViewIcon);
                        }
                    });

                }
            }
        });

        binding.buttonCheckForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoForecast(mCity);
            }
        });

    }

    CurrentWeatherListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CurrentWeatherListener) context;
    }

    public interface CurrentWeatherListener {
        void gotoForecast(DataService.City city);
    }

}