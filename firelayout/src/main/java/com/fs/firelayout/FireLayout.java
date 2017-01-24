package com.fs.firelayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by Francesco on 22/01/17.
 */

public class FireLayout extends CoordinatorLayout {

    public FireLayout(Context context) {
        super(context);
    }

    public FireLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FireLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(FirebaseDatabase firebaseDatabase) {
        if (getId() != NO_ID) {
            String id = getResources().getResourceName(getId());
            DatabaseReference databaseReference = firebaseDatabase.getReference(id.substring(id.lastIndexOf("/")));

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null && dataSnapshot.getValue() instanceof HashMap) {
                        removeAllViews();
                        checkLayout(FireLayout.this, (HashMap<String, Object>) dataSnapshot.getValue());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void checkLayout(ViewGroup parent, HashMap<String, Object> map) {
        if (map != null) {
            for (String key : map.keySet()) {
                Object childMap = map.get(key);
                if (childMap != null && childMap instanceof HashMap) {
                    View view = getRealView(key, (HashMap<String, Object>) childMap);

                    if (view != null)
                        parent.addView(view);
                }
            }
        }
    }

    private View getRealView(String name, HashMap<String, Object> map) {
        View view = null;

        FireView.FireViewGenerator fireViewGenerator = FireView.FireViewGenerator.getFireView(name);

        if (fireViewGenerator != null) {
            try {
                view = (fireViewGenerator.viewClass.getConstructor(Context.class, HashMap.class).newInstance(getContext(), map)).getView();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (fireViewGenerator.isViewGroup && map.get("children") != null && map.get("children") instanceof HashMap)
                checkLayout((ViewGroup) view, (HashMap<String, Object>) map.get("children"));
        }

        return view;
    }
}
