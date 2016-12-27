package tg.abiguime.savidor;

import java.util.HashMap;
import java.util.Map;

/**
 * By abiguime on 27/12/2016.
 * email: 2597434002@qq.com
 */
public class SaveWill {
    /**
     * Everyclass that would like to get save to the database should implement this
     * object.
     * All the fields private/public/protected are taken as individual fields of the table
     *  with the same name as the class, and saved to a table inside the database
     */

    SaveWill () {

    }

    public void save() {

        /* retrieve all the fields inside the entity and save them inside a map with their values */

        Map<String, Object> key_value = new HashMap<>();



    }


}
