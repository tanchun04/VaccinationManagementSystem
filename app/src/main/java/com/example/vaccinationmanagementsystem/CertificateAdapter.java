package com.example.vaccinationmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.CertificateViewHolder> {

    private List<VaccinationRecord> vaccinationRecords;
    private OnCertificateClickListener listener;

    public interface OnCertificateClickListener {
        void onCertificateClick(VaccinationRecord record);
    }

    public CertificateAdapter(List<VaccinationRecord> vaccinationRecords) {
        this.vaccinationRecords = vaccinationRecords;
    }

    public CertificateAdapter(List<VaccinationRecord> vaccinationRecords, OnCertificateClickListener listener) {
        this.vaccinationRecords = vaccinationRecords;
        this.listener = listener;
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
        
        holder.tvVaccineType.setText(record.getVaccineType());
        holder.tvVaccinationDate.setText("Ngày tiêm: " + record.getVaccinationDate());
        holder.tvLocation.setText("Nơi tiêm: " + record.getLocation());
        holder.tvDose.setText(record.getDose());
        holder.tvDoctor.setText("Bác sĩ: " + record.getDoctor());

        holder.cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCertificateClick(record);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vaccinationRecords != null ? vaccinationRecords.size() : 0;
    }

    public void updateData(List<VaccinationRecord> newRecords) {
        this.vaccinationRecords = newRecords;
        notifyDataSetChanged();
    }

    public void setOnCertificateClickListener(OnCertificateClickListener listener) {
        this.listener = listener;
    }

    static class CertificateViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvVaccineType, tvVaccinationDate, tvLocation, tvDose, tvDoctor;

        public CertificateViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewCertificate);
            tvVaccineType = itemView.findViewById(R.id.tvVaccineType);
            tvVaccinationDate = itemView.findViewById(R.id.tvVaccinationDate);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDose = itemView.findViewById(R.id.tvDose);
            tvDoctor = itemView.findViewById(R.id.tvDoctor);
        }
    }
}