package com.tomlockapps.userbrowser.base.bindings;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by walczewski on 04.03.2018.
 */

public class GlideBindings {

    private final static Map<String, RequestOptionsProvider> stringToTransformMap = new HashMap<>();

    static {
        stringToTransformMap.put("circleCrop", RequestOptions::circleCropTransform);
        stringToTransformMap.put("centerCrop", RequestOptions::centerCropTransform);
    }

    private GlideBindings() {}

    @BindingAdapter(value = {"imageUrl", "imageTransformType"})
    public static void glideLoadUrl(ImageView imageView, String imageUrl, String imageTransformType) {
        RequestOptionsProvider provider = stringToTransformMap.get(imageTransformType);

        if(provider == null)
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        else
            Glide.with(imageView.getContext()).load(imageUrl).apply(provider.getRequestOption()).into(imageView);
    }

    public interface RequestOptionsProvider {
        RequestOptions getRequestOption();
    }

}
