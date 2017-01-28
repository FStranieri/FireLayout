package com.fs.firelayout.viewgroups;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.fs.firelayout.FireView;

import java.util.HashMap;

/**
 * Created by Francesco on 22/01/17.
 */

public class FireLinearLayout extends FireView {

    public FireLinearLayout(Context context, HashMap<String, Object> map) {
        init(context, map);
    }

    @Override
    public View generateView(Context mContext) {
        LinearLayout linearLayout = new LinearLayout(mContext);

        linearLayout.setOrientation(getOrientation());

        linearLayout.setGravity(getGravity("gravity"));

        return linearLayout;
    }

    private int getOrientation()
    {
        Object orientation = attributesMap.get("orientation");
        if(orientation != null && orientation instanceof String && !TextUtils.isEmpty((String) orientation))
        {
            String o = (String) orientation;

            if(o.equals("vertical"))
                return LinearLayout.VERTICAL;
            else if(o.equals("horizontal"))
                return LinearLayout.HORIZONTAL;
        }

        return LinearLayout.VERTICAL;
    }
}
