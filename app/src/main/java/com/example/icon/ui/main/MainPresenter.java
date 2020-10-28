package com.example.icon.ui.main;

import com.example.icon.data.model.Icons;
import com.example.icon.data.model.Iconsets;
import com.example.icon.data.repository.IconsRepository;

import java.util.List;

public class MainPresenter {

    private MainActivity view;
    private IconsRepository iconsRepository = new IconsRepository();

    MainPresenter(MainActivity activity) {
        view = activity;
    }

    public void search(String name, int size, int offset) {
        iconsRepository.search(name, size, offset, new SearchDataListener() {
            @Override
            public void onSuccess(List<Icons> iconsList) {
                if (view != null) {
                    view.showIconList(iconsList);
                }
            }

            @Override
            public void onError(String message) {
                if (view != null) {
                    view.showError(message);
                }
            }
        });
    }

    public void iconsetId() {
        iconsRepository.iconsetId(new IconsetIdListener() {
            @Override
            public void onSuccess(List<Iconsets> iconsetsList) {
                if (view != null) {
                    view.showIconsetId(iconsetsList);
                }
            }

            @Override
            public void onError(String message) {
                if (view != null) {
                    view.showError(message);
                }
            }
        });
    }

    public void onStop() {
        view = null;
    }

    public interface SearchDataListener {
        void onSuccess(List<Icons> iconsList);

        void onError(String message);
    }

    public interface IconsetIdListener {
        void onSuccess(List<Iconsets> iconsetsList);

        void onError(String message);
    }
}
