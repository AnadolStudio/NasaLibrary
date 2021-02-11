package com.anadolstudio.nasalibrary.presenter.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anadolstudio.nasalibrary.MainContract.View.Detailable;
import com.anadolstudio.nasalibrary.databinding.NasaMediaItemBinding;
import com.anadolstudio.nasalibrary.repository.NasaMediaItem;


public class NMIViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private NasaMediaItemBinding mBinding;
    private NasaMediaItem item;
    private Detailable<NasaMediaItem> mDetailable;

    public NMIViewHolder(@NonNull View itemView, Detailable<NasaMediaItem> detailable) {
        super(itemView);
        mBinding = NasaMediaItemBinding.bind(itemView);
        mDetailable = detailable;
        itemView.setOnClickListener(this);
    }

    public void onBind(NasaMediaItem item) {
        this.item = item;
        mBinding.image.setTag(getAdapterPosition());
        DownloadImage.download(mBinding.image, item);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        if (position == RecyclerView.NO_POSITION) return;

        mDetailable.toDetail(item);
    }
}