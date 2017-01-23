package com.fs.firelayout.viewgroups;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.fs.firelayout.FireView;

import java.util.HashMap;

/**
 * Created by Francesco on 22/01/17.
 */

public class FireRelativeLayout extends FireView {

    public FireRelativeLayout(Context context, HashMap<String, Object> map) {
        init(context, map);
    }

    @Override
    public View generateView(Context mContext) {
        RelativeLayout relativeLayout = new RelativeLayout(mContext);

        relativeLayout.setGravity(getGravity("gravity"));

        return relativeLayout;
    }
}
