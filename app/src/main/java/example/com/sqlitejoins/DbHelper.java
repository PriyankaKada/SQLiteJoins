package example.com.sqlitejoins;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by webwerks on 3/22/18.
 */

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // This would be called only once/ per version change so create tables here
//        sqLiteDatabase.execSQL("create table InfoTable(user_id INTEGER " +
//                "PRIMARY KEY AUTOINCREMENT " +
//                ",myname text ,myage INTEGER)");

        sqLiteDatabase.execSQL("create table InformationTable(myname text ,myage number)");

        sqLiteDatabase.execSQL("create table AddressTable(myname_address text ," +
                "myaddress text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int olderVersion, int newVersion) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS InformationTable");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AddressTable");
        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
