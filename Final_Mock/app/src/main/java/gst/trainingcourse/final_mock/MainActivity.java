package gst.trainingcourse.final_mock;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import gst.trainingcourse.final_mock.adapter.PageAdapter;


public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private PageAdapter adapter;
    private TabItem tabChats, tabStatus, tabCalls, tabPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        mTabLayout = findViewById(R.id.tablayout);
        tabChats = findViewById(R.id.tabChats);
        tabStatus = findViewById(R.id.tabStatus);
        tabCalls = findViewById(R.id.tabCalls);
        tabPhoto = findViewById(R.id.tabPhoto);
        mViewpager = findViewById(R.id.viewPager);

        adapter = new PageAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewpager.setAdapter(adapter);
        mTabLayout.addOnTabSelectedListener(mOnTabSelect);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


    }

    private TabLayout.OnTabSelectedListener mOnTabSelect = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewpager.setCurrentItem(tab.getPosition());
            if (tab.getPosition() == 1) {
                if (checkPermision(MainActivity.this)) {
                    Toast.makeText(getApplicationContext(), checkPermision(MainActivity.this) + "", Toast.LENGTH_SHORT).show();
                    ITemVideo mItemVideo = new ITemVideo();
                    mItemVideo.parseAllVideo(MainActivity.this);
                }
                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        R.color.colorAccent));
                mTabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        R.color.colorAccent));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorAccent));
                }
            } else if (tab.getPosition() == 2) {


                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        android.R.color.darker_gray));
                mTabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        android.R.color.darker_gray));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                } else if (tab.getPosition() == 3) {
                    if (checkPermision(MainActivity.this)) {
                        Toast.makeText(getApplicationContext(), checkPermision(MainActivity.this) + "", Toast.LENGTH_SHORT).show();
                    }

                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                    mTabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            android.R.color.darker_gray));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                android.R.color.darker_gray));
                    }
                }

            } else {
                toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        R.color.colorPrimary));
                mTabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                        R.color.colorPrimary));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                            R.color.colorPrimaryDark));
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

    private boolean checkPermision(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("Request permission", MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private void showDialog(final String msg, final Context context,
                            final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{permission},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


}
