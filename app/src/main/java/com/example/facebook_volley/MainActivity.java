package com.example.facebook_volley;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.facebook_volley.adapter.ViewPagerAdapter;
import com.example.facebook_volley.friend_online_menu.BaseActivity;
import com.example.facebook_volley.friend_online_menu.SampleListFragment;
import com.example.facebook_volley.widget.BadgeView;
import com.example.facebook_volley.widget.SlidingTabLayout;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {
//    public MainActivity() {
//        super(R.string.app_name);
//    }

    ViewPager pager;
    ViewPagerAdapter adapter;
    ActionBar actionBar;
    SlidingTabLayout tab,tab_footer;
    int icons[] = {R.drawable.tab1, R.drawable.tab2, R.drawable.tab3, R.drawable.tab4, R.drawable.tab5};
    String titles[] = {"New Feed", "Request", "Messenger", "Notification", "More"};
    BadgeView[] notification = new BadgeView[4];



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSlidingMenu().setMode(SlidingMenu.RIGHT);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);  //15dp
        getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset); //60dp
        getSlidingMenu().setFadeDegree(0.35f);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.menu_frame_two);
       // getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
        //  getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame_two, new SampleListFragment())
                .commit();


        pager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), icons);
        pager.setAdapter(adapter);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#47639e")));
        tab = (SlidingTabLayout) findViewById(R.id.tab);
        tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getActionBar().setTitle(titles[position]);
                if (position != 4) {
                    notification[position].hide();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tab.setDistributeEvenly(true);
        tab.setCustomTabView(R.layout.custom_tabs_layout, 0);
        tab.setViewPager(pager);


        // setup badge view

        for (int i = 0; i < notification.length; i++) {
            // de su dung dc ham get child phai dat class SlidingTabTripLayout o public
            notification[i] = new BadgeView(getApplicationContext(), tab.getTabStrip().getChildAt(i));
            int index = (int) (100 * Math.random());
            notification[i].setText("" + index);
            if (index != 0 && i != 0)
                notification[i].toggle();
        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getSlidingMenu().showMenu();
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
        }
        return super.onOptionsItemSelected(item);
    }


}
