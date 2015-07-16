package me.pastleo.androidhw02;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class ExtActivity extends ActionBarActivity {

    TextView ext_msg;
    GridLayout frame;
    String clear_t;
    String base_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ext);

        ext_msg = (TextView) findViewById(R.id.ext_tv);
        frame = (GridLayout) findViewById(R.id.ext_view);
        clear_t = getResources().getString(R.string.clear_t);
        base_msg = getResources().getString(R.string.ext_msg);

        View.OnClickListener ls = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ori = ext_msg.getText().toString();
                String tmp = ((Button)v).getText().toString();

                if(tmp.equals(clear_t))
                    ext_msg.setText(base_msg);
                else
                    ext_msg.setText(ori + tmp);
            }
        };

        Button btn_tmp;

        for(int i = 1; i <= 9; i++){
            btn_tmp = new Button(this);
            btn_tmp.setOnClickListener(ls);
            btn_tmp.setText(String.format("%d",i));
            frame.addView(btn_tmp);
        }

        String[] more_act = {"*","0",clear_t};
        for(String i: more_act){
            btn_tmp = new Button(this);
            btn_tmp.setOnClickListener(ls);
            btn_tmp.setText(i);
            frame.addView(btn_tmp);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ext, menu);
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
