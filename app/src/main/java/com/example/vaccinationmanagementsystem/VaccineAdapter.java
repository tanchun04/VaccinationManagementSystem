package com.example.vaccinationmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineViewHolder> {

    private List<Vaccine> vaccineList;

    public VaccineAdapter(List<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }

    @NonNull
    @Override
    public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vaccine, parent, false);
        return new VaccineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineViewHolder holder, int position) {
        Vaccine vaccine = vaccineList.get(position);
        holder.bind(vaccine);
    }

    @Override
    public int getItemCount() {
        return vaccineList != null ? vaccineList.size() : 0;
    }

    public void updateData(List<Vaccine> newVaccines) {
        this.vaccineList = newVaccines;
        notifyDataSetChanged();
    }

    static class VaccineViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVaccineName, tvManufacturer, tvLotNumber, tvQuantity, tvExpiryDate;

        public VaccineViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVaccineName = itemView.findViewById(R.id.tvVaccineName);
            tvManufacturer = itemView.findViewById(R.id.tvManufacturer);
            tvLotNumber = itemView.findViewById(R.id.tvLotNumber);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvExpiryDate = itemView.findViewById(R.id.tvExpiryDate);
        }

        public void bind(Vaccine vaccine) {
            tvVaccineName.setText(vaccine.getName());
            tvManufacturer.setText(vaccine.getManufacturer());
            tvLotNumber.setText("Lô: " + vaccine.getLotNumber());
            tvQuantity.setText("Số lượng: " + vaccine.getQuantity());
            tvExpiryDate.setText("HSD: " + vaccine.getExpiryDate());
        }
    }
}