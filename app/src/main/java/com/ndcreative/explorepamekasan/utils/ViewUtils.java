package com.ndcreative.explorepamekasan.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ndcreative.explorepamekasan.R;

/**
 * Created by Umar Fadil on 11,Oct,2019
 * ND Creative Solution
 * id.ndcreativesolution@gmail.com
 */

public class ViewUtils {

    public ViewUtils() {
    }

    public static void showImageToView(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.img_no_image)
                .error(R.drawable.img_no_image)
                .crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
