package com.example.icon.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icon.data.model.Iconsets;
import com.example.icon.R;

import java.util.ArrayList;

public class IconsetAdapter extends RecyclerView.Adapter<IconsetAdapter.IconsetViewHolder> {
    private ArrayList<Iconsets> data;
    private IconsetClickListener listener;

    public IconsetAdapter(IconsetClickListener listener) {
        this.listener = listener;
        data = new ArrayList<>();
    }

    public void addIconset(ArrayList<Iconsets> newIconset) {
        data.addAll(newIconset);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IconsetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iconset_layout, parent, false);
        return new IconsetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconsetViewHolder holder, int position) {
        holder.iconsetName.setText(data.get(position).getName());
        String iconsetId = data.get(position).getIconset_id();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(iconsetId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface IconsetClickListener {
        void onClick(String iconsetId);
    }


    public class IconsetViewHolder extends RecyclerView.ViewHolder {
        private TextView iconsetName;
        private ImageView download;
        private CardView cardView;

        public IconsetViewHolder(@NonNull View itemView) {
            super(itemView);
            iconsetName = itemView.findViewById(R.id.textView);
            download = itemView.findViewById(R.id.dwnld);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
