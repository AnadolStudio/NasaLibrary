package com.anadolstudio.nasalibrary.presenter.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.anadolstudio.nasalibrary.R;
import com.anadolstudio.nasalibrary.repository.ImageMedia;
import com.anadolstudio.nasalibrary.repository.NMILink;
import com.anadolstudio.nasalibrary.repository.NasaMediaItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import static com.anadolstudio.nasalibrary.repository.ImageMedia.LARGE;


public class DownloadImage {
    private static int placeholder = R.drawable.nasa_loading;

    public static void download(ImageView imageView, NasaMediaItem item) {
        List<NMILink> links = item.getLinks();

        if (links != null && links.size() > 0 && links.get(0) != null) {
            download(imageView, item.getLinks().get(0).getHref());
        }
    }

    public static void downloadLargeImage(ImageView imageView, List<ImageMedia> list) {
        // orig > large> medium > small > thumb > metadata
        ImageMedia media = list.get(0);
        if (list.get(1).getHref().contains(LARGE)) {
            media = list.get(1);
        }

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.i("TAG", "onBitmapLoaded");
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                imageView.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        imageView.setTag(target);
        Picasso.get()
                .load(validateLink(media.getHref()))
                .error(placeholder)
                .priority(Picasso.Priority.HIGH)
                .noFade()
                .into(target);
    }

    public static void download(ImageView imageView, String link) {
        Log.i("TAG", "download: " + link);

        Picasso.get()
                .load(validateLink(link))
                .error(placeholder)
                .placeholder(placeholder)
                .fit()
                .centerCrop()
                .noFade()
                .into(imageView);
    }

    private static String validateLink(String link) {
        if (!link.contains("https")) {
            link = link.replace("http", "https");
        }
        Log.i("TAG", "validateLink: " + link);
        return link;
    }
}
