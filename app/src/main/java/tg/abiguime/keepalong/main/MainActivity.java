package tg.abiguime.keepalong.main;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import tg.abiguime.keepalong.BaseView;
import tg.abiguime.keepalong.R;
import tg.abiguime.keepalong._core.PockiApplication;
import tg.abiguime.keepalong._data.Task;
import tg.abiguime.keepalong._data.source.local.TaskListDataSource;
import tg.abiguime.keepalong.main.home.HomeFragment;
import tg.abiguime.keepalong.main.home.HomePresenter;
import tg.abiguime.keepalong.main.home.StepService;
import tg.abiguime.keepalong.main.tobuy.ToBuyFragment;
import tg.abiguime.keepalong.main.todos.TaskContract;
import tg.abiguime.keepalong.main.todos.TaskFragment;
import tg.abiguime.keepalong.main.todos.TaskPresenter;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        TaskFragment.OnListFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        ToBuyFragment.OnFragmentInteractionListener{


    /* Views */
    TextView tv_steps_count;
    TextView tv_daily_quote;
    TextView tv_quotes_title;
    TextView tv_quote_autor;


    /* */
    private boolean activityIsRunning = false;


    /* Steps receiver */
    private BroadcastReceiver stepReceiver;
    /* Fragment holder */
    Map<Integer, Fragment> fcontainer;


    /* Presenters */
    HomePresenter homePresenter;
    TaskPresenter taskPresenter;


    /* Repository files */
    private TaskListDataSource taskRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        initFonts();
        initFootStep();

        initQuotes();

        fcontainer = new HashMap<>();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initQuotes() {

        String quotes = getResources().getStringArray(R.array.loca_quotes)[0];
        String auth = getResources().getStringArray(R.array.quotes_author)[0];

        tv_daily_quote.setText(quotes);
        tv_quote_autor.setText(auth);
    }

    private void initFootStep() {

        Intent service = new Intent(this, StepService.class);

        bindService(service, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                StepService.StepServiceBinder binder = (StepService.StepServiceBinder) service;
                updateStep(binder.getCurrentStepCount());
                unbindService(this);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        }, BIND_AUTO_CREATE);
    }


    private void initFonts() {

        tv_quotes_title.setTypeface(((PockiApplication)getApplication()).getTypeFace(PockiApplication.PockiTypeFace.dailyQuote));
        tv_daily_quote.setTypeface(((PockiApplication)getApplication()).getTypeFace(PockiApplication.PockiTypeFace.dailyQuote));
        tv_quote_autor.setTypeface(((PockiApplication)getApplication()).getTypeFace(PockiApplication.PockiTypeFace.dailyQuote));
        tv_steps_count.setTypeface(((PockiApplication)getApplication()).getTypeFace(PockiApplication.PockiTypeFace.footstep));
    }

    private void initViews() {
        tv_quotes_title = (TextView) findViewById(R.id.tv_quotes_title);
        tv_steps_count = (TextView) findViewById(R.id.tv_steps_count);
        tv_daily_quote = (TextView) findViewById(R.id.tv_daily_quote);
        tv_quote_autor = (TextView) findViewById(R.id.tv_quote_autor);
    }


    @Override
    protected void onStart() {
        super.onStart();
        activityIsRunning = true;

        registerForStepCountsReceiver();
    }


    private void registerForStepCountsReceiver() {

        if (stepReceiver == null)
            stepReceiver = new StepCountReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(StepCountReceiver.STEP_CHANGE);
        registerReceiver(stepReceiver, filter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        activityIsRunning = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityIsRunning = false;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(stepReceiver);
        super.onDestroy();
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment frg = null;
        switch (id) {
            case R.id.nav_home:
                frg = fcontainer.get(R.id.nav_home);
                if (frg == null)
                    frg = HomeFragment.newInstance();
                break;
            case R.id.nav_todos:
                frg = fcontainer.get(R.id.nav_todos);
                if (frg == null) {
                    frg = TaskFragment.newInstance();
                        }
                if (taskRepo == null) {
                    taskRepo = new TaskListDataSource(this);
                    taskPresenter = new TaskPresenter((TaskContract.View) frg, taskRepo);
                }
                break;
            case R.id.nav_tobuy:
                frg = fcontainer.get(R.id.nav_tobuy);
                if (frg == null)
                    frg = ToBuyFragment.newInstance();
                break;
           /* case R.id.nav_achievements:
                frg = fcontainer.get(R.id.nav_home).get();
                break;*/
        }

        if (frg != null) {
            fcontainer.put(id, frg);
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.content_main, frg, id+"");
            ((BaseView)frg).setPresenter(taskPresenter);
            trans.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Task item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    /**
     *
     */
    public class StepCountReceiver extends BroadcastReceiver {

        public static final String STEP_CHANGE = "tg.abiguime.keepalong.main.MainActivity$StepCountReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(STEP_CHANGE)) {
                int stepCount = intent.getIntExtra("step", 0);
                updateStep(stepCount);
            }
        }
    }

    private void updateStep(int stepCount) {
        if (activityIsRunning) {
            tv_steps_count.setText(String.valueOf(stepCount));
        }
    }

}
