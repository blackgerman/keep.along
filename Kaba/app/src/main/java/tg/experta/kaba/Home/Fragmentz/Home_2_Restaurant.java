package tg.experta.kaba.Home.Fragmentz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tg.experta.kaba.Home.Fragmentz.dummy.DummyContent.DummyItem;
import tg.experta.kaba.Menu.RestaurantMenuActivity;
import tg.experta.kaba.Menu.RestaurantSubMenuFragment;
import tg.experta.kaba.R;
import tg.experta.kaba.data.Restaurant.RestaurantEntity;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class Home_2_Restaurant extends Fragment {


    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public Home_2_Restaurant() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyRestaurantRecyclerViewAdapter(context, RestaurantEntity.fakeList(10), mListener));
        }
        return view;
    }

    public class MyRestaurantRecyclerViewAdapter extends RecyclerView.Adapter<MyRestaurantRecyclerViewAdapter.ViewHolder> {

        private final List<RestaurantEntity> data;
        private final OnListFragmentInteractionListener mListener;
        private final Context ctx;

        public MyRestaurantRecyclerViewAdapter(Context ctx, List<RestaurantEntity> data, OnListFragmentInteractionListener listener) {
            this.ctx = ctx;
            this.data = data;
            mListener = listener;
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

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_restaurant_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(ctx, RestaurantMenuActivity.class);
                    startActivity(in);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View rootView;

            public ViewHolder(View view) {
                super(view);
                rootView = view;
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
