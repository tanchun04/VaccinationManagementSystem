package com.example.vaccinationmanagementsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;
    private OnAppointmentActionListener listener;

    public interface OnAppointmentActionListener {
        void onAppointmentAction(String action, Appointment appointment);
    }

    public AppointmentAdapter(List<Appointment> appointmentList, OnAppointmentActionListener listener) {
        this.appointmentList = appointmentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.bind(appointment, listener);
    }

    @Override
    public int getItemCount() {
        return appointmentList != null ? appointmentList.size() : 0;
    }

    public void updateData(List<Appointment> newAppointments) {
        this.appointmentList = newAppointments;
        notifyDataSetChanged();
    }

    static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVaccineType, tvDate, tvTime, tvLocation, tvStatus;
        private Button btnCancel, btnReschedule, btnView;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVaccineType = itemView.findViewById(R.id.tvVaccineType);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            btnReschedule = itemView.findViewById(R.id.btnReschedule);
            btnView = itemView.findViewById(R.id.btnView);
        }

        public void bind(Appointment appointment, OnAppointmentActionListener listener) {
            tvVaccineType.setText(appointment.getVaccineType());
            tvDate.setText(appointment.getDate());
            tvTime.setText(appointment.getTime());
            tvLocation.setText(appointment.getLocation());
            tvStatus.setText(appointment.getStatus());

            btnCancel.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAppointmentAction("cancel", appointment);
                }
            });

            btnReschedule.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAppointmentAction("reschedule", appointment);
                }
            });

            btnView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAppointmentAction("view", appointment);
                }
            });
        }
    }
}