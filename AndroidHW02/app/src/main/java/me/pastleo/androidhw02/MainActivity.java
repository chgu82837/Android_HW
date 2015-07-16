package me.pastleo.androidhw02;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.Intent;

public class MainActivity extends ActionBarActivity {

    View.OnClickListener ls;
    TextView tv;
    Intent ext_i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ext_i = new Intent(this,ExtActivity.class);
        tv = (TextView) findViewById(R.id.msg);

        ls = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.button:
                        tv.setText(R.string.btn1_msg);
                        break;
                    case R.id.button2:
                        tv.setText(R.string.btn2_msg);
                        break;
                    case R.id.ext:
                        startActivity(ext_i);
                        break;
                }
            }
        };

        findViewById(R.id.button).setOnClickListener(ls);
        findViewById(R.id.button2).setOnClickListener(ls);
        findViewById(R.id.ext).setOnClickListener(ls);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
