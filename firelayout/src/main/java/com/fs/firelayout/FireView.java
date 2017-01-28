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
    protected HashMap<String, Object> attributesMap;
    View view;

    public void init(Context context, HashMap<String, Object> map) {
        this.attributesMap = map;
        view = generateView(context);

        resolveLayoutParams();

        checkTag();
        checkPadding();

        String color = getValue("background", null);
        if (color != null)
            view.setBackgroundColor(Color.parseColor(color));

        view.setEnabled(getValue("enable", true));

        view.setVisibility(getVisibility());
    }

    private void checkTag() {
        String tag = getValue("tag", "");

        if (!TextUtils.isEmpty(tag))
            view.setTag(tag);
    }

    private void checkPadding() {
        if (attributesMap.get("padding") != null) {
            int padding = getValue("padding", 0).intValue();
            view.setPadding(padding, padding, padding, padding);
        } else
            view.setPadding(getValue("padding_left", 0).intValue(), getValue("padding_top", 0).intValue(), getValue("padding_right", 0).intValue(), getValue("padding_bottom", 0).intValue());
    }

    private void resolveLayoutParams() {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(getSize(attributesMap.get("layout_width")), getSize(attributesMap.get("layout_height")));

        if (attributesMap.get("margin") != null) {
            int margin = getValue("margin", 0).intValue();
            marginLayoutParams.setMargins(margin, margin, margin, margin);
        } else
            marginLayoutParams.setMargins(getValue("margin_left", 0).intValue(), getValue("margin_top", 0).intValue(), getValue("margin_right", 0).intValue(), getValue("margin_bottom", 0).intValue());

        view.setLayoutParams(marginLayoutParams);
    }

    private int getSize(Object size) {
        if (size != null) {
            if (size instanceof Integer)
                return (int) size;
            else if (size instanceof String) {
                switch ((String) size) {
                    case "wrap_content":
                        return ViewGroup.LayoutParams.WRAP_CONTENT;
                    case "match_parent":
                        return ViewGroup.LayoutParams.MATCH_PARENT;
                }
            }
        }

        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    int getVisibility() {
        Object visibility = attributesMap.get("visibility");
        if (visibility != null && visibility instanceof String && !TextUtils.isEmpty((String) visibility)) {
            switch ((String) visibility) {
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
        Object gravity = attributesMap.get(key);
        if (gravity != null && gravity instanceof String && !TextUtils.isEmpty((String) gravity)) {
            switch ((String) gravity) {
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

    protected Number getValue(String key, int def) {
        Object b;
        if ((b = attributesMap.get(key)) != null && b instanceof Number)
            return (Number) b;

        return def;
    }

    protected Boolean getValue(String key, boolean def) {
        Object b;
        if ((b = attributesMap.get(key)) != null && b instanceof Boolean)
            return (Boolean) b;

        return def;
    }

    protected String getValue(String key, String def) {
        Object s;
        if ((s = attributesMap.get(key)) != null && s instanceof String)
            return (String) s;

        return def;
    }

    public void setEventsListener(final FireLayout.EventsListener listener) {
        if (getValue("onClick", false))
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFireLayoutChildClicked(view);
                }
            });

        if (getValue("onLongClick", false))
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onFireLayoutChildLongClicked(view);
                    return true;
                }
            });
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

        public static FireViewGenerator getFireView(String name) {
            for (FireViewGenerator fireViewGenerator : values()) {
                if (fireViewGenerator.name.equals(name.toLowerCase()))
                    return fireViewGenerator;
            }

            return null;
        }
    }
}
