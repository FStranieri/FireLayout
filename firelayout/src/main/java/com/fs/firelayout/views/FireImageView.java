package com.fs.firelayout.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.fs.firelayout.FireView;
import com.fs.firelayout.utils.FireViewUtils;

import java.util.HashMap;

/**
 * Created by Francesco on 01/02/17.
 */

public class FireImageView extends FireView {

    public FireImageView(Context context, HashMap<String, Object> map) {
        init(context, map);
    }

    @Override
    public View generateView(Context mContext) {
        ImageView imageView = new ImageView(mContext);

        loadImage(imageView, mContext);

        imageView.setAdjustViewBounds(FireViewUtils.getValue(attributesMap.get("adjustViewBounds"), false));

        String colorFilter = FireViewUtils.getValue(attributesMap.get("colorFilter"), "");

        if (!TextUtils.isEmpty(colorFilter))
            imageView.setColorFilter(Color.parseColor(colorFilter));

        return imageView;
    }

    private void loadImage(final ImageView imageView, Context mContext) {
        int srcId = FireViewUtils.getResourceId(mContext, attributesMap.get("src"), "", "drawable");

        if (srcId != -1) {
            imageView.setImageResource(srcId);
            imageView.setScaleType(getScaleType());
            imageView.setMaxWidth(FireViewUtils.getValue(attributesMap.get("maxWidth"), 0).intValue());
            imageView.setMaxHeight(FireViewUtils.getValue(attributesMap.get("maxHeight"), 0).intValue());
        } else {
            String srcUrl = FireViewUtils.getValue(attributesMap.get("srcUrl"), "");

            if (!TextUtils.isEmpty(srcUrl)) {
                final int srcErrorId = FireViewUtils.getResourceId(mContext, attributesMap.get("srcError"), "", "drawable");

                ImageRequest imageRequest = new ImageRequest(srcUrl,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap bitmap) {
                                imageView.setImageBitmap(bitmap);
                            }
                        }, FireViewUtils.getValue(attributesMap.get("maxWidth"), 0).intValue(), FireViewUtils.getValue(attributesMap.get("maxHeight"), 0).intValue(), getScaleType(), null,
                        new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                if (srcErrorId != -1)
                                    imageView.setImageResource(srcErrorId);
                            }
                        });

                Volley.newRequestQueue(mContext.getApplicationContext()).add(imageRequest);
            }
        }
    }

    private ImageView.ScaleType getScaleType() {
        String scale = FireViewUtils.getValue(attributesMap.get("scaleType"), "CENTER_INSIDE");

        ImageView.ScaleType scaleType = ImageView.ScaleType.valueOf(scale.toUpperCase());

        return scaleType;
    }
}
