package com.example.icon.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icon.R;
import com.example.icon.data.model.Icons;
import com.example.icon.data.model.Iconsets;
import com.example.icon.ui.adapter.IconsAdapter;
import com.example.icon.ui.adapter.IconsetAdapter;
import com.example.icon.ui.selectedicon.SelectedIconActivity;
import com.example.icon.ui.selectediconset.SelectedIconsetActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private IconsetAdapter iconsetAdapter;
    private IconsAdapter iconsAdapter;
    private GridLayoutManager layoutManager;
    private Boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;
    private String searchedName;
    private ProgressBar progressBar;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);

        setAdapter();
        iconsetId();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();
                int offset = layoutManager.findLastCompletelyVisibleItemPosition() + 1;

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    progressBar.setVisibility(View.VISIBLE);
                    search(searchedName, offset);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter = new MainPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icon_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.iconSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchedName = query;
                search(query, 0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setQueryHint("Search");
        return true;

    }

    public void search(String name, int offset) {
        presenter.search(name, 10, offset);
    }

    public void showIconList(List<Icons> icons) {
        iconsAdapter = new IconsAdapter(new IconsAdapter.IconClickListener() {
            @Override
            public void onClick(String prevUrl, String name, String dwnUrl, Boolean isPremium, String price, String currency) {
                Intent intent = new Intent(MainActivity.this, SelectedIconActivity.class);
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
        iconsAdapter.addData(icons);
        progressBar.setVisibility(View.GONE);
    }

    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    public void iconsetId() {
        presenter.iconsetId();
    }

    public void showIconsetId(List<Iconsets> iconsetsList) {
        iconsetAdapter.addIconset((ArrayList<Iconsets>) iconsetsList);
    }

    public void setAdapter() {
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        iconsetAdapter = new IconsetAdapter(new IconsetAdapter.IconsetClickListener() {
            @Override
            public void onClick(String iconsetId) {
                Intent intent = new Intent(MainActivity.this, SelectedIconsetActivity.class);
                intent.putExtra("iconsetId", iconsetId);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(iconsetAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}