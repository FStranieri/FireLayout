package com.fs.firelayout.utils;

import com.fs.firelayout.FireView;

public enum FireViewGenerator {
    FireTextView("textview", com.fs.firelayout.views.FireTextView.class, false),
    FireEditText("edittext", com.fs.firelayout.views.FireEditText.class, false),
    FireButton("button", com.fs.firelayout.views.FireButton.class, false),
    FireImageView("imageview", com.fs.firelayout.views.FireImageView.class, false),
    FireLinearLayout("linearlayout", com.fs.firelayout.viewgroups.FireLinearLayout.class, true),
    FireRelativeLayout("relativelayout", com.fs.firelayout.viewgroups.FireRelativeLayout.class, true);

    String name;
    public Class<? extends FireView> viewClass;
    public boolean isViewGroup;

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