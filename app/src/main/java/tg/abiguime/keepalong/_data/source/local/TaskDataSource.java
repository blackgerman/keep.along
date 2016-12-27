package tg.abiguime.keepalong._data.source.local;

import android.content.Context;

import tg.abiguime.keepalong._commons.listener.OnSimpleTaskCompletedListener;
import tg.abiguime.keepalong._data.Task;
import tg.abiguime.keepalong._data.source.TaskData;

/**
 * By abiguime on 23/12/2016.
 * email: 2597434002@qq.com
 */

public class TaskDataSource implements TaskData {


    private final Context ctx;


    public TaskDataSource(Context context) {
        this.ctx = context;
    }


    @Override
    public void save(Task task, OnSimpleTaskCompletedListener listener) {

    }

    @Override
    public void delete(Task task, OnSimpleTaskCompletedListener listener) {

    }

    @Override
    public void update(Task task, OnSimpleTaskCompletedListener listener) {

    }

    @Override
    public void add(Task task, OnSimpleTaskCompletedListener listener) {

    }
}
