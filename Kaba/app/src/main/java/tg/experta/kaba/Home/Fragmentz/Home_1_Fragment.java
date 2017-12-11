package tg.experta.kaba.Home.Fragmentz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import tg.experta.kaba.Home.HomeActivity;
import tg.experta.kaba.R;
import tg.experta.kaba._commons.OnImageClickListener;
import tg.experta.kaba._commons.cviews.SlidingBanner_Directionnal;
import tg.experta.kaba._commons.decorator.SpacesItemDecoration;
import tg.experta.kaba._commons.utils.UriCustomBuilder;
import tg.experta.kaba.data.advert.AdvertItem;


public class Home_1_Fragment extends Fragment {

    private static final int MENU_SPAN_COUNT = 3;

    private static final int AD_SPACE_4_COUNT = 2;

    private OnFragmentInteractionListener mListener;

    public Home_1_Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_1_, container, false);
    }



    RecyclerView recyclerview_home_restaurant_icon;

    SlidingBanner_Directionnal slidingBanner;

    HomeActivity activity;

    RecyclerView recyclerview_home_4_adspace;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview_home_restaurant_icon = view.findViewById(R.id.recyclerview_home_restaurant_icon);
        slidingBanner = view.findViewById(R.id.sliding_banner);
        recyclerview_home_4_adspace = view.findViewById(R.id.recyclerview_home_4_adspace);
        activity = (HomeActivity) getActivity();
        fakeLoadRestaurantList();
        fakeLoadSlidingBanner();
        fakeLoad_Ad_spaces();




    }


    private void fakeLoad_Ad_spaces() {

        GridLayoutManager grdLm = new GridLayoutManager(getContext(), AD_SPACE_4_COUNT);
//        recyclerview_home_4_adspace
        Ad_4_Adapter adp = new Ad_4_Adapter(activity, AdvertItem.fakeList(4));
        recyclerview_home_4_adspace.setLayoutManager(grdLm);
        recyclerview_home_4_adspace.setAdapter(adp);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.ad_4_spacing);
        recyclerview_home_4_adspace.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

    }

    private void fakeLoadSlidingBanner() {

        // create a customview viewgroup, that contains a viewpager and navigation button
        slidingBanner.load();
        slidingBanner.setOnClickListener((OnImageClickListener)activity);
    }

    private void fakeLoadRestaurantList() {
        LinearLayoutManager lnyMng = new GridLayoutManager(getContext(), MENU_SPAN_COUNT);
        MainRestaurantIconAdapter adap = new MainRestaurantIconAdapter();
        recyclerview_home_restaurant_icon.setLayoutManager(lnyMng);
        recyclerview_home_restaurant_icon.setAdapter(adap);
        Toast.makeText(getContext(), "loading fakeload", Toast.LENGTH_SHORT).show();
    }

    class Ad_4_Adapter extends RecyclerView.Adapter<Ad_4_Adapter.ViewHolder> {


        private final List<AdvertItem> data;
        private final Context ctx;


        public Ad_4_Adapter (Context ctx, List<AdvertItem> data) {

            this.ctx = ctx;
            this.data = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.ad_4_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }


    class MainRestaurantIconAdapter extends RecyclerView.Adapter<MainRestaurantIconAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.main_restaurant_icon, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            // bind the data.
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onFragmentInteraction(
                            UriCustomBuilder.buildToRestaurantMenuUri("0")
                    );
                }
            });
        }


        @Override
        public int getItemCount() {
            return 6;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public View rootView;

            public ViewHolder(View itemView) {
                super(itemView);
                this.rootView = itemView;
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
