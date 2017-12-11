package tg.experta.kaba.FoodDetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import tg.experta.kaba.R;
import tg.experta.kaba._commons.OnImageClickListener;
import tg.experta.kaba._commons.cviews.SlidingBanner_LilRound;
import tg.experta.kaba.data.Food.Food_Tag;
import tg.experta.kaba.data.Food.Restaurant_Menu_FoodEntity;

public class FoodDetailsActivity extends AppCompatActivity implements OnImageClickListener,
        AddDrinkDialogFragment.Listener, ConfirmTransactionDialogFragment.Listener {


    private static final int TAG_SPAN_COUNT = 4;

    SlidingBanner_LilRound slidingBanner;

    RecyclerView recyclerViewFoodTags;

    Restaurant_Menu_FoodEntity foodEntity;

    ImageButton ib_purchase_now;

    ImageView iv_add_a_drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodEntity = new Restaurant_Menu_FoodEntity();
        for (int i = 0; i < 6; i++) {

            foodEntity.food_tags.add(new Food_Tag("pain_6"));
        }

        // get images of the food and init the food_details_activity
        initSlidingBanner();
        initFoodTags();

        iv_add_a_drink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AddDrinkDialogFragment.newInstance(8).show(getSupportFragmentManager(), "add_drink_dialog");
            }
        });

        ib_purchase_now.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ConfirmTransactionDialogFragment.newInstance(10).show(getSupportFragmentManager(), "confirm_dialog");
            }
        });
    }

    private void initFoodTags() {

        // gridview with selected items ...
        FoodTagAdapter tagAdapter = new FoodTagAdapter(this, foodEntity);
        GridLayoutManager lny = new GridLayoutManager(this, TAG_SPAN_COUNT);

        recyclerViewFoodTags.setLayoutManager(lny);
        recyclerViewFoodTags.setAdapter(tagAdapter);
    }

    private void initSlidingBanner() {
        slidingBanner.load();
    }


    private void initViews() {
        slidingBanner = findViewById(R.id.sliding_banner);
        recyclerViewFoodTags = findViewById(R.id.recyclerview_food_tags);
        ib_purchase_now = findViewById(R.id.ib_purchase_now);
        iv_add_a_drink = findViewById(R.id.iv_add_a_drink);
    }

    /* clicking on images */
    @Override
    public void onClick(View view) {

    }

    public void geek(View view) {
        Toast.makeText(this, "ok", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(int position) {

    }


    private class FoodTagAdapter extends RecyclerView.Adapter<FoodTagAdapter.ViewHolder> {

        private final Context ctx;
        private final List<Food_Tag> data;


        public FoodTagAdapter(Context ctx, Restaurant_Menu_FoodEntity foodEntity) {

            this.ctx = ctx;
            this.data = foodEntity.food_tags;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.food_details_tag_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    data.get(position).switchState();
                    bindOrUpdateTextView(holder);
                }
            });
            bindOrUpdateTextView(holder);
        }

        private void bindOrUpdateTextView(ViewHolder holder) {

            ((TextView) holder.rootView).setText(data.get(holder.getAdapterPosition()).name);
            ((TextView) holder.rootView).setBackgroundColor(
                    data.get(holder.getAdapterPosition()).state == 1 ? getResources().getColor(R.color.selected_yellow) : getResources().getColor(R.color.unselected_gray)
            );
        }


        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final View rootView;

            public ViewHolder(View itemView) {
                super(itemView);
                this.rootView = itemView;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_details, menu);
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
        } else if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
