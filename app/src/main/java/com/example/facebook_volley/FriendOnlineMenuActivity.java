package com.example.facebook_volley;

import android.app.Activity;
import android.os.Bundle;

import com.example.facebook_volley.friend_online_menu.BaseActivity;
import com.example.facebook_volley.friend_online_menu.SampleListFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by Doan Vo Viet Hoai on 6/25/2015.
 */
public class FriendOnlineMenuActivity extends BaseActivity {
    public FriendOnlineMenuActivity() {
        super(R.string.app_name);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSlidingMenu().setMode(SlidingMenu.RIGHT);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        setContentView(R.layout.activity_main);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.layout1, new SampleListFragment())
//                .commit();

        getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
      //  getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame_two, new SampleListFragment())
                .commit();
    }

    /**
     * Created by Doan Vo Viet Hoai on 7/2/2015.
     */
    public static class ViewImage extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
    }
}
