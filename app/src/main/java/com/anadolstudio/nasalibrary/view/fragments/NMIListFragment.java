package com.anadolstudio.nasalibrary.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.anadolstudio.nasalibrary.MainContract;
import com.anadolstudio.nasalibrary.R;
import com.anadolstudio.nasalibrary.databinding.FragmentNmiListBinding;
import com.anadolstudio.nasalibrary.presenter.InternetConnection;
import com.anadolstudio.nasalibrary.presenter.MainPresenter;
import com.anadolstudio.nasalibrary.presenter.QueryPreference;
import com.anadolstudio.nasalibrary.presenter.adapter.ILoadMore;
import com.anadolstudio.nasalibrary.presenter.adapter.NMIListAdapter;
import com.anadolstudio.nasalibrary.repository.NasaMediaItem;
import com.anadolstudio.nasalibrary.view.SpacesItemDecoration;
import com.anadolstudio.nasalibrary.view.activities.NMIDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.anadolstudio.nasalibrary.presenter.MainPresenter.DEFAULT_QUERY;
import static com.anadolstudio.nasalibrary.presenter.MainPresenter.START_QUERY_PAGE;
import static com.anadolstudio.nasalibrary.repository.NasaMediaItem.NASA_MEDIA_ITEM;

public class NMIListFragment extends SimpleFragment implements MainContract.View.ListView<NasaMediaItem>, ILoadMore {
    public static final String TAG = NMIListFragment.class.getSimpleName();
    private MainPresenter mPresenter;
    private FragmentNmiListBinding mBinding;
    private NMIListAdapter mAdapter;

    public static NMIListFragment newInstance() {

        Bundle args = new Bundle();

        NMIListFragment fragment = new NMIListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentNmiListBinding.inflate(inflater);
        setup();

        // Открыть последний запрос
        mPresenter.updateList(
                QueryPreference.getSearchQuery(getContext()),
                QueryPreference.getQueryPage(getContext()),
                this);

        return mBinding.getRoot();
    }

    private void setup() {
        RecyclerView recyclerView = mBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setItemViewCacheSize(50);
        recyclerView.addItemDecoration(new SpacesItemDecoration(2));
        recyclerView.setDrawingCacheEnabled(true);

        mAdapter = new NMIListAdapter(new ArrayList<>(), this, this);
        recyclerView.setAdapter(mAdapter);

        mBinding.emptyText.setText(R.string.nothing_found);

        //Возвращает к 1 странице
        SwipeRefreshLayout swipeRefreshLayout = mBinding.swipeRefreshLayout;
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorSecondary));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.updateList(QueryPreference.getSearchQuery(getContext()), START_QUERY_PAGE, this);
            mBinding.swipeRefreshLayout.setRefreshing(false);
        });
        mPresenter = new MainPresenter(getContext());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_nmi_list, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        setupSearchView((SearchView) searchItem.getActionView());
    }

    private void setupSearchView(SearchView searchView) {
        searchView.setQueryHint(getString(R.string.search2));
        searchView.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        String q = QueryPreference.getSearchQuery(getContext());
        if (!q.equals(DEFAULT_QUERY)) {
            searchView.setQuery(q, false);
        }

        searchView.setOnCloseListener(() -> {
            String lastQuery = QueryPreference.getSearchQuery(getContext());
            // Не обновлять страницу, если последний запрос был дефолтным
            if (!lastQuery.equals(DEFAULT_QUERY)) {
                mPresenter.updateList(DEFAULT_QUERY, START_QUERY_PAGE, NMIListFragment.this);
            }
            return false;
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "onQueryTextSubmit: " + query);
                mPresenter.updateList(query, 1, NMIListFragment.this);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public boolean onPreShow() {
        boolean already;
        already = InternetConnection.isNetworkAvailable(getContext());

        if (already) {
            showLoadingDialog();
        }

        return already;
    }

    @Override
    public void onPostShow() {
        hideLoadingDialog();
        mBinding.emptyText.setVisibility(mAdapter.getData().size() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void show(List<NasaMediaItem> items) {
        if (items != null) {
            int currentPage = QueryPreference.getQueryPage(getContext());
            if (currentPage == START_QUERY_PAGE) {
                mAdapter.setData(items);
            } else if (currentPage > START_QUERY_PAGE) {
                mAdapter.addData(items);
            }
        }
    }

    @Override
    public void toDetail(NasaMediaItem item) {
        Log.i(TAG, "toDetail: " + item.getData().get(0).getMediaType());
        Intent intent = new Intent(getContext(), NMIDetailActivity.class);
        intent.putExtra(NASA_MEDIA_ITEM, item);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onLoadMore() {
        int currentPage = QueryPreference.getQueryPage(getContext());
        mPresenter.updateList(
                QueryPreference.getSearchQuery(getContext()),
                ++currentPage,
                this);
    }
}
