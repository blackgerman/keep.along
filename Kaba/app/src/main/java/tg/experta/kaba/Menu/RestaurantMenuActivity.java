package tg.experta.kaba.Menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tg.experta.kaba.FoodDetails.FoodDetailsActivity;
import tg.experta.kaba.R;
import tg.experta.kaba.data.Menu.Restaurant_MenuEntity;
import tg.experta.kaba.data.Menu.Restaurant_SubMenuEntity;

public class RestaurantMenuActivity extends AppCompatActivity implements RestaurantSubMenuFragment.OnFragmentInteractionListener{


    // views
    ViewPager vp_menus;
    TabLayout tablelayout_title_strip;
    private Restaurant_MenuEntity restaurant_menu;

    ImageButton ib_close_window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews ();
        initData ();
        initMenus ();

        tablelayout_title_strip.setupWithViewPager(vp_menus);

        ib_close_window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestaurantMenuActivity.this.finish();
            }
        });
    }

    private void initData() {

        restaurant_menu = new Restaurant_MenuEntity();
        List<Restaurant_SubMenuEntity> entityList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            entityList.add(Restaurant_SubMenuEntity.fake());
        }
        restaurant_menu.sub_menus = entityList;
    }

    private void initMenus() {

        // menu should be gotten in the json file also
        MenuViewpagerAdapter menuViewpagerAdapter = new MenuViewpagerAdapter(getSupportFragmentManager());
        vp_menus.setAdapter(menuViewpagerAdapter);
    }

    private void initViews() {

        vp_menus = findViewById(R.id.viewpager_menus);
        tablelayout_title_strip = findViewById(R.id.tablayout_vp_strip);
        ib_close_window = findViewById(R.id.ib_close_window);
    }

    private class MenuViewpagerAdapter extends FragmentPagerAdapter {

        public MenuViewpagerAdapter(FragmentManager fm) {
            super(fm);
            frg_map = new HashMap<>();
        }

        Map<Integer, Fragment> frg_map;

        @Override
        public Fragment getItem(int position) {

            if (frg_map == null)
                frg_map = new HashMap<>();

            if (frg_map.get(position) == null) {
                frg_map.put(position, RestaurantSubMenuFragment.instantiate(RestaurantMenuActivity.this,
                        restaurant_menu.sub_menus.get(position)));
            }
            return frg_map.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "TITLE "+position;
        }

        @Override
        public int getCount() {
            return restaurant_menu.sub_menus.size();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

        // uri : after clicking on this, will get to another page
        // jump to another activity
        Intent in = new Intent(this, FoodDetailsActivity.class);
        startActivity(in);
    }

}
