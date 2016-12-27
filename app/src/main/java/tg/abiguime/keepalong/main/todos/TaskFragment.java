package tg.abiguime.keepalong.main.todos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import tg.abiguime.keepalong.R;
import tg.abiguime.keepalong._commons.decorators.StaggeredDivider;
import tg.abiguime.keepalong._core.AppConstant;
import tg.abiguime.keepalong._data.Task;
import tg.abiguime.keepalong._data.TaskList;
import tg.abiguime.keepalong.main.home.HomeContract;
import tg.abiguime.keepalong.main.todos.adapter.MyTaskRecyclerViewAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TaskFragment extends Fragment implements TaskContract.View{

    // TODO: Customize parameter argument names
    private OnListFragmentInteractionListener mListener;

    TaskPresenter presenter;


    private MyTaskRecyclerViewAdapter adapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TaskFragment newInstance() {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    ProgressBar pb;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        initViews(view);
        recyclerView.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.VISIBLE);
        pb.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.loadTasksFromDb();
            }
        }, AppConstant.LOCAL_LOADING_DELAY);
        return view;
    }

    private void initViews(View view) {

        pb = (ProgressBar) view.findViewById(R.id.loading_progressbar);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
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


    @Override
    public void noResult() {
            // show no results
    }


    @Override
    public void showTask(List<TaskList> data) {
        // inflate the recycler view
        adapter = new MyTaskRecyclerViewAdapter(data, mListener);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        recyclerView.addItemDecoration(new StaggeredDivider(getResources().getDrawable(R.drawable.decorator_divider)));

        recyclerView.setAdapter(adapter);

        pb.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(Object presenter) {
        this.presenter = (TaskPresenter) presenter;
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
        void onListFragmentInteraction(Task item);
    }
}
