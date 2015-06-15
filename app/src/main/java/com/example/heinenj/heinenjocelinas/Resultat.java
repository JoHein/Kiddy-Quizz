package com.example.heinenj.heinenjocelinas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Resultat extends Activity {
    public String log;
    public int scoreData;
    public int scoreQuest;

    public final static String LOGUSER = "logUser";
    public final static String PASS = "password";
    public final static String SCOREQ = "scoreQ";
    public final static String SCOREMATH = "scoreD";


    public String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        log = getIntent().getStringExtra(Addition.LOGUSER);
        pass = getIntent().getStringExtra(Addition.PASS);

        scoreData = getIntent().getIntExtra(Addition.SCOREMATH, 0);
        scoreQuest = getIntent().getIntExtra(Questions.SCOREQ, 0);



        TextView greatingP = (TextView)findViewById(R.id.TitreR);

        if (log!=null) {
            greatingP.setText("Resultat " + log);
        }else{
            greatingP.setText("Resultat ");
        }

        TextView result = (TextView) findViewById(R.id.score);


        RegisterDAO registerDAO = new RegisterDAO(this);
        registerDAO.open();
        if (log!=null) {
            Register exist = registerDAO.checkUser(log, pass);
            if (scoreData!=0) {
                result.setText(scoreData + "/10");
                exist.setScoreMath(scoreData);
                registerDAO.updateScoreMath(exist);

            }else{
                result.setText(scoreQuest + "/10");
                exist.setScoreQuest(scoreQuest);
                registerDAO.updateScoreQ(exist);
            }
        }else if (scoreData!=0){
            result.setText(scoreData + "/10");
        }else{
            result.setText(scoreQuest + "/10");
        }




        registerDAO.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultat, menu);
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

    public void retourHub (View view){
        Intent intent = new Intent(this,Hub_choix.class);
        intent.putExtra(LOGUSER, log);
        intent.putExtra(PASS, pass);
        intent.putExtra(SCOREQ, scoreQuest);
        intent.putExtra(SCOREMATH, scoreData);


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

}
