package com.example.mediaapp.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mediaapp.R;
import com.example.mediaapp.activity.fragment.NewsFragment;

public class MediaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new NewsFragment(), "SOMETAG").
                    commit();

        }
    }

}



