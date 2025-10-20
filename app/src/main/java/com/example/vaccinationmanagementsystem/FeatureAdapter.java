package com.example.vaccinationmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;

public class FeatureAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Feature> features;
    private LayoutInflater inflater;

    // Interface để xử lý sự kiện click
    public interface OnFeatureClickListener {
        void onFeatureClick(Feature feature);
    }

    private OnFeatureClickListener listener;

    // Constructor
    public FeatureAdapter(Context context, ArrayList<Feature> features) {
        this.context = context;
        this.features = features;
        this.inflater = LayoutInflater.from(context);
    }

    // Constructor với listener
    public FeatureAdapter(Context context, ArrayList<Feature> features, OnFeatureClickListener listener) {
        this.context = context;
        this.features = features;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    // Set listener
    public void setOnFeatureClickListener(OnFeatureClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return features != null ? features.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return features != null ? features.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Khởi tạo view mới
            convertView = inflater.inflate(R.layout.grid_item_feature, parent, false);
            holder = new ViewHolder();
            holder.cardView = convertView.findViewById(R.id.cardViewFeature);
            holder.ivIcon = convertView.findViewById(R.id.ivFeatureIcon);
            holder.tvName = convertView.findViewById(R.id.tvFeatureName);
            convertView.setTag(holder);
        } else {
            // Tái sử dụng view
            holder = (ViewHolder) convertView.getTag();
        }

        // Lấy feature tại vị trí hiện tại
        Feature feature = features.get(position);

        // Thiết lập dữ liệu
        holder.tvName.setText(feature.getName());
        holder.ivIcon.setImageResource(feature.getIconResId());

        // Thiết lập màu nền nếu có
        if (feature.getColorResId() != android.R.color.white) {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(feature.getColorResId()));
        }

        // Xử lý sự kiện click
        holder.cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFeatureClick(feature);
            }
        });

        return convertView;
    }

    // Cập nhật dữ liệu mới
    public void updateData(ArrayList<Feature> newFeatures) {
        this.features = newFeatures;
        notifyDataSetChanged();
    }

    // Thêm feature mới
    public void addFeature(Feature feature) {
        if (features != null) {
            features.add(feature);
            notifyDataSetChanged();
        }
    }

    // Xóa feature
    public void removeFeature(int position) {
        if (features != null && position >= 0 && position < features.size()) {
            features.remove(position);
            notifyDataSetChanged();
        }
    }

    // Lấy tất cả features
    public ArrayList<Feature> getAllFeatures() {
        return features;
    }

    // ViewHolder pattern để tối ưu hiệu năng
    private static class ViewHolder {
        CardView cardView;
        ImageView ivIcon;
        TextView tvName;
    }
}