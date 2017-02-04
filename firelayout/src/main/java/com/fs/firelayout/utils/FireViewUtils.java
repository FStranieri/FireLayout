package com.fs.firelayout.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.HashMap;

/**
 * Created by Francesco on 04/02/17.
 */

public class FireViewUtils {

    public static Number getValue(Object value, int def) {
        if (value != null && value instanceof Number)
            return (Number) value;

        return def;
    }

    public static Boolean getValue(Object value, boolean def) {
        if (value != null && value instanceof Boolean)
            return (Boolean) value;

        return def;
    }

    public static String getValue(Object value, String def) {
        if (value != null && value instanceof String)
            return (String) value;

        return def;
    }

    public static int getResourceId(Context mContext, Object value, String def, String path) {
        Resources resources = mContext.getResources();

        String resource = getValue(value, def);
        int resId = -1;

        if (!TextUtils.isEmpty(resource))
            resId = resources.getIdentifier(resource, path, mContext.getPackageName());

        return resId;
    }

    public static void setLayoutParamsForParent(ViewGroup parent, View child, HashMap<String, Object> childAttributes) {
        ViewGroup.MarginLayoutParams oldLP = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

        if (parent instanceof RelativeLayout) {
            RelativeLayout.LayoutParams newLP = new RelativeLayout.LayoutParams(oldLP.width, oldLP.height);
            newLP.setMargins(oldLP.leftMargin, oldLP.topMargin, oldLP.rightMargin, oldLP.bottomMargin);
            checkRelativeRule(newLP, childAttributes);
            child.setLayoutParams(newLP);
        }
    }

    private static void checkRelativeRule(RelativeLayout.LayoutParams layoutParams, HashMap<String, Object> attr) {
        if(getValue(attr.get("align_parent_left"), false))
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        if(getValue(attr.get("align_parent_top"), false))
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        if(getValue(attr.get("align_parent_right"), false))
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        if(getValue(attr.get("align_parent_bottom"), false))
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        if(getValue(attr.get("align_parent_start"), false))
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        if(getValue(attr.get("align_parent_end"), false))
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        if(getValue(attr.get("center_in_parent"), false))
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        if(getValue(attr.get("center_horizontal"), false))
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        if(getValue(attr.get("center_vertical"), false))
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);

        //TODO: MANAGE WITH ABOVE, BELOW, LEFT_OF AND RIGHT_OF. THE PROBLEM IS THE VIEW ID MANAGEMENT.
        //the following code doesn't work if applied on index < last_index
        /*String aboveTag = getValue(attr.get("above"), "");
        if(!TextUtils.isEmpty(aboveTag))
        {
            View view = parent.findViewWithTag(aboveTag);
            if(view != null)
            {
                layoutParams.addRule(RelativeLayout.ABOVE, view.getId());
            }
        }

        String belowTag = getValue(attr.get("below"), "");
        if(!TextUtils.isEmpty(aboveTag))
        {
            View view = parent.findViewWithTag(belowTag);
            if(view != null)
            {
                layoutParams.addRule(RelativeLayout.BELOW, view.getId());
            }
        }

        String leftTag = getValue(attr.get("left_of"), "");
        if(!TextUtils.isEmpty(aboveTag))
        {
            View view = parent.findViewWithTag(leftTag);
            if(view != null)
            {
                layoutParams.addRule(RelativeLayout.LEFT_OF, view.getId());
            }
        }

        String rightTag = getValue(attr.get("right_of"), "");
        if(!TextUtils.isEmpty(aboveTag))
        {
            View view = parent.findViewWithTag(rightTag);
            if(view != null)
            {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, view.getId());
            }
        }*/
    }
}
