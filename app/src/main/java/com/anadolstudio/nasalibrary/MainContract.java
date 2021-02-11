package com.anadolstudio.nasalibrary;

import androidx.annotation.NonNull;

import com.anadolstudio.nasalibrary.repository.ImageMedia;
import com.anadolstudio.nasalibrary.repository.NasaImageResponse;
import com.anadolstudio.nasalibrary.repository.NasaItemResponse;
import com.anadolstudio.nasalibrary.repository.NasaMediaItem;

import java.util.List;

import io.reactivex.SingleObserver;

public interface MainContract {

    interface View<T> {
        void show(T t);

        boolean onPreShow();

        void onPostShow();

        interface Detailable<T> {
            void toDetail(T t);
        }

        interface ListView<T> extends View<List<T>>, Detailable<T> {
            @Override
            void show(List<T> ts);

        }

        interface DetailView<T> extends View<List<T>> {
            @Override
            void show(List<T> ts);
        }
    }

    interface Presenter {
        void updateList(String query, int page, View.ListView<NasaMediaItem> listView);

        void updateDetail(@NonNull String id, View.DetailView<ImageMedia> detailView);

        void onDestroy();
    }

    interface Repository {

        void loadCollection(String query, int page, SingleObserver<NasaItemResponse> observer);

        void loadNasaImageMedia(String id, SingleObserver<NasaImageResponse> observer);
    }

}
