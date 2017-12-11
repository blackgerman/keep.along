package tg.experta.kaba.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import tg.experta.kaba.Home.Fragmentz.Home_1_Fragment;
import tg.experta.kaba.Home.Fragmentz.Home_2_Restaurant;
import tg.experta.kaba.Home.Fragmentz.dummy.DummyContent;
import tg.experta.kaba.Menu.RestaurantMenuActivity;
import tg.experta.kaba.R;
import tg.experta.kaba._commons.OnImageClickListener;
import tg.experta.kaba.config.Config;

public class HomeActivity extends AppCompatActivity implements
        Home_1_Fragment.OnFragmentInteractionListener, Home_2_Restaurant.OnListFragmentInteractionListener,
        OnImageClickListener {

    private static final int HOME = 0;
    private static final int RESTAURANT = 1;

    private TextView mTextMessage;
    FrameLayout frm_main;
    SwipeRefreshLayout swp_home_frg;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(HOME);
                    return true;
                case R.id.navigation_restaurant:
                    switchFragment(RESTAURANT);
                    return true;
                case R.id.navigation_shoping_card:
                    mToast(getString(R.string.title_shopping_card));
                    return true;
                case R.id.navigation_my_account:
                    mToast(getString((R.string.title_myaccount)));
                    return true;
            }
            return false;
        }
    };

    private Home_1_Fragment frg_1_home;
    private Home_2_Restaurant frg_2_restaurants;

    private void switchFragment(int frgId) {

        terminateRefreshing();
        switch (frgId) {
            case HOME:
                if (frg_1_home == null)
                    frg_1_home = new Home_1_Fragment();
                performNoBackStackTransaction(getSupportFragmentManager(), getString(R.string.title_home),  frg_1_home);
                break;
            case RESTAURANT:
                if (frg_2_restaurants == null)
                    frg_2_restaurants = new Home_2_Restaurant();
                performNoBackStackTransaction(getSupportFragmentManager(), getString(R.string.title_restaurant),  frg_2_restaurants);
                break;
        }
        // save the current fragment in the shared_pref and remember to delete before adding another one
    }

    public static void performNoBackStackTransaction(final FragmentManager fragmentManager, String tag, Fragment fragment) {
        final int newBackStackLength = fragmentManager.getBackStackEntryCount() +1;

        fragmentManager.beginTransaction()
                .replace(R.id.frame_main_layout_content, fragment, tag)
                .addToBackStack(tag)
                .commit();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int nowCount = fragmentManager.getBackStackEntryCount();
                if (newBackStackLength != nowCount) {
                    // we don't really care if going back or forward. we already performed the logic here.
                    fragmentManager.removeOnBackStackChangedListener(this);

                    if ( newBackStackLength > nowCount ) { // user pressed back
                        fragmentManager.popBackStackImmediate();
                    }
                }
            }
        });
    }

    public void terminateRefreshing() {

        if (swp_home_frg.isRefreshing()) {
            swp_home_frg.setRefreshing(false);
            swp_home_frg.destroyDrawingCache();
            swp_home_frg.clearAnimation();
        }
    }


    private void mToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }


    FrameLayout frm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        initViews();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        switchFragment(HOME);

        swp_home_frg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                swp_home_frg.destroyDrawingCache();

                swp_home_frg.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swp_home_frg.setRefreshing(false);
                    }
                }, 5000);
            }
        });

    }

    private void initViews() {
        frm = findViewById(R.id.frame_main_layout_content);
        swp_home_frg = findViewById(R.id.swp_home_frg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_mymessages) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

//        check this scheme
        /*
        *  builder.scheme(Config.APP_SCHEME)
                .authority(Config.APP_AUTHORITY)
                .appendPath(Config.PATH_RESTAURANT)
                .appendPath(Config.PATH_MENU)
                .appendQueryParameter(Config.PARAMS_RESTAURANT_ID, restaurantId);
        * */
        if (uri == null) {
            mSnack("Uri null");
            return;
        }

        String restaurantId = uri.getQueryParameter(Config.PARAMS_RESTAURANT_ID);
        startRestaurantMenuActivity(restaurantId);
    }

    private void startRestaurantMenuActivity(String restaurantId) {

        Intent intent = new Intent(this, RestaurantMenuActivity.class);
        startActivity(intent);
    }

    private void mSnack(String s) {
        Snackbar.make(getSupportActionBar().getCustomView(), s, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {
        // always have an overlapping view for showing the images in huge ...
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
