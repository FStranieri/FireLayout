package com.fs.firelayout.views;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.fs.firelayout.FireView;

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

        button.setText(getValue("text", ""));

        String textColor = getValue("textColor", null);
        if (textColor != null)
            button.setTextColor(Color.parseColor(textColor));

        button.setGravity(getGravity("gravity"));

        return button;
    }
}
