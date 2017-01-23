package com.fs.firelayout.views;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;

import com.fs.firelayout.FireView;

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

        editText.setText(getValue("text", ""));

        editText.setHint(getValue("hint", ""));

        String textColor = getValue("textColor", null);
        if (textColor != null)
            editText.setTextColor(Color.parseColor(textColor));

        editText.setGravity(getGravity("gravity"));

        return editText;
    }
}
