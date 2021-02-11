package com.anadolstudio.nasalibrary.view.activities;

import androidx.fragment.app.Fragment;

import com.anadolstudio.nasalibrary.view.fragments.NMIListFragment;

public class NMIListActivity extends SimpleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return NMIListFragment.newInstance();
    }

}