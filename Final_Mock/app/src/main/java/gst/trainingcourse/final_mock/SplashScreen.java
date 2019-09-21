package gst.trainingcourse.final_mock;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    private Handler mHandler;

    private Runnable mRunnable;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        mImageView = findViewById(R.id.img_splash);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageView, View.TRANSLATION_Y, -100,0,-50);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        mRunnable = () -> startActivity(new Intent(getApplicationContext(), MainActivity.class));

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable,500);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mHandler!=null&&mRunnable!=null){
            mHandler.removeCallbacks(mRunnable);
        }
        finish();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
