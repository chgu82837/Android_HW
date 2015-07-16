package me.pastleo.androidhw04;

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
