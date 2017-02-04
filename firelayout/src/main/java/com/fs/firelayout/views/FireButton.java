package com.fs.firelayout.views;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.fs.firelayout.FireView;
import com.fs.firelayout.utils.FireViewUtils;

import java.util.HashMap;

/**
 * Created by Francesco on 22/01/17.
 */

public class FireButton extends FireView {

    public FireButton(Context context, HashMap<String, Object> map) {
        init(context, map);
    }

    @Override
    public View generateView(Context mContext) {
        Button button = new Button(mContext);

        button.setText(FireViewUtils.getValue(attributesMap.get("text"), ""));

        String textColor = FireViewUtils.getValue(attributesMap.get("textColor"), null);
        if (!TextUtils.isEmpty(textColor))
            button.setTextColor(Color.parseColor(textColor));

        float textSize = FireViewUtils.getValue(attributesMap.get("textSize"), -1).floatValue();
        if (textSize >= 0f)
            button.setTextSize(textSize);

        button.setGravity(getGravity("gravity"));

        return button;
    }
}
