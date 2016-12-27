package tg.abiguime.keepalong._data;

import java.util.ArrayList;
import java.util.List;

/**
 * By abiguime on 23/12/2016.
 * email: 2597434002@qq.com
 */
public class TaskList {

    /**
     * - list of Task
     * - creation, modification date
     * - name
     * - id
     * - checked time :: the list have been checked how many times
     * -
     */

    int id;
    int checkedCount;
    List<Task> tasks;
    String name;
    String description;
    String creation_date;
    String modification_date;
    String due_date;


    public static List<TaskList> fake(int count) {
        List<TaskList> task = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            task.add(new TaskList());
        }
        return task;
    }
}
