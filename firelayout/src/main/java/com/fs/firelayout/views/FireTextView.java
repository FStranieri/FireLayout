package com.fs.firelayout.views;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.fs.firelayout.FireView;
import com.fs.firelayout.utils.FireViewUtils;

import java.util.HashMap;

/**
 * Created by Francesco on 22/01/17.
 */

public class FireTextView extends FireView {

    public FireTextView(Context context, HashMap<String, Object> map) {
        init(context, map);
    }

    @Override
    public View generateView(Context mContext) {
        TextView textView = new TextView(mContext);

        textView.setText(FireViewUtils.getValue(attributesMap.get("text"), ""));

        String textColor = FireViewUtils.getValue(attributesMap.get("textColor"), null);
        if (!TextUtils.isEmpty(textColor))
            textView.setTextColor(Color.parseColor(textColor));

        float textSize = FireViewUtils.getValue(attributesMap.get("textSize"), -1).floatValue();
        if (textSize >= 0f)
            textView.setTextSize(textSize);

        textView.setGravity(getGravity("gravity"));

        return textView;
    }
}
