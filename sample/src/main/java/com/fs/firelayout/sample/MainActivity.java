package com.fs.firelayout.sample;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.fs.firelayout.FireLayout;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements FireLayout.EventsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FireLayout fireLayout = (FireLayout) findViewById(R.id.fire1);

        fireLayout.setEventsListener(this);
        fireLayout.init(FirebaseDatabase.getInstance());
    }

    @Override
    public void onFireLayoutChildClicked(View view) {
        //if you add a tag in your view, you can do some logic to identify the view
        Snackbar
                .make(view, view.getTag() != null ? view.getTag() + " - Clicked!" : "Clicked!", Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onFireLayoutChildLongClicked(View view) {
        //if you add a tag in your view, you can do some logic to identify the view
        Snackbar
                .make(view, view.getTag() != null ? view.getTag() + " - LongClicked!" : "LongClicked!", Snackbar.LENGTH_SHORT)
                .show();
    }
}
