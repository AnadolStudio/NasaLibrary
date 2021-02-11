package com.anadolstudio.nasalibrary.view.activities;

import androidx.fragment.app.Fragment;

import com.anadolstudio.nasalibrary.repository.NasaMediaItem;
import com.anadolstudio.nasalibrary.view.fragments.NMIDetailFragment;
import com.anadolstudio.nasalibrary.view.fragments.NMIListFragment;

import static com.anadolstudio.nasalibrary.repository.NasaMediaItem.NASA_MEDIA_ITEM;

public class NMIDetailActivity extends SimpleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        NasaMediaItem item = getIntent().getParcelableExtra(NASA_MEDIA_ITEM);
        return NMIDetailFragment.newInstance(item);
    }

}