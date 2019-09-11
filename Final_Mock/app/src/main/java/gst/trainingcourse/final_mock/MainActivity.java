package gst.trainingcourse.final_mock;

import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import gst.trainingcourse.final_mock.adapter.ViewPagerApdater;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabItem mTabApp, mTabMusic, mTabImage, mTabVideo;
    private ViewPagerApdater mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        mTabLayout = findViewById(R.id.tablayout);
        mTabApp = findViewById(R.id.tabapp);
        mTabMusic = findViewById(R.id.tabmusic);
        mTabImage = findViewById(R.id.tabimage);
        mTabVideo = findViewById(R.id.tabvideo);
        mViewPager = findViewById(R.id.viewPager);
        mAdapter = new ViewPagerApdater(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.addOnTabSelectedListener(mOnTabListener);
        mViewPager.addOnAdapterChangeListener((ViewPager.OnAdapterChangeListener) mOnTabListener);
    }

    private TabLayout.TabLayoutOnPageChangeListener mOnPageChangeLister = new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);

    private final TabLayout.OnTabSelectedListener mOnTabListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
            if (tab.getPosition() == 1) {
                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        R.color.colorAccent));
                mTabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        R.color.colorAccent));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                }

            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }

    };


}
