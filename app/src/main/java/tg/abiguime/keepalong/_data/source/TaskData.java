package tg.abiguime.keepalong._data.source;

import android.database.sqlite.SQLiteTransactionListener;

import tg.abiguime.keepalong._commons.listener.OnSimpleTaskCompletedListener;
import tg.abiguime.keepalong._data.Task;

/**
 * By abiguime on 23/12/2016.
 * email: 2597434002@qq.com
 */

public interface TaskData {

    void save(Task task, OnSimpleTaskCompletedListener listener);

    void delete(Task task, OnSimpleTaskCompletedListener listener);

    void update(Task task, OnSimpleTaskCompletedListener listener);

    void add(Task task, OnSimpleTaskCompletedListener listener);

    public class Contract {

        public String TABLE_NAME = "task";
        public int id;
        public String NAME = "name";
        public String DESCRIPTION = "description";
        public String creation_date;
        public String modification_date;
        public int progressStatus;
        public int priorityStyleStatus;
    }

}
