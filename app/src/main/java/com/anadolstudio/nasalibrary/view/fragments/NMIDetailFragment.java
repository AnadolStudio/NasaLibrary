package com.anadolstudio.nasalibrary.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anadolstudio.nasalibrary.MainContract;
import com.anadolstudio.nasalibrary.R;
import com.anadolstudio.nasalibrary.databinding.FragmentNmiDetailBinding;
import com.anadolstudio.nasalibrary.presenter.InternetConnection;
import com.anadolstudio.nasalibrary.presenter.MainPresenter;
import com.anadolstudio.nasalibrary.presenter.TimeConverter;
import com.anadolstudio.nasalibrary.presenter.adapter.DownloadImage;
import com.anadolstudio.nasalibrary.repository.Datum;
import com.anadolstudio.nasalibrary.repository.ImageMedia;
import com.anadolstudio.nasalibrary.repository.NasaMediaItem;

import java.util.List;

import static com.anadolstudio.nasalibrary.presenter.TimeConverter.FULL_FORMAT;
import static com.anadolstudio.nasalibrary.presenter.TimeConverter.MONTH_DAY_YEAR;
import static com.anadolstudio.nasalibrary.repository.NasaMediaItem.NASA_MEDIA_ITEM;

public class NMIDetailFragment extends SimpleFragment implements MainContract.View.DetailView<ImageMedia> {
    public static final String TAG = NMIDetailFragment.class.getSimpleName();
    private FragmentNmiDetailBinding mBinding;
    private MainPresenter mPresenter;

    public static NMIDetailFragment newInstance(NasaMediaItem item) {
        Bundle args = new Bundle();
        args.putParcelable(NASA_MEDIA_ITEM, item);
        NMIDetailFragment fragment = new NMIDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentNmiDetailBinding.inflate(inflater);
        setup();
        return mBinding.getRoot();
    }

    private void setup() {
        if (getArguments() == null
                || getArguments().getParcelable(NASA_MEDIA_ITEM) == null) {
            Log.e(TAG, "setup: argument is null");
            showFailUploadFileToast();
            return;
        }

        NasaMediaItem item = getArguments().getParcelable(NASA_MEDIA_ITEM);
        if (item.getData() == null || item.getData().size() == 0) {
            Log.e(TAG, "setup: data is null");
            showFailUploadFileToast();
            return;
        }

        Datum data = item.getData().get(0);

        mBinding.titleText.setText(data.getTitle());
        mBinding.centerText.setText(data.getCenter());
        mBinding.timeText.setText(TimeConverter.convertToNewFormat(
                data.getDateCreated(), FULL_FORMAT, MONTH_DAY_YEAR));
        mBinding.descriptionText.setText(data.getDescription());
        DownloadImage.download(mBinding.imageCardView.image, item);
        mPresenter = new MainPresenter(getContext());
        mPresenter.updateDetail(data.getNasaId(), this);
    }

    @Override
    public boolean onPreShow() {
        boolean already;
        already = InternetConnection.isNetworkAvailable(getContext());

        return already;
    }

    @Override
    public void onPostShow() {
    }

    @Override
    public void show(List<ImageMedia> list) {

        if (list != null && list.size() != 0) {
            DownloadImage.downloadLargeImage(mBinding.imageCardView.image, list);
        }
    }

    private void showFailUploadFileToast(){
        Toast.makeText(getContext(), R.string.failed_to_upload_file, Toast.LENGTH_SHORT).show();
    }
}
