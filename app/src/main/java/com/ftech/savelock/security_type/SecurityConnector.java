package com.ftech.savelock.security_type;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.ftech.savelock.HomeActivity;
import com.ftech.savelock.R;
import com.ftech.savelock.utils.Constants;

import java.util.List;

/**
 * Created by HP on 1/24/2018.
 */

public class SecurityConnector extends AppCompatActivity implements View.OnClickListener {
    private AppCompatImageButton closePatternBgBtn,next;
    //initiating pattern variable
    private AppBarLayout appBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_type);
        appBar = (AppBarLayout)findViewById(R.id.appBar);
        String navHeaderColor = getIntent().getStringExtra(Constants.NAV_HEADER_COLOR_TAG);
        appBar.setBackgroundColor(Color.parseColor(navHeaderColor));
    }

    @Override
    public void onClick(View v) {
        
    }
}
