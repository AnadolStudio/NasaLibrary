package com.anadolstudio.nasalibrary.view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SimpleFragment extends Fragment {

    private LoadingView mLoadingView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    protected void showLoadingDialog() {
        FragmentManager fm = getFragmentManager();
        if (fm == null) return;

        mLoadingView = LoadingDialog.view(getFragmentManager());
        mLoadingView.showLoadingIndicator();
    }

    protected void hideLoadingDialog() {
        if (mLoadingView != null) mLoadingView.hideLoadingIndicator();
    }

}
