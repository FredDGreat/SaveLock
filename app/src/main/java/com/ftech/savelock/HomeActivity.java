package com.ftech.savelock;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String SUBJECT = "SaveLock- ";
    // Create a spinners
    Spinner secTypeSpan,lockType;
    ArrayList<String> securityType = new ArrayList<String>();
    String[] spinnerItems = new String[]{
            "Password",
            "Pattern"
    };
    String[] lockTypeSpinnerItems = new String[]{
            "SaveLock",
            "Inbuilt lock system"
    };
    private RelativeLayout passwordBg;
    private CardView secTypeShadowBg;
    EditText email1;
    EditText email2;
    private EditText password;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.);
        /*Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.myanimation);
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        image.startAnimation(animation);*/
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);/*
        Intent i = new Intent(HomeActivity.this,ScreenLock1.class);
        startActivity(i);*/
        email1 = (EditText) findViewById(R.id.email1);
        email2 = (EditText) findViewById(R.id.email2);
        //password = (EditText) findViewById(R.id.password);
        //Instantiating the spinners
        secTypeSpan = (Spinner) findViewById(R.id.secType);
        lockType = (Spinner) findViewById(R.id.chooseLockSystem);
        passwordBg = (RelativeLayout)findViewById(R.id.passwordBg);
        secTypeShadowBg = (CardView)findViewById(R.id.secTypeId);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        // Create adapter for spinner
        final ArrayAdapter<CharSequence> secTypeAdapter = ArrayAdapter.createFromResource
                (this,R.array.security_type_array,R.layout.simple_item_for_spinner);
        final ArrayAdapter<CharSequence> lockTypeAdapter = ArrayAdapter.createFromResource
                (this,R.array.lock_type_array,R.layout.simple_item_for_spinner);
        // Dropdown layer style
        lockTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Attach secTypeAdapter to spinner
        //lockType.setTextColor(Color.parseColor("#000000"));
        lockType.setAdapter(new NothingSelectedSpinnerAdapter(
                lockTypeAdapter,
                R.layout.spinner_nothing_selected,
                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                this));
        lockType.setOnItemSelectedListener(this);
        secTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Attach secTypeAdapter to spinner
        secTypeSpan.setAdapter(new NothingSelectedSpinnerAdapter(
                secTypeAdapter,
                R.layout.spinner_nothing_selected,
                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                this));
        secTypeSpan.setOnItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void saveSettings(){
        Date now = new Date();
        DateFormat sdf;
        sdf = new SimpleDateFormat("EEE dd MMM yyyy 'Time:'hh:mm a ");
        String strDate = sdf.format(now);
        //String subject = textTo.getText().toString();
        String email1Str = email1.getText().toString();
        String email2Str = email2.getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ email1Str,email2Str});
        //email.putExtra(Intent.EXTRA_CC, new String[]{ email2Str});
        //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, SUBJECT+strDate);
        if(secTypeSpan.getSelectedItemPosition() == 0 && email1Str.trim().length()>0) {
            email.putExtra(Intent.EXTRA_TEXT, "Your password is: "+secTypeSpan.getSelectedItem().toString());
        }
        //need this to prompts email client only
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(HomeActivity.this,ConfigLockScreen.class);
        startActivity(i);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //((TextView) lockType.getSelectedView()).setTextColor(Color.parseColor("#99333333"));
        if(id == R.id.chooseLockSystem) {
            int index = parent.getSelectedItemPosition();
            //((TextView) secTypeSpan.getSelectedView()).setTextColor(Color.parseColor("#99333333"));
            if (index == 0) {
                secTypeShadowBg.setVisibility(View.GONE);
                passwordBg.setVisibility(View.GONE);
                startActivityForResult(new Intent(Settings.ACTION_SECURITY_SETTINGS), 0);
                //patternBg.setVisibility(View.GONE);
            }
            if (index == 1) {
                secTypeShadowBg.setVisibility(View.VISIBLE);
                passwordBg.setVisibility(View.GONE);
            }
            Toast.makeText(getBaseContext(),"position="+index,Toast.LENGTH_LONG).show();
        }
        if(id == R.id.secType){
            int index = parent.getSelectedItemPosition();
            if(index==0){
                //Copy snippet
                passwordBg.setVisibility(View.VISIBLE);
                Intent i = new Intent(this,ConfigLockScreen.class);
                startActivity(i);
            }
            if(index==1){
                passwordBg.setVisibility(View.GONE);
            }
            Toast.makeText(getBaseContext(),"position="+index,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //lockType.setSelection(0);
        //((TextView)parent.getChildAt(0)).setTextColor(0x808080);
    }
}
