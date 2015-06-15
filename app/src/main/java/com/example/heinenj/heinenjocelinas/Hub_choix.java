package com.example.heinenj.heinenjocelinas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Hub_choix extends Activity {

    public final static String LOGUSER = "logUser";
    public final static String PASS = "password";

    public String log;
    public int scoreData;
    public String pass;
    public int scoreQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_choix);

        TextView greatingP = (TextView)findViewById(R.id.greating);
        TextView scoreCulture = (TextView)findViewById(R.id.scoreCulture);
        TextView scoreMath = (TextView)findViewById(R.id.scoreMath);


        log = getIntent().getStringExtra(Login.LOGUSER);
        pass = getIntent().getStringExtra(Login.PASS);

        log = getIntent().getStringExtra(Resultat.LOGUSER);
        pass = getIntent().getStringExtra(Resultat.PASS);
        scoreQuest = getIntent().getIntExtra(Resultat.SCOREQ,0);
        scoreData = getIntent().getIntExtra(Resultat.SCOREMATH,0);



        RegisterDAO registerDAO=new RegisterDAO(this);
        registerDAO.open();

        if (log!=null) {
            Register exist = registerDAO.checkUser(log, pass);
            scoreData = exist.getScoreMath();
            scoreQuest = exist.getscoreQuest();
            greatingP.setText("Bonjour " + log);

        }
        else
        {
            greatingP.setText("Bonjour ");
        }

        scoreMath.setText("Dernier score Math: "+ scoreData);
        scoreCulture.setText("Dernier score Culture: "+ scoreQuest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hub_choix, menu);
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

    public void mathQuestions (View view){
        Intent intent = new Intent(this,Math.class);
        intent.putExtra(LOGUSER, log);
        intent.putExtra(PASS, pass);

        startActivity(intent);
    }
    public void cultureQuestions (View view){
        Intent intent = new Intent(this,Category.class);

        intent.putExtra(LOGUSER, log);
        intent.putExtra(PASS, pass);

        startActivity(intent);
    }

    public void explication (View view){
        Intent intent = new Intent(this,Explications.class);
        startActivity(intent);
    }

    public void back (View view){
        Intent intent = new Intent(this,Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
