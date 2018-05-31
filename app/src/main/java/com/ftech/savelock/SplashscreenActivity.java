package com.ftech.savelock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftech.savelock.utils.Prefs;

import java.util.Timer;

/**
 * Created by Frederick on 1/24/2018.
 */

public class SplashscreenActivity extends AppCompatActivity  {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private TextView mLoadingTxt,mTitle;
    private ImageView mLogo;
    private Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mLogo = (ImageView)findViewById(R.id.logo);
        mTitle = (TextView)findViewById(R.id.title);
        mLoadingTxt = (TextView)findViewById(R.id.loadingLabel);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        // Checking for first time launch - before calling setContentView()
        prefs = new Prefs(this,MODE_PRIVATE);
        //
        //loading and setting animation to views
        final Animation anim1 = AnimationUtils.loadAnimation(SplashscreenActivity.this,R.anim.move_right_to_left);
        final Animation anim2 = AnimationUtils.loadAnimation(SplashscreenActivity.this,R.anim.move_from_left_to_right);
        final Animation anim3 = AnimationUtils.loadAnimation(SplashscreenActivity.this,R.anim.fadein_spalshscreen);
        mLogo.startAnimation(anim1);
        mTitle.startAnimation(anim2);
        mLoadingTxt.startAnimation(anim3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!prefs.isNotFirstTimeLaunch()){
                    //prefs.setFirstTimeLaunch(true);
                    Intent i = new Intent(SplashscreenActivity.this,IntroActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashscreenActivity.this,HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
        /*final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (prefs.isNotFirstTimeLaunch()) {
                    Intent intent = new Intent(SplashscreenActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashscreenActivity.this,IntroActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.postDelayed(update,SPLASH_TIME_OUT);*/
        /*timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                mLogo.startAnimation(anim1);
                mTitle.startAnimation(anim2);
                mLoadingTxt.startAnimation(anim3);
                //handler.post(update);
            }
        }, SPLASH_TIME_OUT,DELAY_MS);*/
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }
}
