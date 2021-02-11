package com.anadolstudio.nasalibrary.presenter.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.anadolstudio.nasalibrary.repository.NasaMediaItem;

import java.util.List;

public class NMIDiffUtilCallback extends DiffUtil.Callback {
    private final List<NasaMediaItem> oldList;
    private final List<NasaMediaItem> newList;

    public NMIDiffUtilCallback(List<NasaMediaItem> oldList, List<NasaMediaItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        NasaMediaItem oldItem = oldList.get(oldItemPosition);
        NasaMediaItem newItem = newList.get(newItemPosition);

        String oldId = oldItem.getData().get(0).getNasaId();
        String newId = newItem.getData().get(0).getNasaId();

        return oldId.equals(newId);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        NasaMediaItem oldItem = oldList.get(oldItemPosition);
        NasaMediaItem newItem = newList.get(newItemPosition);
        return oldItem.equals(newItem);
    }
}
