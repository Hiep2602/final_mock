package gst.trainingcourse.final_mock;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import gst.trainingcourse.final_mock.adapter.PageAdapter;


public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private PageAdapter adapter;
    FloatingActionButton fab;
    private BluetoothAdapter bluetoothAdapter;
    private TabItem tabChats, tabStatus, tabCalls, tabPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermision(MainActivity.this);
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
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(mFloatBut);
        adapter = new PageAdapter(getSupportFragmentManager(), mTabLayout.getTabCount(), MainActivity.this);
        mViewpager.setAdapter(adapter);
        mTabLayout.addOnTabSelectedListener(mOnTabSelect);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


    }

    private final View.OnClickListener mFloatBut = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    };

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                openBlueTooth();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @SuppressLint("MissingPermission")
    private void openBlueTooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        } else if (!bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
            IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReciver, intentFilter);


        }

    }

    public boolean checkPermision(Context context) {
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

    private final BroadcastReceiver mBroadcastReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String address = device.getAddress();
                Toast.makeText(MainActivity.this, deviceName + "\t" + address, Toast.LENGTH_SHORT).show();
            }
        }
    };

}
