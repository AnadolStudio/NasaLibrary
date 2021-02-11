package com.anadolstudio.nasalibrary.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.anadolstudio.nasalibrary.MainContract;
import com.anadolstudio.nasalibrary.MainContract.View;
import com.anadolstudio.nasalibrary.repository.ImageMedia;
import com.anadolstudio.nasalibrary.repository.MainRepository;
import com.anadolstudio.nasalibrary.repository.NasaImageResponse;
import com.anadolstudio.nasalibrary.repository.NasaItemResponse;
import com.anadolstudio.nasalibrary.repository.NasaMediaItem;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainPresenter implements MainContract.Presenter {
    public static final String TAG = MainPresenter.class.getSimpleName();
    public static final String DEFAULT_QUERY = "\"\"";
    public static final int START_QUERY_PAGE = 1;

    private MainRepository mRepository;
    private CompositeDisposable mCompositeDisposable;
    private Context mContext;

    public MainPresenter(Context context) {
        mRepository = new MainRepository();
        mCompositeDisposable = new CompositeDisposable();
        mContext = context;
    }

    @Override
    public void updateList(String query, int page, View.ListView<NasaMediaItem> view) {
        if (!view.onPreShow()) return;
        if (query == null || query.isEmpty()) query = DEFAULT_QUERY;

        Log.i(TAG, "updateList: query " + query + " page " + page);

        saveQuery(query);

        DisposableSingleObserver<NasaItemResponse> observer = new DisposableSingleObserver<NasaItemResponse>() {
            @Override
            public void onSuccess(NasaItemResponse nasaResponse) {
                List<NasaMediaItem> items = nasaResponse.getCollection().getItems();
                if (items != null && items.size() > 0) QueryPreference.setQueryPage(mContext, page);
                view.show(items);
                view.onPostShow();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                e.printStackTrace();
                view.onPostShow();
            }
        };
        mCompositeDisposable.add(observer);
        mRepository.loadCollection(query, page, observer);
    }

    @Override
    public void updateDetail(@NonNull String id, View.DetailView<ImageMedia> view) {
        if (!view.onPreShow()) return;

        DisposableSingleObserver<NasaImageResponse> observer = new DisposableSingleObserver<NasaImageResponse>() {
            @Override
            public void onSuccess(NasaImageResponse imageResponse) {
                List<ImageMedia> items = imageResponse.getCollection().getItems();
                view.show(items);
                view.onPostShow();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                e.printStackTrace();
                view.onPostShow();
            }
        };
        mCompositeDisposable.add(observer);
        mRepository.loadNasaImageMedia(id, observer);

    }

    private void saveQuery(String newQuery) {
        String lastQuery = QueryPreference.getSearchQuery(mContext);
        // Если это новый запрос, а не новая страница старого
        if (!lastQuery.toLowerCase().equals(newQuery.toLowerCase())) {
            QueryPreference.setSearchQuery(mContext, newQuery);
        }
    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
