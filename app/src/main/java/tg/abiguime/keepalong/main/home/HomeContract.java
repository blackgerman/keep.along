package tg.abiguime.keepalong.main.home;

import java.util.List;

import tg.abiguime.keepalong.BasePresenter;
import tg.abiguime.keepalong.BaseView;
import tg.abiguime.keepalong._data.TaskList;

/**
 * By abiguime on 25/12/2016.
 * email: 2597434002@qq.com
 */

public class HomeContract {

    public interface Presenter extends BasePresenter {

        void loadTasksFromDb();
    }


    public interface View extends BaseView {

        void showTaskStack(List<TaskList> taskListList);
    }

}
