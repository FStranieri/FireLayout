package com.fs.firelayout.views;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.fs.firelayout.FireView;
import com.fs.firelayout.utils.FireViewUtils;

import java.util.HashMap;

/**
 * Created by Francesco on 22/01/17.
 */

public class FireEditText extends FireView {

    public FireEditText(Context context, HashMap<String, Object> map) {
        init(context, map);
    }

    @Override
    public View generateView(Context mContext) {
        EditText editText = new EditText(mContext);

        editText.setText(FireViewUtils.getValue(attributesMap.get("text"), ""));

        editText.setHint(FireViewUtils.getValue(attributesMap.get("hint"), ""));

        String textColor = FireViewUtils.getValue(attributesMap.get("textColor"), null);
        if (!TextUtils.isEmpty(textColor))
            editText.setTextColor(Color.parseColor(textColor));

        float textSize = FireViewUtils.getValue(attributesMap.get("textSize"), -1).floatValue();
        if (textSize >= 0f)
            editText.setTextSize(textSize);

        editText.setGravity(getGravity("gravity"));

        return editText;
    }
}
