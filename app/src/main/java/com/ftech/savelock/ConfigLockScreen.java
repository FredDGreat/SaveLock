package com.ftech.savelock;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.patternlockview.utils.ResourceUtils;
import com.andrognito.rxpatternlockview.RxPatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import io.reactivex.functions.Consumer;

public class ConfigLockScreen extends AppCompatActivity
        implements View.OnClickListener {
    public static final String SUBJECT = "SaveLock- ";
    private AppCompatImageButton closePatternBgBtn,next;
    //initiating pattern variable
    private PatternLockView mPatternLockView;
    private RelativeLayout patternBg;
    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.move_in_from_right,R.anim.fadeout_a_bit);
        /*Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.myanimation);
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        image.startAnimation(animation);*/
        setContentView(R.layout.lock_screen);
        /*next = (AppCompatImageButton) findViewById(R.id.next);
        next.setOnClickListener(this);

        //
        mPatternLockView = (PatternLockView) findViewById(R.id.patter_lock_view);
        mPatternLockView.setDotCount(3);
        mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size));
        mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size));
        mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width));
        mPatternLockView.setAspectRatioEnabled(true);
        mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS);
        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
        mPatternLockView.setDotAnimationDuration(150);
        mPatternLockView.setPathEndAnimationDuration(100);
        mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.pattern_color));
        mPatternLockView.setInStealthMode(false);
        mPatternLockView.setTactileFeedbackEnabled(true);
        mPatternLockView.setInputEnabled(true);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);

        RxPatternLockView.patternComplete(mPatternLockView)
        .subscribe(new Consumer<PatternLockCompleteEvent>() {
            @Override
            public void accept(PatternLockCompleteEvent patternLockCompleteEvent) throws Exception {
                Log.d(getClass().getName(), "Complete: " + patternLockCompleteEvent.getPattern().toString());
            }
        });

        RxPatternLockView.patternChanges(mPatternLockView)
        .subscribe(new Consumer<PatternLockCompoundEvent>() {
            @Override
            public void accept(PatternLockCompoundEvent event) throws Exception {
                if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
                    Log.d(getClass().getName(), "Pattern drawing started");
                } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
                    Log.d(getClass().getName(), "Pattern progress: " +
                            PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
                    Log.d(getClass().getName(), "Pattern complete: " +
                            PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
                    Log.d(getClass().getName(), "Pattern has been cleared");
                }
            }
        });*/
    }
    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        /*if(v == closePatternBgBtn){
            patternBg.setVisibility(View.GONE);
        }*/
        Intent i = new Intent(ConfigLockScreen.this,HomeActivity.class);
        startActivity(i);
    }
}
