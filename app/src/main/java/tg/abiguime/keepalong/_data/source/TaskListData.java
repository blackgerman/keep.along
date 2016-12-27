package tg.abiguime.keepalong._data.source;

import tg.abiguime.keepalong._data.Task;
import tg.abiguime.keepalong._data.TaskList;

/**
 * By abiguime on 23/12/2016.
 * email: 2597434002@qq.com
 */

public interface TaskListData {


        void save(TaskList taskList);

        void delete(TaskList taskList);

        void update(TaskList taskList);

        void add(TaskList taskList);

}
