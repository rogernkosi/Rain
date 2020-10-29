package com.rogernkosi.rainassessment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.rogernkosi.rainassessment.R;
import com.rogernkosi.rainassessment.model.SignalPowerModel;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceSignalListAdapter extends RecyclerView.Adapter<DeviceSignalListAdapter.DeviceSignalViewHolder> {

    private List<SignalPowerModel> powerModels;

    @NonNull
    @Override
    public DeviceSignalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_signal_strength_item, parent, false);
        return new DeviceSignalListAdapter.DeviceSignalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceSignalViewHolder holder, int position) {
        SignalPowerModel powerModel = powerModels.get(position);
        holder.connectionName.setText(getConnectionName(powerModel.getDataConnectionName()));
        holder.powerStrength.setText(powerModel.getDataConnectionValue());
    }

    private String getConnectionName(String name){
        if (name != null && StringUtils.isNotEmpty(name)){
            return name;
        }else {
            return "N/A";
        }
    }

    @Override
    public int getItemCount() {
        return powerModels.size();
    }

    public void setPowerModels(List<SignalPowerModel> powerModels) {
        this.powerModels = powerModels;
        notifyDataSetChanged();
    }

    static class DeviceSignalViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.connection_name) AppCompatTextView connectionName;
        @BindView(R.id.power_strength) AppCompatTextView powerStrength;

        public DeviceSignalViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
