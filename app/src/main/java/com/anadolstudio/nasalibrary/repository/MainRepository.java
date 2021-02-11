package com.anadolstudio.nasalibrary.repository;

import android.util.Log;

import com.anadolstudio.nasalibrary.MainContract;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainRepository implements MainContract.Repository {
    public static final String TAG = MainRepository.class.getSimpleName();
    public static final String IMAGE_MEDIA_TYPE = "image";

    @Override
    public void loadCollection(String query, int page, SingleObserver<NasaItemResponse> observer) {
        Log.i(TAG, "loadCollection");

        Server.get().getApi().getCollection(query, page, IMAGE_MEDIA_TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void loadNasaImageMedia(String id, SingleObserver<NasaImageResponse> observer) {
        Log.i(TAG, "loadNasaImageMedia");

        Server.get().getApi().getNasaImageMedia(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
