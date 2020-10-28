package com.example.icon.data.repository;

import androidx.annotation.NonNull;

import com.example.icon.data.model.Icon;
import com.example.icon.data.model.Icons;
import com.example.icon.data.model.IconsetObject;
import com.example.icon.data.model.Iconsets;
import com.example.icon.data.rest.ApiClient;
import com.example.icon.data.rest.ApiInterface;
import com.example.icon.ui.main.MainPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IconsRepository {

    public void search(String name, int size, int offset, MainPresenter.SearchDataListener listener) {
        Retrofit apiInstance = ApiClient.getApiClient();

        ApiInterface service = apiInstance.create(ApiInterface.class);
        service.getIconsByQuery(name, size, offset).enqueue(new Callback<Icon>() {
            @Override
            public void onResponse(@NonNull Call<Icon> call, @NonNull Response<Icon> response) {
                if (!response.isSuccessful()) {
                    listener.onError("Kuch to gadbad h!");
                    return;
                }

                ArrayList<Icons> searchList = response.body().getIcons();

                listener.onSuccess(searchList);
            }

            @Override
            public void onFailure(@NonNull Call<Icon> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void iconsetId(MainPresenter.IconsetIdListener listener) {
        Retrofit apiInstance = ApiClient.getApiClient();

        ApiInterface service = apiInstance.create(ApiInterface.class);
        service.getIconsetsByquery().enqueue(new Callback<IconsetObject>() {
            @Override
            public void onResponse(Call<IconsetObject> call, Response<IconsetObject> response) {
                if (!response.isSuccessful()) {
                    listener.onError("Kuch to gadbad h!");
                    return;
                }
                ArrayList<Iconsets> data = response.body().getIconsets();
                listener.onSuccess(data);

            }

            @Override
            public void onFailure(Call<IconsetObject> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
}
