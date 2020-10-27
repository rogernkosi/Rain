package com.rogernkosi.rainassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rogernkosi.rainassessment.adapters.ForecastRecyclerViewAdapter;
import com.rogernkosi.rainassessment.model.Hour;
import com.rogernkosi.rainassessment.persistance.model.PinnedLocation;
import com.rogernkosi.rainassessment.util.TimeUtils;
import com.rogernkosi.rainassessment.viewmodel.ForecastViewModel;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class WeatherDetailsActivity extends AppCompatActivity {

    public static final String POSITION_EXTRA = "POSITION_EXTRA";
    private ForecastViewModel forecastViewModel;
    private AppCompatTextView condition;
    private AppCompatTextView location;
    private AppCompatTextView temperature;
    private AppCompatImageView timeOfDayBackground;

    private ProgressDialog progressDialog;
    private RecyclerView forecastRecyclerView;
    private ForecastRecyclerViewAdapter forecastRecyclerViewAdapter;

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
        timeOfDayBackground = findViewById(R.id.time_of_day);

        setupToolBar();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading_weather));
        progressDialog.setCancelable(false);
        progressDialog.show();

        forecastViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ForecastViewModel.class);
        render();
    }

    void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void render() {
        if (getIntent().hasExtra(POSITION_EXTRA)) {
            LatLng latLng = getIntent().getParcelableExtra(POSITION_EXTRA);
            StringBuilder positionBuilder = new StringBuilder();
            String position = positionBuilder.append(latLng.latitude).append(",").append(latLng.longitude).toString();
            forecastViewModel.getForecastRepository(position).observe(this, response -> {
                progressDialog.dismiss();
                location.setText(response.getLocation().getName());
                displayCurrentWeather(response.getCurrent().getCondition().getText(), response.getCurrent().getTempC());
                List<Hour> hours = response.getForecast().getForecastday().get(0).getHour();
                displayWeatherTimeLine(hours); // I always want today's weather unless we have an error on the data which should be caught on the repository
                showDayTimeImage(TimeUtils.dateTimeToHourStripMinutes(response.getCurrent().getLastUpdated()));
            });
        } else {

        }
    }

    private void displayCurrentWeather(String weatherCondition, double currentTemperature) {
        setCondition(weatherCondition);
        setTemperature(currentTemperature);
    }

    private void setCondition(String weatherCondition) {
        if (StringUtils.isNotEmpty(weatherCondition)) {
            condition.setText(weatherCondition);
        } else {
            condition.setText(R.string.not_available);
        }
    }

    private void setTemperature(double currentTemperature) {
        StringBuilder builder = new StringBuilder();
        String temp = builder.append(currentTemperature).append("\u2103").toString();
        if (StringUtils.isNotEmpty(temp)) {
            temperature.setText(temp);
        } else {
            condition.setText(R.string.not_available);
        }
    }

    void displayWeatherTimeLine(List<Hour> hourList) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL);
        forecastRecyclerView = findViewById(R.id.forecast_list);
        forecastRecyclerViewAdapter = new ForecastRecyclerViewAdapter();
        forecastRecyclerView.addItemDecoration(dividerItemDecoration);
        forecastRecyclerView.setAdapter(forecastRecyclerViewAdapter);
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        forecastRecyclerViewAdapter.setHourList(hourList);
        forecastRecyclerViewAdapter.getHourPublishSubject()
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(hour -> {
                    forecastRecyclerViewAdapter.notifyDataSetChanged();
                    displayCurrentWeather(hour.getHour().getCondition().getText(), hour.getHour().getTempC());
                    showDayTimeImage(hour.getSelectedHour());
                });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showDayTimeImage(String hours) {
        switch (hours) {
            case "00:00":
            case "01:00":
            case "02:00":
            case "03:00":
            case "04:00":
            case "20:00":
            case "19:00":
            case "21:00":
            case "22:00":
            case "23:00": {
                timeOfDayBackground.setImageDrawable(getResources().getDrawable(R.drawable.ic_night_camp_));
            }
            break;
            case "06:00":
            case "07:00":
            case "08:00":
            case "09:00":
            case "10:00":
            case "11:00":
            case "05:00": {
                timeOfDayBackground.setImageDrawable(getResources().getDrawable(R.drawable.sunrise));
            }
            break;
            case "12:00":
            case "13:00":
            case "14:00":
            case "15:00":
            case "16:00":
            case "17:00":
            case "18:00": {
                timeOfDayBackground.setImageDrawable(getResources().getDrawable(R.drawable.kids_play_day_time)); // This image is heavy, I need a better image
            }
            break;
            default:
                return;
        }
    }

}