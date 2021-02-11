package com.anadolstudio.nasalibrary.presenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.anadolstudio.nasalibrary.R;
import com.anadolstudio.nasalibrary.repository.NasaMediaItem;

import java.util.List;

import static com.anadolstudio.nasalibrary.MainContract.View.Detailable;

public class NMIListAdapter extends RecyclerView.Adapter<NMIViewHolder> {
    public static final String TAG = NMIListAdapter.class.getName();

    private List<NasaMediaItem> mList;
    private Detailable<NasaMediaItem> mDetailable;
    private ILoadMore mLoadMore;
    private boolean isLoading;

    public NMIListAdapter(List<NasaMediaItem> list, Detailable<NasaMediaItem> detailable, @Nullable ILoadMore loadMore) {
        mList = list;
        mDetailable = detailable;
        isLoading = false;
        mLoadMore = loadMore;
    }

    @NonNull
    @Override
    public NMIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_media_item, parent, false);
        return new NMIViewHolder(view, mDetailable);
    }

    @Override
    public void onBindViewHolder(@NonNull NMIViewHolder holder, int position) {
        holder.onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull NMIViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getAdapterPosition();
        if (position == getItemCount() - 1 && !isLoading) {
            isLoading = true;

            if (mLoadMore != null) {
                mLoadMore.onLoadMore();
            }

            isLoading = false;
        }
    }

    public List<NasaMediaItem> getData() {
        return mList;
    }

    public void setData(List<NasaMediaItem> list) {
        NMIDiffUtilCallback NMIDiffUtilCallback =
                new NMIDiffUtilCallback(mList, list);
        DiffUtil.DiffResult hotelDiffResult = DiffUtil.calculateDiff(NMIDiffUtilCallback, false);

        mList = list;
        hotelDiffResult.dispatchUpdatesTo(this);
    }

    public void addData(List<NasaMediaItem> list) {
        mList.addAll(list);
        notifyItemRangeInserted(mList.size() - list.size(), list.size());
    }
}
