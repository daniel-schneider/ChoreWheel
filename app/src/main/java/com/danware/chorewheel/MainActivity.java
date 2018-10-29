package com.danware.chorewheel;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ChoreFragment.OnFragmentInteractionListener {

    private ViewPager mPager;
    private ChorePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.fragment_pager);
        mPagerAdapter = new ChorePagerAdapter(getSupportFragmentManager());
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i("MainActivity", "fragment interaction");
    }
}
