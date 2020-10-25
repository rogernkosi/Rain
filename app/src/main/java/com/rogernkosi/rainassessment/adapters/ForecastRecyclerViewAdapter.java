package com.rogernkosi.rainassessment.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.rogernkosi.rainassessment.R;
import com.rogernkosi.rainassessment.model.Hour;
import com.rogernkosi.rainassessment.model.OnClickHour;
import com.rogernkosi.rainassessment.util.TimeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.ForecastHolder> {

    private List<Hour> hourList = new ArrayList<>();
    private PublishSubject<OnClickHour> hourPublishSubject = PublishSubject.create();
    private int selectedPosition = -1;

    @NonNull
    @Override
    public ForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_timeline_item, parent, false);
        return new ForecastHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastHolder holder, int position) {
        Hour hour = hourList.get(position);
        holder.condition.setText(hour.getCondition().getText());
        holder.temperature.setText(getTemperature(hour.getTempC()));
        holder.time.setText(TimeUtils.dateTimeToHour(hour.getTime()));
        if (position == selectedPosition) {
            holder.currentItem.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_baseline_chevron_right_24));
        } else {
            holder.currentItem.setImageDrawable(null);
        }
        holder.itemView.setOnClickListener(view -> {
            selectedPosition = position;
            hourPublishSubject.onNext(new OnClickHour(hour, TimeUtils.dateTimeToHour(hour.getTime())));
        });
    }

    private String getTemperature(double temp) {
        return new StringBuilder().append(temp).append("\u2103").toString();
    }

    public Observable<OnClickHour> getHourPublishSubject() {
        return hourPublishSubject.hide();
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return hourList.size();
    }

    public void setHourList(List<Hour> hourList) {
        this.hourList = hourList;
        sortData(hourList);
    }

    private void sortData(List<Hour> optionList) {
        Collections.sort(optionList, (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        notifyDataSetChanged();
    }

    static class ForecastHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView currentItem;
        private AppCompatTextView time;
        private AppCompatTextView temperature;
        private AppCompatTextView condition;

        public ForecastHolder(@NonNull View itemView) {
            super(itemView);
            currentItem = itemView.findViewById(R.id.current_item);
            time = itemView.findViewById(R.id.time);
            temperature = itemView.findViewById(R.id.temperature);
            condition = itemView.findViewById(R.id.weather_condition);
        }
    }
}
