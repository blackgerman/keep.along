package tg.abiguime.keepalong.main.home;

import tg.abiguime.keepalong._data.source.TaskListData;
import tg.abiguime.keepalong._data.source.local.TaskListDataSource;

/**
 * By abiguime on 25/12/2016.
 * email: 2597434002@qq.com
 */

public class HomePresenter implements HomeContract.Presenter {


    private HomeContract.View view;

    private TaskListDataSource repo;

    public HomePresenter(HomeContract.View view, TaskListDataSource repo) {
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void loadTasksFromDb() {

    }

    @Override
    public void start() {

    }
}
