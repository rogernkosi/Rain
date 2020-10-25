package com.rogernkosi.rainassessment;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.rogernkosi.rainassessment.viewmodel.ForecastViewModel;

public class WeatherDetailsActivity extends FragmentActivity {

    public static final String POSITION_EXTRA = "POSITION_EXTRA";
    private ForecastViewModel forecastViewModel;
    private AppCompatTextView condition;
    private AppCompatTextView location;
    private AppCompatTextView temperature;
    private ProgressDialog progressDialog;
    private RecyclerView forecastRecyclerView;

    public static Intent getStartIntent(Context context, LatLng latLng) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(POSITION_EXTRA, latLng);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather_details);

        condition = findViewById(R.id.weather_condition);
        location = findViewById(R.id.location);
        temperature = findViewById(R.id.temperature);
        forecastRecyclerView = findViewById(R.id.forecast_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_weather));
        progressDialog.setCancelable(false);
        progressDialog.show();

        forecastViewModel = ViewModelProviders.of(this).get(ForecastViewModel.class);
        forecastViewModel.init();
        render();
    }

    private void render() {
        if (getIntent().hasExtra(POSITION_EXTRA)) {
            LatLng latLng = getIntent().getParcelableExtra(POSITION_EXTRA);
            StringBuilder positionBuilder = new StringBuilder();
            String position = positionBuilder.append(latLng.latitude).append(",").append(latLng.longitude).toString();
            forecastViewModel.getForecastRepository(position).observe(this, response -> {
                progressDialog.dismiss();
                condition.setText(response.getCurrent().getCondition().getText());
                location.setText(response.getLocation().getName());
                temperature.setText(String.valueOf(response.getCurrent().getTempC()).concat("\u2103"));
            });
        } else {

        }
    }

}