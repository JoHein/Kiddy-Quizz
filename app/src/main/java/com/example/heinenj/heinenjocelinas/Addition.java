package com.example.heinenj.heinenjocelinas;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class Addition extends Activity {
    public Integer nb1;
    public Integer nb2;
    public Integer i;
    public Jeu game;

    public String log;

    public final static String LOGUSER = "logUser";
    public final static String SCOREMATH = "scoremath";
    public final static String PASS = "password";
    public String pass;
    public String plusmult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);


        log = getIntent().getStringExtra(Math.LOGUSER);
        pass = getIntent().getStringExtra(Math.PASS);
        plusmult = getIntent().getStringExtra(Math.OPERCALC);

        Random r = new Random();
        nb1 = r.nextInt(6);
        nb2 = r.nextInt(6);

        TextView changeVal1 = (TextView) findViewById(R.id.var1);
        changeVal1.setText("" + nb1);

        TextView changeVal2 = (TextView) findViewById(R.id.var2);
        changeVal2.setText("" + nb2);

        TextView changeOpe = (TextView) findViewById(R.id.fixe);
        changeOpe.setText("" + plusmult);


        game = new Jeu();
        i=1;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addition, menu);
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

    public void next(View view) {
        TextView Val1 = (TextView) findViewById(R.id.var1);
        nb1 = Integer.parseInt(Val1.getText().toString());

        TextView Val2 = (TextView) findViewById(R.id.var2);
        nb2 = Integer.parseInt(Val2.getText().toString());

        EditText varR1 = (EditText) findViewById(R.id.resMult);
        int res1 = Integer.parseInt(varR1.getText().toString().replace(" ",""));

        if (plusmult.equals(" X ")) {

            game.verifMulti(nb1,nb2,res1);
        } else {
            game.verifAdd(nb1,nb2,res1);
        }
        if(i < 10)
        {
            Random r = new Random();
            nb1 = r.nextInt(6);
            nb2 = r.nextInt(6);

            Val1.setText("" + nb1);
            Val2.setText("" + nb2);
            varR1.setText(null);

            i++;

        }
        else {
            Intent intent = new Intent(this, Resultat.class);
            intent.putExtra(LOGUSER, log);
            intent.putExtra(SCOREMATH, game.getScore());
            intent.putExtra(PASS, pass);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
