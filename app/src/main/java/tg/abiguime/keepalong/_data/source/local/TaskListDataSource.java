package tg.abiguime.keepalong._data.source.local;

import android.content.Context;

import java.util.List;

import tg.abiguime.keepalong._commons.listener.OnSimpleTaskCompletedListener;
import tg.abiguime.keepalong._data.Task;
import tg.abiguime.keepalong._data.TaskList;
import tg.abiguime.keepalong._data.source.TaskListData;

/**
 * By abiguime on 25/12/2016.
 * email: 2597434002@qq.com
 */

public class TaskListDataSource implements TaskListData {


    private final Context context;


    public TaskListDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void save(TaskList taskList) {

    }

    @Override
    public void delete(TaskList taskList) {

    }

    @Override
    public void update(TaskList taskList) {

    }

    @Override
    public void add(TaskList taskList) {

    }

    public void loadTask(OnSimpleTaskCompletedListener<List<TaskList>> onSimpleTaskCompletedListener) {
        onSimpleTaskCompletedListener.taskCompleted(TaskList.fake(10));
    }

}
