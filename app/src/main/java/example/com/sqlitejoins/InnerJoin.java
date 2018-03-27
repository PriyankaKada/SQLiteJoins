package example.com.sqlitejoins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InnerJoin extends AppCompatActivity {
    TextView title, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_join);
        title = (TextView) findViewById(R.id.txt);
        output = (TextView) findViewById(R.id.txt_outPut);

        String titleString = getIntent().getExtras().getString("title", "Join Activity");

        String outString = getIntent().getExtras().getString("output", "No Data");
       
            title.setText(titleString);
            output.setText(outString);

    }
}
