package com.fs.firelayout;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by Francesco on 22/01/17.
 */

public abstract class FireView {
    protected HashMap<String, Object> map;
    View view;

    public void init(Context context, HashMap<String, Object> map) {
        this.map = map;
        view = generateView(context);

        resolveLayoutParams();

        view.setPadding(getValue("padding_left", 0), getValue("padding_top", 0), getValue("padding_right", 0), getValue("padding_bottom", 0));

        String color = getValue("background", null);
        if (color != null)
            view.setBackgroundColor(Color.parseColor(color));


        view.setEnabled(getValue("enable", true));

        view.setVisibility(getVisibility());
    }

    private void resolveLayoutParams()
    {
        int width = ViewGroup.LayoutParams.WRAP_CONTENT, height = ViewGroup.LayoutParams.WRAP_CONTENT;
        Object w, h;

        if ((w = map.get("layout_width")) != null && w instanceof Integer)
            width = (Integer) w;

        if ((h = map.get("layout_height")) != null && h instanceof Integer)
            height = (Integer) h;

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(width, height);
        marginLayoutParams.setMargins(getValue("margin_left", 0), getValue("margin_top", 0), getValue("margin_right", 0), getValue("margin_bottom", 0));

        view.setLayoutParams(marginLayoutParams);
    }

    int getVisibility() {
        Object visibility = map.get("visibility");
        if(visibility != null && visibility instanceof String && !TextUtils.isEmpty((String) visibility))
        {
            switch ((String) visibility)
            {
                case "gone":
                    return View.GONE;
                case "invisible":
                    return View.INVISIBLE;
            }
        }

        return View.VISIBLE;
    }

    //TODO: MORE KINDS OF GRAVITY
    protected int getGravity(String key) {
        Object gravity = map.get(key);
        if(gravity != null && gravity instanceof String && !TextUtils.isEmpty((String) gravity))
        {
            switch ((String) gravity)
            {
                case "center":
                    return Gravity.CENTER;
                case "center_horizontal":
                    return Gravity.CENTER_HORIZONTAL;
                case "center_vertical":
                    return Gravity.CENTER_VERTICAL;
                case "top":
                    return Gravity.TOP;
                case "right":
                    return Gravity.RIGHT;
                case "bottom":
                    return Gravity.BOTTOM;
                case "left":
                    return Gravity.LEFT;
            }
        }

        return Gravity.NO_GRAVITY;
    }

    protected float getValue(String key, float def) {
        Object b;
        if ((b = map.get(key)) != null && b instanceof Float)
            return (float) b;

        return def;
    }

    protected double getValue(String key, double def) {
        Object b;
        if ((b = map.get(key)) != null && b instanceof Double)
            return (double) b;

        return def;
    }

    protected int getValue(String key, int def) {
        Object b;
        if ((b = map.get(key)) != null && b instanceof Integer)
            return (int) b;

        return def;
    }

    protected Boolean getValue(String key, boolean def) {
        Object b;
        if ((b = map.get(key)) != null && b instanceof Boolean)
            return (Boolean) b;

        return def;
    }

    protected String getValue(String key, String def) {
        Object s;
        if ((s = map.get(key)) != null && s instanceof String)
            return (String) s;

        return def;
    }

    public abstract View generateView(Context mContext);

    public View getView() {
        return view;
    }

    public enum FireViewGenerator {
        FireTextView("textview", com.fs.firelayout.views.FireTextView.class, false),
        FireEditText("edittext", com.fs.firelayout.views.FireEditText.class, false),
        FireButton("button", com.fs.firelayout.views.FireButton.class, false),
        FireLinearLayout("linearlayout", com.fs.firelayout.viewgroups.FireLinearLayout.class, true),
        FireRelativeLayout("relativelayout", com.fs.firelayout.viewgroups.FireRelativeLayout.class, true);

        String name;
        Class<? extends FireView> viewClass;
        boolean isViewGroup;

        FireViewGenerator(String name, Class<? extends FireView> viewClass, boolean isViewGroup) {
            this.name = name;
            this.viewClass = viewClass;
            this.isViewGroup = isViewGroup;
        }

        public static FireViewGenerator getFireView(String name)
        {
            for(FireViewGenerator fireViewGenerator : values())
            {
                if(fireViewGenerator.name.equals(name.toLowerCase()))
                    return fireViewGenerator;
            }

            return null;
        }
    }
}
