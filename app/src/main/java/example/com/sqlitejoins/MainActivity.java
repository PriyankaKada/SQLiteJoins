package example.com.sqlitejoins;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button myInfo,myAddress,btn_join,btn_left,btn_outer,btn_right;
DbHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DbHelper(this, "mydb", null, 5);

        myInfo=(Button) findViewById(R.id.myInfo);
        myAddress=(Button) findViewById(R.id.myAddress);
        btn_join=(Button) findViewById(R.id.btn_join);

        myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Myinfo.class);
                startActivity(intent);
            }
        });
        myAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MyAddress.class);
                startActivity(intent);

            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output="";
                SQLiteDatabase db=helper.getWritableDatabase();

                String query="SELECT * FROM InformationTable info INNER JOIN AddressTable" +
                        " address ON info.myname = address.myname_address";

                Cursor cursor=db.rawQuery(query,null);

                if (cursor.moveToFirst()){
                    do {
                        String myName=cursor.getString(0);
                        Integer myAge=cursor.getInt(1);
                        String myAddress=cursor.getString(3);
                        output=output+"Name: "+myName+"\n"+"Age: "+myAge+"\n"+"Address: "+myAddress+"\n";
                        Log.i("Inner Join", "My Information"+"\n"+myName+"\n"+myAge+"\n"+myAddress);
                        // Do something Here with values
                    } while(cursor.moveToNext());
                }
                db.close();

                Intent intent=new Intent(MainActivity.this,InnerJoin.class);
                intent.putExtra("output",output);
                intent.putExtra("title","Inner Join");
                startActivity(intent);

            }
        });
        btn_left=(Button)findViewById(R.id.btn_left_join);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getWritableDatabase();
                String output="";
                String query="SELECT * FROM InformationTable info LEFT OUTER JOIN AddressTable" +
                        " address ON info.myname = address.myname_address";

                Cursor cursor=db.rawQuery(query,null);

                if (cursor.moveToFirst()){
                    do {
                        String myName=cursor.getString(0);
                        Integer myAge=cursor.getInt(1);
                        String myAddress=cursor.getString(3);
                        Log.i("LEFT OUTER JOIN", "My Information"+"\n"+myName+"\n"+myAge+"\n"+myAddress);
                        // Do something Here with values
                        output=output+"Name: "+myName+"\n"+"Age: "+myAge+"\n"+"Address: "+myAddress+"\n";

                    } while(cursor.moveToNext());
                }
                db.close();
                Intent intent=new Intent(MainActivity.this,InnerJoin.class);
                intent.putExtra("output",output);
                intent.putExtra("title","LEFT OUTER JOIN");
                startActivity(intent);
            }
        });
        btn_right=(Button)findViewById(R.id.btn__right_join);
        btn_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getWritableDatabase();
                String output="";
                String query="SELECT * FROM InformationTable info RIGHT JOIN AddressTable" +
                        " address ON info.myname = address.myname_address";

                Cursor cursor=db.rawQuery(query,null);

                if (cursor.moveToFirst()){
                    do {
                        String myName=cursor.getString(0);
                        Integer myAge=cursor.getInt(1);
                        String myAddress=cursor.getString(3);
                        Log.i("RIGHT JOIN", "My Information"+"\n"+myName+"\n"+myAge+"\n"+myAddress);
                        output=output+"Name: "+myName+"\n"+"Age: "+myAge+"\n"+"Address:"+myAddress+"\n";

                        // Do something Here with values
                    } while(cursor.moveToNext());
                }
                db.close();
                Intent intent=new Intent(MainActivity.this,InnerJoin.class);
                intent.putExtra("output",output);
                intent.putExtra("title","RIGHT JOIN");
                startActivity(intent);
            }
        });
//        btn_outer=(Button)findViewById(R.id.btn_outer_join);
//        btn_outer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SQLiteDatabase db=helper.getWritableDatabase();
//
//                String query="SELECT * FROM InformationTable info FULL OUTER JOIN AddressTable" +
//                        " address ON info.myname = address.myname_address";
//
//                Cursor cursor=db.rawQuery(query,null);
//
//                if (cursor.moveToFirst()){
//                    do {
//                        String myName=cursor.getString(0);
//                        Integer myAge=cursor.getInt(1);
//                        String myAddress=cursor.getString(3);
//                        Log.i("FULL OUTER JOIN", "My Information"+"\n"+myName+"\n"+myAge+"\n"+myAddress);
//                        // Do something Here with values
//                    } while(cursor.moveToNext());
//                }
//                db.close();
//            }
//        });


    }


}
