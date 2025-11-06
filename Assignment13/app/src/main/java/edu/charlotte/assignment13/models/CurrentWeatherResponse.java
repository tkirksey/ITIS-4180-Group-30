package edu.charlotte.assignment13.models;

public class CurrentWeatherResponse {

    public Main main;
    public Wind wind;
    public Clouds clouds;

    public Weather[] weather;

    public static class Main {
        public float temp, temp_min, temp_max;
        public int humidity;
    }

    public static class Wind {
        public float speed, deg;
    }

    public static class Clouds {
        public int all;
    }

    public static class Weather {
        public String description;
        public String icon;
    }

}
