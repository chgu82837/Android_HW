package me.pastleo.androidhw03;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Integer;

public class MainActivity extends Activity {

    int player_score;
    int computer_score;
    ArrayList<Integer> record;
    private String recordFormat;
    private Integer[] img_res;
    private String[] status_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordFormat = getResources().getString(R.string.record);
        img_res = new Integer[]{R.drawable.paper,R.drawable.scissor,R.drawable.stone};
        status_t = new String[]{
                getResources().getString(R.string.tie),
                getResources().getString(R.string.lose),
                getResources().getString(R.string.win),
        };

        record = new ArrayList<>();

        final ImageView player = (ImageView) findViewById(R.id.player);
        final TextView player_state = (TextView) findViewById(R.id.player_state);
        final ImageView computer = (ImageView) findViewById(R.id.computer);
        final TextView computer_state = (TextView) findViewById(R.id.computer_state);
        final TextView result = (TextView) findViewById(R.id.result);

        player_score = 0;
        computer_score = 0;

        player_state.setText(getResources().getText(R.string.state).toString() + player_score);
        computer_state.setText(getResources().getText(R.string.state).toString() + computer_score);
        result.setText(getResources().getText(R.string.intro));

        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.record){
                    StringBuffer sb = new StringBuffer();
                    for(int i = 0; i<record.size() ; i++)
                        sb.append(String.format(recordFormat,i,status_t[record.get(i)]));
                    Toast.makeText(getApplicationContext(),sb.toString(),Toast.LENGTH_LONG).show();
                    return;
                }

                int computer_choice = new Random(new Date().getTime()).nextInt(3); // 0 for paper, 1 for scissor, 2 for stone
                int player_choice = 0; // 0 for paper, 1 for scissor, 2 for stone
                int set;

                switch(view.getId()){
                    case R.id.paper: player_choice = 0; break;
                    case R.id.scissor: player_choice = 1; break;
                    case R.id.stone: player_choice = 2; break;
                }

                computer.setImageDrawable(getResources().getDrawable(img_res[computer_choice],null));
                player.setImageDrawable(getResources().getDrawable(img_res[player_choice],null));

                if(computer_choice == player_choice){
                    // tie
                    set = 0;
                }
                else if((computer_choice - player_choice == 1) || (computer_choice == 0 && player_choice == 2)){
                    // lose
                    computer_score++;
                    set = 1;
                }
                else{
                    // win
                    player_score++;
                    set = 2;
                }

                record.add(set);
                result.setText(status_t[set]);

                player_state.setText(getResources().getText(R.string.state).toString() + player_score);
                computer_state.setText(getResources().getText(R.string.state).toString() + computer_score);
            }
        };


        (findViewById(R.id.paper)).setOnClickListener(l);
        (findViewById(R.id.scissor)).setOnClickListener(l);
        (findViewById(R.id.stone)).setOnClickListener(l);
        (findViewById(R.id.record)).setOnClickListener(l);
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

/* Teacher's version =================
 *
package com.example.sps;
 
import android.R.integer;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 
 
public class MainActivity extends ActionBarActivity {
    private TextView result;
    private Button btnScisor, btnPaper, btnStone, btnRecord;
    Toast toast;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        result =(TextView) findViewById(R.id.result);
        btnScisor =(Button) findViewById(R.id.button1);
        btnPaper =(Button) findViewById(R.id.button2);
        btnStone =(Button) findViewById(R.id.button3);
        btnRecord =(Button) findViewById(R.id.button4);
       
        btnScisor.setOnClickListener(doClick);
        btnPaper.setOnClickListener(doClick);
        btnStone.setOnClickListener(doClick);
        btnRecord.setOnClickListener(doClick);
         
    }
 
  private Button.OnClickListener doClick = new Button.OnClickListener(){
 
    @Override
    public void onClick(View v) {
        int sysValue = 0; //剪刀
        int yourValue = -1;
        int winNum=5;
        int lossNum;
        int totalNum;
        
        String s;
        // TODO Auto-generated method stub
        switch(v.getId())
        {
        case R.id.button1:
            yourValue = 0;
            s="You propose Scissor! Tie!!!";
            result.setText(s);
            toast=Toast.makeText(MainActivity.this, "sysVale=Scissor yourValue =Scissor",Toast.LENGTH_LONG );
            toast.show();
            break;
        case R.id.button2:
            yourValue = 1;
            s="You propose Stone! You Win!!!";
            result.setText(s);
            toast=Toast.makeText(MainActivity.this, "sysVale=Scissor yourValue =Paper",Toast.LENGTH_LONG );
            toast.show();
            break;
        case R.id.button3:
            yourValue = 2;
            s="You propose Stone! You lose!!!";
            result.setText(s);
            toast=Toast.makeText(MainActivity.this, "sysVale=Scissor yourValue =Stone",Toast.LENGTH_LONG );
            toast.show();
            break;
        case R.id.button4:
            
            new AlertDialog.Builder(MainActivity.this).setTitle("Your Records are summarized as below").setIcon(R.drawable.ic_launcher).setMessage("Total successs is "+winNum).show();
            
            break;
            
        }
    }
 
    
 
        
    };
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
*/
