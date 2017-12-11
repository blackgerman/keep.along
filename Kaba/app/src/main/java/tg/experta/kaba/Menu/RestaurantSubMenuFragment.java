package tg.experta.kaba.Menu;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tg.experta.kaba.Home.Fragmentz.Home_1_Fragment;
import tg.experta.kaba.R;
import tg.experta.kaba.data.Food.Restaurant_Menu_FoodEntity;
import tg.experta.kaba.data.Menu.Restaurant_SubMenuEntity;

/**
 * A placeholder fragment containing a simple view.
 */
public class RestaurantSubMenuFragment extends Fragment {


    private static final String DATA_1 = "subMenu";
    private OnFragmentInteractionListener mListener;

    public static RestaurantSubMenuFragment instantiate (Context ctx,  Restaurant_SubMenuEntity  subMenu) {

        RestaurantSubMenuFragment frg = new RestaurantSubMenuFragment();
        Bundle args = new Bundle();
        args.putParcelable(DATA_1,  subMenu);
        frg.setArguments(args);
        return frg;
    }

    Restaurant_SubMenuEntity subMenuEntity;


    private RecyclerView recyclerview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the bundle data
        Bundle args = getArguments();
        if (args!= null)
            subMenuEntity = args.getParcelable(DATA_1);
    }


    public RestaurantSubMenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurant_submenu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview = view.findViewById(R.id.recyclerview);

        initRecyclerview(subMenuEntity.foods);
    }

    private void initRecyclerview(List<Restaurant_Menu_FoodEntity>  foods) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(linearLayoutManager);

        //
        SubMenuRecyclerviewAdapter adap = new SubMenuRecyclerviewAdapter(getContext(), foods);
        recyclerview.setAdapter(adap);
    }


    public class SubMenuRecyclerviewAdapter extends RecyclerView.Adapter<SubMenuRecyclerviewAdapter.ViewHolder> {

        private final List<Restaurant_Menu_FoodEntity> data;
        private final Context ctx;

        SubMenuRecyclerviewAdapter (Context ctx,List<Restaurant_Menu_FoodEntity> data) {

            this.ctx = ctx;
            this.data = data;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            // if you are the last ...
            if (viewType == data.size()-1) {
                return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.menu_food_item_space, parent, false));
            }

            if (viewType % 2 == 0) {
                return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.menu_food_item_left, parent, false));
            } else {
                return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.menu_food_item_right, parent, false));
            }
            // at the bottom of the list -- need to add the kaba gray icon... and few informations
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            if (position != data.size()-1)
                holder.viewRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mListener.onFragmentInteraction(null);
                    }
                });



        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public View viewRoot;

            public ViewHolder(View itemView) {
                super(itemView);
                this.viewRoot = itemView;
            }
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RestaurantSubMenuFragment.OnFragmentInteractionListener) {
            mListener = (RestaurantSubMenuFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
