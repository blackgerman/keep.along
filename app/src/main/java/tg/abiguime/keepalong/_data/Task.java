package tg.abiguime.keepalong._data;

/**
 * Created by abiguime on 23/12/2016.
 */
public class Task {

    /* - ongoing, overdue, done /// whateever the status we need to keep the task pressable
     * - theme : important, normal, less_important
     * -
     */

    public enum ProgressStatus {
        ONGOING, DONE, CAN_BE_REMOVED;
    }


    public enum PriorityStyleStatus {
        IMPORTANT, NORMAL, LESS_IMPORTANT
    }


    /*
    * id
    * name of task
    * description of task
    * creation date, modification date
    * issued date & time
    **/

    int id;
    String name;
    String description;
    String creation_date;
    String modification_date;
    ProgressStatus progressStatus = ProgressStatus.ONGOING;
    PriorityStyleStatus priorityStyleStatus = PriorityStyleStatus.NORMAL;

}
