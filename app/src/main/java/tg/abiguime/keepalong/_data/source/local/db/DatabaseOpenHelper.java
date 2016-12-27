package tg.abiguime.keepalong._data.source.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tg.abiguime.keepalong._data.constants.DbConstant;

/**
 * By abiguime on 23/12/2016.
 * email: 2597434002@qq.com
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {




    public DatabaseOpenHelper(Context context) {
        super(context, DbConstant.dbName, null, DbConstant.version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
