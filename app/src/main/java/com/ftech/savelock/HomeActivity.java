package com.ftech.savelock;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ftech.savelock.adapters.RecyclerAdapter;
import com.ftech.savelock.data.ListData;
import com.ftech.savelock.utils.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String SUBJECT = "SaveLock- ";
    private static final String TAG = "HomeActivity";
    private AutoScrollViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private TabLayout mTabLayout;
    SwitchCompat reminderSwitch;
    public static TextView autoData;
    //for recycler view
    private List<ListData> mListData = new ArrayList<>();
    private RecyclerView recyclerView;
    RecyclerAdapter mAdapter;

    private Toolbar toolbar = null;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        /*Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
        R.anim.myanimation);
        ImageView image1 = (ImageView)findViewById(R.id.imageView1);
        image.startAnimation(animation);*/
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*reminderSwitch = (SwitchCompat) findViewById(R.id.timerSwitch);
        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //localData.setReminderStatus(isChecked);
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: true");
                    *//*NotificationScheduler.setReminder(MainActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                    ll_set_time.setAlpha(1f);*//*
                } else {
                    Log.d(TAG, "onCheckedChanged: false");
                    *//*NotificationScheduler.cancelReminder(MainActivity.this, AlarmReceiver.class);
                    ll_set_time.setAlpha(0.4f);*//*
                }

            }
        });
        autoData = (TextView)findViewById(R.id.autoData);*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //initiate recycler view function
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //if you want items in ur recycler view to display in a grid form
        //recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        runAnimation(recyclerView,1);
        initControls();
        addListItems();
    }

    private void runAnimation(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if(type == 0) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_down);
        else if(type == 1) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_up);
        else if(type == 2) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_from_right);
        else if(type == 3) controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_anim_from_left);

        mAdapter = new RecyclerAdapter(this,mListData);
        recyclerView.setAdapter(mAdapter);
        //
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void addListItems(){
        mListData.add(new ListData("None","Apply","N"));
        mListData.add(new ListData("Swipe","Apply","S"));
        mListData.add(new ListData("Pattern","Apply","P"));
        mListData.add(new ListData("Face","Apply","F"));
        mListData.add(new ListData("Pin","Apply","P"));
        mListData.add(new ListData("Password","Apply","P"));
    }
    private void initControls() {
        viewPager = (AutoScrollViewPager) findViewById(R.id.topViewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        initViewPagerControls();

    }
    private void initViewPagerControls() {

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.head_page1,
                R.layout.head_page2,
                R.layout.head_page3};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.fadein_with_zoom_in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewPager.startAutoScroll();
                viewPager.setInterval(3000);
                viewPager.setCycle(true);
                viewPager.setStopScrollWhenTouch(true);
                viewPager.setAutoScrollDurationFactor(4);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewPager.startAnimation(anim);
        //viewPager.setPageTransformer(true, new ZoomOutTranformer());
    }
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_auto_pager_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_auto_pager_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }//  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                /*btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);*/
            } else {
                // still pages are left
                /*btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);*/
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    public void autoSlideWithTimerTask(){
        /*int currentPage = 0;
        Timer timer;
        final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

            viewPager = (ViewPager) findViewById(R.id.viewPager);
            PagerAdapter adapter = new CustomAdapter(MainActivity.this,imageId,imagesName);
            viewPager.setAdapter(adapter);

*//*After setting the adapter use the timer *//*
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES-1) {
                        currentPage = 0;
                    }
                    viewPager.setCurrentItem(currentPage++, true);
                }
            };

            timer = new Timer(); // This will create a new Thread
            timer .schedule(new TimerTask() { // task to be scheduled

                @Override
                public void run() {
                    handler.post(Update);
                }
            }, DELAY_MS, PERIOD_MS);*/
    }
    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void saveSettings(){
        /*Date now = new Date();
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
        startActivity(Intent.createChooser(email, "Choose an Email client :"));*/
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
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
            finish();
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

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //lockType.setSelection(0);
        //((TextView)parent.getChildAt(0)).setTextColor(0x808080);
    }
}
