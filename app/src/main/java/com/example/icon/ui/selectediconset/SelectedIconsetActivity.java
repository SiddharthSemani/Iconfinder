package com.example.icon.ui.selectediconset;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icon.R;
import com.example.icon.data.model.Icon;
import com.example.icon.data.model.Icons;
import com.example.icon.ui.adapter.IconsAdapter;
import com.example.icon.data.rest.ApiInterface;
import com.example.icon.ui.selectedicon.SelectedIconActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectedIconsetActivity extends AppCompatActivity {
    private String iconsetId;
    private RecyclerView recyclerView;
    private IconsAdapter iconsAdapter;
    private GridLayoutManager layoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_iconset);

        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            iconsetId = extras.getString("iconsetId");
            iconsFromIconset(iconsetId);
        }
        else {
            Toast.makeText(SelectedIconsetActivity.this,"Can't",Toast.LENGTH_LONG).show();
        }
    }

    public void iconsFromIconset(String iconsetId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.iconfinder.com/v4/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiInterface service = retrofit.create(ApiInterface.class);
                service.getIconsFromIconset(iconsetId).enqueue(new Callback<Icon>() {
                    @Override
                    public void onResponse(Call<Icon> call, Response<Icon> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(SelectedIconsetActivity.this, "Response fail" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        ArrayList<Icons> searchList = response.body().getIcons();
                        iconsAdapter = new IconsAdapter(new IconsAdapter.IconClickListener() {
                            @Override
                            public void onClick(String prevUrl, String name, String dwnUrl, Boolean isPremium, String price, String currency) {
                                Intent intent = new Intent(SelectedIconsetActivity.this, SelectedIconActivity.class);
                                intent.putExtra("prevUrl", prevUrl);
                                intent.putExtra("name", name);
                                intent.putExtra("dwnUrl", dwnUrl);
                                intent.putExtra("isPremium", isPremium);
                                intent.putExtra("price", price);
                                intent.putExtra("currency", currency);

                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(iconsAdapter);
                        iconsAdapter.resetData();
                        iconsAdapter.addData(searchList);
                    }

                    @Override
                    public void onFailure(Call<Icon> call, Throwable t) {
                        Toast.makeText(SelectedIconsetActivity.this, "Failure" + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}