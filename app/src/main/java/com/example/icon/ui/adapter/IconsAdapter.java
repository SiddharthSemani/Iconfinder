package com.example.icon.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icon.data.model.Icons;
import com.example.icon.data.model.Formats;
import com.example.icon.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class IconsAdapter extends RecyclerView.Adapter<IconsAdapter.IconViewHolder> {

    private ArrayList<Icons> data;
    private IconClickListener listener;

    public IconsAdapter(IconClickListener listener) {
        this.listener = listener;
        data = new ArrayList<>();
    }

    public void addData(List<Icons> newData) {
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public void resetData() {
        this.data.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder holder, int position) {
        Icons currentIcon = data.get(position);
        Formats currentFormats;
        if (currentIcon.getRaster_sizes().size() >= 7) {
            currentFormats = currentIcon.getRaster_sizes().get(6).getFormats().get(0);
        } else {
            currentFormats = currentIcon.getRaster_sizes().get(5).getFormats().get(0);
        }


        String name = currentIcon.getTags().get(0);
        holder.iconName.setText(name);
        Boolean premium = currentIcon.getIs_premium();
        String price = "0";
        String currency = "0";
        if (premium) {
            price = currentIcon.getPrices().get(0).getPrice();
            currency = currentIcon.getPrices().get(0).getCurrency();
            if (currency.equals("USD")) {
                holder.currencyView.setText("$" + price);
            } else {
                holder.currencyView.setText("₹" + price);
            }
            holder.isPremiumView.setVisibility(View.VISIBLE);
            holder.currencyView.setVisibility(View.VISIBLE);
            holder.downloadIcon.setVisibility(View.GONE);

        } else {
            holder.isPremiumView.setVisibility(View.GONE);
            holder.currencyView.setVisibility(View.GONE);
            holder.downloadIcon.setVisibility(View.VISIBLE);
        }
        String prevUrl = currentFormats.getPreview_url();
        String dwnUrl = currentFormats.getDownload_url();
        Picasso.get().load(prevUrl).into(holder.icon);

        String finalPrice = price;
        String finalCurrency = currency;
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(prevUrl, name, dwnUrl, premium, finalPrice, finalCurrency);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface IconClickListener {
        void onClick(String prevUrl, String name, String dwnUrl, Boolean isPremium, String price, String currency);
    }

    public class IconViewHolder extends RecyclerView.ViewHolder {
        private TextView iconName, currencyView;
        private ImageView icon, isPremiumView, downloadIcon;
        private CardView cardView;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            iconName = itemView.findViewById(R.id.iconName);
            currencyView = itemView.findViewById(R.id.currency);
            downloadIcon = itemView.findViewById(R.id.downloadView);
            icon = itemView.findViewById(R.id.icon);
            cardView = itemView.findViewById(R.id.root_view);
            isPremiumView = itemView.findViewById(R.id.isPremium);
        }
    }
}
