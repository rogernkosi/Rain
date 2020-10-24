package com.rogernkosi.rainassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class WeatherDetailsActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context){
        return new Intent(context, WeatherDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather_details);
    }


}