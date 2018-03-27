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
import android.widget.TextView;
import android.widget.Toast;

public class MyAddress extends AppCompatActivity {
    EditText myName, myaddress;
    Button btn_insert, btn_display;
    DbHelper helper;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        helper = new DbHelper(this, "mydb", null, 5);

        myName = (EditText) findViewById(R.id.myName);
        myaddress = (EditText) findViewById(R.id.myAddress);
        txtInfo = (TextView) findViewById(R.id.txt);


        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("myname_address", getMyName());
                values.put("myaddress", getMyAddress());
                db.insert("AddressTable", null, values);
                db.close();
            }
        });
        btn_display = (Button) findViewById(R.id.btn_display);

        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AddressInformation="";
                SQLiteDatabase db = helper.getReadableDatabase();
                String table = "AddressTable";
                String[] column = null;
                //where clause
                String selection =null;
                String[] selectionArgs = null;
                String groupby = null;
                String having = null;
                String orderby = null;

                Cursor cursor = db.query(table, column, selection, selectionArgs, groupby, having, orderby);

                while (cursor.moveToNext()) {
                    String myName = cursor.getString(cursor.getColumnIndex("myname_address"));
                    String myAddress = cursor.getString(cursor.getColumnIndex("myaddress"));
                    AddressInformation=AddressInformation+"Name:"+myName+"\n"+"Address:"+myAddress+"\n";
                }

                Toast.makeText(getApplicationContext(),"Address Information Inserted",Toast.LENGTH_LONG).show();
                myName.setText("");
                myaddress.setText("");
                db.close();
                Log.e("MyAddressActivity",AddressInformation);
            }
        });
    }

    private String getMyAddress() {
        return myaddress.getText().toString().trim();
    }

    private String getMyName() {
        return myName.getText().toString().trim();
    }
}
