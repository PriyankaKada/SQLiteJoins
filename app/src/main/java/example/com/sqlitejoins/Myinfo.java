package example.com.sqlitejoins;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Myinfo extends AppCompatActivity {
    EditText myName, myAge;
    Button btn_insert, btn_display;
    DbHelper helper;
    String name;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        helper = new DbHelper(this, "mydb", null, 5);
        myName = (EditText) findViewById(R.id.myName);
        myAge = (EditText) findViewById(R.id.myAge);


        btn_insert = (Button) findViewById(R.id.btn_insert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getmyName().equals("") || getMyAge() == 0) {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put("myname", getmyName());
                    values.put("myage", getMyAge());

                    db.insert("InformationTable", null, values);

                }
                Toast.makeText(getApplicationContext(), "Information Inserted", Toast.LENGTH_LONG).show();
                myName.setText("");
                myAge.setText("");

            }
        });
        btn_display = (Button) findViewById(R.id.btn_display);

        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MyInformation = "";
                String myName = null;
                int age = 0;
                SQLiteDatabase db = helper.getReadableDatabase();
                String table = "InformationTable";
                String[] column = null;
                //where clause
                String selection = null;
                String[] selectionArgs = null;
                String groupby = null;
                String having = null;
                String orderby = null;

                Cursor cursor = db.query(table, column, selection, selectionArgs, groupby, having, orderby);
                while (cursor.moveToNext()) {
                    myName = cursor.getString(cursor.getColumnIndex("myname"));
                    age = cursor.getInt(cursor.getColumnIndex("myage"));
                    MyInformation = MyInformation + "Name:" + myName + "\n" + "Age:" + age + "\n";
                }


                db.close();
                Log.e("MyInfo Activity", MyInformation);
            }
        });

    }

    private int getMyAge() {
        return Integer.parseInt(myAge.getText().toString().trim());
    }

    private String getmyName() {
        return myName.getText().toString().trim();
    }
}
