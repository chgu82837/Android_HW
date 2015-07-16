package com.example.pastleo.androidmidterm;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView record;
    private Button b;
    private void print(String s){
        print(s,false);
    }
    private void print(String s,boolean useToast){
        if(useToast) print(s,s);
        else print(s,null);
    }
    private void print(String s,String toastMsg){
        record.setText(record.getText() + "\n" + s);
        if(toastMsg != null) Toast.makeText(this,toastMsg,Toast.LENGTH_SHORT).show();
    }
    private boolean debug = false;
    private String answer;
    private String gameHeader;
    private int guessed = -1;
    private void gameSet(boolean win){
        guessed = -1;
        print(getResources().getString(win ? R.string.win : R.string.lose),true);
        b.setText(getResources().getText(R.string.playAgain));
    }
    private void gameInit(){
        Date d = new Date();
        Random r = new Random(d.getTime());
        int[] ans_t = new int[3];
        ans_t[0] = r.nextInt(9);
        ans_t[1] = (r.nextInt(9) + ans_t[0] + 1) % 10;
        ans_t[2] = r.nextInt(9);
        while(ans_t[2] == ans_t[1] || ans_t[2] == ans_t[0]) ans_t[2] = r.nextInt(9);
        answer = String.format("%d%d%d",ans_t[0],ans_t[1],ans_t[2]);
        if(gameHeader == null)
            gameHeader = getResources().getString(R.string.recordHeader) + "\n" + getResources().getString(R.string.recordSplit);
        record.setText(getResources().getText(R.string.recordTitle));
        print(gameHeader);
        b.setText(getResources().getText(R.string.submitAnswer));
        guessed = 0;
        if(debug) print("Answer is " + answer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.submit);
        record = (TextView) findViewById(R.id.recordTextView);
        b.setOnClickListener(this);
        gameInit();
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

    @Override
    public void onClick(View view) {
        int A = 0, B = 0;
        EditText ans = (EditText) findViewById(R.id.answer);
        String cur_ans_s = ans.getText().toString();
        String result;
        char[] cur_ans = cur_ans_s.toCharArray();
        if(guessed < 0) gameInit();
        else if(cur_ans_s.length() < 3 || cur_ans[0] == cur_ans[1] || cur_ans[1] == cur_ans[2] || cur_ans[0] == cur_ans[2])
            print(getResources().getString(R.string.invalid),true);
        else{
            if(cur_ans_s.equals(answer)) gameSet(true);
            else{
                for(int i=0; i<3 ; i++){
                    if(cur_ans[i] == answer.charAt(i)){
                        A++;
                        cur_ans[i] = 0;
                    }
                }
                for(int i=0; i<3 ; i++){
                    if(answer.indexOf(cur_ans[i]) != -1){
                        B++;
                        cur_ans[i] = 0;
                    }
                }
                result = String.format(getResources().getString(R.string.recordBody),guessed,cur_ans_s,A,B);
                print(result,gameHeader + "\n" + result);
                if(guessed++ >= 20) gameSet(false);
            }
        }
        ans.setText("");
    }
}
