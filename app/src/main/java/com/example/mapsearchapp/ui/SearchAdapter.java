package com.example.mapsearchapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mapsearchapp.R;
import com.example.mapsearchapp.model.LocationResult;

import java.util.ArrayList;
import java.util.List;

import com.example.mapsearchapp.utils.highlight;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<LocationResult> locationList = new ArrayList<>(); // danh sach dia diem
    private Context context;
    private String currentKeyword = ""; // tu khoa tim kiem de highlight

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<LocationResult> newList) {
        locationList = newList; // cap nhat du lieu
        notifyDataSetChanged(); // thong bao RecyclerView update
    }

    public void setKeyword(String keyword) {
        this.currentKeyword = keyword;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocationResult location = locationList.get(position);
        Spannable highlighted = highlight.highlightKeyword(location.getDisplayName(), currentKeyword);
        holder.tvName.setText(highlighted);

        // khi user click vao item thi mo google maps
        holder.ivNavigate.setOnClickListener(v -> {
            String uri = "https://www.google.com/maps/dir/?api=1&destination=" + location.getLat() + "," + location.getLon();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivNavigate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivNavigate = itemView.findViewById(R.id.ivNavigate);
        }
    }
}
