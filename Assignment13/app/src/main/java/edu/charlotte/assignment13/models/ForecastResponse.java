package edu.charlotte.assignment13.models;

public class ForecastResponse {

    public Forecast[] list;

    public static class Forecast {

        public String dt_txt;

        public Main main;

        public Weather[] weather;

        public static class Main {

            public float temp;
            public float temp_max;
            public float temp_min;
            public int humidity;

        }

        public static class Weather {
            public String description;
            public String icon;
        }

    }

}
