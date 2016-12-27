package tg.abiguime.keepalong.main.todos.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tg.abiguime.keepalong.R;
import tg.abiguime.keepalong._data.Task;
import tg.abiguime.keepalong._data.TaskList;
import tg.abiguime.keepalong.main.todos.TaskFragment;

import java.util.List;
import java.util.Random;


public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder>{

    private final List<TaskList>mValues;
    private final TaskFragment.OnListFragmentInteractionListener mListener;

    public MyTaskRecyclerViewAdapter(List<TaskList>items, TaskFragment.OnListFragmentInteractionListener listener){
        mValues=items;
        mListener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,int position){
        holder.mItem=mValues.get(position);

        Random r = new Random();


        holder.tv_mTitle.setText("" + position);
        holder.mView
                .setBackgroundColor(Color.argb(255,
                r.nextInt(255),
                r.nextInt(255),
                r.nextInt(255)));
        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(null!=mListener){
//                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tv_mTitle;
//        public final TextView mContentView;
        public TaskList mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_mTitle = (TextView) view.findViewById(R.id.tv_mTitle);
//            mContentView = (TextView) view.findViewById(R.id.content);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}
