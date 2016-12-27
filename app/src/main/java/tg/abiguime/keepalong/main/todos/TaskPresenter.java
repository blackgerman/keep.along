package tg.abiguime.keepalong.main.todos;

import java.util.List;

import tg.abiguime.keepalong._commons.listener.OnSimpleTaskCompletedListener;
import tg.abiguime.keepalong._data.Task;
import tg.abiguime.keepalong._data.TaskList;
import tg.abiguime.keepalong._data.source.local.TaskListDataSource;

/**
 * By abiguime on 25/12/2016.
 * email: 2597434002@qq.com
 */

public class TaskPresenter implements TaskContract.Presenter{


    private TaskContract.View view;

    private TaskListDataSource repo;

    public TaskPresenter(TaskContract.View view, TaskListDataSource repo) {
        this.view = view;
        this.repo = repo;
    }


    @Override
    public void loadTasksFromDb() {

        repo.loadTask (new OnSimpleTaskCompletedListener<List<TaskList>>(){

            @Override
            public void taskCompleted(List<TaskList> data) {
                view.showTask(data);
            }
        });
    }

    @Override
    public void start() {

    }
}
