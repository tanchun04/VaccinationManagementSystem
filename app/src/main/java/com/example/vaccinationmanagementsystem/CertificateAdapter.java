package com.example.vaccinationmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.CertificateViewHolder> {

    private List<VaccinationRecord> vaccinationRecords;

    public CertificateAdapter(List<VaccinationRecord> vaccinationRecords) {
        this.vaccinationRecords = vaccinationRecords;
    }

    @NonNull
    @Override
    public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_certificate, parent, false);
        return new CertificateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CertificateViewHolder holder, int position) {
        VaccinationRecord record = vaccinationRecords.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return vaccinationRecords != null ? vaccinationRecords.size() : 0;
    }

    public void updateData(List<VaccinationRecord> newRecords) {
        this.vaccinationRecords = newRecords;
        notifyDataSetChanged();
    }

    static class CertificateViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVaccineName, tvVaccineType, tvDate, tvLocation, tvLotNumber, tvStatus;

        public CertificateViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVaccineName = itemView.findViewById(R.id.tvVaccineName);
            tvVaccineType = itemView.findViewById(R.id.tvVaccineType);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvLotNumber = itemView.findViewById(R.id.tvLotNumber);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }

        public void bind(VaccinationRecord record) {
            tvVaccineName.setText(record.getVaccineName());
            tvVaccineType.setText(record.getVaccineType());
            tvDate.setText(record.getVaccinationDate());
            tvLocation.setText(record.getLocation());
            tvLotNumber.setText(record.getLotNumber());
            tvStatus.setText(record.getStatus());
        }
    }
}